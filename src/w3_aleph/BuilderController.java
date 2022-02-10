package w3_aleph;

import battlecode.common.*;

public class BuilderController extends Robot {

	public BuilderController(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	MapLocation buildTarget = null;
	MapLocation travelTarget = null;
	Direction prevMoveDirection = Direction.CENTER;
	
	@Override
	public void run(RobotController rc) throws GameActionException {

		MapLocation me = rc.getLocation();
		
		if (buildTarget == null) {
			// Search for nearby locations that we want to build a watchtower at
			int rounded_x = me.x - (me.x%3);
			int rounded_y = me.y - (1+me.y%3);
			
			rc.setIndicatorString(rounded_x + ", " + rounded_y + "; " + buildTarget + "; "+travelTarget);
			
			MapLocation closest = null;
			int closest_r2 = 1000;
			
			for (int i = 3; --i>=-2;) {
				for (int j = 3; --j>=-2;) {
					// Check this location
					MapLocation other = new MapLocation(rounded_x+3*i, rounded_y+3*j);
					int this_r2 = me.distanceSquaredTo(other);

					if (this_r2 < closest_r2) {
						if (rc.canSenseLocation(other) && !rc.canSenseRobotAtLocation(other)) {
							closest_r2 = this_r2;
							closest = other;
						}
					}
				}
			}
			
			if (closest != null) {
				buildTarget = closest;
			} else {
				if (travelTarget == null || me.isAdjacentTo(travelTarget)) {
					travelTarget = Util.getRandomMapLocation(rc, rng);
				}
			}
		}
		
		// Try tuo build stuff
		if (buildTarget != null) {
			if (me.isAdjacentTo(buildTarget)) {
				RobotInfo target = rc.senseRobotAtLocation(buildTarget);
				if (target == null) {
					Direction dir = me.directionTo(buildTarget);
					if (rc.canBuildRobot(RobotType.WATCHTOWER, dir)) {
						rc.buildRobot(RobotType.WATCHTOWER, dir);
					}
				} else {
					if (target.mode == RobotMode.PROTOTYPE) {
						if (rc.canRepair(buildTarget)) {
							rc.repair(buildTarget);
						}
					} else {
						buildTarget = null;
					}
				}
			} else {
				moveToDest(rc, buildTarget);
			}
		} else {
			if (travelTarget != null) {
				moveToDest(rc, travelTarget);
			}
		}

	}
	
	private void moveToDest(RobotController rc, MapLocation target) throws GameActionException {
		
		if (rc.isMovementReady()) {
			MapLocation me = rc.getLocation();
			// Get the direciton to move
			Direction direction = SoldierPathfinding.getBestDirection(rc, target, prevMoveDirection);
			
			// Check if we should actually move that way
			if (direction != null && direction != Direction.CENTER) {
				rc.setIndicatorDot(me.add(direction), 120, 0, 0);
				if (!rc.isLocationOccupied(me.add(direction))) {
					rc.move(direction);
					prevMoveDirection = direction;
				}
			}
		}
	}

}
