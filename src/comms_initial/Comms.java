package comms_initial;

import battlecode.common.*;
import comms_initial.Util.MapSymmetryType;

public class Comms {

	// Stores x,y location in lower bits of integer
	public static MapLocation getLocationFromComms(RobotController rc, int i) throws GameActionException {
		int val = rc.readSharedArray(i);
		return new MapLocation(val&63, (val&4062)/64);
	}
	
	public static int getStoredArchonCount(RobotController rc) throws GameActionException {
		return (rc.readSharedArray(1)>>14)+1;
	}
	
	public static MapSymmetryType readSymmetryType(RobotController rc) throws GameActionException {
		switch (rc.readSharedArray(1) & 12288) { //bits 12,13
		case 4096:
			return MapSymmetryType.HORIZ_REFLECT;
		case 8192:
			return MapSymmetryType.VERT_REFLECT;
		case 12288:
			return MapSymmetryType.ROTATION;
		default:
			return MapSymmetryType.UNKNOWN;
		}
	}
	public static int compressLocation(MapLocation location) {
		return 64*location.y + location.x;
	}
}
