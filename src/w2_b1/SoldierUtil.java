package w2_b1;

import battlecode.common.*;

public class SoldierUtil {
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
