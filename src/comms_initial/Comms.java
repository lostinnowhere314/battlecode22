package comms_initial;

import battlecode.common.*;
import comms_initial.Util.MapSymmetryType;

public class Comms {
	
	public static final int MINER_CT_CYCLE = 3;
	public static final int MINER_FULL_CYCLE = 2*MINER_CT_CYCLE;

	public static int getCurrentMinerRegister(RobotController rc) {
		return ((rc.getRoundNum() % MINER_FULL_CYCLE) < MINER_CT_CYCLE) ? 5 : 4;
	}

	public static int getInactiveMinerRegister(RobotController rc) {
		return ((rc.getRoundNum() % MINER_FULL_CYCLE) < MINER_CT_CYCLE) ? 4 : 5;
	}
	
	/*
	 * Accuracy is guaranteed to within MINER_CT_CYCLE turns
	 */
	public static int getMinerCount(RobotController rc) throws GameActionException {
		return rc.readSharedArray(getCurrentMinerRegister(rc));
	}
	
	public static void incrementMinerCount(RobotController rc) throws GameActionException {
		int register = getInactiveMinerRegister(rc);
		rc.writeSharedArray(register, 1+rc.readSharedArray(register));
	}
	
	public static void cleanMinerCount(RobotController rc) throws GameActionException {
		//Cleans the inactive count bank if needed
		if ((rc.getRoundNum() % MINER_CT_CYCLE)==0) {
			int register = getInactiveMinerRegister(rc);
			if (rc.readSharedArray(register) != 0) {
				rc.writeSharedArray(register, 0);
			}
		}
	}
	
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
	
	public static void writeSymmetryType(RobotController rc, MapSymmetryType sym) throws GameActionException {
		int value;
		switch(sym) {
			case HORIZ_REFLECT:
				value = 4096;
				break;
			case ROTATION:
				value = 12288;
				break;
			case VERT_REFLECT:
				value = 8192;
				break;
			case UNKNOWN:
			default:
				return;
		}
		value = value | (rc.readSharedArray(1) | 4095);
		rc.writeSharedArray(1, value);
	}
	
	public static void writeNotSymmetryType(RobotController rc, MapSymmetryType sym) throws GameActionException {
		int value;
		switch(sym) {
		case HORIZ_REFLECT:
			value = 4096;
			break;
		case VERT_REFLECT:
			value = 8192;
			break;
		case ROTATION:
			value = 16384;
			break;
		case UNKNOWN:
		default:
			return;
	}
		rc.writeSharedArray(1, value | rc.readSharedArray(2));
	}
	
	//Returns the three bits; the number will be in the range 0-7
	public static int readNotSymmetryType(RobotController rc) throws GameActionException {
		return rc.readSharedArray(2)>>13;
	}
	
	public static int compressLocation(MapLocation location) {
		return 64*location.y + location.x;
	}
}
