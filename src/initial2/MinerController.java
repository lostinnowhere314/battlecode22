package initial2;

import battlecode.common.*;

public class MinerController extends Robot {

	private MapLocation dest = null;
	//private Util.RotationDirection bugDirection;
	
	public MinerController(RobotController rc) {
		super(rc);
		//bugDirection = rng.nextDouble()>0.5 ? 
		//		Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
		
		dest = Util.getRandomMapLocation(rc, rng);
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		MapLocation me = rc.getLocation();
		if (dest == null) {
			dest = Util.getRandomMapLocation(rc, rng);
		}

		//determine if there's nearby resources we want to mine; adjust target dest accordingly
		MapLocation[] lead_locs = rc.senseNearbyLocationsWithLead(24);
		MapLocation[] gold_locs = rc.senseNearbyLocationsWithGold(24);
		
		MapLocation[] adj_resources = new MapLocation[5];
		boolean[] isGold = new boolean[5];
		int adj_index = 5;

		double weights[][] = new double[11][11];
		//also should add diags really
		for(int i = gold_locs.length; --i>=0;) {
			MapLocation cl = gold_locs[i];
			int x=cl.x-me.x;
			int y=cl.y-me.y;
			int amt = rc.senseGold(cl);
			double wt = amt*10;
			weights[5+x][5+y] += wt;
			weights[4+x][5+y] += wt;
			weights[6+x][5+y] += wt;
			weights[5+x][4+y] += wt;
			weights[5+x][6+y] += wt;
			weights[4+x][4+y] += wt;
			weights[6+x][4+y] += wt;
			weights[4+x][6+y] += wt;
			weights[6+x][6+y] += wt;
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
			if (i>25) {
				i=25;
			}
			for(; --i>=0;) {
				MapLocation cl = lead_locs[i];
				int amt = rc.senseLead(cl)-1;
				if (amt>40) {
					amt=40;
				}
				if (amt > 0) {
					int x=cl.x-me.x;
					int y=cl.y-me.y;
					weights[5+x][5+y] += amt;
					weights[4+x][5+y] += amt;
					weights[6+x][5+y] += amt;
					weights[5+x][4+y] += amt;
					weights[5+x][6+y] += amt;
					if (me.isAdjacentTo(cl)) {
						for (;--amt>=0&&adj_index>0;) {
							adj_index--;
							adj_resources[adj_index] = cl;
						}
					}
				}
			}
		}
		// determine if there's robots we should avoid
		RobotInfo[] robots = rc.senseNearbyRobots(13);
		{
			//Determine if there are robots we want to avoid
			int i = robots.length;
			if (i>10) {
				i=10;
			}
			for(; --i>=0;) {
				RobotInfo robot = robots[i];
				if(rc.getTeam()==robot.getTeam()) {
					// Our team
					switch (robot.type) {
						case MINER:
						{
							int x=robot.location.x-me.x;
							int y=robot.location.y-me.y;
							weights[5+x][5+y] -= 50;
							weights[4+x][4+y] -= 20;
							weights[5+x][4+y] -= 20;
							weights[6+x][4+y] -= 20;
							weights[4+x][5+y] -= 20;
							weights[6+x][5+y] -= 20;
							weights[4+x][6+y] -= 20;
							weights[5+x][6+y] -= 20;
							weights[6+x][6+y] -= 20;
							weights[4+x][3+y] -= 15;
							weights[5+x][3+y] -= 15;
							weights[6+x][3+y] -= 15;
							weights[4+x][7+y] -= 15;
							weights[5+x][7+y] -= 15;
							weights[6+x][7+y] -= 15;
							weights[3+x][4+y] -= 15;
							weights[3+x][5+y] -= 15;
							weights[3+x][6+y] -= 15;
							weights[7+x][4+y] -= 15;
							weights[7+x][5+y] -= 15;
							weights[7+x][6+y] -= 15;
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
							int x=robot.location.x-me.x;
							int y=robot.location.y-me.y;
							weights[5+x][5+y] -= 80;
							weights[4+x][4+y] -= 80;
							weights[5+x][4+y] -= 80;
							weights[6+x][4+y] -= 80;
							weights[4+x][5+y] -= 80;
							weights[6+x][5+y] -= 80;
							weights[4+x][6+y] -= 80;
							weights[5+x][6+y] -= 80;
							weights[6+x][6+y] -= 80;
							weights[4+x][3+y] -= 80;
							weights[5+x][3+y] -= 80;
							weights[6+x][3+y] -= 80;
							weights[4+x][7+y] -= 80;
							weights[5+x][7+y] -= 80;
							weights[6+x][7+y] -= 80;
							weights[3+x][4+y] -= 80;
							weights[3+x][5+y] -= 80;
							weights[3+x][6+y] -= 80;
							weights[7+x][4+y] -= 80;
							weights[7+x][5+y] -= 80;
							weights[7+x][6+y] -= 80;
						}
							break;
						case MINER:
						/*{ //incentivize being annoying?
							int x=robot.location.x-me.x;
							int y=robot.location.y-me.y;
							weights[5+x][4+y] += 5;
							weights[5+x][6+y] += 5;
							weights[4+x][5+y] += 5;
							weights[6+x][5+y] += 5;
							weights[5+x][3+y] += 5;
							weights[5+x][7+y] += 5;
							weights[3+x][5+y] += 5;
							weights[7+x][5+y] += 5;
						}*/
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
		MapLocation bestLocation = me;
		double bestWeight = 0;
		for (int i=9;--i>=0;) {
			for (int j=9;--j>=0;) {
				double w=weights[i][j];
				// Figure out if we should check rubble
				if(w > bestWeight) {
					MapLocation location = new MapLocation(me.x+i-6, me.y+j-6);
					if (rc.canSenseLocation(location)) {
						w /= (1+0.5*rc.senseRubble(location));
						if (w > bestWeight) {
							bestWeight = w;
							bestLocation = location;
						}
					}
				}
			}
		}
		//threshold is adjustable
		if (bestWeight > 2.5) {
			dest = bestLocation;
		}
		
		//do mining stuff
		for (int i=5; rc.isActionReady() && (--i>adj_index);) {
			if (isGold[i]) {
				rc.mineGold(adj_resources[i]);
			} else {
				rc.mineLead(adj_resources[i]);
			}
		}
		
		//move.
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
		
		rc.setIndicatorLine(rc.getLocation(), dest, 0,220,240);
		
	}
	
	

}
