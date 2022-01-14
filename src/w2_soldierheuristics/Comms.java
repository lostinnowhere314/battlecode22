package w2_soldierheuristics;

import battlecode.common.*;
import w2_soldierheuristics.Util.MapSymmetryType;

public class Comms {

	public static final int MINER_CT_CYCLE = 3;
	public static final int MINER_FULL_CYCLE = 2*MINER_CT_CYCLE;
	public static final int SOLDIER_CT_CYCLE = 1;
	public static final int SOLDIER_FULL_CYCLE = 2*MINER_CT_CYCLE;

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
	

	/*
	 * Soldier counting
	 * Accuracy is guaranteed to within MINER_CT_CYCLE turns
	 */

	public static int getCurrentSoldierRegister(RobotController rc) {
		return ((rc.getRoundNum() % SOLDIER_FULL_CYCLE) < SOLDIER_CT_CYCLE) ? 8 : 7;
	}

	public static int getInactiveSoldierRegister(RobotController rc) {
		return ((rc.getRoundNum() % SOLDIER_FULL_CYCLE) < SOLDIER_CT_CYCLE) ? 7 : 8;
	}
	
	public static int getSoldierCount(RobotController rc) throws GameActionException {
		return rc.readSharedArray(getCurrentSoldierRegister(rc));
	}
	
	public static void incrementSoldierCount(RobotController rc) throws GameActionException {
		int register = getInactiveSoldierRegister(rc);
		rc.writeSharedArray(register, 1+rc.readSharedArray(register));
	}
	
	public static void cleanSoldierCount(RobotController rc) throws GameActionException {
		//Cleans the inactive count bank if needed
		//if ((rc.getRoundNum() % SOLDIER_CT_CYCLE)==0) {
			int register = getInactiveSoldierRegister(rc);
			if (rc.readSharedArray(register) != 0) {
				rc.writeSharedArray(register, 0);
			}
		//}
	}
	
	// Clear all targeting locations that are congruent to this round mod 7 or 15.
	// Only should be called by archons
	public static void clearCommsTargets(RobotController rc) throws GameActionException {

		for (int i=64; --i>=56;) {
			// Miners' messages
			if ((rc.getRoundNum() - (rc.readSharedArray(i)>>12) - 1) % 15 == 0) {
				rc.writeSharedArray(i, 0);
			}
		}

		for (int i=56; --i>=48;) {
			// Soldiers' messages
			if ((rc.getRoundNum() - (((rc.readSharedArray(i)>>12) - 1)&7)) % 7 == 0) {
				rc.writeSharedArray(i, 0);
			}
		}
	}
	
	// Stores x,y location in lower bits of integer
	public static MapLocation getLocationFromComms(RobotController rc, int i) throws GameActionException {
		int val = rc.readSharedArray(i);
		return new MapLocation(val&63, (val>>6)&63);
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
		System.out.println("Wrote: not "+sym);
		int value;
		switch(sym) {
		case HORIZ_REFLECT:
			value = 0b001_0_000000_000000;
			break;
		case VERT_REFLECT:
			value = 0b010_0_000000_000000;
			break;
		case ROTATION:
			value = 0b100_0_000000_000000;
			break;
		case UNKNOWN:
		default:
			return;
		}
		rc.writeSharedArray(2, value | rc.readSharedArray(2));
	}
	
	/*
	 * Returns true if the symmetry type has not been disproven
	 */
	public static boolean readNotSymmetryType(RobotController rc, MapSymmetryType sym) throws GameActionException {
		int value;
		switch(sym) {
		case HORIZ_REFLECT:
			value = 0b001_0_000000_000000;
			break;
		case VERT_REFLECT:
			value = 0b010_0_000000_000000;
			break;
		case ROTATION:
			value = 0b100_0_000000_000000;
			break;
		case UNKNOWN:
		default:
			return false;
		}
		return ((rc.readSharedArray(2) & value)==0);
	}
	
	//Returns the three bits; the number will be in the range 0-7
	public static int readNotSymmetryType(RobotController rc) throws GameActionException {
		return rc.readSharedArray(2)>>13;
	}
	
	public static int compressLocation(MapLocation location) {
		return 64*location.y + location.x;
	}
}
