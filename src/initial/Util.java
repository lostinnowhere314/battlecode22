package initial;

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
	
	// from musketeers' 2021 code
	public static enum RotationDirection {
        CLOCKWISE,
        COUNTERCLOCKWISE
    };
	
	public static MapLocation getRandomMapLocation(RobotController rc) {
		return new MapLocation((int)(Math.random()*rc.getMapWidth()),
				(int)(Math.random()*rc.getMapHeight()));
	}
	
	// tries to move towards the miner's desired location
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
}
