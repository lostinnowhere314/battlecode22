package comms_initial;

import battlecode.common.*;

public class SoldierController extends Robot {

	private static enum Mode {
		ARCHON_TARGET,
		WANDER,
		ATTACK
	}
	
	private MapLocation dest = null;
	private Util.RotationDirection bugDirection;
	private Mode mode = Mode.ARCHON_TARGET;
	private Mode lastMode = Mode.WANDER;
	private int lastTargetID = -1;
	private MapLocation lastTargetLocation = null;
	
	
	public SoldierController(RobotController rc) {
		super(rc);
		bugDirection = rng.nextDouble()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
		lastTargetLocation = Util.getRandomMapLocation(rc, rng);
	}

	private void chooseStateRandom() {
		mode = (rng.nextDouble()<0.2 ? Mode.WANDER : Mode.ARCHON_TARGET);
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
        MapLocation me = rc.getLocation();
        
        
		switch(mode) {
		case WANDER:
		{
			// Choose new dest
			if (me.equals(dest) || dest == null) {
				lastMode = mode;
				chooseStateRandom();
		        switch(mode) {
			        case ARCHON_TARGET:
			        	dest = Util.getRandomEnemyArchonLocation(rc, rng);
			        case WANDER:
			        	dest = Util.getRandomMapLocation(rc, rng);
			    	default:
		        }
			}
	        // Use the function to move
			Util.move_minrubble(rc, me, dest);
		}
		case ARCHON_TARGET:
		{
			// Choose potential archon to target
			if (dest == null || me.isWithinDistanceSquared(dest, 14)) {
				lastMode = mode;
				chooseStateRandom();
		        switch(mode) {
			        case ARCHON_TARGET:
			        	dest = Util.getRandomEnemyArchonLocation(rc, rng);
			        case WANDER:
			        	dest = Util.getRandomMapLocation(rc, rng);
			    	default:
		        }
			}
	        // Use the function to move
			Util.move_minrubble(rc, me, dest);
		}
			break;
		case ATTACK:
		{
			// First, determine whether our target is still visible
			if(rc.canSenseRobot(lastTargetID)) {
				// If they are, try to maintain proper spacing
				RobotInfo targetInfo = rc.senseRobot(lastTargetID);
				int minDist, maxDist;
				switch (targetInfo.type) {
				case ARCHON:
					minDist = 0;
					maxDist = 2;
					break;
				case SAGE:
				case SOLDIER:
				case WATCHTOWER:
					minDist = 6;
					maxDist = 13;
					break;
				case BUILDER:
				case MINER:
				case LABORATORY:
				default:
					minDist = 0;
					maxDist = 13;
					break;
				}
				
				int dist2 = me.distanceSquaredTo(targetInfo.location);
				if (dist2 < minDist) {
					//Avoid being too close
					Util.move_minrubble_direction(rc, me, me.directionTo(targetInfo.location).opposite(), bugDirection);
				} else if (dist2 > maxDist) {
					//But don't go too far away
					Util.move_minrubble_direction(rc, me, me.directionTo(targetInfo.location), bugDirection);
				} else {
					// Maybe move around. I think this might be bad but I'm not sure
					double r = rng.nextDouble();
					
					if (r > 0.8) {
					Util.move_minrubble_parallel(rc, me, 
							Util.getPerpendicularDir(
									(r>0.9)?me.directionTo(targetInfo.location):me.directionTo(targetInfo.location).opposite(),
									bugDirection));
					}
				}
				
			} else {
				// If not, try moving towards them
				Util.move_minrubble(rc, me, lastTargetLocation);
			}
		}
			break;
		default:
			System.out.println("Warning: soldier mode with no code!");
			break;
		}
		me = rc.getLocation();
		// determine most valuable target visible
		// then attack it
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        
        int radius = rc.getType().actionRadiusSquared;
        if (enemies.length > 0) {
	        RobotInfo bestTarget = enemies[0];
	        RobotInfo bestRangeTarget = enemies[0];
	        double bestTargetScore = 0;
	        double bestRangeTargetScore = 0;
	        
	        for (int i = enemies.length; --i>=0;) {
	        	// Get the score
	        	double currentScore;
	        	RobotInfo currentEnemy = enemies[i];
	        	switch (currentEnemy.type) {
				case ARCHON:
					// base 1200, max hp 1200
					currentScore = 1400 - currentEnemy.health/6.;
					break;
				case BUILDER:
					// 
					currentScore = 1000 - currentEnemy.health;
					break;
				case LABORATORY:
					currentScore = 1000 - currentEnemy.health;
					break;
				case MINER:
					currentScore = 1050 - currentEnemy.health;
					break;
				case SAGE:
					// 1200; maxhp 100
					currentScore = 1300 - currentEnemy.health;
					break;
				case SOLDIER:
					// 1201; maxhp 50
					currentScore = 1251 - currentEnemy.health;
					break;
				case WATCHTOWER:
					currentScore = 1400 - currentEnemy.health;
					break;
				default:
					currentScore = 0;
					break;
	        	}

	        	if (currentScore > bestTargetScore) {
	        		bestTargetScore = currentScore;
	        		bestTarget = currentEnemy;
	        	}
	        	if (currentScore > bestRangeTargetScore && me.distanceSquaredTo(currentEnemy.location) <= radius) {
	        		bestRangeTargetScore = currentScore;
	        		bestRangeTarget = currentEnemy;
	        	}
	        }

            rc.setIndicatorString(Integer.toString(me.distanceSquaredTo(lastTargetLocation)));
			rc.setIndicatorLine(me, lastTargetLocation, 240,0,240);
	        if (bestTargetScore > 0) {
	        	lastTargetLocation = bestTarget.location;
	        	lastTargetID = bestTarget.ID;
	        	// If we're in ARCHON_TARGET mode, only switch to attack mode if the thing we found
	        	//  is dangerous
	        	switch(mode) {
					case WANDER:
			        	lastMode = mode;
			        	mode = Mode.ATTACK;
						break;
					case ARCHON_TARGET:
			        	switch(bestTarget.type) {
						case SAGE:
						case SOLDIER:
						case WATCHTOWER:
						case ARCHON:
				        	lastMode = mode;
				        	mode = Mode.ATTACK;
						case BUILDER:
						case LABORATORY:
						case MINER:
						default:
			        	}
					case ATTACK:
					default:
	        	}
	        }
	        //Attack if we can
	        MapLocation toAttack = bestRangeTarget.location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        } else {
        	if (mode == Mode.ATTACK) {
        		//this bit is kind of really garbage
        		if (lastMode == Mode.ATTACK) {
        			chooseStateRandom();
        		} else {
        			mode = lastMode;
        		}
        		if (lastMode == Mode.WANDER) {
        			dest = Util.getRandomMapLocation(rc, rng);
        		}
        		lastMode = Mode.ATTACK;
        	}
        	rc.setIndicatorLine(me, dest, 0,150,50);
        }
		rc.setIndicatorString(mode.toString());
        
	}

}
