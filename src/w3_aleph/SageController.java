package w3_aleph;

import battlecode.common.*;
import w3_aleph.Util.TargetingResult;

public class SageController extends Robot {


	static final int MIN_MESSAGE_NOOVERWRITE = 3;
	
	private static enum Mode {
		ARCHON_TARGET,
		COMMS_TARGET,
		WANDER,
		ATTACK
	}

	
	public SageController(RobotController rc) {
		super(rc);

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

	@Override
	public void run(RobotController rc) throws GameActionException {
		
		MapLocation me = rc.getLocation();
		
		//TODO we need a proper targeting class for sages, because they have unique stuff
		
		
	}

}
