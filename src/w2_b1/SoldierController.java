package w2_b1;

import battlecode.common.*;
import w2_b1.Util.MapSymmetryType;
import w2_b1.Comms;

public class SoldierController extends Robot {


	static final int MIN_MESSAGE_NOOVERWRITE = 3;
	
	private static enum Mode {
		ARCHON_TARGET,
		COMMS_TARGET,
		WANDER,
		ATTACK
	}
	
	private MapLocation dest = null;
	private Util.RotationDirection bugDirection;
	private Mode mode = Mode.ARCHON_TARGET;
	private MapSymmetryType symType = MapSymmetryType.UNKNOWN;
	private Mode lastMode = Mode.WANDER;
	private int lastTargetID = -1;
	private MapLocation lastTargetLocation = null;
	private Direction prevMoveDirection = Direction.CENTER;
	
	private double mapRadius;
	
	//Comms stuff
	private int lastMessageTime = -1;
	private int messagePos = -1;
	
	public SoldierController(RobotController rc) {
		super(rc);
		bugDirection = rng.nextDouble()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
		lastTargetLocation = Util.getRandomMapLocation(rc, rng);
		mapRadius = rc.getMapHeight()*rc.getMapWidth()/4;
	}

	private void chooseStateRandom(RobotController rc) {
		if (rc.getRoundNum() > 400) {
			mode = (rng.nextDouble()<0.05 ? Mode.WANDER : Mode.ARCHON_TARGET);
		} else {
			mode = (rng.nextDouble()<0.3 ? Mode.WANDER : Mode.ARCHON_TARGET);
		}
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		// Keep soldier count incremented
        Comms.incrementSoldierCount(rc);
        
        
        MapLocation me = rc.getLocation();
       
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
		{
			// First, determine whether our target is still visible
			if(rc.canSenseRobot(lastTargetID)) {
				// If they are, try to maintain proper spacing
				RobotInfo targetInfo = rc.senseRobot(lastTargetID);
				int minDist, maxDist;
				switch (targetInfo.type) {
				case ARCHON:
					minDist = 0;
					maxDist = 2;
					break;
				case SAGE:
				case SOLDIER:
				case WATCHTOWER:
					// Let's try determining whether we'll be able to attack this turn
					if (rc.isActionReady()) {
						minDist = 6;
						maxDist = 13;
					} else {
						minDist = 25;
						maxDist = 40;
					}
					break;
				case BUILDER:
				case MINER:
				case LABORATORY:
				default:
					minDist = 0;
					maxDist = 13;
					break;
				}
				
				int dist2 = me.distanceSquaredTo(targetInfo.location);
				if (dist2 < minDist) {
					//Avoid being too close
					Util.move_minrubble_direction_strict(rc, me, me.directionTo(targetInfo.location).opposite(), bugDirection);
				} else if (dist2 > maxDist) {
					//But don't go too far away
					Util.move_minrubble(rc, me, targetInfo.location);
				} /*else { //Tends to make the robots spin around archons for some reason
					// Maybe move around. I think this might be bad but I'm not sure
					double r = rng.nextDouble();
					
					if (r > 0.8) {
					Util.move_minrubble_parallel(rc, me, 
							Util.getPerpendicularDir(
									(r>0.9)?me.directionTo(targetInfo.location):me.directionTo(targetInfo.location).opposite(),
									bugDirection));
					}
				}*/
			} else {
				// If not, try moving towards them
				moveToDest(rc, lastTargetLocation);
				//Util.move_minrubble(rc, me, lastTargetLocation); //replace with move_dest()
			}
			prevMoveDirection = Direction.CENTER;
			
		}
			break;
		default:
			System.out.println("Warning: soldier mode with no code!");
			break;
		}
		me = rc.getLocation();
		// determine most valuable target visible
		// then attack it
        RobotInfo[] enemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        
        int radius = rc.getType().actionRadiusSquared;
        if (enemies.length > 0) {
	        RobotInfo bestTarget = enemies[0];
	        RobotInfo bestRangeTarget = enemies[0];
	        double bestTargetScore = 0;
	        double bestRangeTargetScore = 0;
	        
	        for (int i = enemies.length; --i>=0;) {
	        	// Get the score
	        	double currentScore;
	        	RobotInfo currentEnemy = enemies[i];
	        	switch (currentEnemy.type) {
				case ARCHON:
					// base 1200, max hp 1200
					currentScore = 1200;
					break;
				case BUILDER:
					// 
					currentScore = 1000 - currentEnemy.health;
					break;
				case LABORATORY:
					currentScore = 1000 - currentEnemy.health;
					break;
				case MINER:
					currentScore = 1050 - currentEnemy.health;
					break;
				case SAGE:
					// 1250; maxhp 100
					currentScore = 1350 - currentEnemy.health;
					break;
				case SOLDIER:
					// 1201; maxhp 50
					currentScore = 1251 - currentEnemy.health;
					break;
				case WATCHTOWER:
					currentScore = 1400 - currentEnemy.health;
					break;
				default:
					currentScore = 0;
					break;
	        	}

	        	if (currentScore > bestTargetScore) {
	        		bestTargetScore = currentScore;
	        		bestTarget = currentEnemy;
	        	}
	        	if (currentScore > bestRangeTargetScore && me.distanceSquaredTo(currentEnemy.location) <= radius) {
	        		bestRangeTargetScore = currentScore;
	        		bestRangeTarget = currentEnemy;
	        	}
	        }

            rc.setIndicatorString(Integer.toString(me.distanceSquaredTo(lastTargetLocation)));
			rc.setIndicatorLine(me, lastTargetLocation, 240,0,240);
	        if (bestTargetScore > 0) {
	        	lastTargetLocation = bestTarget.location;
	        	lastTargetID = bestTarget.ID;
	        	// If we're in ARCHON_TARGET mode, only switch to attack mode if the thing we found
	        	//  is dangerous
	        	switch(mode) {
					case WANDER:
					case COMMS_TARGET:
			        	lastMode = mode;
			        	mode = Mode.ATTACK;
						break;
					case ARCHON_TARGET:
			        	switch(bestTarget.type) {
						case SAGE:
						case SOLDIER:
						case WATCHTOWER:
						case ARCHON:
				        	lastMode = mode;
				        	mode = Mode.ATTACK;
						case BUILDER:
						case LABORATORY:
						case MINER:
						default:
			        	}
					case ATTACK:
					default:
	        	}
	        	
	        	switch(bestTarget.type) {
				case ARCHON:
				case SAGE:
				case LABORATORY:
				case SOLDIER:
				case WATCHTOWER:
		            //Broadcast enemy locations
		            broadcastTargetLocation(rc, bestTarget);
					break;
				case BUILDER:
				case MINER:
				default:
					break;
	        	
	        	}
	        }
	        if (bestRangeTargetScore>0) {
		        //Attack if we can
		        MapLocation toAttack = bestRangeTarget.location;
	            if (rc.canAttack(toAttack)) {
	                rc.attack(toAttack);
	                if (rc.isMovementReady()) {
	                	switch (bestRangeTarget.type) {
						case WATCHTOWER:
						case SAGE:
						case SOLDIER:
							Util.move_minrubble_direction_strict(rc, me,
									me.directionTo(bestRangeTarget.location).opposite(), bugDirection);
							break;
						case ARCHON:
						case BUILDER:
						case LABORATORY:
						case MINER:
						default:
							break;
	                	}
	                	
	                	
	                }
	            }
	        }
            
        } else {
        	if (mode == Mode.ATTACK) {
        		//this bit is kind of really garbage
        		if (lastMode == Mode.ATTACK || lastMode == Mode.COMMS_TARGET) {
        			chooseStateRandom(rc);
        		} else {
        			mode = lastMode;
        		}
        		if (lastMode == Mode.WANDER) {
        			dest = Util.getRandomMapLocation(rc, rng);
        		}
        		lastMode = Mode.ATTACK;
        	}
        	rc.setIndicatorLine(me, dest, 0,150,50);
        }
		rc.setIndicatorString(mode.toString());


		cleanMessageSlot(rc);
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
	
	private void checkIfNewLocation(RobotController rc) throws GameActionException {
		// Choose new dest if needed
		if (rc.getLocation().distanceSquaredTo(dest) < 15 || dest == null) {
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
