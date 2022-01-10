package pre_sprint1;

import battlecode.common.*;

public class MinerController extends Robot {
	
	// Constants for destination choosing
	static final int MAX_LEAD_VAL = 20;
	static final int GOLD_VAL = 15;
	static final int FRIENDLY_MINER_WEIGHT1 = -100;
	static final int FRIENDLY_MINER_WEIGHT2 = -50;
	static final int FRIENDLY_MINER_WEIGHT3 = -15;
	static final int ENEMY_WEIGHT = -500;
	static final int ENEMY_MINER_WEIGHT = -30;
	static final int ENEMY_MINER_MAX_DIST = 24;
	static final int MAX_MINER_DIST = 14;
	static final int RESOURCE_SENSE_RANGE = 24;
	static final int ROBOT_SENSE_RANGE = 24;
	
	static final int LEAD_RETRY_AMT = 30; //this is about 60% of tiles in vision range have lead
	static final int LEAD_RETRY_R2 = 4;
	static final int MAX_LEAD_DEPOSITS = 16;
	static final int MAX_ROBOTS = 16;
	static final int MAX_MINEABLE = 5;
	
	static final int UNROLLED_CENTER = 60;
	static final int OTHER_MINERS_BYTECODE_REM_LIM = 850; //this probably needs adjusted
	
	static final double IN_PLACE_MULTIPLIER = 1.7;
	/////
	//comms
	static final int MIN_MESSAGE_NOOVERWRITE = 5;
	private int messagePos = -1;
	private int lastMessageTime = -1;
	
	private boolean hasRegisteredForCount = false;
	private MapLocation dest = null;
	private Util.RotationDirection bugDirection;
	private Direction runDirection = Direction.CENTER;
	
	public MinerController(RobotController rc) {
		super(rc);
		bugDirection = rng.nextDouble()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
		
		dest = Util.getRandomMapLocation(rc, rng);
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		String indicatorString = "";
		
		if ((rc.getRoundNum() % Comms.MINER_CT_CYCLE) == 0) {
			hasRegisteredForCount = false;
		}
		
		MapLocation me = rc.getLocation();
		if (dest == null) {
			dest = Util.getRandomMapLocation(rc, rng);
		}
		
		MapLocation[] adjResources = new MapLocation[MAX_MINEABLE];
		boolean[] isGold = new boolean[MAX_MINEABLE];
		int adjIndex = MAX_MINEABLE;
		MapLocation enemyLocation = null;
		
		RobotInfo[] robots = rc.senseNearbyRobots(ROBOT_SENSE_RANGE);
		boolean unimportantEnemy = true;
		
		//This should really get moved into its own function
		if (rc.isMovementReady()) {
			//Used to determine if we want to run away from anything, or move towards resources
			
			//determine if there's nearby resources we want to mine; adjust target dest accordingly
			MapLocation[] leadLocs = rc.senseNearbyLocationsWithLead(24);
			MapLocation[] goldLocs = rc.senseNearbyLocationsWithGold(24);
			
			indicatorString =  Integer.toString(leadLocs.length) + ";" + Integer.toString(goldLocs.length)+ ";" + Integer.toString(robots.length) + ";";
			rc.setIndicatorString(indicatorString);
			
			//Unrolled array of an 11x11 grid centered on the robot
			double weights[] = new double[121];
			//also should add diags really
			for(int i = goldLocs.length; --i>=0;) {
				MapLocation cl = goldLocs[i];
				int amt = rc.senseGold(cl);
				double wt = amt*GOLD_VAL;
	
				int index = cl.x-me.x+11*(cl.y-me.y)+59;
				
				weights[index] += wt;
				weights[index-11] += wt;
				weights[index+11] += wt;
				weights[++index] += wt;
				weights[index-11] += wt;
				weights[index+11] += wt;
				weights[++index] += wt;
				weights[index-11] += wt;
				weights[index+11] += wt;
				if (me.isAdjacentTo(cl)) {
					for (;--amt>=0&&adjIndex>0;) {
						adjIndex--;
						adjResources[adjIndex] = cl;
						isGold[adjIndex] = true;
					}
				}
			}
	
			{
				
				int i = leadLocs.length;
				boolean ignoreFar = false;
				//If there's too much lead, reduce for computation time
				if (i >= LEAD_RETRY_AMT) {
					// If there's *really* too much, rescan with smaller radius
					leadLocs = rc.senseNearbyLocationsWithLead(LEAD_RETRY_R2);
					i = leadLocs.length;
				} else if (i>MAX_LEAD_DEPOSITS) {
					//i=MAX_LEAD_DEPOSITS;
					ignoreFar = true;
				}
	
				for(; --i>=0;) {
					MapLocation cl = leadLocs[i];
					if (ignoreFar && !me.isWithinDistanceSquared(cl, LEAD_RETRY_R2)) {
						continue;
					}
					int amt = rc.senseLead(cl);
					if (amt > MAX_LEAD_VAL) {
						amt = MAX_LEAD_VAL;
					}
					if (amt > 0) {
						int index = cl.x-me.x+11*(cl.y-me.y)+59;
	
						weights[index] += amt;
						weights[index-11] += amt;
						weights[index+11] += amt;
						weights[++index] += amt;
						weights[index-11] += amt;
						weights[index+11] += amt;
						weights[++index] += amt;
						weights[index-11] += amt;
						weights[index+11] += amt;
						
						if (me.isAdjacentTo(cl)) {
							amt--;
							for (;--amt>=0 && adjIndex>0;) {
								adjIndex--;
								adjResources[adjIndex] = cl;
							}
						}
					}
				}
			}
			
			// determine if there's robots we should avoid
			{
				int i = robots.length;
				if (i>MAX_ROBOTS) {
					i=MAX_ROBOTS;
				}
				for(; --i>=0;) {
					RobotInfo robot = robots[i];
					if(rc.getTeam()==robot.getTeam()) {
						// Our team
						switch (robot.type) {
							case MINER:
							if (me.isWithinDistanceSquared(robot.location, MAX_MINER_DIST)) {
								if (Clock.getBytecodesLeft() < OTHER_MINERS_BYTECODE_REM_LIM) {
									continue;
								}
								
								int index = robot.location.x-me.x+11*(robot.location.y-me.y)+58;
								
								// x-2
								weights[index] += FRIENDLY_MINER_WEIGHT3;
								weights[index-11] += FRIENDLY_MINER_WEIGHT3;
								weights[index+11] += FRIENDLY_MINER_WEIGHT3;
								
								// x-1
								weights[++index] += FRIENDLY_MINER_WEIGHT2;
								weights[index-11] += FRIENDLY_MINER_WEIGHT2;
								weights[index+11] += FRIENDLY_MINER_WEIGHT2;
								weights[index-22] += FRIENDLY_MINER_WEIGHT3;
								weights[index+22] += FRIENDLY_MINER_WEIGHT3;
	
								// x
								weights[++index] += FRIENDLY_MINER_WEIGHT1;
								weights[index-11] += FRIENDLY_MINER_WEIGHT2;
								weights[index+11] += FRIENDLY_MINER_WEIGHT2;
								weights[index-22] += FRIENDLY_MINER_WEIGHT3;
								weights[index+22] += FRIENDLY_MINER_WEIGHT3;
	
								// x+1
								weights[++index] += FRIENDLY_MINER_WEIGHT2;
								weights[index-11] += FRIENDLY_MINER_WEIGHT2;
								weights[index+11] += FRIENDLY_MINER_WEIGHT2;
								weights[index-22] += FRIENDLY_MINER_WEIGHT3;
								weights[index+22] += FRIENDLY_MINER_WEIGHT3;
								
								// x+2
								weights[++index] += FRIENDLY_MINER_WEIGHT3;
								weights[index-11] += FRIENDLY_MINER_WEIGHT3;
								weights[index+11] += FRIENDLY_MINER_WEIGHT3;
							}
								break;
							case ARCHON:
							case BUILDER:
							case LABORATORY:
							case SAGE:
							case SOLDIER:
							case WATCHTOWER:
							default:
								break;
						}
					} else {
						//Enemy team
						switch (robot.type) {
							case SAGE:
							case SOLDIER:
							case WATCHTOWER:
							{
								/*int x=robot.location.x-me.x;
								int y=robot.location.y-me.y;
								weights[5+x][5+y] += ENEMY_WEIGHT;
								weights[4+x][4+y] += ENEMY_WEIGHT;
								weights[5+x][4+y] += ENEMY_WEIGHT;
								weights[6+x][4+y] += ENEMY_WEIGHT;
								weights[4+x][5+y] += ENEMY_WEIGHT;
								weights[6+x][5+y] += ENEMY_WEIGHT;
								weights[4+x][6+y] += ENEMY_WEIGHT;
								weights[5+x][6+y] += ENEMY_WEIGHT;
								weights[6+x][6+y] += ENEMY_WEIGHT;
								weights[4+x][3+y] += ENEMY_WEIGHT;
								weights[5+x][3+y] += ENEMY_WEIGHT;
								weights[6+x][3+y] += ENEMY_WEIGHT;
								weights[4+x][7+y] += ENEMY_WEIGHT;
								weights[5+x][7+y] += ENEMY_WEIGHT;
								weights[6+x][7+y] += ENEMY_WEIGHT;
								weights[3+x][4+y] += ENEMY_WEIGHT;
								weights[3+x][5+y] += ENEMY_WEIGHT;
								weights[3+x][6+y] += ENEMY_WEIGHT;
								weights[7+x][4+y] += ENEMY_WEIGHT;
								weights[7+x][5+y] += ENEMY_WEIGHT;
								weights[7+x][6+y] += ENEMY_WEIGHT;*/
								
								//Aggressively try to move away
								switch(runDirection) {
								case CENTER:
									runDirection = me.directionTo(robot.location).opposite();
								default:
								}
								if (unimportantEnemy) {
									unimportantEnemy=false;
									enemyLocation = robot.location;
								}
								
							}
								break;
							case ARCHON:
							case LABORATORY:
							{
								enemyLocation = robot.location;
								unimportantEnemy = false;
							}
								break;
							case MINER:
							{
								int index = robot.location.x-me.x+11*(robot.location.y-me.y)+59;
	
								weights[index] += ENEMY_MINER_WEIGHT;
								weights[index-11] += ENEMY_MINER_WEIGHT;
								weights[index+11] += ENEMY_MINER_WEIGHT;
								weights[++index] += ENEMY_MINER_WEIGHT;
								weights[index-11] += ENEMY_MINER_WEIGHT;
								weights[index+11] += ENEMY_MINER_WEIGHT;
								weights[++index] += ENEMY_MINER_WEIGHT;
								weights[index-11] += ENEMY_MINER_WEIGHT;
								weights[index+11] += ENEMY_MINER_WEIGHT;
							}
							case BUILDER:
								if (enemyLocation == null) {
									enemyLocation = robot.location;
								}
							default:
						}
					}
					
				}
			}
			// Prefer staying in place if it isn't a bad spot
			weights[60] *= IN_PLACE_MULTIPLIER;
			
			//find the max weight
			switch(runDirection) {
			case CENTER:
				//In this case, move towards the best tile
				MapLocation bestLocation = me;
				MapLocation offsetLocation = me.add(
						Util.directions[(rc.getID()+rc.getRoundNum())%8]);
				double bestWeight = 0;
				for (int i=109;--i>=0;) {
					if (i%11==0) { //skip the left- and right-most columns
						i--;
						if (i<11) {
							break;
						} else {
							continue;
						}
					}
					
					double w=weights[i];
					// Figure out if we should check rubble
					if(w > bestWeight) {
						int x = i%11;
						int y = i / 11;
						MapLocation location = new MapLocation(me.x+x-6, me.y+y-6);
						if (location.equals(offsetLocation)) {
							w += 1;
						}
						if (rc.canSenseLocation(location)) {
							w /= (1+0.3*rc.senseRubble(location));
							if (w > bestWeight) {
								bestWeight = w;
								bestLocation = location;
							}
						}
					}
				}
				indicatorString = indicatorString+Double.toString(bestWeight);
				//threshold is adjustable
				if (bestWeight > 1.1) {
					dest = bestLocation;
				} else {
					if (me.isWithinDistanceSquared(dest, 10)) {
						dest = Util.getRandomMapLocation(rc, rng);
					}
				}
			default:
			}
		} else {
			//We still need to calculate nearby resources
			{
				MapLocation[] goldLocs = rc.senseNearbyLocationsWithGold(3);
				
				for(int i = goldLocs.length; --i>=0 && adjIndex>0;) {
					MapLocation cl = goldLocs[i];
					int amt = rc.senseGold(cl);
					for (;--amt>=0 && adjIndex>0;) {
						adjIndex--;
						adjResources[adjIndex] = cl;
						isGold[adjIndex] = true;
					}
				}
				
				if (adjIndex > 0) {
					MapLocation[] leadLocs = rc.senseNearbyLocationsWithLead(3);
					for(int i = leadLocs.length; --i>=0 && adjIndex>0;) {
						MapLocation cl = leadLocs[i];
						int amt = rc.senseLead(cl) - 1;
						for (;--amt>=0 && adjIndex>0;) {
							adjIndex--;
							adjResources[adjIndex] = cl;
						}
					}
				}
			}
			
			
			//Also check for nearby enemies for comms purposes
			{
				int i = robots.length;
				if (i>MAX_ROBOTS) {
					i=MAX_ROBOTS;
				}
				for(; --i>=0;) {
					RobotInfo robot = robots[i];
					if(rc.getTeam()!=robot.getTeam()) {
						//Enemy team
						switch (robot.type) {
							case SAGE:
							case SOLDIER:
							case WATCHTOWER:
								if (unimportantEnemy) {
									unimportantEnemy=false;
									enemyLocation = robot.location;
								}
								break;
							case ARCHON:
							case LABORATORY:
							{
								enemyLocation = robot.location;
								unimportantEnemy = false;
							}
								break;
							case MINER:
							case BUILDER:
								if (enemyLocation == null) {
									enemyLocation = robot.location;
								}
							default:
						}
					}
				}
			}
		}
		
		//do mining stuff
		for (int i=MAX_MINEABLE; rc.isActionReady() && (--i>=adjIndex);) {
			if (isGold[i]) {
				rc.mineGold(adjResources[i]);
			} else {
				rc.mineLead(adjResources[i]);
			}
		}
		indicatorString = Integer.toString(5-adjIndex)+", "+indicatorString;
		rc.setIndicatorString(indicatorString);
		
		if (rc.isMovementReady()) {
			// Move
			switch(runDirection) {
			case CENTER:
				switch (Util.move_minrubble(rc, me, dest)) {
				case SUCCESS:
					break;
				case FAILURE:
					//todo add impatience
				case NO_MOVE:
				default:
					//select a new destination if we're stuck?
					//this might cause problems
					if (adjIndex > 0) {
						// TODO select a location within a certain smaller distance
						dest = Util.getRandomMapLocation(rc, rng);
					}
					break;
				}
				break;
			default:
				switch (Util.move_minrubble_direction_strict(rc, me,
						runDirection, bugDirection)) {
				case FAILURE:
				case NO_MOVE:
					runDirection = Direction.CENTER;
					break;
				case SUCCESS:
				default:
					if (runDirection != Direction.CENTER) {
						if (rng.nextDouble() < 0.4) {
							runDirection = Direction.CENTER;
						}
					}
				}
			}
		} else {
			// Otherwise, we can handle some extra comms stuff
			
			// Track our own existence in the comms channels
			if (!hasRegisteredForCount) {
				Comms.incrementMinerCount(rc);
			}
		}

		//Send messages about enemy locations
		if (Clock.getBytecodesLeft() > 300) {
			if (messagePos > 0) {
				// check that our last message hasn't been overwritten
				int readMessageTime = (rc.readSharedArray(messagePos) >> 12);
				if (readMessageTime != lastMessageTime) {
					messagePos = -1;
				} else if (enemyLocation==null && 
						readMessageTime == ((rc.getRoundNum()-1) % 15 + 1)) {
					//clear if it's been around for too long
					rc.writeSharedArray(messagePos, 0);
					messagePos = -1;
				}
			}
			if (enemyLocation != null) {
				// If we don't have a slot we've claimed, look for the oldest overwritable message
				if (messagePos < 0) {
					int best_diff = -1;
					for (int i=64; --i>=56;) {
						int val = (rc.readSharedArray(i) >> 12);
						if (val==0) { //prioritize empty slots
							val = 100;
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
					lastMessageTime = (rc.getRoundNum() % 15) + 1;
					int message = (lastMessageTime<<12)
							+ (enemyLocation.y<<6)
							+ enemyLocation.x;
					rc.writeSharedArray(messagePos, message);
				}
			}
		}
		rc.setIndicatorString(indicatorString);
	}
}
