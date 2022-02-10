package w3_aleph;

import battlecode.common.*;
import w3_aleph.Util.ArchonTarget;
import w3_aleph.Util.MapSymmetryType;

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

		int writeVal;
		switch(archonOrder) {
		case 1:
			writeVal = Comms.compressLocation(rc.getLocation())|0b100_000000_000000;
			break;
		case 0:
		case 2:
		case 3:
		default:
			writeVal = Comms.compressLocation(rc.getLocation())|0b1_000000_000000;
			break;
		}
		rc.writeSharedArray(archonOrder, writeVal);
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
					rc.writeSharedArray(0, Comms.compressLocation(me) | archonAliveVal);
					
					switch (totalArchons) {
					case 1:
						rc.writeSharedArray(1, (totalArchons-1)<<14);
					case 2:
					case 3:
						// Alive enemy archons
						rc.writeSharedArray(3, archonAliveVal);
					default:
					}
				}
				break;
			case 1:
				{
					//Write total number of archons
					int val = rc.readSharedArray(1);
					rc.writeSharedArray(1, val & 0b11_111111_111111 + ((totalArchons-1)<<14));
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

			System.out.println(Comms.readNotSymmetryType(rc));
			MapSymmetryType symType = Comms.readSymmetryType(rc);

			if (symType == MapSymmetryType.UNKNOWN) {
				OUTER_LOOP:for (int j = 3; --j>=0;) {
					symType = Util.SymmetryTypes[j];
					if (Comms.readNotSymmetryType(rc, symType)) {
						for (int i = totalArchons; --i>=0;) {
							// way to make more efficient: take these outside the main loop
							MapLocation ourArchon = Comms.getLocationFromComms(rc, i);
							MapLocation other = Util.getSymmetricLocation(rc, ourArchon, symType);
							//System.out.println(other.x + ","+other.y);
							
							//See if we can just check whether the square is there
							if (rc.canSenseLocation(other)) {
								//System.out.println("Can sense");
								RobotInfo robot = rc.senseRobotAtLocation(other);
								if (robot != null && robot.getType() == RobotType.ARCHON
											&& !robot.getTeam().equals(rc.getTeam())) {
									//System.out.println("Has enemy archon");
									//We then know that there is an archon there, so we "know" the symmetry type
									Comms.writeSymmetryType(rc, symType);
									nearestDistance = me.distanceSquaredTo(other);
									break OUTER_LOOP;
								} else {
									//System.out.println("No enemy archon");
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
		
		//System.out.println("Sym. type: "+Comms.readSymmetryType(rc)+", "+Comms.readNotSymmetryType(rc));
		
		if (Comms.readSymmetryType(rc) == MapSymmetryType.UNKNOWN) {
			// Check if we can determine the symmetry type
			switch (Comms.readNotSymmetryType(rc)) {
			case 0b011:
				Comms.writeSymmetryType(rc, MapSymmetryType.ROTATION);
				break;
			case 0b101:
				Comms.writeSymmetryType(rc, MapSymmetryType.VERT_REFLECT);
				break;
			case 0b110:
				Comms.writeSymmetryType(rc, MapSymmetryType.HORIZ_REFLECT);
				break;
			case 0b111:
				// Clear the flags, because something went wrong
				rc.writeSharedArray(2, rc.readSharedArray(2) & 0b000_1_111111_111111);
				break;
			}
		}
		
		
		
		// TODO check if any archons have been destroyed, re-figure out who's in charge
		
		// Miner counts
		Comms.cleanMinerCount(rc);
		Comms.cleanSoldierCount(rc);
		
		int minerCount = Comms.getMinerCount(rc);
		int soldierCount = Comms.getSoldierCount(rc);
		
		// Check for spotted enemies
		if (!enemiesSpotted) {
			for (int i=64; --i>=56;) {
				if (rc.readSharedArray(i) != 0) {
					enemiesSpotted = true;
					break;
				}
			}
			if (rc.senseNearbyRobots(-1, rc.getTeam().opponent()).length > 0) {
				enemiesSpotted = true;
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
					buildType = RobotType.SAGE;
				} else if (rng.nextInt(totalArchons-archonOrder)<numCanBuildSages ) {
					buildType = RobotType.SAGE;
				}
			}
		
			if (buildType == null) {
				if ((rc.getRoundNum() < 2.2*nearestEnemyArchonDist
						|| rc.getRoundNum() < 60) && !enemiesSpotted) {
					weights[0] = 10;
				} else if (rc.getRoundNum() < 500){
					//TODO calcualte other weights
					double soldierMinerRatio = Math.sqrt(rc.getRoundNum());
					double currentRatio = (soldierCount+10) / (minerCount + 1);
					
					weights[0] = currentRatio;
					weights[1] = soldierMinerRatio;
					
				} else {
					if (minerCount*16 < mapArea) { // figure out desired miner ct
						weights[0] = 5;
					}
					weights[1] = 5;
				}
				
				if (teamLeadAmt > 200) {
					weights[2] = 1;
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
				
				ArchonTarget ne = Util.getNearestEnemyArchonLocation(rc);
				int lx = -1;
				int ly = -1;
				if (ne != null && ne.location != null) {
					lx= ne.location.x;
					ly= ne.location.y;
				}
				
				rc.setIndicatorString(String.format("%.1f, (%.2f;%.2f,%.2f,%.2f), (%d, %d), (%d, %d)",
						neededLead, cumWeight, weights[0],
						weights[1], weights[2],
						minerCount, soldierCount,
						lx, ly));
				
				if ((totalArchons - archonOrder) > 1) {
					if (neededLead > teamLeadAmt) {
						if (rng.nextDouble()*neededLead > teamLeadAmt) {
							return;
						}
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
							buildType = RobotType.BUILDER;
						}
					}
				}
			}
			
			if (buildType != null) {
				tryBuildRobot(rc, buildType);
			}
			
			
		}
		
		Comms.clearCommsTargets(rc);
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
