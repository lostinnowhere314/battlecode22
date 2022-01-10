package pre_sprint1;

import battlecode.common.*;
import pre_sprint1.Util.MapSymmetryType;

public class ArchonController extends Robot {

	private double mapArea;
	private int archonOrder;
	private int totalArchons;
	private boolean isPrimaryArchon = false;
	private double nearestEnemyArchonDist = 256;
	
	boolean enemiesSpotted = false;
	
	public ArchonController(RobotController rc) throws GameActionException {
		super(rc);
		mapArea = rc.getMapHeight()*rc.getMapWidth();
		
		archonOrder = 0;
		while (rc.readSharedArray(archonOrder)>0) {
			archonOrder++;
		}
		rc.writeSharedArray(archonOrder, Comms.compressLocation(rc.getLocation())|4096);
		totalArchons = rc.getArchonCount();
		if (archonOrder == 0) {
			isPrimaryArchon = true;
		}
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		
		MapLocation me = rc.getLocation();
		
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
			
			// Determine nearest possible enemy archon
			int nearestDistance = 256;
			
			MapSymmetryType symType = Comms.readSymmetryType(rc);
			
			if (symType == MapSymmetryType.UNKNOWN) {
				OUTER_LOOP:for (int j = 3; --j>=0;) {
					symType = Util.SymmetryTypes[j];
					if (Comms.readNotSymmetryType(rc, symType)) {
						for (int i = totalArchons; --i>=0;) {
							// way to make more efficient: take these outside the main loop
							MapLocation ourArchon = Comms.getLocationFromComms(rc, i);
							MapLocation other = Util.getSymmetricLocation(rc, ourArchon, symType);
							
							//See if we can just check whether the square is there
							if (rc.canSenseLocation(other)) {
								RobotInfo robot = rc.senseRobotAtLocation(other);
								if (robot != null && robot.getType() == RobotType.ARCHON
											&& !robot.getTeam().equals(rc.getTeam())) {
									//We then know that there is an archon there, so we "know" the symmetry type
									Comms.writeSymmetryType(rc, symType);
									break OUTER_LOOP;
								} else {
									// No robot there, so we have disproven that symmetry type
									Comms.writeNotSymmetryType(rc, symType);
									continue OUTER_LOOP;
								}
							}
								
							int dist2 = me.distanceSquaredTo(other);
							if (dist2 < nearestDistance) {
								nearestDistance = dist2;
							}
						}
					}
				}
			}
			symType = Comms.readSymmetryType(rc);
			if (symType != MapSymmetryType.UNKNOWN) {
				nearestDistance = 256;
				for (int i = totalArchons; --i>=0;) {
					// way to make more efficient: take these outside the main loop
					MapLocation ourArchon = Comms.getLocationFromComms(rc, i);
					MapLocation other = Util.getSymmetricLocation(rc, ourArchon, symType);
					
					int dist2 = me.distanceSquaredTo(other);
					if (dist2 < nearestDistance) {
						nearestDistance = dist2;
					}
				}
			}
			nearestEnemyArchonDist = Math.sqrt(nearestDistance);
		}
		
		// TODO check if any archons have been destroyed, re-figure out who's in charge
		
		// Miner counts
		Comms.cleanMinerCount(rc);
		Comms.cleanSoldierCount(rc);
		
		@SuppressWarnings("unused")
		int minerCount = Comms.getMinerCount(rc);
		@SuppressWarnings("unused")
		int soldierCount = Comms.getSoldierCount(rc);
		
		// Check for spotted enemies
		if (!enemiesSpotted) {
			for (int i=64; --i>=56;) {
				if (rc.readSharedArray(i) != 0) {
					enemiesSpotted = true;
					break;
				}
			}
		}
		
		rc.setIndicatorString(Integer.toString(archonOrder));
		
		if (rc.isActionReady()) {
			// Determine priorities
			// Old code
			/*int nArchons = rc.getArchonCount();
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
			}*/
			RobotType buildType = null;
			double[] weights = new double[3];

			int teamLeadAmt = rc.getTeamLeadAmount(rc.getTeam());
			int teamGoldAmt = rc.getTeamGoldAmount(rc.getTeam());
			
			// Decide if we can/want to build a sage
			int numCanBuildSages = teamGoldAmt / 50;
			if (numCanBuildSages > 0) {
				if (numCanBuildSages >= totalArchons-archonOrder) {
					// buildType = RobotType.SAGE;
				} else if (rng.nextInt(totalArchons-archonOrder)<numCanBuildSages ) {
					// buildType = RobotType.SAGE;
				}
			}
		
			if (buildType == null) {
				if ((rc.getRoundNum() < 2.2*nearestEnemyArchonDist
						&& rc.getRoundNum() < 50
						)|| !enemiesSpotted) {
					weights[0] = 1;
				} else if (rc.getRoundNum() < 500){
					//TODO calcualte other weights
					double soldierMinerRatio = Math.sqrt(rc.getRoundNum());
					double currentRatio = (soldierCount+10) / minerCount;
					
					weights[0] = currentRatio;
					if (teamLeadAmt > 60*(totalArchons-archonOrder)) {
						weights[1] = soldierMinerRatio;
					}
					
				} else {
					if (minerCount*16 < mapArea) { // figure out desired miner ct
						weights[0] = 5;
					}
					weights[1] = 5;
				}
				
				
				//Find the chosen one
				double cumWeight = 0;
				
				for (int i = 3; --i>=0;) {
					cumWeight += weights[i];
				}
				
				// Determine if we (probabilistically) have enough lead to do everything
				//    we want to
				double neededLead = weights[0] * RobotType.MINER.buildCostLead
						+weights[1] * RobotType.SOLDIER.buildCostLead
						+weights[2] * RobotType.BUILDER.buildCostLead;
				neededLead *= (totalArchons-archonOrder)/cumWeight;
				
				rc.setIndicatorString(String.format("%.1f, (%.2f,%.2f,%.2f)",
						neededLead, weights[0]/cumWeight,
						weights[1]/cumWeight, weights[2]/cumWeight));
				
				if (neededLead > teamLeadAmt) {
					if (rng.nextDouble()*neededLead > teamLeadAmt) {
						return;
					}
				}
				
				double chosenWeight = rng.nextDouble() * cumWeight;
				
				chosenWeight -= weights[0];
				if (chosenWeight < 0) {
					buildType = RobotType.MINER;
				} else {
					chosenWeight -= weights[1];
					if (chosenWeight < 0) {
						buildType = RobotType.SOLDIER;
					} else {
						chosenWeight -= weights[2];
						if (chosenWeight < 0) {
							// buildType = RobotType.BUILDER;
						}
					}
				}
			}
			
			if (buildType != null) {
				tryBuildRobot(rc, buildType);
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
