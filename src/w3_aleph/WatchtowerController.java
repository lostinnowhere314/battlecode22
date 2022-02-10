package w3_aleph;

import battlecode.common.*;

public class WatchtowerController extends Robot {

	public WatchtowerController(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(RobotController rc) throws GameActionException {
		switch (rc.getMode()) {
		case PROTOTYPE:
			// Can't really do anything
			return;
		case TURRET:
			// Try to attack
			if (rc.isActionReady()) {
				RobotInfo[] targets = rc.senseNearbyRobots(20, rc.getTeam().opponent());
				
				RobotInfo bestTarget = null;
				double bestScore = 0;
				
				for (int i = targets.length; --i>=0;) {
					RobotInfo currentTarget = targets[i];
					double value = getTargetValue(rc, currentTarget);
					
					if (bestScore < value) {
						bestScore = value;
						bestTarget = currentTarget;
					}
				}
				
				if (bestTarget != null) {
					rc.attack(bestTarget.location);
				}
			}
			break;
		case PORTABLE:
			// Figure out where to go
			break;
		default:
			break;
		}
		
	}
	public static double getTargetValue(RobotController rc, RobotInfo robot) throws GameActionException {
		double health = robot.getHealth();
		double rubbleFactor = 10+rc.senseRubble(robot.location);
		
		switch(robot.type) {
		case ARCHON:
			return 8 + 1/health;
		case BUILDER:
			return 9 + 1/health;
		case LABORATORY:
			return 7 + 1/health;
		case MINER:
			return 5 + 1/health;
		case SAGE:
			return 10 + (6/(health*rubbleFactor));
		case SOLDIER:
			return 10 + (3/(health*rubbleFactor));
		case WATCHTOWER:
			switch (robot.mode) { 
				case TURRET:
					// watchtowers are dangerous
					return 10 + (4*robot.level/(health*rubbleFactor));
				default:
					return 10;
			}
		default:
			return 0;
		}
	}
}
