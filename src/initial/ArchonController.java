package initial;

import battlecode.common.*;

public class ArchonController extends Robot {

	private Integer[] priorities = new Integer[7];
	
	public ArchonController() {
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		rc.setIndicatorString(priorities.toString());
		
		if (rc.isActionReady()) {
			int maxIndex = 0;
			switch(maxIndex) {
			case 0:
			{
				if (tryBuildRobot(rc, RobotType.BUILDER)) {
					priorities[0] -= 10;
				}
			}
				break;
			default:
				break;
			}
		}
	}
	
	private boolean tryBuildRobot(RobotController rc, RobotType type) throws GameActionException {
		//Iterate over directions clockwise(?) to find open space
		for (int i=8;--i>0;) {
			Direction dir = Util.directions[(i+rc.getRoundNum())%8];
			if(rc.canBuildRobot(RobotType.MINER, dir)) {
				rc.buildRobot(type, dir);
				return true;
			}
		}
		return false;
	}

}
