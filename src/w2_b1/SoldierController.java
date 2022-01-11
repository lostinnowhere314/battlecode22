package w2_b1;

import battlecode.common.*;
import w2_b1.Util.MapSymmetryType;
import w2_b1.Comms;

public class SoldierController extends Robot {

	
	private static enum Mode {
		ARCHON_TARGET,
		COMMS_TARGET,
		WANDER,
		ATTACK
	}
	
	private MapLocation dest = null;
	private Util.RotationDirection bugDirection;
	private Mode mode = Mode.ARCHON_TARGET;
	private MapSymmetryType symType = MapSymmetryType.UNKNOWN;
	private Mode lastMode = Mode.WANDER;
	private int lastTargetID = -1;
	private MapLocation lastTargetLocation = null;
	
	
	public SoldierController(RobotController rc) {
		super(rc);
		bugDirection = rng.nextDouble()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
		lastTargetLocation = Util.getRandomMapLocation(rc, rng);
	}

	private void chooseStateRandom(RobotController rc) {
		if (rc.getRoundNum() > 400) {
			mode = (rng.nextDouble()<0.05 ? Mode.WANDER : Mode.ARCHON_TARGET);
		} else {
			mode = (rng.nextDouble()<0.3 ? Mode.WANDER : Mode.ARCHON_TARGET);
		}
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		// Keep soldier count incremented
        Comms.incrementSoldierCount(rc);
        
        
        MapLocation me = rc.getLocation();
       
		switch(mode) {
		case WANDER:
		{
			// Check comms for new messages
			int mostRecent = 16;
			int recentIndex = -1;
			for (int i=64; --i>=56;) {
				int val = rc.readSharedArray(i);
				if (val > 0) {
					int time = (rc.getRoundNum()+1 - (val >> 12))%15;
					if (time < mostRecent) {
						mostRecent = time;
						recentIndex = i;
					}
				}
			}
			if (recentIndex > 0) {
				dest = Comms.getLocationFromComms(rc, recentIndex);
				mode = Mode.COMMS_TARGET;
			}
		}
		case COMMS_TARGET:
		{	
			// Choose new dest if needed
			if (me.equals(dest) || dest == null) {
				lastMode = mode;
				chooseStateRandom(rc);
		        switch(mode) {
			        case ARCHON_TARGET:
			        {
			        	Util.ArchonTarget target = Util.getNearestEnemyArchonLocation(rc, rng);
			        	dest = target.location;
			        	symType = target.symmetry;
			        }
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
				if (symType != MapSymmetryType.UNKNOWN) {
					if (Comms.readSymmetryType(rc) == MapSymmetryType.UNKNOWN) {
						//Test if there's an archon at our destination
						if (rc.canSenseLocation(dest)) {
							RobotInfo robot = rc.senseRobotAtLocation(dest);
							if (robot != null 
									&& robot.type == RobotType.ARCHON
									&& rc.getTeam().opponent().equals(robot.team)) {
								// then we "know" the symmetry type
								Comms.writeSymmetryType(rc, symType);
							} else {
								// then we "know" it's not that symmetry
								Comms.writeNotSymmetryType(rc, symType);
							}
						}
					}
				}
				
				lastMode = mode;
				chooseStateRandom(rc);
		        switch(mode) {
			        case ARCHON_TARGET:
			        {
			        	Util.ArchonTarget target = Util.getNearestEnemyArchonLocation(rc, rng);
			        	dest = target.location;
			        	symType = target.symmetry;
			        }
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
					// Let's try determining whether we'll be able to attack this turn
					if (rc.isActionReady()) {
						minDist = 6;
						maxDist = 13;
					} else {
						minDist = 25;
						maxDist = 40;
					}
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
					Util.move_minrubble_direction_strict(rc, me, me.directionTo(targetInfo.location).opposite(), bugDirection);
				} else if (dist2 > maxDist) {
					//But don't go too far away
					Util.move_minrubble(rc, me, targetInfo.location);
				} /*else { //Tends to make the robots spin around archons for some reason
					// Maybe move around. I think this might be bad but I'm not sure
					double r = rng.nextDouble();
					
					if (r > 0.8) {
					Util.move_minrubble_parallel(rc, me, 
							Util.getPerpendicularDir(
									(r>0.9)?me.directionTo(targetInfo.location):me.directionTo(targetInfo.location).opposite(),
									bugDirection));
					}
				}*/
				
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
					currentScore = 1200;
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
					// 1250; maxhp 100
					currentScore = 1350 - currentEnemy.health;
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
					case COMMS_TARGET:
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
                if (rc.isMovementReady()) {
                	switch (bestRangeTarget.type) {
					case WATCHTOWER:
					case SAGE:
					case SOLDIER:
						Util.move_minrubble_direction_strict(rc, me,
								me.directionTo(bestRangeTarget.location).opposite(), bugDirection);
						break;
					case ARCHON:
					case BUILDER:
					case LABORATORY:
					case MINER:
					default:
						break;
                	}
                	
                	
                }
            }
        } else {
        	if (mode == Mode.ATTACK) {
        		//this bit is kind of really garbage
        		if (lastMode == Mode.ATTACK || lastMode == Mode.COMMS_TARGET) {
        			chooseStateRandom(rc);
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
        Direction[] dirs = {Direction.SOUTHWEST, Direction.EAST};
	}

}
