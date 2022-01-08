package comms_initial;

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
	static final int MAX_LEAD_DEPOSITS = 15;
	static final int MAX_ROBOTS = 13;
	static final int MAX_MINEABLE = 5;
	
	static final int UNROLLED_CENTER = 60;
	/////
	
	
	private MapLocation dest = null;
	private Util.RotationDirection bugDirection;
	
	public MinerController(RobotController rc) {
		super(rc);
		bugDirection = rng.nextDouble()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
		
		dest = Util.getRandomMapLocation(rc, rng);
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		MapLocation me = rc.getLocation();
		if (dest == null) {
			dest = Util.getRandomMapLocation(rc, rng);
		}

		//Used to determine if we want to run away from anything, or move towards resources
		Direction runDirection = Direction.CENTER;
		//determine if there's nearby resources we want to mine; adjust target dest accordingly
		MapLocation[] lead_locs = rc.senseNearbyLocationsWithLead(24);
		MapLocation[] gold_locs = rc.senseNearbyLocationsWithGold(24);
		
		MapLocation[] adj_resources = new MapLocation[MAX_MINEABLE];
		boolean[] isGold = new boolean[MAX_MINEABLE];
		int adj_index = MAX_MINEABLE;

		//Unrolled array of an 11x11 grid centered on the robot
		double weights[] = new double[121];
		//also should add diags really
		for(int i = gold_locs.length; --i>=0;) {
			MapLocation cl = gold_locs[i];
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
				for (;--amt>=0&&adj_index>0;) {
					adj_index--;
					adj_resources[adj_index] = cl;
					isGold[adj_index] = true;
				}
			}
		}
		{
			int i = lead_locs.length;
			if (i>MAX_LEAD_DEPOSITS) {
				i=MAX_LEAD_DEPOSITS;
			}
			for(; --i>=0;) {
				MapLocation cl = lead_locs[i];
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
						for (;--amt>=0&&adj_index>0;) {
							adj_index--;
							adj_resources[adj_index] = cl;
						}
					}
				}
			}
		}
		// determine if there's robots we should avoid
		RobotInfo[] robots = rc.senseNearbyRobots(ROBOT_SENSE_RANGE);
		{
			//Determine if there are robots we want to avoid
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
							break;
						case ARCHON:
						case BUILDER:
						case LABORATORY:
						default:
							break;
					}
				}
			}
		}
		rc.setIndicatorString(Integer.toString(Clock.getBytecodesLeft()));
		//find the max weight
		switch(runDirection) {
		case CENTER:
			MapLocation bestLocation = me;
			double bestWeight = 0;
			for (int i=109;--i>=11;) {
				if (i%11==0) { //skip the left- and right-most columns
					i--;
					continue;
				}
				
				double w=weights[i];
				// Figure out if we should check rubble
				if(w > bestWeight) {
					int x = i%11;
					int y = i / 11;
					MapLocation location = new MapLocation(me.x+x-6, me.y+y-6);
					if (rc.canSenseLocation(location)) {
						w /= (1+0.5*rc.senseRubble(location));
						if (w > bestWeight) {
							bestWeight = w;
							bestLocation = location;
						}
					}
				}
			}
			//threshold is adjustable
			if (bestWeight > 0.5) {
				dest = bestLocation;
			} else {
				//this needs states for it to not just wander randomly
				//dest = Util.getRandomMapLocation(rc, rng);
			}
		default:
		}
		
		rc.setIndicatorString(Integer.toString(adj_index));
		//do mining stuff
		for (int i=MAX_MINEABLE; rc.isActionReady() && (--i>=adj_index);) {
			if (isGold[i]) {
				rc.mineGold(adj_resources[i]);
			} else {
				rc.mineLead(adj_resources[i]);
			}
		}
		
		//move.
		switch(runDirection) {
		case CENTER:
			switch (Util.move_minrubble(rc, me, dest)) {
			case FAILURE:
				//todo add impatience
				break;
			case SUCCESS:
				break;
			case NO_MOVE:
			default:
				//select a new destination if we're stuck?
				//this might cause problems
				if (adj_index > 0) {
					dest = Util.getRandomMapLocation(rc, rng);
				}
				break;
			}
			break;
		default:
			switch (Util.move_minrubble_direction_strict(rc, me,
					runDirection, bugDirection)) {
			case SUCCESS:
			default:
			}
		}
		
		//rc.setIndicatorLine(rc.getLocation(), dest, 0,220,240);
		
	}
	
	

}
