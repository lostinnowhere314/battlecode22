package initial2;

import battlecode.common.*;

public class ArchonController extends Robot {

	private final int N_CLASSES = 7;
	private int[] priorities = new int[N_CLASSES];
	private double mapArea;
	
	public ArchonController(RobotController rc) {
		super(rc);
		mapArea = rc.getMapHeight()*rc.getMapWidth();
		priorities[0] = 200;
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		rc.setIndicatorString(priorities.toString());
		
		if (rc.isActionReady()) {
			// Determine priorities
			int nArchons = rc.getArchonCount();
			if (nArchons>1 && (rc.getTeamLeadAmount(rc.getTeam()) < 10*nArchons) && rc.getRoundNum() > 50) {
				if (rng.nextDouble()>2/(nArchons+1)) {
					return; //// Hypothetically will help distribute units more evenly if lots of archons
				}
			}
			
			if (rc.getRoundNum() < 200 || (mapArea/rc.getRobotCount())>120) {
				priorities[0] += 3;
			} else if (rc.getRoundNum() < 500){
				priorities[0] += 1;
			}
			if (rc.getRoundNum() > 200) {
				priorities[1] += 3;
			} else {
				priorities[1] += 1;
			}
			
			
			//Find the maximum
			int maxIndex = 0;
			int maxPriority = 0;
			for (int i=N_CLASSES; --i>=0;) {
				if (priorities[i]>maxPriority) {
					maxPriority = priorities[i];
					maxIndex = i;
				}
			}
			
			if (maxPriority > 0) {
				switch(maxIndex) {
				case 0:
				{
					if (tryBuildRobot(rc, RobotType.MINER)) {
						priorities[0] -= 10;
					}
				}
				case 1:
				{
					if (tryBuildRobot(rc, RobotType.SOLDIER)) {
						priorities[1] -= 10;
					}
				}
					break;
				default:
					break;
				}
			}
		}
	}
	
	private boolean tryBuildRobot(RobotController rc, RobotType type) throws GameActionException {
		//Iterate over directions clockwise(?) to find open space
		for (int i=8;--i>0;) {
			Direction dir = Util.directions[(i+rc.getRoundNum())%8];
			if(rc.canBuildRobot(type, dir)) {
				rc.buildRobot(type, dir);
				return true;
			}
		}
		return false;
	}

}
