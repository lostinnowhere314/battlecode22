package pre_sprint1;

import battlecode.common.*;

public class ArchonController extends Robot {

	private final int N_CLASSES = 7;
	private int[] priorities = new int[N_CLASSES];
	private double mapArea;
	private int archonOrder;
	private int totalArchons;
	
	boolean enemiesSpotted = false;
	
	public ArchonController(RobotController rc) throws GameActionException {
		super(rc);
		mapArea = rc.getMapHeight()*rc.getMapWidth();
		priorities[0] = 200;
		
		archonOrder = 0;
		while (rc.readSharedArray(archonOrder)>0) {
			archonOrder++;
		}
		rc.writeSharedArray(archonOrder, Comms.compressLocation(rc.getLocation())|4096);
		totalArchons = rc.getArchonCount();
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		
		// Comms //
		if (rc.getRoundNum()==2) {
			// Clean up
			switch (archonOrder) {
			case 0:
				{
					//Write which archons are alive
					int archonAliveVal;
					switch(totalArchons) {
					case 1:
						archonAliveVal = 4096;
						break;
					case 2:
						archonAliveVal = 12288;
						break;
					case 3:
						archonAliveVal = 28672;
						break;
					case 0:
					default:
						archonAliveVal = 61440;
						break;
					}
					rc.writeSharedArray(0, rc.readSharedArray(0) | archonAliveVal);
					
					switch (totalArchons) {
					case 3:
						// Alive enemy archons
						rc.writeSharedArray(3, archonAliveVal);
					case 2:
					case 1:
						rc.writeSharedArray(1, (totalArchons-1)*16384);
					default:
					}
				}
				break;
			case 1:
				{
					//Write total number of archons
					int val = rc.readSharedArray(1);
					rc.writeSharedArray(1, val & 4095 + (totalArchons-1)*16384);
				}
				break;
			case 2:
				{
					rc.writeSharedArray(archonOrder, rc.readSharedArray(archonOrder) & 4095);
				}
				break;
			case 3:
				{
					//Write which enemy archons are alive
					int archonAliveVal;
					switch(totalArchons) {
					case 1:
						archonAliveVal = 4096;
						break;
					case 2:
						archonAliveVal = 12288;
						break;
					case 3:
						archonAliveVal = 28672;
						break;
					case 0:
					default:
						archonAliveVal = 61440;
						break;
					}
					rc.writeSharedArray(3, rc.readSharedArray(3) | archonAliveVal);
				}
				break;
			default:
			}
		}
		// Miner counts
		Comms.cleanMinerCount(rc);
		@SuppressWarnings("unused")
		int minerCount = Comms.getMinerCount(rc);
		// Check for spotted enemies
		if (!enemiesSpotted) {
			for (int i=64; --i>=56;) {
				if (rc.readSharedArray(i) != 0) {
					enemiesSpotted = true;
					break;
				}
			}
		}
		
		rc.setIndicatorString(Integer.toString(archonOrder)+"; "+priorities.toString());
		
		if (rc.isActionReady()) {
			// Determine priorities
			int nArchons = rc.getArchonCount();
			if (rc.getRoundNum() > 15 && nArchons>1
					&& (rc.getTeamLeadAmount(rc.getTeam()) < 44*(nArchons-archonOrder))) {
				if (rng.nextDouble()>2/(nArchons+1)) {
					return; //// Hypothetically will help distribute units more evenly if lots of archons
				}
			}

			// This really needs to be reworked but idk what exactly to do otherwise
			if (priorities[0] < 1) {
				priorities[0] = 20;
			} else if (rc.getRoundNum() < 200 || (mapArea/rc.getRobotCount())>120) {
				priorities[0] += 6;
			} else if (rc.getRoundNum() < 500){
				priorities[0] += 5;
			} else {
				priorities[0] += 1;
			}
			if (priorities[1] < 1) {
				priorities[1] = 50;
			} else if (rc.getRoundNum() > 50) {
				priorities[1] += 9;
			} else {
				priorities[1] += 4;
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
