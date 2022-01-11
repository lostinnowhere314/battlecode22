package w2_b1;

import java.util.Random;

import battlecode.common.*;
import w2_b1.Comms;

public class Util {
	
	public static class ArchonTarget {
		public MapSymmetryType symmetry;
		public MapLocation location;
		public ArchonTarget(MapSymmetryType s, MapLocation l) {
			symmetry = s;
			location = l;
		}
	};
	
	public static enum MapSymmetryType {
		HORIZ_REFLECT,
		VERT_REFLECT,
		ROTATION,
		UNKNOWN
	};
	public static MapSymmetryType[] SymmetryTypes = {
			MapSymmetryType.HORIZ_REFLECT,
			MapSymmetryType.VERT_REFLECT,
			MapSymmetryType.ROTATION,
	};
	
	public static MapSymmetryType selectRandomSymmetry(Random rng) {
		double r = rng.nextDouble();
		if (r < 0.3) {
			return MapSymmetryType.ROTATION;
		} else if (r<0.65) {
			return MapSymmetryType.VERT_REFLECT;
		}
		return MapSymmetryType.VERT_REFLECT;
	}
	
	public static MapSymmetryType selectRandomPossibleSymmetry(RobotController rc, Random rng) throws GameActionException {
		switch (Comms.readNotSymmetryType(rc)) {
		case 1: return rng.nextBoolean() ? MapSymmetryType.VERT_REFLECT : MapSymmetryType.ROTATION;
		case 2: return rng.nextBoolean() ? MapSymmetryType.HORIZ_REFLECT : MapSymmetryType.ROTATION;
		case 3: return MapSymmetryType.ROTATION;
		case 4: return rng.nextBoolean() ? MapSymmetryType.HORIZ_REFLECT : MapSymmetryType.VERT_REFLECT;
		case 5: return MapSymmetryType.VERT_REFLECT;
		case 6: return MapSymmetryType.HORIZ_REFLECT;
		default:
			return selectRandomSymmetry(rng);
		}
	}
	
	
	
	public static ArchonTarget getRandomEnemyArchonLocation(RobotController rc, Random rng) throws GameActionException {
		MapSymmetryType sym = Comms.readSymmetryType(rc);
		if (sym == MapSymmetryType.UNKNOWN) {
			sym = selectRandomPossibleSymmetry(rc, rng);
		}
		int which = rng.nextInt(Comms.getStoredArchonCount(rc));
		return new ArchonTarget(sym,
				getSymmetricLocation(rc, Comms.getLocationFromComms(rc, which), sym));
	}
	
	/*
	 * Warning: this might be somewhat of an expensive method
	 */
	public static ArchonTarget getNearestEnemyArchonLocation(RobotController rc, Random rng) throws GameActionException {
		MapLocation me = rc.getLocation();
		int teamArchonCount = Comms.getStoredArchonCount(rc);
		
		int nearestDistance = 256;
		MapLocation bestLocation = null;
		MapSymmetryType bestSymType = MapSymmetryType.UNKNOWN;
		
		MapSymmetryType symType = Comms.readSymmetryType(rc);
		
		if (symType == MapSymmetryType.UNKNOWN) {
			MapLocation[] archonLocs = new MapLocation[teamArchonCount];

			for (int i = teamArchonCount; --i>=0;) {
				archonLocs[i] = Comms.getLocationFromComms(rc, i);
			}
			for (MapSymmetryType symType2 : Util.SymmetryTypes) {
				if (Comms.readNotSymmetryType(rc, symType2)) {
					for (int i = teamArchonCount; --i>=0;) {
						MapLocation otherArchon = Util.getSymmetricLocation(rc,
								archonLocs[i], symType2);
						
						int dist2 = me.distanceSquaredTo(otherArchon);
						if (dist2 < nearestDistance) {
							nearestDistance = dist2;
							bestLocation = otherArchon;
							bestSymType = symType2;
						}
					}
				}
			}
		} else {
			bestSymType = symType;
			for (int i = teamArchonCount; --i>=0;) {
				MapLocation otherArchon = Util.getSymmetricLocation(rc,
						Comms.getLocationFromComms(rc, i), symType);
				
				int dist2 = me.distanceSquaredTo(otherArchon);
				if (dist2 < nearestDistance) {
					nearestDistance = dist2;
					bestLocation = otherArchon;
				}
			}
		}
		return new ArchonTarget(bestSymType, bestLocation);
	}
	
	
	public static MapLocation getSymmetricLocation(RobotController rc, MapLocation loc, MapSymmetryType sym) {
		switch(sym) {
		case HORIZ_REFLECT:
			return new MapLocation(rc.getMapWidth() - loc.x - 1, loc.y);
		case VERT_REFLECT:
			return new MapLocation(loc.x, rc.getMapHeight() - loc.y - 1);
		case ROTATION:
		default:
			return new MapLocation(rc.getMapWidth() - loc.x - 1, rc.getMapHeight() - loc.y - 1);
		}
	}
	
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
    
    public static Direction getPerpendicularDir(Direction dir, RotationDirection clk) {
		switch(clk) {
		case CLOCKWISE:
			switch(dir) {
				case NORTH: return Direction.EAST;
				case NORTHEAST: return Direction.SOUTHEAST;
				case EAST: return Direction.SOUTH;
				case SOUTHEAST: return Direction.SOUTHWEST;
				case SOUTH: return Direction.WEST;
				case SOUTHWEST: return Direction.NORTHWEST;
				case WEST: return Direction.NORTH;
				case NORTHWEST: return Direction.NORTHEAST;
				default: return dir;
			}
		case COUNTERCLOCKWISE:
			switch(dir) {
				case NORTH: return Direction.WEST;
				case NORTHEAST: return Direction.NORTHWEST;
				case EAST: return Direction.NORTH;
				case SOUTHEAST: return Direction.NORTHEAST;
				case SOUTH: return Direction.EAST;
				case SOUTHWEST: return Direction.SOUTHEAST;
				case WEST: return Direction.SOUTH;
				case NORTHWEST: return Direction.SOUTHWEST;
				default: return dir;
			}
		}
		return dir;
	}

    
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
		rc.setIndicatorDot(me.add(targetDir), 200,250,0);
		// Attempts to move to the lowest-passability unoccupied square 
		//  close to that direction

		int best_passability = 1000;
		Direction best_direction = Direction.CENTER;
		
		targetDir = targetDir.rotateLeft().rotateLeft();
		for (int i=5; --i>=0; targetDir = targetDir.rotateRight()) {
			MapLocation other = me.add(targetDir);
			if (rc.canMove(targetDir)) {
				int pass = rc.senseRubble(other);
				if (pass < best_passability) {
					best_passability = pass;
					best_direction = targetDir;
					// We want to prioritize the original direction
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
		rc.setIndicatorDot(me.add(targetDir), 250,200,0);
		// Attempts to move to the lowest-passability unoccupied square 
		//  close to that direction

		int best_passability = 1000;
		Direction best_direction = Direction.CENTER;
		
		targetDir = targetDir.rotateLeft();
		for (int i=3; --i>=0; targetDir = targetDir.rotateRight()) {
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

	public static MoveResult move_minrubble_parallel(RobotController rc, MapLocation me,
			Direction targetDir) throws GameActionException {
		if (targetDir == Direction.CENTER) {
			return MoveResult.NO_MOVE;
		}
		int best_passability = 1000;
		Direction best_direction = Direction.CENTER;
		for (int i=2; --i>=0; targetDir = targetDir.opposite()) {
			MapLocation other = me.add(targetDir);
			if (rc.canMove(targetDir)) {
				int pass = rc.senseRubble(other);
				if (pass < best_passability) {
					best_passability = pass;
					best_direction = targetDir;
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
