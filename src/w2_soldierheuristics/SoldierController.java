package w2_soldierheuristics;

import battlecode.common.*;
import w2_soldierheuristics.Util.ArchonTarget;
import w2_soldierheuristics.Util.MapSymmetryType;
import w2_soldierheuristics.Util.RotationDirection;
import w2_soldierheuristics.Util.TargetingResult;
import w2_soldierheuristics.Comms;

public class SoldierController extends Robot {


	static final int MIN_MESSAGE_NOOVERWRITE = 3;
	
	private static enum Mode {
		ARCHON_TARGET,
		COMMS_TARGET,
		WANDER,
		ATTACK
	}
	
	private MapLocation dest;
	//private Util.RotationDirection bugDirection;
	private Mode mode = Mode.ARCHON_TARGET;
	private MapSymmetryType symType = MapSymmetryType.UNKNOWN;
	private Mode lastMode = Mode.WANDER;

	private Direction prevMoveDirection = Direction.CENTER;
	
	private double mapRadius;
	
	//Comms stuff
	private int lastMessageTime = -1;
	private int messagePos = -1;
	
	public SoldierController(RobotController rc) {
		super(rc);
		//bugDirection = rng.nextDouble()>0.5 ? 
		//		Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
		
		dest = Util.getRandomMapLocation(rc, rng);
		
		mapRadius = rc.getMapHeight()*rc.getMapWidth()/4;
	}

	private void chooseStateRandom(RobotController rc) {
		if (rc.getRoundNum() > 400) {
			mode = (rng.nextDouble()<0.3 ? Mode.WANDER : Mode.ARCHON_TARGET);
		} else {
			mode = (rng.nextDouble()<0.5 ? Mode.WANDER : Mode.ARCHON_TARGET);
		}
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		// Keep soldier count incremented
        Comms.incrementSoldierCount(rc);
        
        MapLocation me = rc.getLocation();
       
        boolean hasMoved = false;
        
    	TargetingResult targetResult = SoldierTargeting.getBestTarget(rc);

		String indicatorString = "";

    	if (targetResult != null) {
    		// Debugging //
    		if (targetResult.target != null) {
    			indicatorString += String.format("(%d,%d)", targetResult.target.location.x,
    					targetResult.target.location.y);
    		} else {
    			indicatorString += "(null)";
    		}
			indicatorString += ", ";
    		if (targetResult.moveDirection != null) {
    			indicatorString += targetResult.moveDirection;
    		} else {
    			indicatorString += "null";
    		}
			indicatorString += ", ";
			indicatorString += targetResult.order;
    		/////////////////////
			
			
    		
    		switch (targetResult.order) {
			case AFTER:
				{
					if (targetResult.canAttack) {
						rc.attack(targetResult.target.location);
					}
					if (targetResult.canMove) {
						if (targetResult.retreat) {
							System.out.println("Retreating");
							Util.move_minrubble_direction(rc, me, targetResult.moveDirection, RotationDirection.CLOCKWISE);
						} else if (targetResult.moveDirection == null) {
							moveToDest(rc, dest);
						} else {
							rc.move(targetResult.moveDirection);
						}
					}
				}
				break;
			case BEFORE:
				{
					if (targetResult.canMove) {
						if (targetResult.moveDirection == null) {
							moveToDest(rc, dest);
						} else {
							rc.move(targetResult.moveDirection);
						}
					}
					if (targetResult.canAttack) {
						rc.attack(targetResult.target.location);
					}
				}
				break;
			case NONE:
			default:
				{
					// Moving isn't possible, so just attack
					if (targetResult.canAttack) {
						rc.attack(targetResult.target.location);
					}
				}
				break;
    		}
			hasMoved = true;
    		//Broadcast enemy locations as appropriate
    		if (targetResult.target != null) {
	    		switch(targetResult.target.type) {
					case ARCHON:
					case SAGE:
					case LABORATORY:
					case SOLDIER:
					case WATCHTOWER:
			            //Broadcast enemy locations
			            broadcastTargetLocation(rc, targetResult.target);
						break;
					case BUILDER:
					case MINER:
					default:
						break;
	    		}
        	}
    		prevMoveDirection = Direction.CENTER;
    	} else {
    		// There are no targets in range in this case
    		if (mode == Mode.ATTACK) {
        		//this bit is kind of garbage
        		if (lastMode == Mode.ATTACK || lastMode == Mode.COMMS_TARGET) {
        			chooseStateRandom(rc);
        		} else {
        			mode = lastMode;
        		}

    			getNewDestination(rc);
    			
        		lastMode = Mode.ATTACK;
        	}
        	rc.setIndicatorLine(me, dest, 0,150,50);
    	}
    	

    	
    	//////////////////
        if (!hasMoved) {
		switch(mode) {
			case WANDER:
			{
				// Check comms for new messages
				double scanDist = mapRadius/2;
				if (scanDist < 256) {
					scanDist = 256;
				}
				MapLocation closestBroadcastDest = scanCommsSoldierTargets(rc, scanDist);
				if (closestBroadcastDest == null) {
					closestBroadcastDest = scanCommsMinerTargets(rc, scanDist);
				}
				if (closestBroadcastDest != null) {
					dest = closestBroadcastDest;
					lastMode = mode;
					mode = Mode.COMMS_TARGET;
				}
				checkIfNewLocation(rc);
				//Util.move_minrubble(rc, me, dest);
				moveToDest(rc, dest);
			}
			break;
			case COMMS_TARGET:
			{	
				// See if there's somewhere closer we want to be
				MapLocation closestBroadcastDest = scanCommsAllTargets(rc, 1000);
				if (closestBroadcastDest!=null &&
						me.distanceSquaredTo(closestBroadcastDest) < me.distanceSquaredTo(dest)) {
					dest = closestBroadcastDest;
				}
				// Choose new dest if needed
				
				checkIfNewLocation(rc);

				// Move
				//Util.move_minrubble(rc, me, dest);
				moveToDest(rc, dest);
			}
			break;
			case ARCHON_TARGET:
			{
				// Choose potential archon to target
				if (dest == null || me.isWithinDistanceSquared(dest, 14)) {
					if (symType != MapSymmetryType.UNKNOWN) {
						if (Comms.readSymmetryType(rc) == MapSymmetryType.UNKNOWN) {
							//Test if there's an archon at our destination
							if (rc.canSenseLocation(dest)) {
								RobotInfo robot = rc.senseRobotAtLocation(dest);
								if (robot != null 
										&& robot.type == RobotType.ARCHON
										&& rc.getTeam().opponent().equals(robot.team)) {
									// then we "know" the symmetry type
									Comms.writeSymmetryType(rc, symType);
								} else {
									// then we "know" it's not that symmetry
									Comms.writeNotSymmetryType(rc, symType);
								}
							}
						}
					}
					
					lastMode = mode;
					chooseStateRandom(rc);
			        switch(mode) {
				        case ARCHON_TARGET:
				        {
				        	Util.ArchonTarget target = Util.getNearestEnemyArchonLocation(rc);
				        	dest = target.location;
				        	symType = target.symmetry;
				        }
				        case WANDER:
				        	dest = Util.getRandomMapLocation(rc, rng);
				    	default:
			        }
				}
	
		        MapLocation closestBroadcastDest = scanCommsSoldierTargets(rc, 65);
				if (closestBroadcastDest != null) {
					dest = closestBroadcastDest;
					lastMode = mode;
					mode = Mode.COMMS_TARGET;
				}
		        // Use the function to move
				//Util.move_minrubble(rc, me, dest);
				moveToDest(rc, dest);
			}
				break;
			case ATTACK:
			default:
				break;
			}
        }

		me = rc.getLocation();
		//rc.setIndicatorString(mode.toString());
		cleanMessageSlot(rc);
		indicatorString += ", " + dest+", " + mode + ", "+ symType;
		rc.setIndicatorString(indicatorString);
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

	private void broadcastTargetLocation(RobotController rc, RobotInfo bestTarget) throws GameActionException {
		if (messagePos < 0) {
			int best_diff = -1;
			for (int i=56; --i>=48;) {
				int val;
				if (rc.readSharedArray(i)==0) { //prioritize empty slots
					val = 100;
				} else {
					val = ((1+rc.getRoundNum() - ((rc.readSharedArray(i) >> 12)&7)) % 7);
				}
				int diff = val - MIN_MESSAGE_NOOVERWRITE;
				if (diff > best_diff) {
					messagePos = i;
					best_diff = diff;
				}
			}
			if (best_diff < 0) messagePos = -1;  
		}
		
		if (messagePos > 0) {
			// Write the message
			lastMessageTime = (rc.getRoundNum() % 7) + 1;
			int message = (lastMessageTime<<12)
					+ (bestTarget.location.y<<6)
					+ bestTarget.location.x;
			switch(bestTarget.type) {
			case ARCHON:
				message += 32768;
				break;
			case BUILDER:
				break;
			case LABORATORY:
				break;
			case MINER:
				break;
			case SAGE:
				break;
			case SOLDIER:
				break;
			case WATCHTOWER:
				break;
			default:
				break;
			}
			rc.writeSharedArray(messagePos, message);
		}
	}
	
	private void cleanMessageSlot(RobotController rc) throws GameActionException {
		if (messagePos > 0) {
			int readMessageTime = (rc.readSharedArray(messagePos) >> 12)&7;
			// check that our last message hasn't been overwritten
			if (readMessageTime != lastMessageTime) {
				messagePos = -1;
			// check if we should clear it
			} else if (readMessageTime%7 == rc.getRoundNum() % 7) {
				//clear if it's been around for too long
				rc.writeSharedArray(messagePos, 0);
				messagePos = -1;
			}
		}
	}
	
	private void getNewDestination(RobotController rc) throws GameActionException {
		switch(mode) {
        case ARCHON_TARGET:
        {
        	
        	ArchonTarget target = Util.getNearestEnemyArchonLocation(rc);

        	dest = target.location;
        	symType = target.symmetry;
        }
        break;
        case WANDER:
        	dest = Util.getRandomMapLocation(rc, rng);
	        break;
    	default:
	        break;
    }
	}
	
	private void checkIfNewLocation(RobotController rc) throws GameActionException {
		// Choose new dest if needed
		if (rc.getLocation().distanceSquaredTo(dest) < 15 || dest == null) {
			lastMode = mode;

			chooseStateRandom(rc);
			getNewDestination(rc);

		}
	}
	
	private static MapLocation scanCommsMinerTargets(RobotController rc, double maxDistance2) throws GameActionException {
		// Check comms for new messages
		MapLocation me = rc.getLocation();
		MapLocation bestDest = null;
		double bestDistance = maxDistance2+1;
		for (int i=64; --i>=56;) {
			if (rc.readSharedArray(i)!=0) {
				MapLocation other = Comms.getLocationFromComms(rc, i);
				int dist = other.distanceSquaredTo(me);
				if (dist < bestDistance) {
					bestDest = other;
					bestDistance = dist;
				}
			}
		}
		
		return bestDest;
	}

	private static MapLocation scanCommsSoldierTargets(RobotController rc, double maxDistance2) throws GameActionException {
		// Check comms for new messages
		MapLocation me = rc.getLocation();
		MapLocation bestDest = null;
		double bestDistance = maxDistance2+1;
		for (int i=56; --i>=48;) {
			if (rc.readSharedArray(i)!=0) {
				MapLocation other = Comms.getLocationFromComms(rc, i);
				int dist = other.distanceSquaredTo(me);
				if (dist < bestDistance) {
					bestDest = other;
					bestDistance = dist;
				}
			}
		}
		
		return bestDest;
	}


	private static MapLocation scanCommsAllTargets(RobotController rc, double maxDistance2) throws GameActionException {
		// Check comms for new messages
		MapLocation me = rc.getLocation();
		MapLocation bestDest = null;
		double bestDistance = maxDistance2+1;
		for (int i=64; --i>=48;) {
			if (rc.readSharedArray(i)!=0) {
				MapLocation other = Comms.getLocationFromComms(rc, i);
				int dist = other.distanceSquaredTo(me);
				if (dist < bestDistance) {
					bestDest = other;
					bestDistance = dist;
				}
			}
		}
		
		return bestDest;
	}
}
