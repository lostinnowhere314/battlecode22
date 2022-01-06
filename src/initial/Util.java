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
}
