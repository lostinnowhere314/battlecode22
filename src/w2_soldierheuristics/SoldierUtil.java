package w2_soldierheuristics;

import battlecode.common.*;
import w2_soldierheuristics.Util.MoveOrder;
import w2_soldierheuristics.Util.TargetingResult;

public class SoldierUtil {

	
	
	
	public static double getTargetValue(RobotInfo robot) {
		//TODO improve this?
		switch (robot.type) {
		case ARCHON:
			// base 1200, max hp 1200
			return 1200;
		case BUILDER:
			// 
			return 1000 - robot.health;
		case LABORATORY:
			return 1000 - robot.health;
		case MINER:
			return 1050 - robot.health;
		case SAGE:
			// 1250; maxhp 100
			return 1350 - robot.health;
		case SOLDIER:
			// 1201; maxhp 50
			return 1251 - robot.health;
		case WATCHTOWER:
			return 1400 - robot.health;
		default:
			return 0;
    	}
	}
	
	public static TargetingResult getBestTarget_bad(RobotController rc) {
		RobotInfo[] nearbyEnemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
		
		if (nearbyEnemies.length == 0) {
			return null;
		}
		// Check if we can move
		if (rc.isMovementReady()) {
			MapLocation me = rc.getLocation();
			int mx = me.x;
			int my = me.y;
			// Figure out the optimal combination of moving and attacking
			
			//TODO de-array this with python
			MapLocation[][] locations = new MapLocation[3][3];
			
			RobotInfo[][] bestTargets = new RobotInfo[3][3];
			double[][] bestTargetScores = new double[3][3];
			double[][] enemyScores = new double[3][3];
			double[][] alliesScore = new double[3][3];

			double dxEnemySum = 0;
			double dyEnemySum = 0;
			
			for (int i = nearbyEnemies.length; --i>=0;) {
				RobotInfo robot = nearbyEnemies[i];
				
				double targetScore = getTargetValue(robot);
				
				// Enemy mean location
				dxEnemySum += robot.location.x-mx;
				dyEnemySum += robot.location.y-my;
				// Figure out where this enemy is relevant
				for (int x=3;--x>=0;) {
					for (int y=3;--y>=0;) {
						int dist2 = robot.location.distanceSquaredTo(locations[x][y]);
						if (dist2 <= 20) {
							// We can shoot at it from that tile
							if (targetScore < bestTargetScores[x][y]) {
								bestTargetScores[x][y] = targetScore;
								bestTargets[x][y] = robot;
							}
						}
						//TODO adjust for different enemy types
						if (dist2 <= 34) {
							// It can potentially shoot at us if we're at this tile
							enemyScores[x][y] += 5;
						}
					}
				}
			}
			
			// flip the sign to make the logistic function take less bytecode
			double norm = Math.sqrt(dxEnemySum*dxEnemySum+dyEnemySum*dyEnemySum);
			if (norm > 0.1) {
				dxEnemySum /= -norm;
				dyEnemySum /= -norm;
			}
			
			// Use a heuristic to determine how "shielded" we are by nearby allies
			RobotInfo[] nearbyAllies = rc.senseNearbyRobots(-1, rc.getTeam());

			for (int i = nearbyAllies.length; --i>=0;) {
				RobotInfo ally = nearbyAllies[i];
				for (int x=2;--x>=-1;) {
					for (int y=2;--y>=-1;) {
						alliesScore[x+1][y+1] += 0.3/(0.3+Math.exp(
								dxEnemySum*(ally.location.x-x)
								+dyEnemySum*(ally.location.y-y)
										));
					}
				}
			}
			
			//Find if the center can see the best target
			// This would probably be easier to do with just hardcoding tbh
			
			//Iterate over the squares and choose the best one
			MapLocation bestLocation = null;
			double bestTileScore = -100000;

			for (int x=3;--x>=0;) {
				for (int y=3;--y>=0;) {
					// check if location is senseable and unoccupied
					double rubble = 5;
					double score = (alliesScore[x][y]+bestTargetScores[x][y])/rubble
							- enemyScores[x][y];
					if (score > bestTileScore) {
						bestTileScore = score;
						bestLocation = locations[x][y];
					}
							
				}
			}
			return new TargetingResult(bestTargets[1][1], me.directionTo(bestLocation), MoveOrder.BEFORE);
			
		} else {
			MapLocation me = rc.getLocation();
			// Otherwise, just find the best-scoring enemy
			RobotInfo bestEnemy = null;
			double bestScore = 0;
			
			for (int i = nearbyEnemies.length; --i>=0;) {
				RobotInfo enemy = nearbyEnemies[i];
				if (enemy.location.isWithinDistanceSquared(me, 20)) {
					double score = getTargetValue(enemy);
					if (score > bestScore) {
						bestEnemy = enemy;
						bestScore = score;
					}
				}
			}
			return new TargetingResult(bestEnemy, Direction.CENTER, MoveOrder.NONE);
		}
		
		
	}
	
	
	
	public static Direction[] soldierGetDirectionsWithin(MapLocation me, MapLocation target) {
		switch (target.x-me.x) {
			case -4:
			switch (target.y-me.y) {
				case -2:
					return new Direction[] {Direction.SOUTHWEST, Direction.WEST};
				case -1:
					return new Direction[] {Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST};
				case 0:
					return new Direction[] {Direction.WEST, Direction.SOUTHWEST, Direction.NORTHWEST};
				case 1:
					return new Direction[] {Direction.NORTHWEST, Direction.WEST, Direction.SOUTHWEST};
				case 2:
					return new Direction[] {Direction.NORTHWEST, Direction.WEST};
			default: return null;
			}
			case -3:
			switch (target.y-me.y) {
				case -3:
					return new Direction[] {Direction.SOUTHWEST, Direction.SOUTH, Direction.WEST};
				case -2:
					return new Direction[] {Direction.SOUTHWEST, Direction.WEST, Direction.SOUTH, Direction.NORTHWEST};
				case -1:
					return new Direction[] {Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST, Direction.SOUTH, Direction.NORTH};
				case 0:
					return new Direction[] {Direction.WEST, Direction.SOUTHWEST, Direction.NORTHWEST, Direction.NORTH, Direction.SOUTH};
				case 1:
					return new Direction[] {Direction.NORTHWEST, Direction.WEST, Direction.SOUTHWEST, Direction.NORTH, Direction.SOUTH};
				case 2:
					return new Direction[] {Direction.NORTHWEST, Direction.WEST, Direction.NORTH, Direction.SOUTHWEST};
				case 3:
					return new Direction[] {Direction.NORTHWEST, Direction.NORTH, Direction.WEST};
			default: return null;
			}
			case -2:
			switch (target.y-me.y) {
				case -4:
					return new Direction[] {Direction.SOUTHWEST, Direction.SOUTH};
				case -3:
					return new Direction[] {Direction.SOUTHWEST, Direction.SOUTH, Direction.WEST, Direction.SOUTHEAST};
				case -2:
					return new Direction[] {Direction.SOUTHWEST, Direction.SOUTH, Direction.WEST, Direction.SOUTHEAST, Direction.NORTHWEST, Direction.NORTH, Direction.EAST};
				case -1:
					return new Direction[] {Direction.SOUTHWEST, Direction.WEST, Direction.SOUTH, Direction.NORTHWEST, Direction.NORTH, Direction.SOUTHEAST, Direction.EAST, Direction.NORTHEAST};
				case 0:
					return new Direction[] {Direction.WEST, Direction.SOUTHWEST, Direction.NORTHWEST, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.NORTHEAST, Direction.SOUTHEAST};
				case 1:
					return new Direction[] {Direction.NORTHWEST, Direction.WEST, Direction.NORTH, Direction.SOUTHWEST, Direction.SOUTH, Direction.NORTHEAST, Direction.EAST, Direction.SOUTHEAST};
				case 2:
					return new Direction[] {Direction.NORTHWEST, Direction.NORTH, Direction.WEST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.EAST, Direction.SOUTH};
				case 3:
					return new Direction[] {Direction.NORTHWEST, Direction.NORTH, Direction.WEST, Direction.NORTHEAST};
				case 4:
					return new Direction[] {Direction.NORTHWEST, Direction.NORTH};
			default: return null;
			}
			case -1:
			switch (target.y-me.y) {
				case -4:
					return new Direction[] {Direction.SOUTHWEST, Direction.SOUTH, Direction.SOUTHEAST};
				case -3:
					return new Direction[] {Direction.SOUTHWEST, Direction.SOUTH, Direction.SOUTHEAST, Direction.WEST, Direction.EAST};
				case -2:
					return new Direction[] {Direction.SOUTHWEST, Direction.SOUTH, Direction.WEST, Direction.SOUTHEAST, Direction.EAST, Direction.NORTHWEST, Direction.NORTH, Direction.NORTHEAST};
				case -1:
					return new Direction[] {Direction.SOUTHWEST, Direction.SOUTH, Direction.WEST, Direction.SOUTHEAST, Direction.NORTHWEST, Direction.NORTH, Direction.EAST, Direction.NORTHEAST};
				case 0:
					return new Direction[] {Direction.WEST, Direction.SOUTHWEST, Direction.NORTHWEST, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.NORTHEAST, Direction.SOUTHEAST};
				case 1:
					return new Direction[] {Direction.NORTHWEST, Direction.NORTH, Direction.WEST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.EAST, Direction.SOUTH, Direction.SOUTHEAST};
				case 2:
					return new Direction[] {Direction.NORTHWEST, Direction.NORTH, Direction.WEST, Direction.NORTHEAST, Direction.EAST, Direction.SOUTHWEST, Direction.SOUTH, Direction.SOUTHEAST};
				case 3:
					return new Direction[] {Direction.NORTHWEST, Direction.NORTH, Direction.NORTHEAST, Direction.WEST, Direction.EAST};
				case 4:
					return new Direction[] {Direction.NORTHWEST, Direction.NORTH, Direction.NORTHEAST};
			default: return null;
			}
			case 0:
			switch (target.y-me.y) {
				case -4:
					return new Direction[] {Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST};
				case -3:
					return new Direction[] {Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.EAST, Direction.WEST};
				case -2:
					return new Direction[] {Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.EAST, Direction.WEST, Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST};
				case -1:
					return new Direction[] {Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.EAST, Direction.WEST, Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST};
				case 0:
					return new Direction[] {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST};
				case 1:
					return new Direction[] {Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST};
				case 2:
					return new Direction[] {Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST};
				case 3:
					return new Direction[] {Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.EAST, Direction.WEST};
				case 4:
					return new Direction[] {Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST};
			default: return null;
			}
			case 1:
			switch (target.y-me.y) {
				case -4:
					return new Direction[] {Direction.SOUTHEAST, Direction.SOUTH, Direction.SOUTHWEST};
				case -3:
					return new Direction[] {Direction.SOUTHEAST, Direction.SOUTH, Direction.SOUTHWEST, Direction.EAST, Direction.WEST};
				case -2:
					return new Direction[] {Direction.SOUTHEAST, Direction.SOUTH, Direction.EAST, Direction.SOUTHWEST, Direction.WEST, Direction.NORTHEAST, Direction.NORTH, Direction.NORTHWEST};
				case -1:
					return new Direction[] {Direction.SOUTHEAST, Direction.EAST, Direction.SOUTH, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.NORTH, Direction.WEST, Direction.NORTHWEST};
				case 0:
					return new Direction[] {Direction.EAST, Direction.NORTHEAST, Direction.SOUTHEAST, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.SOUTHWEST, Direction.NORTHWEST};
				case 1:
					return new Direction[] {Direction.NORTHEAST, Direction.NORTH, Direction.EAST, Direction.SOUTHEAST, Direction.NORTHWEST, Direction.SOUTH, Direction.WEST, Direction.SOUTHWEST};
				case 2:
					return new Direction[] {Direction.NORTHEAST, Direction.NORTH, Direction.EAST, Direction.NORTHWEST, Direction.WEST, Direction.SOUTHEAST, Direction.SOUTH, Direction.SOUTHWEST};
				case 3:
					return new Direction[] {Direction.NORTHEAST, Direction.NORTH, Direction.NORTHWEST, Direction.EAST, Direction.WEST};
				case 4:
					return new Direction[] {Direction.NORTHEAST, Direction.NORTH, Direction.NORTHWEST};
			default: return null;
			}
			case 2:
			switch (target.y-me.y) {
				case -4:
					return new Direction[] {Direction.SOUTHEAST, Direction.SOUTH};
				case -3:
					return new Direction[] {Direction.SOUTHEAST, Direction.SOUTH, Direction.EAST, Direction.SOUTHWEST};
				case -2:
					return new Direction[] {Direction.SOUTHEAST, Direction.EAST, Direction.SOUTH, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.NORTH, Direction.WEST};
				case -1:
					return new Direction[] {Direction.SOUTHEAST, Direction.EAST, Direction.SOUTH, Direction.NORTHEAST, Direction.NORTH, Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST};
				case 0:
					return new Direction[] {Direction.EAST, Direction.NORTHEAST, Direction.SOUTHEAST, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.SOUTHWEST, Direction.NORTHWEST};
				case 1:
					return new Direction[] {Direction.NORTHEAST, Direction.EAST, Direction.NORTH, Direction.SOUTHEAST, Direction.SOUTH, Direction.NORTHWEST, Direction.WEST, Direction.SOUTHWEST};
				case 2:
					return new Direction[] {Direction.NORTHEAST, Direction.NORTH, Direction.EAST, Direction.SOUTHEAST, Direction.NORTHWEST, Direction.SOUTH, Direction.WEST};
				case 3:
					return new Direction[] {Direction.NORTHEAST, Direction.NORTH, Direction.EAST, Direction.NORTHWEST};
				case 4:
					return new Direction[] {Direction.NORTHEAST, Direction.NORTH};
			default: return null;
			}
			case 3:
			switch (target.y-me.y) {
				case -3:
					return new Direction[] {Direction.SOUTHEAST, Direction.EAST, Direction.SOUTH};
				case -2:
					return new Direction[] {Direction.SOUTHEAST, Direction.EAST, Direction.SOUTH, Direction.NORTHEAST};
				case -1:
					return new Direction[] {Direction.SOUTHEAST, Direction.EAST, Direction.NORTHEAST, Direction.SOUTH, Direction.NORTH};
				case 0:
					return new Direction[] {Direction.EAST, Direction.NORTHEAST, Direction.SOUTHEAST, Direction.NORTH, Direction.SOUTH};
				case 1:
					return new Direction[] {Direction.NORTHEAST, Direction.EAST, Direction.SOUTHEAST, Direction.NORTH, Direction.SOUTH};
				case 2:
					return new Direction[] {Direction.NORTHEAST, Direction.EAST, Direction.NORTH, Direction.SOUTHEAST};
				case 3:
					return new Direction[] {Direction.NORTHEAST, Direction.NORTH, Direction.EAST};
			default: return null;
			}
			case 4:
			switch (target.y-me.y) {
				case -2:
					return new Direction[] {Direction.SOUTHEAST, Direction.EAST};
				case -1:
					return new Direction[] {Direction.SOUTHEAST, Direction.EAST, Direction.NORTHEAST};
				case 0:
					return new Direction[] {Direction.EAST, Direction.NORTHEAST, Direction.SOUTHEAST};
				case 1:
					return new Direction[] {Direction.NORTHEAST, Direction.EAST, Direction.SOUTHEAST};
				case 2:
					return new Direction[] {Direction.NORTHEAST, Direction.EAST};
			default: return null;
			}
		default: return null;
		}
	}


}
