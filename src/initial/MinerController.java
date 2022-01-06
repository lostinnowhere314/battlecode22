package initial;

import battlecode.common.*;

public class MinerController extends Robot {

	private MapLocation dest = null;
	private Util.RotationDirection bugDirection;
	
	public MinerController() {
		bugDirection = Math.random()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		MapLocation me = rc.getLocation();
		if (me.equals(dest) || dest == null) {
			dest = Util.getRandomMapLocation(rc);
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
			if (me.isAdjacentTo(cl)) {
				for (;--amt>=0&&adj_index>0;) {
					adj_index--;
					adj_resources[adj_index] = cl;
					isGold[adj_index] = true;
				}
			}
		}
		for(int i = lead_locs.length; --i>=0;) {
			MapLocation cl = lead_locs[i];
			int amt = rc.senseLead(cl)-1;
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
		//find the max weight
		int mx=0;
		int my=0;
		double mw = 0;
		for (int i=10;--i>=0;) {
			for (int j=10;--j>=0;) {
				double w=weights[i][j];
				if(w>mw) {
					mx=i;
					my=j;
					mw=w;
				}
			}
		}
		//threshold is adjustable
		if (mw > 0.5) {
			dest = new MapLocation(me.x+mx-5, me.y+my-5);
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
		if (!Util.move(rc, me, dest, bugDirection)) {
			//select a new destination if we're stuck
			dest = Util.getRandomMapLocation(rc);
		}
		
	}
	
	

}
