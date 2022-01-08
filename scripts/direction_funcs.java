public static Direction getPerpendicularDir(Direction dir, RotationDirection clk)
	switch(clk) {
	case CLOCKWISE:
		switch(dir): {
			case NORTH: return Direction.EAST;
			case NORTHEAST: return Direction.SOUTHEAST;
			case EAST: return Direction.SOUTH;
			case SOUTHEAST: return Direction.SOUTHWEST;
			case SOUTH: return Direction.WEST;
			case SOUTHWEST: return Direction.NORTHWEST;
			case WEST: return Direction.NORTH;
			case NORTHWEST: return Direction.NORTHEAST;
			default: return dir;
		}	case COUNTERCLOCKWISE:
		switch(dir): {
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
}
