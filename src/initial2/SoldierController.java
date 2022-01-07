package initial2;

import battlecode.common.*;

public class SoldierController extends Robot {

	private static enum Mode {
		WANDER,
		ATTACK
	}
	
	private MapLocation dest = null;
	private Util.RotationDirection bugDirection;
	private Mode mode = Mode.WANDER;
	private int lastTargetID = -1;
	private MapLocation lastTargetLocation = null;
	
	public SoldierController(RobotController rc) {
		super(rc);
		bugDirection = Math.random()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
		lastTargetLocation = Util.getRandomMapLocation(rc, rng);
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
        MapLocation me = rc.getLocation();
        
		switch(mode) {
		case WANDER:
		{
			// Choose locations randomly
			if (me.equals(dest) || dest == null) {
				dest = Util.getRandomMapLocation(rc, rng);
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
				int dist2 = me.distanceSquaredTo(targetInfo.location);
				if (dist2 <= 5) {
					//Avoid being too close
					Util.move_minrubble_direction(rc, me, me.directionTo(targetInfo.location).opposite(), bugDirection);
				} else if (dist2 > 13) {
					//But don't go too far away
					Util.move_minrubble_direction(rc, me, me.directionTo(targetInfo.location), bugDirection);
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
					currentScore = 2400 - currentEnemy.health;
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
					// 1100; maxhp 100
					currentScore = 1200 - currentEnemy.health;
					break;
				case SOLDIER:
					// 1050; maxhp 50
					currentScore = 1100 - currentEnemy.health;
					break;
				case WATCHTOWER:
					currentScore = 1200 - currentEnemy.health;
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
	        	mode = Mode.ATTACK;
	        }
	        //Attack if we can
	        MapLocation toAttack = bestRangeTarget.location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        } else {
        	if (mode == Mode.ATTACK) {
        		mode = Mode.WANDER;
        		dest = Util.getRandomMapLocation(rc, rng);
        	}
        }
		
        
	}

}
