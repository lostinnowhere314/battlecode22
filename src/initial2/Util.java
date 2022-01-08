package initial2;

import java.util.Random;

import battlecode.common.*;

public class Util {
	
	
	public static final Direction[] directions = {
        Direction.NORTH,
        Direction.NORTHEAST,
        Direction.EAST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST,
        Direction.WEST,
        Direction.NORTHWEST,
    };
	
	// this enum is from musketeers' 2021 code
	public static enum RotationDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE
    };
    
	public static MapLocation getRandomMapLocation(RobotController rc, Random rng) {
		return new MapLocation((int)(rng.nextDouble()*rc.getMapWidth()),
				(int)(rng.nextDouble()*rc.getMapHeight()));
	}
	
	// tries to move towards the robot's desired location
	public static boolean move(RobotController rc, MapLocation me,
			MapLocation dest, RotationDirection bugDir) throws GameActionException {
		if (rc.isMovementReady()) {
			Direction dir = me.directionTo(dest);
			for (int i=4;--i>0;) {
				if (rc.canMove(dir)) {
		            rc.move(dir);
		            return true;
				}
				if(i==2) {
					dir = dir.opposite();
				} else if(bugDir == RotationDirection.CLOCKWISE) {
					dir = dir.rotateLeft();
				} else {
					dir = dir.rotateRight();
				}
				
			}
			return false;
		}
		return true;
	}

	public static boolean move(RobotController rc, MapLocation me,
			Direction dir, RotationDirection bugDir) throws GameActionException {
		if (rc.isMovementReady()) {
			for (int i=4;--i>0;) {
				if (rc.canMove(dir)) {
		            rc.move(dir);
		            return true;
				}
				if(i==2) {
					dir = dir.opposite();
				} else if(bugDir == RotationDirection.CLOCKWISE) {
					dir = dir.rotateLeft();
				} else {
					dir = dir.rotateRight();
				}
				
			}
			return false;
		}
		return true;
	}
	

    public static enum MoveResult {
    	SUCCESS,
    	FAILURE,
    	NO_MOVE
    };
	
	public static MoveResult move_minrubble(RobotController rc, MapLocation me,
			MapLocation dest) throws GameActionException {
		// Attempts to move to the lowest-passability square strictly closer
		//	to destination than the current square
		if (me.equals(dest)) {
			return MoveResult.NO_MOVE;
		} else if (me.isAdjacentTo(dest)) {
			Direction dir = me.directionTo(dest); 
			if (rc.canMove(dir)) {
	            rc.move(dir);
	            return MoveResult.SUCCESS;
			}
			return MoveResult.FAILURE;
		}
		//then, we actually apply our heuristic
		int current_r2 = me.distanceSquaredTo(dest);
		int best_score = 100000000;
		Direction best_direction = Direction.CENTER;
		//for (int i=8;--i>0;) {
		for (Direction dir : Util.directions) {
			//Direction dir = Util.directions[i];
			MapLocation other = me.add(dir);
			int dist2 = other.distanceSquaredTo(dest);
			if (dist2 < current_r2 && rc.canMove(dir)) {
				int score = 10000*rc.senseRubble(other)+dist2;
				if (score < best_score) {
					best_score = score;
					best_direction = dir;
				}
			}
		}
		if (best_direction != Direction.CENTER) {
			rc.move(best_direction);
			return MoveResult.SUCCESS;
		}
		return MoveResult.FAILURE;
	}
	
	public static MoveResult move_minrubble_direction(RobotController rc, MapLocation me,
			Direction targetDir, RotationDirection bugDirection) throws GameActionException {
		// Attempts to move to the lowest-passability unoccupied square 
		//  close to that direction

		int best_passability = 1000;
		Direction best_direction = Direction.CENTER;
		
		targetDir = targetDir.rotateLeft().rotateLeft();
		for (int i=4; --i>0; targetDir = targetDir.rotateRight()) {
			MapLocation other = me.add(targetDir);
			if (rc.canMove(targetDir)) {
				int pass = rc.senseRubble(other);
				if (pass < best_passability) {
					best_passability = pass;
					best_direction = targetDir;
					switch(i) {
					case 2:
						switch(bugDirection) {
						case COUNTERCLOCKWISE:
							best_passability--;
						default:					
						}
					default:
						switch(bugDirection) {
						case CLOCKWISE:
							best_passability++;
						default:					
						}
					}
				}
			}
		}
		if (best_direction != Direction.CENTER) {
			rc.move(best_direction);
			return MoveResult.SUCCESS;
		}
		return MoveResult.FAILURE;
	}

	public static MoveResult move_minrubble_direction_strict(RobotController rc, MapLocation me,
			Direction targetDir, RotationDirection bugDirection) throws GameActionException {
		// Attempts to move to the lowest-passability unoccupied square 
		//  close to that direction

		int best_passability = 1000;
		Direction best_direction = Direction.CENTER;
		
		targetDir = targetDir.rotateLeft();
		for (int i=2; --i>0; targetDir = targetDir.rotateRight()) {
			MapLocation other = me.add(targetDir);
			if (rc.canMove(targetDir)) {
				int pass = rc.senseRubble(other);
				if (pass < best_passability) {
					best_passability = pass;
					best_direction = targetDir;
					switch(i) {
					case 1:
						switch(bugDirection) {
						case COUNTERCLOCKWISE:
							best_passability--;
						default:					
						}
					default:
						switch(bugDirection) {
						case CLOCKWISE:
							best_passability++;
						default:					
						}
					}
				}
			}
		}
		if (best_direction != Direction.CENTER) {
			rc.move(best_direction);
			return MoveResult.SUCCESS;
		}
		return MoveResult.FAILURE;
	}
}
