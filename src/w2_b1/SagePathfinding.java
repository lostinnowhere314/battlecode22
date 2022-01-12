package w2_b1;

import battlecode.common.*;

public class SagePathfinding {
	
	public static double dist_n5_n3;
	public static MapLocation loc_n5_n3;
	public static Direction direc_n5_n3;
	public static double pass_n5_n3;
	
	public static double dist_n5_n2;
	public static MapLocation loc_n5_n2;
	public static Direction direc_n5_n2;
	public static double pass_n5_n2;
	
	public static double dist_n5_n1;
	public static MapLocation loc_n5_n1;
	public static Direction direc_n5_n1;
	public static double pass_n5_n1;
	
	public static double dist_n5_0;
	public static MapLocation loc_n5_0;
	public static Direction direc_n5_0;
	public static double pass_n5_0;
	
	public static double dist_n5_1;
	public static MapLocation loc_n5_1;
	public static Direction direc_n5_1;
	public static double pass_n5_1;
	
	public static double dist_n5_2;
	public static MapLocation loc_n5_2;
	public static Direction direc_n5_2;
	public static double pass_n5_2;
	
	public static double dist_n5_3;
	public static MapLocation loc_n5_3;
	public static Direction direc_n5_3;
	public static double pass_n5_3;
	
	public static double dist_n4_n4;
	public static MapLocation loc_n4_n4;
	public static Direction direc_n4_n4;
	public static double pass_n4_n4;
	
	public static double dist_n4_n3;
	public static MapLocation loc_n4_n3;
	public static Direction direc_n4_n3;
	public static double pass_n4_n3;
	
	public static double dist_n4_n2;
	public static MapLocation loc_n4_n2;
	public static Direction direc_n4_n2;
	public static double pass_n4_n2;
	
	public static double dist_n4_n1;
	public static MapLocation loc_n4_n1;
	public static Direction direc_n4_n1;
	public static double pass_n4_n1;
	
	public static double dist_n4_0;
	public static MapLocation loc_n4_0;
	public static Direction direc_n4_0;
	public static double pass_n4_0;
	
	public static double dist_n4_1;
	public static MapLocation loc_n4_1;
	public static Direction direc_n4_1;
	public static double pass_n4_1;
	
	public static double dist_n4_2;
	public static MapLocation loc_n4_2;
	public static Direction direc_n4_2;
	public static double pass_n4_2;
	
	public static double dist_n4_3;
	public static MapLocation loc_n4_3;
	public static Direction direc_n4_3;
	public static double pass_n4_3;
	
	public static double dist_n4_4;
	public static MapLocation loc_n4_4;
	public static Direction direc_n4_4;
	public static double pass_n4_4;
	
	public static double dist_n3_n5;
	public static MapLocation loc_n3_n5;
	public static Direction direc_n3_n5;
	public static double pass_n3_n5;
	
	public static double dist_n3_n4;
	public static MapLocation loc_n3_n4;
	public static Direction direc_n3_n4;
	public static double pass_n3_n4;
	
	public static double dist_n3_n3;
	public static MapLocation loc_n3_n3;
	public static Direction direc_n3_n3;
	public static double pass_n3_n3;
	
	public static double dist_n3_n2;
	public static MapLocation loc_n3_n2;
	public static Direction direc_n3_n2;
	public static double pass_n3_n2;
	
	public static double dist_n3_n1;
	public static MapLocation loc_n3_n1;
	public static Direction direc_n3_n1;
	public static double pass_n3_n1;
	
	public static double dist_n3_0;
	public static MapLocation loc_n3_0;
	public static Direction direc_n3_0;
	public static double pass_n3_0;
	
	public static double dist_n3_1;
	public static MapLocation loc_n3_1;
	public static Direction direc_n3_1;
	public static double pass_n3_1;
	
	public static double dist_n3_2;
	public static MapLocation loc_n3_2;
	public static Direction direc_n3_2;
	public static double pass_n3_2;
	
	public static double dist_n3_3;
	public static MapLocation loc_n3_3;
	public static Direction direc_n3_3;
	public static double pass_n3_3;
	
	public static double dist_n3_4;
	public static MapLocation loc_n3_4;
	public static Direction direc_n3_4;
	public static double pass_n3_4;
	
	public static double dist_n3_5;
	public static MapLocation loc_n3_5;
	public static Direction direc_n3_5;
	public static double pass_n3_5;
	
	public static double dist_n2_n5;
	public static MapLocation loc_n2_n5;
	public static Direction direc_n2_n5;
	public static double pass_n2_n5;
	
	public static double dist_n2_n4;
	public static MapLocation loc_n2_n4;
	public static Direction direc_n2_n4;
	public static double pass_n2_n4;
	
	public static double dist_n2_n3;
	public static MapLocation loc_n2_n3;
	public static Direction direc_n2_n3;
	public static double pass_n2_n3;
	
	public static double dist_n2_n2;
	public static MapLocation loc_n2_n2;
	public static Direction direc_n2_n2;
	public static double pass_n2_n2;
	
	public static double dist_n2_n1;
	public static MapLocation loc_n2_n1;
	public static Direction direc_n2_n1;
	public static double pass_n2_n1;
	
	public static double dist_n2_0;
	public static MapLocation loc_n2_0;
	public static Direction direc_n2_0;
	public static double pass_n2_0;
	
	public static double dist_n2_1;
	public static MapLocation loc_n2_1;
	public static Direction direc_n2_1;
	public static double pass_n2_1;
	
	public static double dist_n2_2;
	public static MapLocation loc_n2_2;
	public static Direction direc_n2_2;
	public static double pass_n2_2;
	
	public static double dist_n2_3;
	public static MapLocation loc_n2_3;
	public static Direction direc_n2_3;
	public static double pass_n2_3;
	
	public static double dist_n2_4;
	public static MapLocation loc_n2_4;
	public static Direction direc_n2_4;
	public static double pass_n2_4;
	
	public static double dist_n2_5;
	public static MapLocation loc_n2_5;
	public static Direction direc_n2_5;
	public static double pass_n2_5;
	
	public static double dist_n1_n5;
	public static MapLocation loc_n1_n5;
	public static Direction direc_n1_n5;
	public static double pass_n1_n5;
	
	public static double dist_n1_n4;
	public static MapLocation loc_n1_n4;
	public static Direction direc_n1_n4;
	public static double pass_n1_n4;
	
	public static double dist_n1_n3;
	public static MapLocation loc_n1_n3;
	public static Direction direc_n1_n3;
	public static double pass_n1_n3;
	
	public static double dist_n1_n2;
	public static MapLocation loc_n1_n2;
	public static Direction direc_n1_n2;
	public static double pass_n1_n2;
	
	public static double dist_n1_n1;
	public static MapLocation loc_n1_n1;
	public static Direction direc_n1_n1;
	
	public static double dist_n1_0;
	public static MapLocation loc_n1_0;
	public static Direction direc_n1_0;
	
	public static double dist_n1_1;
	public static MapLocation loc_n1_1;
	public static Direction direc_n1_1;
	
	public static double dist_n1_2;
	public static MapLocation loc_n1_2;
	public static Direction direc_n1_2;
	public static double pass_n1_2;
	
	public static double dist_n1_3;
	public static MapLocation loc_n1_3;
	public static Direction direc_n1_3;
	public static double pass_n1_3;
	
	public static double dist_n1_4;
	public static MapLocation loc_n1_4;
	public static Direction direc_n1_4;
	public static double pass_n1_4;
	
	public static double dist_n1_5;
	public static MapLocation loc_n1_5;
	public static Direction direc_n1_5;
	public static double pass_n1_5;
	
	public static double dist_0_n5;
	public static MapLocation loc_0_n5;
	public static Direction direc_0_n5;
	public static double pass_0_n5;
	
	public static double dist_0_n4;
	public static MapLocation loc_0_n4;
	public static Direction direc_0_n4;
	public static double pass_0_n4;
	
	public static double dist_0_n3;
	public static MapLocation loc_0_n3;
	public static Direction direc_0_n3;
	public static double pass_0_n3;
	
	public static double dist_0_n2;
	public static MapLocation loc_0_n2;
	public static Direction direc_0_n2;
	public static double pass_0_n2;
	
	public static double dist_0_n1;
	public static MapLocation loc_0_n1;
	public static Direction direc_0_n1;
	
	public static double dist_0_1;
	public static MapLocation loc_0_1;
	public static Direction direc_0_1;
	
	public static double dist_0_2;
	public static MapLocation loc_0_2;
	public static Direction direc_0_2;
	public static double pass_0_2;
	
	public static double dist_0_3;
	public static MapLocation loc_0_3;
	public static Direction direc_0_3;
	public static double pass_0_3;
	
	public static double dist_0_4;
	public static MapLocation loc_0_4;
	public static Direction direc_0_4;
	public static double pass_0_4;
	
	public static double dist_0_5;
	public static MapLocation loc_0_5;
	public static Direction direc_0_5;
	public static double pass_0_5;
	
	public static double dist_1_n5;
	public static MapLocation loc_1_n5;
	public static Direction direc_1_n5;
	public static double pass_1_n5;
	
	public static double dist_1_n4;
	public static MapLocation loc_1_n4;
	public static Direction direc_1_n4;
	public static double pass_1_n4;
	
	public static double dist_1_n3;
	public static MapLocation loc_1_n3;
	public static Direction direc_1_n3;
	public static double pass_1_n3;
	
	public static double dist_1_n2;
	public static MapLocation loc_1_n2;
	public static Direction direc_1_n2;
	public static double pass_1_n2;
	
	public static double dist_1_n1;
	public static MapLocation loc_1_n1;
	public static Direction direc_1_n1;
	
	public static double dist_1_0;
	public static MapLocation loc_1_0;
	public static Direction direc_1_0;
	
	public static double dist_1_1;
	public static MapLocation loc_1_1;
	public static Direction direc_1_1;
	
	public static double dist_1_2;
	public static MapLocation loc_1_2;
	public static Direction direc_1_2;
	public static double pass_1_2;
	
	public static double dist_1_3;
	public static MapLocation loc_1_3;
	public static Direction direc_1_3;
	public static double pass_1_3;
	
	public static double dist_1_4;
	public static MapLocation loc_1_4;
	public static Direction direc_1_4;
	public static double pass_1_4;
	
	public static double dist_1_5;
	public static MapLocation loc_1_5;
	public static Direction direc_1_5;
	public static double pass_1_5;
	
	public static double dist_2_n5;
	public static MapLocation loc_2_n5;
	public static Direction direc_2_n5;
	public static double pass_2_n5;
	
	public static double dist_2_n4;
	public static MapLocation loc_2_n4;
	public static Direction direc_2_n4;
	public static double pass_2_n4;
	
	public static double dist_2_n3;
	public static MapLocation loc_2_n3;
	public static Direction direc_2_n3;
	public static double pass_2_n3;
	
	public static double dist_2_n2;
	public static MapLocation loc_2_n2;
	public static Direction direc_2_n2;
	public static double pass_2_n2;
	
	public static double dist_2_n1;
	public static MapLocation loc_2_n1;
	public static Direction direc_2_n1;
	public static double pass_2_n1;
	
	public static double dist_2_0;
	public static MapLocation loc_2_0;
	public static Direction direc_2_0;
	public static double pass_2_0;
	
	public static double dist_2_1;
	public static MapLocation loc_2_1;
	public static Direction direc_2_1;
	public static double pass_2_1;
	
	public static double dist_2_2;
	public static MapLocation loc_2_2;
	public static Direction direc_2_2;
	public static double pass_2_2;
	
	public static double dist_2_3;
	public static MapLocation loc_2_3;
	public static Direction direc_2_3;
	public static double pass_2_3;
	
	public static double dist_2_4;
	public static MapLocation loc_2_4;
	public static Direction direc_2_4;
	public static double pass_2_4;
	
	public static double dist_2_5;
	public static MapLocation loc_2_5;
	public static Direction direc_2_5;
	public static double pass_2_5;
	
	public static double dist_3_n5;
	public static MapLocation loc_3_n5;
	public static Direction direc_3_n5;
	public static double pass_3_n5;
	
	public static double dist_3_n4;
	public static MapLocation loc_3_n4;
	public static Direction direc_3_n4;
	public static double pass_3_n4;
	
	public static double dist_3_n3;
	public static MapLocation loc_3_n3;
	public static Direction direc_3_n3;
	public static double pass_3_n3;
	
	public static double dist_3_n2;
	public static MapLocation loc_3_n2;
	public static Direction direc_3_n2;
	public static double pass_3_n2;
	
	public static double dist_3_n1;
	public static MapLocation loc_3_n1;
	public static Direction direc_3_n1;
	public static double pass_3_n1;
	
	public static double dist_3_0;
	public static MapLocation loc_3_0;
	public static Direction direc_3_0;
	public static double pass_3_0;
	
	public static double dist_3_1;
	public static MapLocation loc_3_1;
	public static Direction direc_3_1;
	public static double pass_3_1;
	
	public static double dist_3_2;
	public static MapLocation loc_3_2;
	public static Direction direc_3_2;
	public static double pass_3_2;
	
	public static double dist_3_3;
	public static MapLocation loc_3_3;
	public static Direction direc_3_3;
	public static double pass_3_3;
	
	public static double dist_3_4;
	public static MapLocation loc_3_4;
	public static Direction direc_3_4;
	public static double pass_3_4;
	
	public static double dist_3_5;
	public static MapLocation loc_3_5;
	public static Direction direc_3_5;
	public static double pass_3_5;
	
	public static double dist_4_n4;
	public static MapLocation loc_4_n4;
	public static Direction direc_4_n4;
	public static double pass_4_n4;
	
	public static double dist_4_n3;
	public static MapLocation loc_4_n3;
	public static Direction direc_4_n3;
	public static double pass_4_n3;
	
	public static double dist_4_n2;
	public static MapLocation loc_4_n2;
	public static Direction direc_4_n2;
	public static double pass_4_n2;
	
	public static double dist_4_n1;
	public static MapLocation loc_4_n1;
	public static Direction direc_4_n1;
	public static double pass_4_n1;
	
	public static double dist_4_0;
	public static MapLocation loc_4_0;
	public static Direction direc_4_0;
	public static double pass_4_0;
	
	public static double dist_4_1;
	public static MapLocation loc_4_1;
	public static Direction direc_4_1;
	public static double pass_4_1;
	
	public static double dist_4_2;
	public static MapLocation loc_4_2;
	public static Direction direc_4_2;
	public static double pass_4_2;
	
	public static double dist_4_3;
	public static MapLocation loc_4_3;
	public static Direction direc_4_3;
	public static double pass_4_3;
	
	public static double dist_4_4;
	public static MapLocation loc_4_4;
	public static Direction direc_4_4;
	public static double pass_4_4;
	
	public static double dist_5_n3;
	public static MapLocation loc_5_n3;
	public static Direction direc_5_n3;
	public static double pass_5_n3;
	
	public static double dist_5_n2;
	public static MapLocation loc_5_n2;
	public static Direction direc_5_n2;
	public static double pass_5_n2;
	
	public static double dist_5_n1;
	public static MapLocation loc_5_n1;
	public static Direction direc_5_n1;
	public static double pass_5_n1;
	
	public static double dist_5_0;
	public static MapLocation loc_5_0;
	public static Direction direc_5_0;
	public static double pass_5_0;
	
	public static double dist_5_1;
	public static MapLocation loc_5_1;
	public static Direction direc_5_1;
	public static double pass_5_1;
	
	public static double dist_5_2;
	public static MapLocation loc_5_2;
	public static Direction direc_5_2;
	public static double pass_5_2;
	
	public static double dist_5_3;
	public static MapLocation loc_5_3;
	public static Direction direc_5_3;
	public static double pass_5_3;
	
	public static double bestDistance;
	public static double currentDistance;
	public static Direction bestDirection;
	
	public static Direction getBestDirection(RobotController rc, MapLocation dest, Direction lastMoveDirection) throws GameActionException {
		
		MapLocation me = rc.getLocation();
		
		if (me.equals(dest)) {
			return null;
		}
		
		if (me.isAdjacentTo(dest)) {
			if (!rc.canSenseRobotAtLocation(dest)) {
				return me.directionTo(dest);
			} else {
				return null;
			}
		}
		
		dist_n5_n3 = 100000;
		loc_n5_n3 = me.translate(-5,-3);
		direc_n5_n3 = null;
		
		dist_n5_n2 = 100000;
		loc_n5_n2 = me.translate(-5,-2);
		direc_n5_n2 = null;
		
		dist_n5_n1 = 100000;
		loc_n5_n1 = me.translate(-5,-1);
		direc_n5_n1 = null;
		
		dist_n5_0 = 100000;
		loc_n5_0 = me.translate(-5,0);
		direc_n5_0 = null;
		
		dist_n5_1 = 100000;
		loc_n5_1 = me.translate(-5,1);
		direc_n5_1 = null;
		
		dist_n5_2 = 100000;
		loc_n5_2 = me.translate(-5,2);
		direc_n5_2 = null;
		
		dist_n5_3 = 100000;
		loc_n5_3 = me.translate(-5,3);
		direc_n5_3 = null;
		
		dist_n4_n4 = 100000;
		loc_n4_n4 = me.translate(-4,-4);
		direc_n4_n4 = null;
		
		dist_n4_n3 = 100000;
		loc_n4_n3 = me.translate(-4,-3);
		direc_n4_n3 = null;
		
		dist_n4_n2 = 100000;
		loc_n4_n2 = me.translate(-4,-2);
		direc_n4_n2 = null;
		
		dist_n4_n1 = 100000;
		loc_n4_n1 = me.translate(-4,-1);
		direc_n4_n1 = null;
		
		dist_n4_0 = 100000;
		loc_n4_0 = me.translate(-4,0);
		direc_n4_0 = null;
		
		dist_n4_1 = 100000;
		loc_n4_1 = me.translate(-4,1);
		direc_n4_1 = null;
		
		dist_n4_2 = 100000;
		loc_n4_2 = me.translate(-4,2);
		direc_n4_2 = null;
		
		dist_n4_3 = 100000;
		loc_n4_3 = me.translate(-4,3);
		direc_n4_3 = null;
		
		dist_n4_4 = 100000;
		loc_n4_4 = me.translate(-4,4);
		direc_n4_4 = null;
		
		dist_n3_n5 = 100000;
		loc_n3_n5 = me.translate(-3,-5);
		direc_n3_n5 = null;
		
		dist_n3_n4 = 100000;
		loc_n3_n4 = me.translate(-3,-4);
		direc_n3_n4 = null;
		
		dist_n3_n3 = 100000;
		loc_n3_n3 = me.translate(-3,-3);
		direc_n3_n3 = null;
		
		dist_n3_n2 = 100000;
		loc_n3_n2 = me.translate(-3,-2);
		direc_n3_n2 = null;
		
		dist_n3_n1 = 100000;
		loc_n3_n1 = me.translate(-3,-1);
		direc_n3_n1 = null;
		
		dist_n3_0 = 100000;
		loc_n3_0 = me.translate(-3,0);
		direc_n3_0 = null;
		
		dist_n3_1 = 100000;
		loc_n3_1 = me.translate(-3,1);
		direc_n3_1 = null;
		
		dist_n3_2 = 100000;
		loc_n3_2 = me.translate(-3,2);
		direc_n3_2 = null;
		
		dist_n3_3 = 100000;
		loc_n3_3 = me.translate(-3,3);
		direc_n3_3 = null;
		
		dist_n3_4 = 100000;
		loc_n3_4 = me.translate(-3,4);
		direc_n3_4 = null;
		
		dist_n3_5 = 100000;
		loc_n3_5 = me.translate(-3,5);
		direc_n3_5 = null;
		
		dist_n2_n5 = 100000;
		loc_n2_n5 = me.translate(-2,-5);
		direc_n2_n5 = null;
		
		dist_n2_n4 = 100000;
		loc_n2_n4 = me.translate(-2,-4);
		direc_n2_n4 = null;
		
		dist_n2_n3 = 100000;
		loc_n2_n3 = me.translate(-2,-3);
		direc_n2_n3 = null;
		
		dist_n2_n2 = 100000;
		loc_n2_n2 = me.translate(-2,-2);
		direc_n2_n2 = null;
		
		dist_n2_n1 = 100000;
		loc_n2_n1 = me.translate(-2,-1);
		direc_n2_n1 = null;
		
		dist_n2_0 = 100000;
		loc_n2_0 = me.translate(-2,0);
		direc_n2_0 = null;
		
		dist_n2_1 = 100000;
		loc_n2_1 = me.translate(-2,1);
		direc_n2_1 = null;
		
		dist_n2_2 = 100000;
		loc_n2_2 = me.translate(-2,2);
		direc_n2_2 = null;
		
		dist_n2_3 = 100000;
		loc_n2_3 = me.translate(-2,3);
		direc_n2_3 = null;
		
		dist_n2_4 = 100000;
		loc_n2_4 = me.translate(-2,4);
		direc_n2_4 = null;
		
		dist_n2_5 = 100000;
		loc_n2_5 = me.translate(-2,5);
		direc_n2_5 = null;
		
		dist_n1_n5 = 100000;
		loc_n1_n5 = me.translate(-1,-5);
		direc_n1_n5 = null;
		
		dist_n1_n4 = 100000;
		loc_n1_n4 = me.translate(-1,-4);
		direc_n1_n4 = null;
		
		dist_n1_n3 = 100000;
		loc_n1_n3 = me.translate(-1,-3);
		direc_n1_n3 = null;
		
		dist_n1_n2 = 100000;
		loc_n1_n2 = me.translate(-1,-2);
		direc_n1_n2 = null;
		
		dist_n1_n1 = 100000;
		loc_n1_n1 = me.translate(-1,-1);
		direc_n1_n1 = null;
		
		dist_n1_0 = 100000;
		loc_n1_0 = me.translate(-1,0);
		direc_n1_0 = null;
		
		dist_n1_1 = 100000;
		loc_n1_1 = me.translate(-1,1);
		direc_n1_1 = null;
		
		dist_n1_2 = 100000;
		loc_n1_2 = me.translate(-1,2);
		direc_n1_2 = null;
		
		dist_n1_3 = 100000;
		loc_n1_3 = me.translate(-1,3);
		direc_n1_3 = null;
		
		dist_n1_4 = 100000;
		loc_n1_4 = me.translate(-1,4);
		direc_n1_4 = null;
		
		dist_n1_5 = 100000;
		loc_n1_5 = me.translate(-1,5);
		direc_n1_5 = null;
		
		dist_0_n5 = 100000;
		loc_0_n5 = me.translate(0,-5);
		direc_0_n5 = null;
		
		dist_0_n4 = 100000;
		loc_0_n4 = me.translate(0,-4);
		direc_0_n4 = null;
		
		dist_0_n3 = 100000;
		loc_0_n3 = me.translate(0,-3);
		direc_0_n3 = null;
		
		dist_0_n2 = 100000;
		loc_0_n2 = me.translate(0,-2);
		direc_0_n2 = null;
		
		dist_0_n1 = 100000;
		loc_0_n1 = me.translate(0,-1);
		direc_0_n1 = null;
		
		dist_0_1 = 100000;
		loc_0_1 = me.translate(0,1);
		direc_0_1 = null;
		
		dist_0_2 = 100000;
		loc_0_2 = me.translate(0,2);
		direc_0_2 = null;
		
		dist_0_3 = 100000;
		loc_0_3 = me.translate(0,3);
		direc_0_3 = null;
		
		dist_0_4 = 100000;
		loc_0_4 = me.translate(0,4);
		direc_0_4 = null;
		
		dist_0_5 = 100000;
		loc_0_5 = me.translate(0,5);
		direc_0_5 = null;
		
		dist_1_n5 = 100000;
		loc_1_n5 = me.translate(1,-5);
		direc_1_n5 = null;
		
		dist_1_n4 = 100000;
		loc_1_n4 = me.translate(1,-4);
		direc_1_n4 = null;
		
		dist_1_n3 = 100000;
		loc_1_n3 = me.translate(1,-3);
		direc_1_n3 = null;
		
		dist_1_n2 = 100000;
		loc_1_n2 = me.translate(1,-2);
		direc_1_n2 = null;
		
		dist_1_n1 = 100000;
		loc_1_n1 = me.translate(1,-1);
		direc_1_n1 = null;
		
		dist_1_0 = 100000;
		loc_1_0 = me.translate(1,0);
		direc_1_0 = null;
		
		dist_1_1 = 100000;
		loc_1_1 = me.translate(1,1);
		direc_1_1 = null;
		
		dist_1_2 = 100000;
		loc_1_2 = me.translate(1,2);
		direc_1_2 = null;
		
		dist_1_3 = 100000;
		loc_1_3 = me.translate(1,3);
		direc_1_3 = null;
		
		dist_1_4 = 100000;
		loc_1_4 = me.translate(1,4);
		direc_1_4 = null;
		
		dist_1_5 = 100000;
		loc_1_5 = me.translate(1,5);
		direc_1_5 = null;
		
		dist_2_n5 = 100000;
		loc_2_n5 = me.translate(2,-5);
		direc_2_n5 = null;
		
		dist_2_n4 = 100000;
		loc_2_n4 = me.translate(2,-4);
		direc_2_n4 = null;
		
		dist_2_n3 = 100000;
		loc_2_n3 = me.translate(2,-3);
		direc_2_n3 = null;
		
		dist_2_n2 = 100000;
		loc_2_n2 = me.translate(2,-2);
		direc_2_n2 = null;
		
		dist_2_n1 = 100000;
		loc_2_n1 = me.translate(2,-1);
		direc_2_n1 = null;
		
		dist_2_0 = 100000;
		loc_2_0 = me.translate(2,0);
		direc_2_0 = null;
		
		dist_2_1 = 100000;
		loc_2_1 = me.translate(2,1);
		direc_2_1 = null;
		
		dist_2_2 = 100000;
		loc_2_2 = me.translate(2,2);
		direc_2_2 = null;
		
		dist_2_3 = 100000;
		loc_2_3 = me.translate(2,3);
		direc_2_3 = null;
		
		dist_2_4 = 100000;
		loc_2_4 = me.translate(2,4);
		direc_2_4 = null;
		
		dist_2_5 = 100000;
		loc_2_5 = me.translate(2,5);
		direc_2_5 = null;
		
		dist_3_n5 = 100000;
		loc_3_n5 = me.translate(3,-5);
		direc_3_n5 = null;
		
		dist_3_n4 = 100000;
		loc_3_n4 = me.translate(3,-4);
		direc_3_n4 = null;
		
		dist_3_n3 = 100000;
		loc_3_n3 = me.translate(3,-3);
		direc_3_n3 = null;
		
		dist_3_n2 = 100000;
		loc_3_n2 = me.translate(3,-2);
		direc_3_n2 = null;
		
		dist_3_n1 = 100000;
		loc_3_n1 = me.translate(3,-1);
		direc_3_n1 = null;
		
		dist_3_0 = 100000;
		loc_3_0 = me.translate(3,0);
		direc_3_0 = null;
		
		dist_3_1 = 100000;
		loc_3_1 = me.translate(3,1);
		direc_3_1 = null;
		
		dist_3_2 = 100000;
		loc_3_2 = me.translate(3,2);
		direc_3_2 = null;
		
		dist_3_3 = 100000;
		loc_3_3 = me.translate(3,3);
		direc_3_3 = null;
		
		dist_3_4 = 100000;
		loc_3_4 = me.translate(3,4);
		direc_3_4 = null;
		
		dist_3_5 = 100000;
		loc_3_5 = me.translate(3,5);
		direc_3_5 = null;
		
		dist_4_n4 = 100000;
		loc_4_n4 = me.translate(4,-4);
		direc_4_n4 = null;
		
		dist_4_n3 = 100000;
		loc_4_n3 = me.translate(4,-3);
		direc_4_n3 = null;
		
		dist_4_n2 = 100000;
		loc_4_n2 = me.translate(4,-2);
		direc_4_n2 = null;
		
		dist_4_n1 = 100000;
		loc_4_n1 = me.translate(4,-1);
		direc_4_n1 = null;
		
		dist_4_0 = 100000;
		loc_4_0 = me.translate(4,0);
		direc_4_0 = null;
		
		dist_4_1 = 100000;
		loc_4_1 = me.translate(4,1);
		direc_4_1 = null;
		
		dist_4_2 = 100000;
		loc_4_2 = me.translate(4,2);
		direc_4_2 = null;
		
		dist_4_3 = 100000;
		loc_4_3 = me.translate(4,3);
		direc_4_3 = null;
		
		dist_4_4 = 100000;
		loc_4_4 = me.translate(4,4);
		direc_4_4 = null;
		
		dist_5_n3 = 100000;
		loc_5_n3 = me.translate(5,-3);
		direc_5_n3 = null;
		
		dist_5_n2 = 100000;
		loc_5_n2 = me.translate(5,-2);
		direc_5_n2 = null;
		
		dist_5_n1 = 100000;
		loc_5_n1 = me.translate(5,-1);
		direc_5_n1 = null;
		
		dist_5_0 = 100000;
		loc_5_0 = me.translate(5,0);
		direc_5_0 = null;
		
		dist_5_1 = 100000;
		loc_5_1 = me.translate(5,1);
		direc_5_1 = null;
		
		dist_5_2 = 100000;
		loc_5_2 = me.translate(5,2);
		direc_5_2 = null;
		
		dist_5_3 = 100000;
		loc_5_3 = me.translate(5,3);
		direc_5_3 = null;
		
		
		if (rc.canSenseLocation(loc_n1_0)) {
			if (!rc.isLocationOccupied(loc_n1_0)) {
				dist_n1_0 = 2+rc.senseRubble(loc_n1_0);
				direc_n1_0 = Direction.WEST;
			}
		}
		
		if (rc.canSenseLocation(loc_0_n1)) {
			if (!rc.isLocationOccupied(loc_0_n1)) {
				dist_0_n1 = 2+rc.senseRubble(loc_0_n1);
				direc_0_n1 = Direction.SOUTH;
			}
		}
		
		if (rc.canSenseLocation(loc_0_1)) {
			if (!rc.isLocationOccupied(loc_0_1)) {
				dist_0_1 = 2+rc.senseRubble(loc_0_1);
				direc_0_1 = Direction.NORTH;
			}
		}
		
		if (rc.canSenseLocation(loc_1_0)) {
			if (!rc.isLocationOccupied(loc_1_0)) {
				dist_1_0 = 2+rc.senseRubble(loc_1_0);
				direc_1_0 = Direction.EAST;
			}
		}
		
		if (rc.canSenseLocation(loc_n1_n1)) {
			if (!rc.isLocationOccupied(loc_n1_n1)) {
				dist_n1_n1 = 2+rc.senseRubble(loc_n1_n1);
				direc_n1_n1 = Direction.SOUTHWEST;
			}
		}
		
		if (rc.canSenseLocation(loc_n1_1)) {
			if (!rc.isLocationOccupied(loc_n1_1)) {
				dist_n1_1 = 2+rc.senseRubble(loc_n1_1);
				direc_n1_1 = Direction.NORTHWEST;
			}
		}
		
		if (rc.canSenseLocation(loc_1_n1)) {
			if (!rc.isLocationOccupied(loc_1_n1)) {
				dist_1_n1 = 2+rc.senseRubble(loc_1_n1);
				direc_1_n1 = Direction.SOUTHEAST;
			}
		}
		
		if (rc.canSenseLocation(loc_1_1)) {
			if (!rc.isLocationOccupied(loc_1_1)) {
				dist_1_1 = 2+rc.senseRubble(loc_1_1);
				direc_1_1 = Direction.NORTHEAST;
			}
		}
		
			
			switch (lastMoveDirection) {
				case NORTH:
					dist_0_n1 = 100000;
					dist_1_n1 = 100000;
					dist_n1_n1 = 100000;
					break;
				case NORTHEAST:
					dist_n1_n1 = 100000;
					dist_0_n1 = 100000;
					dist_n1_0 = 100000;
					break;
				case EAST:
					dist_n1_0 = 100000;
					dist_n1_n1 = 100000;
					dist_n1_1 = 100000;
					break;
				case SOUTHEAST:
					dist_n1_1 = 100000;
					dist_n1_0 = 100000;
					dist_0_1 = 100000;
					break;
				case SOUTH:
					dist_0_1 = 100000;
					dist_n1_1 = 100000;
					dist_1_1 = 100000;
					break;
				case SOUTHWEST:
					dist_1_1 = 100000;
					dist_0_1 = 100000;
					dist_1_0 = 100000;
					break;
				case WEST:
					dist_1_0 = 100000;
					dist_1_1 = 100000;
					dist_1_n1 = 100000;
					break;
				case NORTHWEST:
					dist_1_n1 = 100000;
					dist_1_0 = 100000;
					dist_0_n1 = 100000;
					break;
				default:
					break;
			}
			
		if (rc.canSenseLocation(loc_n2_0)) {
			pass_n2_0 = 10+rc.senseRubble(loc_n2_0);
			if (dist_n2_0 > dist_n1_1 + pass_n2_0) {
				dist_n2_0 = dist_n1_1 + pass_n2_0;
				direc_n2_0 = direc_n1_1;
			}
			
			if (dist_n2_0 > dist_n1_0 + pass_n2_0) {
				dist_n2_0 = dist_n1_0 + pass_n2_0;
				direc_n2_0 = direc_n1_0;
			}
			
			if (dist_n2_0 > dist_n1_n1 + pass_n2_0) {
				dist_n2_0 = dist_n1_n1 + pass_n2_0;
				direc_n2_0 = direc_n1_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_0_n2)) {
			pass_0_n2 = 10+rc.senseRubble(loc_0_n2);
			if (dist_0_n2 > dist_0_n1 + pass_0_n2) {
				dist_0_n2 = dist_0_n1 + pass_0_n2;
				direc_0_n2 = direc_0_n1;
			}
			
			if (dist_0_n2 > dist_1_n1 + pass_0_n2) {
				dist_0_n2 = dist_1_n1 + pass_0_n2;
				direc_0_n2 = direc_1_n1;
			}
			
			if (dist_0_n2 > dist_n1_n1 + pass_0_n2) {
				dist_0_n2 = dist_n1_n1 + pass_0_n2;
				direc_0_n2 = direc_n1_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_0_2)) {
			pass_0_2 = 10+rc.senseRubble(loc_0_2);
			if (dist_0_2 > dist_1_1 + pass_0_2) {
				dist_0_2 = dist_1_1 + pass_0_2;
				direc_0_2 = direc_1_1;
			}
			
			if (dist_0_2 > dist_0_1 + pass_0_2) {
				dist_0_2 = dist_0_1 + pass_0_2;
				direc_0_2 = direc_0_1;
			}
			
			if (dist_0_2 > dist_n1_1 + pass_0_2) {
				dist_0_2 = dist_n1_1 + pass_0_2;
				direc_0_2 = direc_n1_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_0)) {
			pass_2_0 = 10+rc.senseRubble(loc_2_0);
			if (dist_2_0 > dist_1_n1 + pass_2_0) {
				dist_2_0 = dist_1_n1 + pass_2_0;
				direc_2_0 = direc_1_n1;
			}
			
			if (dist_2_0 > dist_1_0 + pass_2_0) {
				dist_2_0 = dist_1_0 + pass_2_0;
				direc_2_0 = direc_1_0;
			}
			
			if (dist_2_0 > dist_1_1 + pass_2_0) {
				dist_2_0 = dist_1_1 + pass_2_0;
				direc_2_0 = direc_1_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_n1)) {
			pass_n2_n1 = 10+rc.senseRubble(loc_n2_n1);
			if (dist_n2_n1 > dist_n2_0 + pass_n2_n1) {
				dist_n2_n1 = dist_n2_0 + pass_n2_n1;
				direc_n2_n1 = direc_n2_0;
			}
			
			if (dist_n2_n1 > dist_n1_0 + pass_n2_n1) {
				dist_n2_n1 = dist_n1_0 + pass_n2_n1;
				direc_n2_n1 = direc_n1_0;
			}
			
			if (dist_n2_n1 > dist_n1_n1 + pass_n2_n1) {
				dist_n2_n1 = dist_n1_n1 + pass_n2_n1;
				direc_n2_n1 = direc_n1_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_1)) {
			pass_n2_1 = 10+rc.senseRubble(loc_n2_1);
			if (dist_n2_1 > dist_n1_1 + pass_n2_1) {
				dist_n2_1 = dist_n1_1 + pass_n2_1;
				direc_n2_1 = direc_n1_1;
			}
			
			if (dist_n2_1 > dist_n1_0 + pass_n2_1) {
				dist_n2_1 = dist_n1_0 + pass_n2_1;
				direc_n2_1 = direc_n1_0;
			}
			
			if (dist_n2_1 > dist_n2_0 + pass_n2_1) {
				dist_n2_1 = dist_n2_0 + pass_n2_1;
				direc_n2_1 = direc_n2_0;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n1_n2)) {
			pass_n1_n2 = 10+rc.senseRubble(loc_n1_n2);
			if (dist_n1_n2 > dist_n1_n1 + pass_n1_n2) {
				dist_n1_n2 = dist_n1_n1 + pass_n1_n2;
				direc_n1_n2 = direc_n1_n1;
			}
			
			if (dist_n1_n2 > dist_0_n1 + pass_n1_n2) {
				dist_n1_n2 = dist_0_n1 + pass_n1_n2;
				direc_n1_n2 = direc_0_n1;
			}
			
			if (dist_n1_n2 > dist_0_n2 + pass_n1_n2) {
				dist_n1_n2 = dist_0_n2 + pass_n1_n2;
				direc_n1_n2 = direc_0_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n1_2)) {
			pass_n1_2 = 10+rc.senseRubble(loc_n1_2);
			if (dist_n1_2 > dist_0_2 + pass_n1_2) {
				dist_n1_2 = dist_0_2 + pass_n1_2;
				direc_n1_2 = direc_0_2;
			}
			
			if (dist_n1_2 > dist_0_1 + pass_n1_2) {
				dist_n1_2 = dist_0_1 + pass_n1_2;
				direc_n1_2 = direc_0_1;
			}
			
			if (dist_n1_2 > dist_n1_1 + pass_n1_2) {
				dist_n1_2 = dist_n1_1 + pass_n1_2;
				direc_n1_2 = direc_n1_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_1_n2)) {
			pass_1_n2 = 10+rc.senseRubble(loc_1_n2);
			if (dist_1_n2 > dist_1_n1 + pass_1_n2) {
				dist_1_n2 = dist_1_n1 + pass_1_n2;
				direc_1_n2 = direc_1_n1;
			}
			
			if (dist_1_n2 > dist_0_n2 + pass_1_n2) {
				dist_1_n2 = dist_0_n2 + pass_1_n2;
				direc_1_n2 = direc_0_n2;
			}
			
			if (dist_1_n2 > dist_0_n1 + pass_1_n2) {
				dist_1_n2 = dist_0_n1 + pass_1_n2;
				direc_1_n2 = direc_0_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_1_2)) {
			pass_1_2 = 10+rc.senseRubble(loc_1_2);
			if (dist_1_2 > dist_1_1 + pass_1_2) {
				dist_1_2 = dist_1_1 + pass_1_2;
				direc_1_2 = direc_1_1;
			}
			
			if (dist_1_2 > dist_0_1 + pass_1_2) {
				dist_1_2 = dist_0_1 + pass_1_2;
				direc_1_2 = direc_0_1;
			}
			
			if (dist_1_2 > dist_0_2 + pass_1_2) {
				dist_1_2 = dist_0_2 + pass_1_2;
				direc_1_2 = direc_0_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_n1)) {
			pass_2_n1 = 10+rc.senseRubble(loc_2_n1);
			if (dist_2_n1 > dist_2_0 + pass_2_n1) {
				dist_2_n1 = dist_2_0 + pass_2_n1;
				direc_2_n1 = direc_2_0;
			}
			
			if (dist_2_n1 > dist_1_n1 + pass_2_n1) {
				dist_2_n1 = dist_1_n1 + pass_2_n1;
				direc_2_n1 = direc_1_n1;
			}
			
			if (dist_2_n1 > dist_1_0 + pass_2_n1) {
				dist_2_n1 = dist_1_0 + pass_2_n1;
				direc_2_n1 = direc_1_0;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_1)) {
			pass_2_1 = 10+rc.senseRubble(loc_2_1);
			if (dist_2_1 > dist_2_0 + pass_2_1) {
				dist_2_1 = dist_2_0 + pass_2_1;
				direc_2_1 = direc_2_0;
			}
			
			if (dist_2_1 > dist_1_0 + pass_2_1) {
				dist_2_1 = dist_1_0 + pass_2_1;
				direc_2_1 = direc_1_0;
			}
			
			if (dist_2_1 > dist_1_1 + pass_2_1) {
				dist_2_1 = dist_1_1 + pass_2_1;
				direc_2_1 = direc_1_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_n2)) {
			pass_n2_n2 = 10+rc.senseRubble(loc_n2_n2);
			if (dist_n2_n2 > dist_n2_n1 + pass_n2_n2) {
				dist_n2_n2 = dist_n2_n1 + pass_n2_n2;
				direc_n2_n2 = direc_n2_n1;
			}
			
			if (dist_n2_n2 > dist_n1_n1 + pass_n2_n2) {
				dist_n2_n2 = dist_n1_n1 + pass_n2_n2;
				direc_n2_n2 = direc_n1_n1;
			}
			
			if (dist_n2_n2 > dist_n1_n2 + pass_n2_n2) {
				dist_n2_n2 = dist_n1_n2 + pass_n2_n2;
				direc_n2_n2 = direc_n1_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_2)) {
			pass_n2_2 = 10+rc.senseRubble(loc_n2_2);
			if (dist_n2_2 > dist_n1_2 + pass_n2_2) {
				dist_n2_2 = dist_n1_2 + pass_n2_2;
				direc_n2_2 = direc_n1_2;
			}
			
			if (dist_n2_2 > dist_n1_1 + pass_n2_2) {
				dist_n2_2 = dist_n1_1 + pass_n2_2;
				direc_n2_2 = direc_n1_1;
			}
			
			if (dist_n2_2 > dist_n2_1 + pass_n2_2) {
				dist_n2_2 = dist_n2_1 + pass_n2_2;
				direc_n2_2 = direc_n2_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_n2)) {
			pass_2_n2 = 10+rc.senseRubble(loc_2_n2);
			if (dist_2_n2 > dist_2_n1 + pass_2_n2) {
				dist_2_n2 = dist_2_n1 + pass_2_n2;
				direc_2_n2 = direc_2_n1;
			}
			
			if (dist_2_n2 > dist_1_n2 + pass_2_n2) {
				dist_2_n2 = dist_1_n2 + pass_2_n2;
				direc_2_n2 = direc_1_n2;
			}
			
			if (dist_2_n2 > dist_1_n1 + pass_2_n2) {
				dist_2_n2 = dist_1_n1 + pass_2_n2;
				direc_2_n2 = direc_1_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_2)) {
			pass_2_2 = 10+rc.senseRubble(loc_2_2);
			if (dist_2_2 > dist_2_1 + pass_2_2) {
				dist_2_2 = dist_2_1 + pass_2_2;
				direc_2_2 = direc_2_1;
			}
			
			if (dist_2_2 > dist_1_1 + pass_2_2) {
				dist_2_2 = dist_1_1 + pass_2_2;
				direc_2_2 = direc_1_1;
			}
			
			if (dist_2_2 > dist_1_2 + pass_2_2) {
				dist_2_2 = dist_1_2 + pass_2_2;
				direc_2_2 = direc_1_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_0)) {
			pass_n3_0 = 10+rc.senseRubble(loc_n3_0);
			if (dist_n3_0 > dist_n2_1 + pass_n3_0) {
				dist_n3_0 = dist_n2_1 + pass_n3_0;
				direc_n3_0 = direc_n2_1;
			}
			
			if (dist_n3_0 > dist_n2_0 + pass_n3_0) {
				dist_n3_0 = dist_n2_0 + pass_n3_0;
				direc_n3_0 = direc_n2_0;
			}
			
			if (dist_n3_0 > dist_n2_n1 + pass_n3_0) {
				dist_n3_0 = dist_n2_n1 + pass_n3_0;
				direc_n3_0 = direc_n2_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_0_n3)) {
			pass_0_n3 = 10+rc.senseRubble(loc_0_n3);
			if (dist_0_n3 > dist_0_n2 + pass_0_n3) {
				dist_0_n3 = dist_0_n2 + pass_0_n3;
				direc_0_n3 = direc_0_n2;
			}
			
			if (dist_0_n3 > dist_1_n2 + pass_0_n3) {
				dist_0_n3 = dist_1_n2 + pass_0_n3;
				direc_0_n3 = direc_1_n2;
			}
			
			if (dist_0_n3 > dist_n1_n2 + pass_0_n3) {
				dist_0_n3 = dist_n1_n2 + pass_0_n3;
				direc_0_n3 = direc_n1_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_0_3)) {
			pass_0_3 = 10+rc.senseRubble(loc_0_3);
			if (dist_0_3 > dist_1_2 + pass_0_3) {
				dist_0_3 = dist_1_2 + pass_0_3;
				direc_0_3 = direc_1_2;
			}
			
			if (dist_0_3 > dist_0_2 + pass_0_3) {
				dist_0_3 = dist_0_2 + pass_0_3;
				direc_0_3 = direc_0_2;
			}
			
			if (dist_0_3 > dist_n1_2 + pass_0_3) {
				dist_0_3 = dist_n1_2 + pass_0_3;
				direc_0_3 = direc_n1_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_0)) {
			pass_3_0 = 10+rc.senseRubble(loc_3_0);
			if (dist_3_0 > dist_2_n1 + pass_3_0) {
				dist_3_0 = dist_2_n1 + pass_3_0;
				direc_3_0 = direc_2_n1;
			}
			
			if (dist_3_0 > dist_2_0 + pass_3_0) {
				dist_3_0 = dist_2_0 + pass_3_0;
				direc_3_0 = direc_2_0;
			}
			
			if (dist_3_0 > dist_2_1 + pass_3_0) {
				dist_3_0 = dist_2_1 + pass_3_0;
				direc_3_0 = direc_2_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_n1)) {
			pass_n3_n1 = 10+rc.senseRubble(loc_n3_n1);
			if (dist_n3_n1 > dist_n3_0 + pass_n3_n1) {
				dist_n3_n1 = dist_n3_0 + pass_n3_n1;
				direc_n3_n1 = direc_n3_0;
			}
			
			if (dist_n3_n1 > dist_n2_0 + pass_n3_n1) {
				dist_n3_n1 = dist_n2_0 + pass_n3_n1;
				direc_n3_n1 = direc_n2_0;
			}
			
			if (dist_n3_n1 > dist_n2_n1 + pass_n3_n1) {
				dist_n3_n1 = dist_n2_n1 + pass_n3_n1;
				direc_n3_n1 = direc_n2_n1;
			}
			
			if (dist_n3_n1 > dist_n2_n2 + pass_n3_n1) {
				dist_n3_n1 = dist_n2_n2 + pass_n3_n1;
				direc_n3_n1 = direc_n2_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_1)) {
			pass_n3_1 = 10+rc.senseRubble(loc_n3_1);
			if (dist_n3_1 > dist_n2_2 + pass_n3_1) {
				dist_n3_1 = dist_n2_2 + pass_n3_1;
				direc_n3_1 = direc_n2_2;
			}
			
			if (dist_n3_1 > dist_n2_1 + pass_n3_1) {
				dist_n3_1 = dist_n2_1 + pass_n3_1;
				direc_n3_1 = direc_n2_1;
			}
			
			if (dist_n3_1 > dist_n2_0 + pass_n3_1) {
				dist_n3_1 = dist_n2_0 + pass_n3_1;
				direc_n3_1 = direc_n2_0;
			}
			
			if (dist_n3_1 > dist_n3_0 + pass_n3_1) {
				dist_n3_1 = dist_n3_0 + pass_n3_1;
				direc_n3_1 = direc_n3_0;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n1_n3)) {
			pass_n1_n3 = 10+rc.senseRubble(loc_n1_n3);
			if (dist_n1_n3 > dist_n1_n2 + pass_n1_n3) {
				dist_n1_n3 = dist_n1_n2 + pass_n1_n3;
				direc_n1_n3 = direc_n1_n2;
			}
			
			if (dist_n1_n3 > dist_0_n2 + pass_n1_n3) {
				dist_n1_n3 = dist_0_n2 + pass_n1_n3;
				direc_n1_n3 = direc_0_n2;
			}
			
			if (dist_n1_n3 > dist_0_n3 + pass_n1_n3) {
				dist_n1_n3 = dist_0_n3 + pass_n1_n3;
				direc_n1_n3 = direc_0_n3;
			}
			
			if (dist_n1_n3 > dist_n2_n2 + pass_n1_n3) {
				dist_n1_n3 = dist_n2_n2 + pass_n1_n3;
				direc_n1_n3 = direc_n2_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n1_3)) {
			pass_n1_3 = 10+rc.senseRubble(loc_n1_3);
			if (dist_n1_3 > dist_0_3 + pass_n1_3) {
				dist_n1_3 = dist_0_3 + pass_n1_3;
				direc_n1_3 = direc_0_3;
			}
			
			if (dist_n1_3 > dist_0_2 + pass_n1_3) {
				dist_n1_3 = dist_0_2 + pass_n1_3;
				direc_n1_3 = direc_0_2;
			}
			
			if (dist_n1_3 > dist_n1_2 + pass_n1_3) {
				dist_n1_3 = dist_n1_2 + pass_n1_3;
				direc_n1_3 = direc_n1_2;
			}
			
			if (dist_n1_3 > dist_n2_2 + pass_n1_3) {
				dist_n1_3 = dist_n2_2 + pass_n1_3;
				direc_n1_3 = direc_n2_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_1_n3)) {
			pass_1_n3 = 10+rc.senseRubble(loc_1_n3);
			if (dist_1_n3 > dist_1_n2 + pass_1_n3) {
				dist_1_n3 = dist_1_n2 + pass_1_n3;
				direc_1_n3 = direc_1_n2;
			}
			
			if (dist_1_n3 > dist_2_n2 + pass_1_n3) {
				dist_1_n3 = dist_2_n2 + pass_1_n3;
				direc_1_n3 = direc_2_n2;
			}
			
			if (dist_1_n3 > dist_0_n3 + pass_1_n3) {
				dist_1_n3 = dist_0_n3 + pass_1_n3;
				direc_1_n3 = direc_0_n3;
			}
			
			if (dist_1_n3 > dist_0_n2 + pass_1_n3) {
				dist_1_n3 = dist_0_n2 + pass_1_n3;
				direc_1_n3 = direc_0_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_1_3)) {
			pass_1_3 = 10+rc.senseRubble(loc_1_3);
			if (dist_1_3 > dist_2_2 + pass_1_3) {
				dist_1_3 = dist_2_2 + pass_1_3;
				direc_1_3 = direc_2_2;
			}
			
			if (dist_1_3 > dist_1_2 + pass_1_3) {
				dist_1_3 = dist_1_2 + pass_1_3;
				direc_1_3 = direc_1_2;
			}
			
			if (dist_1_3 > dist_0_2 + pass_1_3) {
				dist_1_3 = dist_0_2 + pass_1_3;
				direc_1_3 = direc_0_2;
			}
			
			if (dist_1_3 > dist_0_3 + pass_1_3) {
				dist_1_3 = dist_0_3 + pass_1_3;
				direc_1_3 = direc_0_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_n1)) {
			pass_3_n1 = 10+rc.senseRubble(loc_3_n1);
			if (dist_3_n1 > dist_3_0 + pass_3_n1) {
				dist_3_n1 = dist_3_0 + pass_3_n1;
				direc_3_n1 = direc_3_0;
			}
			
			if (dist_3_n1 > dist_2_n2 + pass_3_n1) {
				dist_3_n1 = dist_2_n2 + pass_3_n1;
				direc_3_n1 = direc_2_n2;
			}
			
			if (dist_3_n1 > dist_2_n1 + pass_3_n1) {
				dist_3_n1 = dist_2_n1 + pass_3_n1;
				direc_3_n1 = direc_2_n1;
			}
			
			if (dist_3_n1 > dist_2_0 + pass_3_n1) {
				dist_3_n1 = dist_2_0 + pass_3_n1;
				direc_3_n1 = direc_2_0;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_1)) {
			pass_3_1 = 10+rc.senseRubble(loc_3_1);
			if (dist_3_1 > dist_3_0 + pass_3_1) {
				dist_3_1 = dist_3_0 + pass_3_1;
				direc_3_1 = direc_3_0;
			}
			
			if (dist_3_1 > dist_2_0 + pass_3_1) {
				dist_3_1 = dist_2_0 + pass_3_1;
				direc_3_1 = direc_2_0;
			}
			
			if (dist_3_1 > dist_2_1 + pass_3_1) {
				dist_3_1 = dist_2_1 + pass_3_1;
				direc_3_1 = direc_2_1;
			}
			
			if (dist_3_1 > dist_2_2 + pass_3_1) {
				dist_3_1 = dist_2_2 + pass_3_1;
				direc_3_1 = direc_2_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_n2)) {
			pass_n3_n2 = 10+rc.senseRubble(loc_n3_n2);
			if (dist_n3_n2 > dist_n3_n1 + pass_n3_n2) {
				dist_n3_n2 = dist_n3_n1 + pass_n3_n2;
				direc_n3_n2 = direc_n3_n1;
			}
			
			if (dist_n3_n2 > dist_n2_n1 + pass_n3_n2) {
				dist_n3_n2 = dist_n2_n1 + pass_n3_n2;
				direc_n3_n2 = direc_n2_n1;
			}
			
			if (dist_n3_n2 > dist_n2_n2 + pass_n3_n2) {
				dist_n3_n2 = dist_n2_n2 + pass_n3_n2;
				direc_n3_n2 = direc_n2_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_2)) {
			pass_n3_2 = 10+rc.senseRubble(loc_n3_2);
			if (dist_n3_2 > dist_n2_2 + pass_n3_2) {
				dist_n3_2 = dist_n2_2 + pass_n3_2;
				direc_n3_2 = direc_n2_2;
			}
			
			if (dist_n3_2 > dist_n2_1 + pass_n3_2) {
				dist_n3_2 = dist_n2_1 + pass_n3_2;
				direc_n3_2 = direc_n2_1;
			}
			
			if (dist_n3_2 > dist_n3_1 + pass_n3_2) {
				dist_n3_2 = dist_n3_1 + pass_n3_2;
				direc_n3_2 = direc_n3_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_n3)) {
			pass_n2_n3 = 10+rc.senseRubble(loc_n2_n3);
			if (dist_n2_n3 > dist_n2_n2 + pass_n2_n3) {
				dist_n2_n3 = dist_n2_n2 + pass_n2_n3;
				direc_n2_n3 = direc_n2_n2;
			}
			
			if (dist_n2_n3 > dist_n1_n2 + pass_n2_n3) {
				dist_n2_n3 = dist_n1_n2 + pass_n2_n3;
				direc_n2_n3 = direc_n1_n2;
			}
			
			if (dist_n2_n3 > dist_n1_n3 + pass_n2_n3) {
				dist_n2_n3 = dist_n1_n3 + pass_n2_n3;
				direc_n2_n3 = direc_n1_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_3)) {
			pass_n2_3 = 10+rc.senseRubble(loc_n2_3);
			if (dist_n2_3 > dist_n1_3 + pass_n2_3) {
				dist_n2_3 = dist_n1_3 + pass_n2_3;
				direc_n2_3 = direc_n1_3;
			}
			
			if (dist_n2_3 > dist_n1_2 + pass_n2_3) {
				dist_n2_3 = dist_n1_2 + pass_n2_3;
				direc_n2_3 = direc_n1_2;
			}
			
			if (dist_n2_3 > dist_n2_2 + pass_n2_3) {
				dist_n2_3 = dist_n2_2 + pass_n2_3;
				direc_n2_3 = direc_n2_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_n3)) {
			pass_2_n3 = 10+rc.senseRubble(loc_2_n3);
			if (dist_2_n3 > dist_2_n2 + pass_2_n3) {
				dist_2_n3 = dist_2_n2 + pass_2_n3;
				direc_2_n3 = direc_2_n2;
			}
			
			if (dist_2_n3 > dist_1_n3 + pass_2_n3) {
				dist_2_n3 = dist_1_n3 + pass_2_n3;
				direc_2_n3 = direc_1_n3;
			}
			
			if (dist_2_n3 > dist_1_n2 + pass_2_n3) {
				dist_2_n3 = dist_1_n2 + pass_2_n3;
				direc_2_n3 = direc_1_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_3)) {
			pass_2_3 = 10+rc.senseRubble(loc_2_3);
			if (dist_2_3 > dist_2_2 + pass_2_3) {
				dist_2_3 = dist_2_2 + pass_2_3;
				direc_2_3 = direc_2_2;
			}
			
			if (dist_2_3 > dist_1_2 + pass_2_3) {
				dist_2_3 = dist_1_2 + pass_2_3;
				direc_2_3 = direc_1_2;
			}
			
			if (dist_2_3 > dist_1_3 + pass_2_3) {
				dist_2_3 = dist_1_3 + pass_2_3;
				direc_2_3 = direc_1_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_n2)) {
			pass_3_n2 = 10+rc.senseRubble(loc_3_n2);
			if (dist_3_n2 > dist_3_n1 + pass_3_n2) {
				dist_3_n2 = dist_3_n1 + pass_3_n2;
				direc_3_n2 = direc_3_n1;
			}
			
			if (dist_3_n2 > dist_2_n2 + pass_3_n2) {
				dist_3_n2 = dist_2_n2 + pass_3_n2;
				direc_3_n2 = direc_2_n2;
			}
			
			if (dist_3_n2 > dist_2_n1 + pass_3_n2) {
				dist_3_n2 = dist_2_n1 + pass_3_n2;
				direc_3_n2 = direc_2_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_2)) {
			pass_3_2 = 10+rc.senseRubble(loc_3_2);
			if (dist_3_2 > dist_3_1 + pass_3_2) {
				dist_3_2 = dist_3_1 + pass_3_2;
				direc_3_2 = direc_3_1;
			}
			
			if (dist_3_2 > dist_2_1 + pass_3_2) {
				dist_3_2 = dist_2_1 + pass_3_2;
				direc_3_2 = direc_2_1;
			}
			
			if (dist_3_2 > dist_2_2 + pass_3_2) {
				dist_3_2 = dist_2_2 + pass_3_2;
				direc_3_2 = direc_2_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_0)) {
			pass_n4_0 = 10+rc.senseRubble(loc_n4_0);
			if (dist_n4_0 > dist_n3_1 + pass_n4_0) {
				dist_n4_0 = dist_n3_1 + pass_n4_0;
				direc_n4_0 = direc_n3_1;
			}
			
			if (dist_n4_0 > dist_n3_0 + pass_n4_0) {
				dist_n4_0 = dist_n3_0 + pass_n4_0;
				direc_n4_0 = direc_n3_0;
			}
			
			if (dist_n4_0 > dist_n3_n1 + pass_n4_0) {
				dist_n4_0 = dist_n3_n1 + pass_n4_0;
				direc_n4_0 = direc_n3_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_0_n4)) {
			pass_0_n4 = 10+rc.senseRubble(loc_0_n4);
			if (dist_0_n4 > dist_0_n3 + pass_0_n4) {
				dist_0_n4 = dist_0_n3 + pass_0_n4;
				direc_0_n4 = direc_0_n3;
			}
			
			if (dist_0_n4 > dist_1_n3 + pass_0_n4) {
				dist_0_n4 = dist_1_n3 + pass_0_n4;
				direc_0_n4 = direc_1_n3;
			}
			
			if (dist_0_n4 > dist_n1_n3 + pass_0_n4) {
				dist_0_n4 = dist_n1_n3 + pass_0_n4;
				direc_0_n4 = direc_n1_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_0_4)) {
			pass_0_4 = 10+rc.senseRubble(loc_0_4);
			if (dist_0_4 > dist_1_3 + pass_0_4) {
				dist_0_4 = dist_1_3 + pass_0_4;
				direc_0_4 = direc_1_3;
			}
			
			if (dist_0_4 > dist_0_3 + pass_0_4) {
				dist_0_4 = dist_0_3 + pass_0_4;
				direc_0_4 = direc_0_3;
			}
			
			if (dist_0_4 > dist_n1_3 + pass_0_4) {
				dist_0_4 = dist_n1_3 + pass_0_4;
				direc_0_4 = direc_n1_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_0)) {
			pass_4_0 = 10+rc.senseRubble(loc_4_0);
			if (dist_4_0 > dist_3_n1 + pass_4_0) {
				dist_4_0 = dist_3_n1 + pass_4_0;
				direc_4_0 = direc_3_n1;
			}
			
			if (dist_4_0 > dist_3_0 + pass_4_0) {
				dist_4_0 = dist_3_0 + pass_4_0;
				direc_4_0 = direc_3_0;
			}
			
			if (dist_4_0 > dist_3_1 + pass_4_0) {
				dist_4_0 = dist_3_1 + pass_4_0;
				direc_4_0 = direc_3_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_n1)) {
			pass_n4_n1 = 10+rc.senseRubble(loc_n4_n1);
			if (dist_n4_n1 > dist_n4_0 + pass_n4_n1) {
				dist_n4_n1 = dist_n4_0 + pass_n4_n1;
				direc_n4_n1 = direc_n4_0;
			}
			
			if (dist_n4_n1 > dist_n3_0 + pass_n4_n1) {
				dist_n4_n1 = dist_n3_0 + pass_n4_n1;
				direc_n4_n1 = direc_n3_0;
			}
			
			if (dist_n4_n1 > dist_n3_n1 + pass_n4_n1) {
				dist_n4_n1 = dist_n3_n1 + pass_n4_n1;
				direc_n4_n1 = direc_n3_n1;
			}
			
			if (dist_n4_n1 > dist_n3_n2 + pass_n4_n1) {
				dist_n4_n1 = dist_n3_n2 + pass_n4_n1;
				direc_n4_n1 = direc_n3_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_1)) {
			pass_n4_1 = 10+rc.senseRubble(loc_n4_1);
			if (dist_n4_1 > dist_n3_2 + pass_n4_1) {
				dist_n4_1 = dist_n3_2 + pass_n4_1;
				direc_n4_1 = direc_n3_2;
			}
			
			if (dist_n4_1 > dist_n3_1 + pass_n4_1) {
				dist_n4_1 = dist_n3_1 + pass_n4_1;
				direc_n4_1 = direc_n3_1;
			}
			
			if (dist_n4_1 > dist_n3_0 + pass_n4_1) {
				dist_n4_1 = dist_n3_0 + pass_n4_1;
				direc_n4_1 = direc_n3_0;
			}
			
			if (dist_n4_1 > dist_n4_0 + pass_n4_1) {
				dist_n4_1 = dist_n4_0 + pass_n4_1;
				direc_n4_1 = direc_n4_0;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n1_n4)) {
			pass_n1_n4 = 10+rc.senseRubble(loc_n1_n4);
			if (dist_n1_n4 > dist_n1_n3 + pass_n1_n4) {
				dist_n1_n4 = dist_n1_n3 + pass_n1_n4;
				direc_n1_n4 = direc_n1_n3;
			}
			
			if (dist_n1_n4 > dist_0_n3 + pass_n1_n4) {
				dist_n1_n4 = dist_0_n3 + pass_n1_n4;
				direc_n1_n4 = direc_0_n3;
			}
			
			if (dist_n1_n4 > dist_0_n4 + pass_n1_n4) {
				dist_n1_n4 = dist_0_n4 + pass_n1_n4;
				direc_n1_n4 = direc_0_n4;
			}
			
			if (dist_n1_n4 > dist_n2_n3 + pass_n1_n4) {
				dist_n1_n4 = dist_n2_n3 + pass_n1_n4;
				direc_n1_n4 = direc_n2_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n1_4)) {
			pass_n1_4 = 10+rc.senseRubble(loc_n1_4);
			if (dist_n1_4 > dist_0_4 + pass_n1_4) {
				dist_n1_4 = dist_0_4 + pass_n1_4;
				direc_n1_4 = direc_0_4;
			}
			
			if (dist_n1_4 > dist_0_3 + pass_n1_4) {
				dist_n1_4 = dist_0_3 + pass_n1_4;
				direc_n1_4 = direc_0_3;
			}
			
			if (dist_n1_4 > dist_n1_3 + pass_n1_4) {
				dist_n1_4 = dist_n1_3 + pass_n1_4;
				direc_n1_4 = direc_n1_3;
			}
			
			if (dist_n1_4 > dist_n2_3 + pass_n1_4) {
				dist_n1_4 = dist_n2_3 + pass_n1_4;
				direc_n1_4 = direc_n2_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_1_n4)) {
			pass_1_n4 = 10+rc.senseRubble(loc_1_n4);
			if (dist_1_n4 > dist_1_n3 + pass_1_n4) {
				dist_1_n4 = dist_1_n3 + pass_1_n4;
				direc_1_n4 = direc_1_n3;
			}
			
			if (dist_1_n4 > dist_2_n3 + pass_1_n4) {
				dist_1_n4 = dist_2_n3 + pass_1_n4;
				direc_1_n4 = direc_2_n3;
			}
			
			if (dist_1_n4 > dist_0_n4 + pass_1_n4) {
				dist_1_n4 = dist_0_n4 + pass_1_n4;
				direc_1_n4 = direc_0_n4;
			}
			
			if (dist_1_n4 > dist_0_n3 + pass_1_n4) {
				dist_1_n4 = dist_0_n3 + pass_1_n4;
				direc_1_n4 = direc_0_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_1_4)) {
			pass_1_4 = 10+rc.senseRubble(loc_1_4);
			if (dist_1_4 > dist_2_3 + pass_1_4) {
				dist_1_4 = dist_2_3 + pass_1_4;
				direc_1_4 = direc_2_3;
			}
			
			if (dist_1_4 > dist_1_3 + pass_1_4) {
				dist_1_4 = dist_1_3 + pass_1_4;
				direc_1_4 = direc_1_3;
			}
			
			if (dist_1_4 > dist_0_3 + pass_1_4) {
				dist_1_4 = dist_0_3 + pass_1_4;
				direc_1_4 = direc_0_3;
			}
			
			if (dist_1_4 > dist_0_4 + pass_1_4) {
				dist_1_4 = dist_0_4 + pass_1_4;
				direc_1_4 = direc_0_4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_n1)) {
			pass_4_n1 = 10+rc.senseRubble(loc_4_n1);
			if (dist_4_n1 > dist_4_0 + pass_4_n1) {
				dist_4_n1 = dist_4_0 + pass_4_n1;
				direc_4_n1 = direc_4_0;
			}
			
			if (dist_4_n1 > dist_3_n2 + pass_4_n1) {
				dist_4_n1 = dist_3_n2 + pass_4_n1;
				direc_4_n1 = direc_3_n2;
			}
			
			if (dist_4_n1 > dist_3_n1 + pass_4_n1) {
				dist_4_n1 = dist_3_n1 + pass_4_n1;
				direc_4_n1 = direc_3_n1;
			}
			
			if (dist_4_n1 > dist_3_0 + pass_4_n1) {
				dist_4_n1 = dist_3_0 + pass_4_n1;
				direc_4_n1 = direc_3_0;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_1)) {
			pass_4_1 = 10+rc.senseRubble(loc_4_1);
			if (dist_4_1 > dist_4_0 + pass_4_1) {
				dist_4_1 = dist_4_0 + pass_4_1;
				direc_4_1 = direc_4_0;
			}
			
			if (dist_4_1 > dist_3_0 + pass_4_1) {
				dist_4_1 = dist_3_0 + pass_4_1;
				direc_4_1 = direc_3_0;
			}
			
			if (dist_4_1 > dist_3_1 + pass_4_1) {
				dist_4_1 = dist_3_1 + pass_4_1;
				direc_4_1 = direc_3_1;
			}
			
			if (dist_4_1 > dist_3_2 + pass_4_1) {
				dist_4_1 = dist_3_2 + pass_4_1;
				direc_4_1 = direc_3_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_n3)) {
			pass_n3_n3 = 10+rc.senseRubble(loc_n3_n3);
			if (dist_n3_n3 > dist_n3_n2 + pass_n3_n3) {
				dist_n3_n3 = dist_n3_n2 + pass_n3_n3;
				direc_n3_n3 = direc_n3_n2;
			}
			
			if (dist_n3_n3 > dist_n2_n2 + pass_n3_n3) {
				dist_n3_n3 = dist_n2_n2 + pass_n3_n3;
				direc_n3_n3 = direc_n2_n2;
			}
			
			if (dist_n3_n3 > dist_n2_n3 + pass_n3_n3) {
				dist_n3_n3 = dist_n2_n3 + pass_n3_n3;
				direc_n3_n3 = direc_n2_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_3)) {
			pass_n3_3 = 10+rc.senseRubble(loc_n3_3);
			if (dist_n3_3 > dist_n2_3 + pass_n3_3) {
				dist_n3_3 = dist_n2_3 + pass_n3_3;
				direc_n3_3 = direc_n2_3;
			}
			
			if (dist_n3_3 > dist_n2_2 + pass_n3_3) {
				dist_n3_3 = dist_n2_2 + pass_n3_3;
				direc_n3_3 = direc_n2_2;
			}
			
			if (dist_n3_3 > dist_n3_2 + pass_n3_3) {
				dist_n3_3 = dist_n3_2 + pass_n3_3;
				direc_n3_3 = direc_n3_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_n3)) {
			pass_3_n3 = 10+rc.senseRubble(loc_3_n3);
			if (dist_3_n3 > dist_3_n2 + pass_3_n3) {
				dist_3_n3 = dist_3_n2 + pass_3_n3;
				direc_3_n3 = direc_3_n2;
			}
			
			if (dist_3_n3 > dist_2_n3 + pass_3_n3) {
				dist_3_n3 = dist_2_n3 + pass_3_n3;
				direc_3_n3 = direc_2_n3;
			}
			
			if (dist_3_n3 > dist_2_n2 + pass_3_n3) {
				dist_3_n3 = dist_2_n2 + pass_3_n3;
				direc_3_n3 = direc_2_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_3)) {
			pass_3_3 = 10+rc.senseRubble(loc_3_3);
			if (dist_3_3 > dist_3_2 + pass_3_3) {
				dist_3_3 = dist_3_2 + pass_3_3;
				direc_3_3 = direc_3_2;
			}
			
			if (dist_3_3 > dist_2_2 + pass_3_3) {
				dist_3_3 = dist_2_2 + pass_3_3;
				direc_3_3 = direc_2_2;
			}
			
			if (dist_3_3 > dist_2_3 + pass_3_3) {
				dist_3_3 = dist_2_3 + pass_3_3;
				direc_3_3 = direc_2_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_n2)) {
			pass_n4_n2 = 10+rc.senseRubble(loc_n4_n2);
			if (dist_n4_n2 > dist_n4_n1 + pass_n4_n2) {
				dist_n4_n2 = dist_n4_n1 + pass_n4_n2;
				direc_n4_n2 = direc_n4_n1;
			}
			
			if (dist_n4_n2 > dist_n3_n1 + pass_n4_n2) {
				dist_n4_n2 = dist_n3_n1 + pass_n4_n2;
				direc_n4_n2 = direc_n3_n1;
			}
			
			if (dist_n4_n2 > dist_n3_n2 + pass_n4_n2) {
				dist_n4_n2 = dist_n3_n2 + pass_n4_n2;
				direc_n4_n2 = direc_n3_n2;
			}
			
			if (dist_n4_n2 > dist_n3_n3 + pass_n4_n2) {
				dist_n4_n2 = dist_n3_n3 + pass_n4_n2;
				direc_n4_n2 = direc_n3_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_2)) {
			pass_n4_2 = 10+rc.senseRubble(loc_n4_2);
			if (dist_n4_2 > dist_n3_3 + pass_n4_2) {
				dist_n4_2 = dist_n3_3 + pass_n4_2;
				direc_n4_2 = direc_n3_3;
			}
			
			if (dist_n4_2 > dist_n3_2 + pass_n4_2) {
				dist_n4_2 = dist_n3_2 + pass_n4_2;
				direc_n4_2 = direc_n3_2;
			}
			
			if (dist_n4_2 > dist_n3_1 + pass_n4_2) {
				dist_n4_2 = dist_n3_1 + pass_n4_2;
				direc_n4_2 = direc_n3_1;
			}
			
			if (dist_n4_2 > dist_n4_1 + pass_n4_2) {
				dist_n4_2 = dist_n4_1 + pass_n4_2;
				direc_n4_2 = direc_n4_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_n4)) {
			pass_n2_n4 = 10+rc.senseRubble(loc_n2_n4);
			if (dist_n2_n4 > dist_n2_n3 + pass_n2_n4) {
				dist_n2_n4 = dist_n2_n3 + pass_n2_n4;
				direc_n2_n4 = direc_n2_n3;
			}
			
			if (dist_n2_n4 > dist_n1_n3 + pass_n2_n4) {
				dist_n2_n4 = dist_n1_n3 + pass_n2_n4;
				direc_n2_n4 = direc_n1_n3;
			}
			
			if (dist_n2_n4 > dist_n1_n4 + pass_n2_n4) {
				dist_n2_n4 = dist_n1_n4 + pass_n2_n4;
				direc_n2_n4 = direc_n1_n4;
			}
			
			if (dist_n2_n4 > dist_n3_n3 + pass_n2_n4) {
				dist_n2_n4 = dist_n3_n3 + pass_n2_n4;
				direc_n2_n4 = direc_n3_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_4)) {
			pass_n2_4 = 10+rc.senseRubble(loc_n2_4);
			if (dist_n2_4 > dist_n1_4 + pass_n2_4) {
				dist_n2_4 = dist_n1_4 + pass_n2_4;
				direc_n2_4 = direc_n1_4;
			}
			
			if (dist_n2_4 > dist_n1_3 + pass_n2_4) {
				dist_n2_4 = dist_n1_3 + pass_n2_4;
				direc_n2_4 = direc_n1_3;
			}
			
			if (dist_n2_4 > dist_n2_3 + pass_n2_4) {
				dist_n2_4 = dist_n2_3 + pass_n2_4;
				direc_n2_4 = direc_n2_3;
			}
			
			if (dist_n2_4 > dist_n3_3 + pass_n2_4) {
				dist_n2_4 = dist_n3_3 + pass_n2_4;
				direc_n2_4 = direc_n3_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_n4)) {
			pass_2_n4 = 10+rc.senseRubble(loc_2_n4);
			if (dist_2_n4 > dist_2_n3 + pass_2_n4) {
				dist_2_n4 = dist_2_n3 + pass_2_n4;
				direc_2_n4 = direc_2_n3;
			}
			
			if (dist_2_n4 > dist_3_n3 + pass_2_n4) {
				dist_2_n4 = dist_3_n3 + pass_2_n4;
				direc_2_n4 = direc_3_n3;
			}
			
			if (dist_2_n4 > dist_1_n4 + pass_2_n4) {
				dist_2_n4 = dist_1_n4 + pass_2_n4;
				direc_2_n4 = direc_1_n4;
			}
			
			if (dist_2_n4 > dist_1_n3 + pass_2_n4) {
				dist_2_n4 = dist_1_n3 + pass_2_n4;
				direc_2_n4 = direc_1_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_4)) {
			pass_2_4 = 10+rc.senseRubble(loc_2_4);
			if (dist_2_4 > dist_3_3 + pass_2_4) {
				dist_2_4 = dist_3_3 + pass_2_4;
				direc_2_4 = direc_3_3;
			}
			
			if (dist_2_4 > dist_2_3 + pass_2_4) {
				dist_2_4 = dist_2_3 + pass_2_4;
				direc_2_4 = direc_2_3;
			}
			
			if (dist_2_4 > dist_1_3 + pass_2_4) {
				dist_2_4 = dist_1_3 + pass_2_4;
				direc_2_4 = direc_1_3;
			}
			
			if (dist_2_4 > dist_1_4 + pass_2_4) {
				dist_2_4 = dist_1_4 + pass_2_4;
				direc_2_4 = direc_1_4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_n2)) {
			pass_4_n2 = 10+rc.senseRubble(loc_4_n2);
			if (dist_4_n2 > dist_4_n1 + pass_4_n2) {
				dist_4_n2 = dist_4_n1 + pass_4_n2;
				direc_4_n2 = direc_4_n1;
			}
			
			if (dist_4_n2 > dist_3_n3 + pass_4_n2) {
				dist_4_n2 = dist_3_n3 + pass_4_n2;
				direc_4_n2 = direc_3_n3;
			}
			
			if (dist_4_n2 > dist_3_n2 + pass_4_n2) {
				dist_4_n2 = dist_3_n2 + pass_4_n2;
				direc_4_n2 = direc_3_n2;
			}
			
			if (dist_4_n2 > dist_3_n1 + pass_4_n2) {
				dist_4_n2 = dist_3_n1 + pass_4_n2;
				direc_4_n2 = direc_3_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_2)) {
			pass_4_2 = 10+rc.senseRubble(loc_4_2);
			if (dist_4_2 > dist_4_1 + pass_4_2) {
				dist_4_2 = dist_4_1 + pass_4_2;
				direc_4_2 = direc_4_1;
			}
			
			if (dist_4_2 > dist_3_1 + pass_4_2) {
				dist_4_2 = dist_3_1 + pass_4_2;
				direc_4_2 = direc_3_1;
			}
			
			if (dist_4_2 > dist_3_2 + pass_4_2) {
				dist_4_2 = dist_3_2 + pass_4_2;
				direc_4_2 = direc_3_2;
			}
			
			if (dist_4_2 > dist_3_3 + pass_4_2) {
				dist_4_2 = dist_3_3 + pass_4_2;
				direc_4_2 = direc_3_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n5_0)) {
			pass_n5_0 = 10+rc.senseRubble(loc_n5_0);
			if (dist_n5_0 > dist_n4_1 + pass_n5_0) {
				dist_n5_0 = dist_n4_1 + pass_n5_0;
				direc_n5_0 = direc_n4_1;
			}
			
			if (dist_n5_0 > dist_n4_0 + pass_n5_0) {
				dist_n5_0 = dist_n4_0 + pass_n5_0;
				direc_n5_0 = direc_n4_0;
			}
			
			if (dist_n5_0 > dist_n4_n1 + pass_n5_0) {
				dist_n5_0 = dist_n4_n1 + pass_n5_0;
				direc_n5_0 = direc_n4_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_n3)) {
			pass_n4_n3 = 10+rc.senseRubble(loc_n4_n3);
			if (dist_n4_n3 > dist_n4_n2 + pass_n4_n3) {
				dist_n4_n3 = dist_n4_n2 + pass_n4_n3;
				direc_n4_n3 = direc_n4_n2;
			}
			
			if (dist_n4_n3 > dist_n3_n2 + pass_n4_n3) {
				dist_n4_n3 = dist_n3_n2 + pass_n4_n3;
				direc_n4_n3 = direc_n3_n2;
			}
			
			if (dist_n4_n3 > dist_n3_n3 + pass_n4_n3) {
				dist_n4_n3 = dist_n3_n3 + pass_n4_n3;
				direc_n4_n3 = direc_n3_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_3)) {
			pass_n4_3 = 10+rc.senseRubble(loc_n4_3);
			if (dist_n4_3 > dist_n3_3 + pass_n4_3) {
				dist_n4_3 = dist_n3_3 + pass_n4_3;
				direc_n4_3 = direc_n3_3;
			}
			
			if (dist_n4_3 > dist_n3_2 + pass_n4_3) {
				dist_n4_3 = dist_n3_2 + pass_n4_3;
				direc_n4_3 = direc_n3_2;
			}
			
			if (dist_n4_3 > dist_n4_2 + pass_n4_3) {
				dist_n4_3 = dist_n4_2 + pass_n4_3;
				direc_n4_3 = direc_n4_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_n4)) {
			pass_n3_n4 = 10+rc.senseRubble(loc_n3_n4);
			if (dist_n3_n4 > dist_n3_n3 + pass_n3_n4) {
				dist_n3_n4 = dist_n3_n3 + pass_n3_n4;
				direc_n3_n4 = direc_n3_n3;
			}
			
			if (dist_n3_n4 > dist_n2_n3 + pass_n3_n4) {
				dist_n3_n4 = dist_n2_n3 + pass_n3_n4;
				direc_n3_n4 = direc_n2_n3;
			}
			
			if (dist_n3_n4 > dist_n2_n4 + pass_n3_n4) {
				dist_n3_n4 = dist_n2_n4 + pass_n3_n4;
				direc_n3_n4 = direc_n2_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_4)) {
			pass_n3_4 = 10+rc.senseRubble(loc_n3_4);
			if (dist_n3_4 > dist_n2_4 + pass_n3_4) {
				dist_n3_4 = dist_n2_4 + pass_n3_4;
				direc_n3_4 = direc_n2_4;
			}
			
			if (dist_n3_4 > dist_n2_3 + pass_n3_4) {
				dist_n3_4 = dist_n2_3 + pass_n3_4;
				direc_n3_4 = direc_n2_3;
			}
			
			if (dist_n3_4 > dist_n3_3 + pass_n3_4) {
				dist_n3_4 = dist_n3_3 + pass_n3_4;
				direc_n3_4 = direc_n3_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_0_n5)) {
			pass_0_n5 = 10+rc.senseRubble(loc_0_n5);
			if (dist_0_n5 > dist_0_n4 + pass_0_n5) {
				dist_0_n5 = dist_0_n4 + pass_0_n5;
				direc_0_n5 = direc_0_n4;
			}
			
			if (dist_0_n5 > dist_1_n4 + pass_0_n5) {
				dist_0_n5 = dist_1_n4 + pass_0_n5;
				direc_0_n5 = direc_1_n4;
			}
			
			if (dist_0_n5 > dist_n1_n4 + pass_0_n5) {
				dist_0_n5 = dist_n1_n4 + pass_0_n5;
				direc_0_n5 = direc_n1_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_0_5)) {
			pass_0_5 = 10+rc.senseRubble(loc_0_5);
			if (dist_0_5 > dist_1_4 + pass_0_5) {
				dist_0_5 = dist_1_4 + pass_0_5;
				direc_0_5 = direc_1_4;
			}
			
			if (dist_0_5 > dist_0_4 + pass_0_5) {
				dist_0_5 = dist_0_4 + pass_0_5;
				direc_0_5 = direc_0_4;
			}
			
			if (dist_0_5 > dist_n1_4 + pass_0_5) {
				dist_0_5 = dist_n1_4 + pass_0_5;
				direc_0_5 = direc_n1_4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_n4)) {
			pass_3_n4 = 10+rc.senseRubble(loc_3_n4);
			if (dist_3_n4 > dist_3_n3 + pass_3_n4) {
				dist_3_n4 = dist_3_n3 + pass_3_n4;
				direc_3_n4 = direc_3_n3;
			}
			
			if (dist_3_n4 > dist_2_n4 + pass_3_n4) {
				dist_3_n4 = dist_2_n4 + pass_3_n4;
				direc_3_n4 = direc_2_n4;
			}
			
			if (dist_3_n4 > dist_2_n3 + pass_3_n4) {
				dist_3_n4 = dist_2_n3 + pass_3_n4;
				direc_3_n4 = direc_2_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_4)) {
			pass_3_4 = 10+rc.senseRubble(loc_3_4);
			if (dist_3_4 > dist_3_3 + pass_3_4) {
				dist_3_4 = dist_3_3 + pass_3_4;
				direc_3_4 = direc_3_3;
			}
			
			if (dist_3_4 > dist_2_3 + pass_3_4) {
				dist_3_4 = dist_2_3 + pass_3_4;
				direc_3_4 = direc_2_3;
			}
			
			if (dist_3_4 > dist_2_4 + pass_3_4) {
				dist_3_4 = dist_2_4 + pass_3_4;
				direc_3_4 = direc_2_4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_n3)) {
			pass_4_n3 = 10+rc.senseRubble(loc_4_n3);
			if (dist_4_n3 > dist_4_n2 + pass_4_n3) {
				dist_4_n3 = dist_4_n2 + pass_4_n3;
				direc_4_n3 = direc_4_n2;
			}
			
			if (dist_4_n3 > dist_3_n3 + pass_4_n3) {
				dist_4_n3 = dist_3_n3 + pass_4_n3;
				direc_4_n3 = direc_3_n3;
			}
			
			if (dist_4_n3 > dist_3_n2 + pass_4_n3) {
				dist_4_n3 = dist_3_n2 + pass_4_n3;
				direc_4_n3 = direc_3_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_3)) {
			pass_4_3 = 10+rc.senseRubble(loc_4_3);
			if (dist_4_3 > dist_4_2 + pass_4_3) {
				dist_4_3 = dist_4_2 + pass_4_3;
				direc_4_3 = direc_4_2;
			}
			
			if (dist_4_3 > dist_3_2 + pass_4_3) {
				dist_4_3 = dist_3_2 + pass_4_3;
				direc_4_3 = direc_3_2;
			}
			
			if (dist_4_3 > dist_3_3 + pass_4_3) {
				dist_4_3 = dist_3_3 + pass_4_3;
				direc_4_3 = direc_3_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_5_0)) {
			pass_5_0 = 10+rc.senseRubble(loc_5_0);
			if (dist_5_0 > dist_4_n1 + pass_5_0) {
				dist_5_0 = dist_4_n1 + pass_5_0;
				direc_5_0 = direc_4_n1;
			}
			
			if (dist_5_0 > dist_4_0 + pass_5_0) {
				dist_5_0 = dist_4_0 + pass_5_0;
				direc_5_0 = direc_4_0;
			}
			
			if (dist_5_0 > dist_4_1 + pass_5_0) {
				dist_5_0 = dist_4_1 + pass_5_0;
				direc_5_0 = direc_4_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n5_n1)) {
			pass_n5_n1 = 10+rc.senseRubble(loc_n5_n1);
			if (dist_n5_n1 > dist_n5_0 + pass_n5_n1) {
				dist_n5_n1 = dist_n5_0 + pass_n5_n1;
				direc_n5_n1 = direc_n5_0;
			}
			
			if (dist_n5_n1 > dist_n4_0 + pass_n5_n1) {
				dist_n5_n1 = dist_n4_0 + pass_n5_n1;
				direc_n5_n1 = direc_n4_0;
			}
			
			if (dist_n5_n1 > dist_n4_n1 + pass_n5_n1) {
				dist_n5_n1 = dist_n4_n1 + pass_n5_n1;
				direc_n5_n1 = direc_n4_n1;
			}
			
			if (dist_n5_n1 > dist_n4_n2 + pass_n5_n1) {
				dist_n5_n1 = dist_n4_n2 + pass_n5_n1;
				direc_n5_n1 = direc_n4_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n5_1)) {
			pass_n5_1 = 10+rc.senseRubble(loc_n5_1);
			if (dist_n5_1 > dist_n4_2 + pass_n5_1) {
				dist_n5_1 = dist_n4_2 + pass_n5_1;
				direc_n5_1 = direc_n4_2;
			}
			
			if (dist_n5_1 > dist_n4_1 + pass_n5_1) {
				dist_n5_1 = dist_n4_1 + pass_n5_1;
				direc_n5_1 = direc_n4_1;
			}
			
			if (dist_n5_1 > dist_n4_0 + pass_n5_1) {
				dist_n5_1 = dist_n4_0 + pass_n5_1;
				direc_n5_1 = direc_n4_0;
			}
			
			if (dist_n5_1 > dist_n5_0 + pass_n5_1) {
				dist_n5_1 = dist_n5_0 + pass_n5_1;
				direc_n5_1 = direc_n5_0;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n1_n5)) {
			pass_n1_n5 = 10+rc.senseRubble(loc_n1_n5);
			if (dist_n1_n5 > dist_n1_n4 + pass_n1_n5) {
				dist_n1_n5 = dist_n1_n4 + pass_n1_n5;
				direc_n1_n5 = direc_n1_n4;
			}
			
			if (dist_n1_n5 > dist_0_n4 + pass_n1_n5) {
				dist_n1_n5 = dist_0_n4 + pass_n1_n5;
				direc_n1_n5 = direc_0_n4;
			}
			
			if (dist_n1_n5 > dist_0_n5 + pass_n1_n5) {
				dist_n1_n5 = dist_0_n5 + pass_n1_n5;
				direc_n1_n5 = direc_0_n5;
			}
			
			if (dist_n1_n5 > dist_n2_n4 + pass_n1_n5) {
				dist_n1_n5 = dist_n2_n4 + pass_n1_n5;
				direc_n1_n5 = direc_n2_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n1_5)) {
			pass_n1_5 = 10+rc.senseRubble(loc_n1_5);
			if (dist_n1_5 > dist_0_5 + pass_n1_5) {
				dist_n1_5 = dist_0_5 + pass_n1_5;
				direc_n1_5 = direc_0_5;
			}
			
			if (dist_n1_5 > dist_0_4 + pass_n1_5) {
				dist_n1_5 = dist_0_4 + pass_n1_5;
				direc_n1_5 = direc_0_4;
			}
			
			if (dist_n1_5 > dist_n1_4 + pass_n1_5) {
				dist_n1_5 = dist_n1_4 + pass_n1_5;
				direc_n1_5 = direc_n1_4;
			}
			
			if (dist_n1_5 > dist_n2_4 + pass_n1_5) {
				dist_n1_5 = dist_n2_4 + pass_n1_5;
				direc_n1_5 = direc_n2_4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_1_n5)) {
			pass_1_n5 = 10+rc.senseRubble(loc_1_n5);
			if (dist_1_n5 > dist_1_n4 + pass_1_n5) {
				dist_1_n5 = dist_1_n4 + pass_1_n5;
				direc_1_n5 = direc_1_n4;
			}
			
			if (dist_1_n5 > dist_2_n4 + pass_1_n5) {
				dist_1_n5 = dist_2_n4 + pass_1_n5;
				direc_1_n5 = direc_2_n4;
			}
			
			if (dist_1_n5 > dist_0_n5 + pass_1_n5) {
				dist_1_n5 = dist_0_n5 + pass_1_n5;
				direc_1_n5 = direc_0_n5;
			}
			
			if (dist_1_n5 > dist_0_n4 + pass_1_n5) {
				dist_1_n5 = dist_0_n4 + pass_1_n5;
				direc_1_n5 = direc_0_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_1_5)) {
			pass_1_5 = 10+rc.senseRubble(loc_1_5);
			if (dist_1_5 > dist_2_4 + pass_1_5) {
				dist_1_5 = dist_2_4 + pass_1_5;
				direc_1_5 = direc_2_4;
			}
			
			if (dist_1_5 > dist_1_4 + pass_1_5) {
				dist_1_5 = dist_1_4 + pass_1_5;
				direc_1_5 = direc_1_4;
			}
			
			if (dist_1_5 > dist_0_4 + pass_1_5) {
				dist_1_5 = dist_0_4 + pass_1_5;
				direc_1_5 = direc_0_4;
			}
			
			if (dist_1_5 > dist_0_5 + pass_1_5) {
				dist_1_5 = dist_0_5 + pass_1_5;
				direc_1_5 = direc_0_5;
			}
			
		}
		
		if (rc.canSenseLocation(loc_5_n1)) {
			pass_5_n1 = 10+rc.senseRubble(loc_5_n1);
			if (dist_5_n1 > dist_5_0 + pass_5_n1) {
				dist_5_n1 = dist_5_0 + pass_5_n1;
				direc_5_n1 = direc_5_0;
			}
			
			if (dist_5_n1 > dist_4_n2 + pass_5_n1) {
				dist_5_n1 = dist_4_n2 + pass_5_n1;
				direc_5_n1 = direc_4_n2;
			}
			
			if (dist_5_n1 > dist_4_n1 + pass_5_n1) {
				dist_5_n1 = dist_4_n1 + pass_5_n1;
				direc_5_n1 = direc_4_n1;
			}
			
			if (dist_5_n1 > dist_4_0 + pass_5_n1) {
				dist_5_n1 = dist_4_0 + pass_5_n1;
				direc_5_n1 = direc_4_0;
			}
			
		}
		
		if (rc.canSenseLocation(loc_5_1)) {
			pass_5_1 = 10+rc.senseRubble(loc_5_1);
			if (dist_5_1 > dist_5_0 + pass_5_1) {
				dist_5_1 = dist_5_0 + pass_5_1;
				direc_5_1 = direc_5_0;
			}
			
			if (dist_5_1 > dist_4_0 + pass_5_1) {
				dist_5_1 = dist_4_0 + pass_5_1;
				direc_5_1 = direc_4_0;
			}
			
			if (dist_5_1 > dist_4_1 + pass_5_1) {
				dist_5_1 = dist_4_1 + pass_5_1;
				direc_5_1 = direc_4_1;
			}
			
			if (dist_5_1 > dist_4_2 + pass_5_1) {
				dist_5_1 = dist_4_2 + pass_5_1;
				direc_5_1 = direc_4_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n5_n2)) {
			pass_n5_n2 = 10+rc.senseRubble(loc_n5_n2);
			if (dist_n5_n2 > dist_n5_n1 + pass_n5_n2) {
				dist_n5_n2 = dist_n5_n1 + pass_n5_n2;
				direc_n5_n2 = direc_n5_n1;
			}
			
			if (dist_n5_n2 > dist_n4_n1 + pass_n5_n2) {
				dist_n5_n2 = dist_n4_n1 + pass_n5_n2;
				direc_n5_n2 = direc_n4_n1;
			}
			
			if (dist_n5_n2 > dist_n4_n2 + pass_n5_n2) {
				dist_n5_n2 = dist_n4_n2 + pass_n5_n2;
				direc_n5_n2 = direc_n4_n2;
			}
			
			if (dist_n5_n2 > dist_n4_n3 + pass_n5_n2) {
				dist_n5_n2 = dist_n4_n3 + pass_n5_n2;
				direc_n5_n2 = direc_n4_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n5_2)) {
			pass_n5_2 = 10+rc.senseRubble(loc_n5_2);
			if (dist_n5_2 > dist_n4_3 + pass_n5_2) {
				dist_n5_2 = dist_n4_3 + pass_n5_2;
				direc_n5_2 = direc_n4_3;
			}
			
			if (dist_n5_2 > dist_n4_2 + pass_n5_2) {
				dist_n5_2 = dist_n4_2 + pass_n5_2;
				direc_n5_2 = direc_n4_2;
			}
			
			if (dist_n5_2 > dist_n4_1 + pass_n5_2) {
				dist_n5_2 = dist_n4_1 + pass_n5_2;
				direc_n5_2 = direc_n4_1;
			}
			
			if (dist_n5_2 > dist_n5_1 + pass_n5_2) {
				dist_n5_2 = dist_n5_1 + pass_n5_2;
				direc_n5_2 = direc_n5_1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_n5)) {
			pass_n2_n5 = 10+rc.senseRubble(loc_n2_n5);
			if (dist_n2_n5 > dist_n2_n4 + pass_n2_n5) {
				dist_n2_n5 = dist_n2_n4 + pass_n2_n5;
				direc_n2_n5 = direc_n2_n4;
			}
			
			if (dist_n2_n5 > dist_n1_n4 + pass_n2_n5) {
				dist_n2_n5 = dist_n1_n4 + pass_n2_n5;
				direc_n2_n5 = direc_n1_n4;
			}
			
			if (dist_n2_n5 > dist_n1_n5 + pass_n2_n5) {
				dist_n2_n5 = dist_n1_n5 + pass_n2_n5;
				direc_n2_n5 = direc_n1_n5;
			}
			
			if (dist_n2_n5 > dist_n3_n4 + pass_n2_n5) {
				dist_n2_n5 = dist_n3_n4 + pass_n2_n5;
				direc_n2_n5 = direc_n3_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n2_5)) {
			pass_n2_5 = 10+rc.senseRubble(loc_n2_5);
			if (dist_n2_5 > dist_n1_5 + pass_n2_5) {
				dist_n2_5 = dist_n1_5 + pass_n2_5;
				direc_n2_5 = direc_n1_5;
			}
			
			if (dist_n2_5 > dist_n1_4 + pass_n2_5) {
				dist_n2_5 = dist_n1_4 + pass_n2_5;
				direc_n2_5 = direc_n1_4;
			}
			
			if (dist_n2_5 > dist_n2_4 + pass_n2_5) {
				dist_n2_5 = dist_n2_4 + pass_n2_5;
				direc_n2_5 = direc_n2_4;
			}
			
			if (dist_n2_5 > dist_n3_4 + pass_n2_5) {
				dist_n2_5 = dist_n3_4 + pass_n2_5;
				direc_n2_5 = direc_n3_4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_n5)) {
			pass_2_n5 = 10+rc.senseRubble(loc_2_n5);
			if (dist_2_n5 > dist_2_n4 + pass_2_n5) {
				dist_2_n5 = dist_2_n4 + pass_2_n5;
				direc_2_n5 = direc_2_n4;
			}
			
			if (dist_2_n5 > dist_3_n4 + pass_2_n5) {
				dist_2_n5 = dist_3_n4 + pass_2_n5;
				direc_2_n5 = direc_3_n4;
			}
			
			if (dist_2_n5 > dist_1_n5 + pass_2_n5) {
				dist_2_n5 = dist_1_n5 + pass_2_n5;
				direc_2_n5 = direc_1_n5;
			}
			
			if (dist_2_n5 > dist_1_n4 + pass_2_n5) {
				dist_2_n5 = dist_1_n4 + pass_2_n5;
				direc_2_n5 = direc_1_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_2_5)) {
			pass_2_5 = 10+rc.senseRubble(loc_2_5);
			if (dist_2_5 > dist_3_4 + pass_2_5) {
				dist_2_5 = dist_3_4 + pass_2_5;
				direc_2_5 = direc_3_4;
			}
			
			if (dist_2_5 > dist_2_4 + pass_2_5) {
				dist_2_5 = dist_2_4 + pass_2_5;
				direc_2_5 = direc_2_4;
			}
			
			if (dist_2_5 > dist_1_4 + pass_2_5) {
				dist_2_5 = dist_1_4 + pass_2_5;
				direc_2_5 = direc_1_4;
			}
			
			if (dist_2_5 > dist_1_5 + pass_2_5) {
				dist_2_5 = dist_1_5 + pass_2_5;
				direc_2_5 = direc_1_5;
			}
			
		}
		
		if (rc.canSenseLocation(loc_5_n2)) {
			pass_5_n2 = 10+rc.senseRubble(loc_5_n2);
			if (dist_5_n2 > dist_5_n1 + pass_5_n2) {
				dist_5_n2 = dist_5_n1 + pass_5_n2;
				direc_5_n2 = direc_5_n1;
			}
			
			if (dist_5_n2 > dist_4_n3 + pass_5_n2) {
				dist_5_n2 = dist_4_n3 + pass_5_n2;
				direc_5_n2 = direc_4_n3;
			}
			
			if (dist_5_n2 > dist_4_n2 + pass_5_n2) {
				dist_5_n2 = dist_4_n2 + pass_5_n2;
				direc_5_n2 = direc_4_n2;
			}
			
			if (dist_5_n2 > dist_4_n1 + pass_5_n2) {
				dist_5_n2 = dist_4_n1 + pass_5_n2;
				direc_5_n2 = direc_4_n1;
			}
			
		}
		
		if (rc.canSenseLocation(loc_5_2)) {
			pass_5_2 = 10+rc.senseRubble(loc_5_2);
			if (dist_5_2 > dist_5_1 + pass_5_2) {
				dist_5_2 = dist_5_1 + pass_5_2;
				direc_5_2 = direc_5_1;
			}
			
			if (dist_5_2 > dist_4_1 + pass_5_2) {
				dist_5_2 = dist_4_1 + pass_5_2;
				direc_5_2 = direc_4_1;
			}
			
			if (dist_5_2 > dist_4_2 + pass_5_2) {
				dist_5_2 = dist_4_2 + pass_5_2;
				direc_5_2 = direc_4_2;
			}
			
			if (dist_5_2 > dist_4_3 + pass_5_2) {
				dist_5_2 = dist_4_3 + pass_5_2;
				direc_5_2 = direc_4_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_n4)) {
			pass_n4_n4 = 10+rc.senseRubble(loc_n4_n4);
			if (dist_n4_n4 > dist_n4_n3 + pass_n4_n4) {
				dist_n4_n4 = dist_n4_n3 + pass_n4_n4;
				direc_n4_n4 = direc_n4_n3;
			}
			
			if (dist_n4_n4 > dist_n3_n3 + pass_n4_n4) {
				dist_n4_n4 = dist_n3_n3 + pass_n4_n4;
				direc_n4_n4 = direc_n3_n3;
			}
			
			if (dist_n4_n4 > dist_n3_n4 + pass_n4_n4) {
				dist_n4_n4 = dist_n3_n4 + pass_n4_n4;
				direc_n4_n4 = direc_n3_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n4_4)) {
			pass_n4_4 = 10+rc.senseRubble(loc_n4_4);
			if (dist_n4_4 > dist_n3_4 + pass_n4_4) {
				dist_n4_4 = dist_n3_4 + pass_n4_4;
				direc_n4_4 = direc_n3_4;
			}
			
			if (dist_n4_4 > dist_n3_3 + pass_n4_4) {
				dist_n4_4 = dist_n3_3 + pass_n4_4;
				direc_n4_4 = direc_n3_3;
			}
			
			if (dist_n4_4 > dist_n4_3 + pass_n4_4) {
				dist_n4_4 = dist_n4_3 + pass_n4_4;
				direc_n4_4 = direc_n4_3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_n4)) {
			pass_4_n4 = 10+rc.senseRubble(loc_4_n4);
			if (dist_4_n4 > dist_4_n3 + pass_4_n4) {
				dist_4_n4 = dist_4_n3 + pass_4_n4;
				direc_4_n4 = direc_4_n3;
			}
			
			if (dist_4_n4 > dist_3_n4 + pass_4_n4) {
				dist_4_n4 = dist_3_n4 + pass_4_n4;
				direc_4_n4 = direc_3_n4;
			}
			
			if (dist_4_n4 > dist_3_n3 + pass_4_n4) {
				dist_4_n4 = dist_3_n3 + pass_4_n4;
				direc_4_n4 = direc_3_n3;
			}
			
		}
		
		if (rc.canSenseLocation(loc_4_4)) {
			pass_4_4 = 10+rc.senseRubble(loc_4_4);
			if (dist_4_4 > dist_4_3 + pass_4_4) {
				dist_4_4 = dist_4_3 + pass_4_4;
				direc_4_4 = direc_4_3;
			}
			
			if (dist_4_4 > dist_3_3 + pass_4_4) {
				dist_4_4 = dist_3_3 + pass_4_4;
				direc_4_4 = direc_3_3;
			}
			
			if (dist_4_4 > dist_3_4 + pass_4_4) {
				dist_4_4 = dist_3_4 + pass_4_4;
				direc_4_4 = direc_3_4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n5_n3)) {
			pass_n5_n3 = 10+rc.senseRubble(loc_n5_n3);
			if (dist_n5_n3 > dist_n5_n2 + pass_n5_n3) {
				dist_n5_n3 = dist_n5_n2 + pass_n5_n3;
				direc_n5_n3 = direc_n5_n2;
			}
			
			if (dist_n5_n3 > dist_n4_n2 + pass_n5_n3) {
				dist_n5_n3 = dist_n4_n2 + pass_n5_n3;
				direc_n5_n3 = direc_n4_n2;
			}
			
			if (dist_n5_n3 > dist_n4_n3 + pass_n5_n3) {
				dist_n5_n3 = dist_n4_n3 + pass_n5_n3;
				direc_n5_n3 = direc_n4_n3;
			}
			
			if (dist_n5_n3 > dist_n4_n4 + pass_n5_n3) {
				dist_n5_n3 = dist_n4_n4 + pass_n5_n3;
				direc_n5_n3 = direc_n4_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n5_3)) {
			pass_n5_3 = 10+rc.senseRubble(loc_n5_3);
			if (dist_n5_3 > dist_n4_4 + pass_n5_3) {
				dist_n5_3 = dist_n4_4 + pass_n5_3;
				direc_n5_3 = direc_n4_4;
			}
			
			if (dist_n5_3 > dist_n4_3 + pass_n5_3) {
				dist_n5_3 = dist_n4_3 + pass_n5_3;
				direc_n5_3 = direc_n4_3;
			}
			
			if (dist_n5_3 > dist_n4_2 + pass_n5_3) {
				dist_n5_3 = dist_n4_2 + pass_n5_3;
				direc_n5_3 = direc_n4_2;
			}
			
			if (dist_n5_3 > dist_n5_2 + pass_n5_3) {
				dist_n5_3 = dist_n5_2 + pass_n5_3;
				direc_n5_3 = direc_n5_2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_n5)) {
			pass_n3_n5 = 10+rc.senseRubble(loc_n3_n5);
			if (dist_n3_n5 > dist_n3_n4 + pass_n3_n5) {
				dist_n3_n5 = dist_n3_n4 + pass_n3_n5;
				direc_n3_n5 = direc_n3_n4;
			}
			
			if (dist_n3_n5 > dist_n2_n4 + pass_n3_n5) {
				dist_n3_n5 = dist_n2_n4 + pass_n3_n5;
				direc_n3_n5 = direc_n2_n4;
			}
			
			if (dist_n3_n5 > dist_n2_n5 + pass_n3_n5) {
				dist_n3_n5 = dist_n2_n5 + pass_n3_n5;
				direc_n3_n5 = direc_n2_n5;
			}
			
			if (dist_n3_n5 > dist_n4_n4 + pass_n3_n5) {
				dist_n3_n5 = dist_n4_n4 + pass_n3_n5;
				direc_n3_n5 = direc_n4_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_n3_5)) {
			pass_n3_5 = 10+rc.senseRubble(loc_n3_5);
			if (dist_n3_5 > dist_n2_5 + pass_n3_5) {
				dist_n3_5 = dist_n2_5 + pass_n3_5;
				direc_n3_5 = direc_n2_5;
			}
			
			if (dist_n3_5 > dist_n2_4 + pass_n3_5) {
				dist_n3_5 = dist_n2_4 + pass_n3_5;
				direc_n3_5 = direc_n2_4;
			}
			
			if (dist_n3_5 > dist_n3_4 + pass_n3_5) {
				dist_n3_5 = dist_n3_4 + pass_n3_5;
				direc_n3_5 = direc_n3_4;
			}
			
			if (dist_n3_5 > dist_n4_4 + pass_n3_5) {
				dist_n3_5 = dist_n4_4 + pass_n3_5;
				direc_n3_5 = direc_n4_4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_n5)) {
			pass_3_n5 = 10+rc.senseRubble(loc_3_n5);
			if (dist_3_n5 > dist_3_n4 + pass_3_n5) {
				dist_3_n5 = dist_3_n4 + pass_3_n5;
				direc_3_n5 = direc_3_n4;
			}
			
			if (dist_3_n5 > dist_4_n4 + pass_3_n5) {
				dist_3_n5 = dist_4_n4 + pass_3_n5;
				direc_3_n5 = direc_4_n4;
			}
			
			if (dist_3_n5 > dist_2_n5 + pass_3_n5) {
				dist_3_n5 = dist_2_n5 + pass_3_n5;
				direc_3_n5 = direc_2_n5;
			}
			
			if (dist_3_n5 > dist_2_n4 + pass_3_n5) {
				dist_3_n5 = dist_2_n4 + pass_3_n5;
				direc_3_n5 = direc_2_n4;
			}
			
		}
		
		if (rc.canSenseLocation(loc_3_5)) {
			pass_3_5 = 10+rc.senseRubble(loc_3_5);
			if (dist_3_5 > dist_4_4 + pass_3_5) {
				dist_3_5 = dist_4_4 + pass_3_5;
				direc_3_5 = direc_4_4;
			}
			
			if (dist_3_5 > dist_3_4 + pass_3_5) {
				dist_3_5 = dist_3_4 + pass_3_5;
				direc_3_5 = direc_3_4;
			}
			
			if (dist_3_5 > dist_2_4 + pass_3_5) {
				dist_3_5 = dist_2_4 + pass_3_5;
				direc_3_5 = direc_2_4;
			}
			
			if (dist_3_5 > dist_2_5 + pass_3_5) {
				dist_3_5 = dist_2_5 + pass_3_5;
				direc_3_5 = direc_2_5;
			}
			
		}
		
		if (rc.canSenseLocation(loc_5_n3)) {
			pass_5_n3 = 10+rc.senseRubble(loc_5_n3);
			if (dist_5_n3 > dist_5_n2 + pass_5_n3) {
				dist_5_n3 = dist_5_n2 + pass_5_n3;
				direc_5_n3 = direc_5_n2;
			}
			
			if (dist_5_n3 > dist_4_n4 + pass_5_n3) {
				dist_5_n3 = dist_4_n4 + pass_5_n3;
				direc_5_n3 = direc_4_n4;
			}
			
			if (dist_5_n3 > dist_4_n3 + pass_5_n3) {
				dist_5_n3 = dist_4_n3 + pass_5_n3;
				direc_5_n3 = direc_4_n3;
			}
			
			if (dist_5_n3 > dist_4_n2 + pass_5_n3) {
				dist_5_n3 = dist_4_n2 + pass_5_n3;
				direc_5_n3 = direc_4_n2;
			}
			
		}
		
		if (rc.canSenseLocation(loc_5_3)) {
			pass_5_3 = 10+rc.senseRubble(loc_5_3);
			if (dist_5_3 > dist_5_2 + pass_5_3) {
				dist_5_3 = dist_5_2 + pass_5_3;
				direc_5_3 = direc_5_2;
			}
			
			if (dist_5_3 > dist_4_2 + pass_5_3) {
				dist_5_3 = dist_4_2 + pass_5_3;
				direc_5_3 = direc_4_2;
			}
			
			if (dist_5_3 > dist_4_3 + pass_5_3) {
				dist_5_3 = dist_4_3 + pass_5_3;
				direc_5_3 = direc_4_3;
			}
			
			if (dist_5_3 > dist_4_4 + pass_5_3) {
				dist_5_3 = dist_4_4 + pass_5_3;
				direc_5_3 = direc_4_4;
			}
			
		}
		
		
		int dx = dest.x - me.x;
		int dy = dest.y - me.y;
		
		switch(dx) {
			case -5:
				switch(dy) {
					case -3:
						return direc_n5_n3;
					case -2:
						return direc_n5_n2;
					case -1:
						return direc_n5_n1;
					case 0:
						return direc_n5_0;
					case 1:
						return direc_n5_1;
					case 2:
						return direc_n5_2;
					case 3:
						return direc_n5_3;
				}
		break;
			case -4:
				switch(dy) {
					case -4:
						return direc_n4_n4;
					case -3:
						return direc_n4_n3;
					case -2:
						return direc_n4_n2;
					case -1:
						return direc_n4_n1;
					case 0:
						return direc_n4_0;
					case 1:
						return direc_n4_1;
					case 2:
						return direc_n4_2;
					case 3:
						return direc_n4_3;
					case 4:
						return direc_n4_4;
				}
		break;
			case -3:
				switch(dy) {
					case -5:
						return direc_n3_n5;
					case -4:
						return direc_n3_n4;
					case -3:
						return direc_n3_n3;
					case -2:
						return direc_n3_n2;
					case -1:
						return direc_n3_n1;
					case 0:
						return direc_n3_0;
					case 1:
						return direc_n3_1;
					case 2:
						return direc_n3_2;
					case 3:
						return direc_n3_3;
					case 4:
						return direc_n3_4;
					case 5:
						return direc_n3_5;
				}
		break;
			case -2:
				switch(dy) {
					case -5:
						return direc_n2_n5;
					case -4:
						return direc_n2_n4;
					case -3:
						return direc_n2_n3;
					case -2:
						return direc_n2_n2;
					case -1:
						return direc_n2_n1;
					case 0:
						return direc_n2_0;
					case 1:
						return direc_n2_1;
					case 2:
						return direc_n2_2;
					case 3:
						return direc_n2_3;
					case 4:
						return direc_n2_4;
					case 5:
						return direc_n2_5;
				}
		break;
			case -1:
				switch(dy) {
					case -5:
						return direc_n1_n5;
					case -4:
						return direc_n1_n4;
					case -3:
						return direc_n1_n3;
					case -2:
						return direc_n1_n2;
					case -1:
						return direc_n1_n1;
					case 0:
						return direc_n1_0;
					case 1:
						return direc_n1_1;
					case 2:
						return direc_n1_2;
					case 3:
						return direc_n1_3;
					case 4:
						return direc_n1_4;
					case 5:
						return direc_n1_5;
				}
		break;
			case 0:
				switch(dy) {
					case -5:
						return direc_0_n5;
					case -4:
						return direc_0_n4;
					case -3:
						return direc_0_n3;
					case -2:
						return direc_0_n2;
					case -1:
						return direc_0_n1;
					case 1:
						return direc_0_1;
					case 2:
						return direc_0_2;
					case 3:
						return direc_0_3;
					case 4:
						return direc_0_4;
					case 5:
						return direc_0_5;
				}
		break;
			case 1:
				switch(dy) {
					case -5:
						return direc_1_n5;
					case -4:
						return direc_1_n4;
					case -3:
						return direc_1_n3;
					case -2:
						return direc_1_n2;
					case -1:
						return direc_1_n1;
					case 0:
						return direc_1_0;
					case 1:
						return direc_1_1;
					case 2:
						return direc_1_2;
					case 3:
						return direc_1_3;
					case 4:
						return direc_1_4;
					case 5:
						return direc_1_5;
				}
		break;
			case 2:
				switch(dy) {
					case -5:
						return direc_2_n5;
					case -4:
						return direc_2_n4;
					case -3:
						return direc_2_n3;
					case -2:
						return direc_2_n2;
					case -1:
						return direc_2_n1;
					case 0:
						return direc_2_0;
					case 1:
						return direc_2_1;
					case 2:
						return direc_2_2;
					case 3:
						return direc_2_3;
					case 4:
						return direc_2_4;
					case 5:
						return direc_2_5;
				}
		break;
			case 3:
				switch(dy) {
					case -5:
						return direc_3_n5;
					case -4:
						return direc_3_n4;
					case -3:
						return direc_3_n3;
					case -2:
						return direc_3_n2;
					case -1:
						return direc_3_n1;
					case 0:
						return direc_3_0;
					case 1:
						return direc_3_1;
					case 2:
						return direc_3_2;
					case 3:
						return direc_3_3;
					case 4:
						return direc_3_4;
					case 5:
						return direc_3_5;
				}
		break;
			case 4:
				switch(dy) {
					case -4:
						return direc_4_n4;
					case -3:
						return direc_4_n3;
					case -2:
						return direc_4_n2;
					case -1:
						return direc_4_n1;
					case 0:
						return direc_4_0;
					case 1:
						return direc_4_1;
					case 2:
						return direc_4_2;
					case 3:
						return direc_4_3;
					case 4:
						return direc_4_4;
				}
		break;
			case 5:
				switch(dy) {
					case -3:
						return direc_5_n3;
					case -2:
						return direc_5_n2;
					case -1:
						return direc_5_n1;
					case 0:
						return direc_5_0;
					case 1:
						return direc_5_1;
					case 2:
						return direc_5_2;
					case 3:
						return direc_5_3;
				}
		break;
		}
		
		bestDistance = 1000000;
		bestDirection = null;
		
		switch(me.directionTo(dest)) {
			case NORTH:
				currentDistance = dist_0_5 + 3*Math.sqrt(loc_0_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_5;
				}
				
				currentDistance = dist_n1_5 + 3*Math.sqrt(loc_n1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_5;
				}
				
				currentDistance = dist_1_5 + 3*Math.sqrt(loc_1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_5;
				}
				
				currentDistance = dist_n2_5 + 3*Math.sqrt(loc_n2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_5;
				}
				
				currentDistance = dist_2_5 + 3*Math.sqrt(loc_2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_5;
				}
				
				currentDistance = dist_n3_5 + 3*Math.sqrt(loc_n3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_5;
				}
				
				currentDistance = dist_3_5 + 3*Math.sqrt(loc_3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_5;
				}
				
				currentDistance = dist_n3_4 + 3*Math.sqrt(loc_n3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_4;
				}
				
				currentDistance = dist_3_4 + 3*Math.sqrt(loc_3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_4;
				}
				
				currentDistance = dist_n4_4 + 3*Math.sqrt(loc_n4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_4;
				}
				
				currentDistance = dist_4_4 + 3*Math.sqrt(loc_4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_4;
				}
				
				currentDistance = dist_n4_3 + 3*Math.sqrt(loc_n4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_3;
				}
				
				currentDistance = dist_4_3 + 3*Math.sqrt(loc_4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_3;
				}
				
				currentDistance = dist_n5_3 + 3*Math.sqrt(loc_n5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_3;
				}
				
				currentDistance = dist_5_3 + 3*Math.sqrt(loc_5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_3;
				}
				
				currentDistance = dist_n5_2 + 3*Math.sqrt(loc_n5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_2;
				}
				
				currentDistance = dist_5_2 + 3*Math.sqrt(loc_5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_2;
				}
				
				currentDistance = dist_n5_1 + 3*Math.sqrt(loc_n5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_1;
				}
				
				currentDistance = dist_5_1 + 3*Math.sqrt(loc_5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_1;
				}
				
				currentDistance = dist_n5_0 + 3*Math.sqrt(loc_n5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_0;
				}
				
				currentDistance = dist_5_0 + 3*Math.sqrt(loc_5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_0;
				}
				
				currentDistance = dist_n5_n1 + 3*Math.sqrt(loc_n5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n1;
				}
				
				currentDistance = dist_5_n1 + 3*Math.sqrt(loc_5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n1;
				}
				
				currentDistance = dist_n5_n2 + 3*Math.sqrt(loc_n5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n2;
				}
				
				currentDistance = dist_5_n2 + 3*Math.sqrt(loc_5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n2;
				}
				
				currentDistance = dist_n4_n3 + 3*Math.sqrt(loc_n4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n3;
				}
				
				currentDistance = dist_4_n3 + 3*Math.sqrt(loc_4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n3;
				}
				
				currentDistance = dist_n5_n3 + 3*Math.sqrt(loc_n5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n3;
				}
				
				currentDistance = dist_5_n3 + 3*Math.sqrt(loc_5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n3;
				}
				
				currentDistance = dist_n3_n4 + 3*Math.sqrt(loc_n3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n4;
				}
				
				currentDistance = dist_3_n4 + 3*Math.sqrt(loc_3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n4;
				}
				
				currentDistance = dist_n4_n4 + 3*Math.sqrt(loc_n4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n4;
				}
				
				currentDistance = dist_4_n4 + 3*Math.sqrt(loc_4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n4;
				}
				
				currentDistance = dist_0_n5 + 3*Math.sqrt(loc_0_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_n5;
				}
				
				currentDistance = dist_n1_n5 + 3*Math.sqrt(loc_n1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_n5;
				}
				
				currentDistance = dist_1_n5 + 3*Math.sqrt(loc_1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_n5;
				}
				
				currentDistance = dist_n2_n5 + 3*Math.sqrt(loc_n2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_n5;
				}
				
				currentDistance = dist_2_n5 + 3*Math.sqrt(loc_2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_n5;
				}
				
				currentDistance = dist_n3_n5 + 3*Math.sqrt(loc_n3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n5;
				}
				
				currentDistance = dist_3_n5 + 3*Math.sqrt(loc_3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n5;
				}
				
				return bestDirection;
				
			case NORTHEAST:
				currentDistance = dist_4_4 + 3*Math.sqrt(loc_4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_4;
				}
				
				currentDistance = dist_3_5 + 3*Math.sqrt(loc_3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_5;
				}
				
				currentDistance = dist_5_3 + 3*Math.sqrt(loc_5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_3;
				}
				
				currentDistance = dist_3_4 + 3*Math.sqrt(loc_3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_4;
				}
				
				currentDistance = dist_4_3 + 3*Math.sqrt(loc_4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_3;
				}
				
				currentDistance = dist_2_5 + 3*Math.sqrt(loc_2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_5;
				}
				
				currentDistance = dist_5_2 + 3*Math.sqrt(loc_5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_2;
				}
				
				currentDistance = dist_1_5 + 3*Math.sqrt(loc_1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_5;
				}
				
				currentDistance = dist_5_1 + 3*Math.sqrt(loc_5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_1;
				}
				
				currentDistance = dist_0_5 + 3*Math.sqrt(loc_0_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_5;
				}
				
				currentDistance = dist_5_0 + 3*Math.sqrt(loc_5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_0;
				}
				
				currentDistance = dist_n1_5 + 3*Math.sqrt(loc_n1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_5;
				}
				
				currentDistance = dist_5_n1 + 3*Math.sqrt(loc_5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n1;
				}
				
				currentDistance = dist_n2_5 + 3*Math.sqrt(loc_n2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_5;
				}
				
				currentDistance = dist_5_n2 + 3*Math.sqrt(loc_5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n2;
				}
				
				currentDistance = dist_n3_5 + 3*Math.sqrt(loc_n3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_5;
				}
				
				currentDistance = dist_5_n3 + 3*Math.sqrt(loc_5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n3;
				}
				
				currentDistance = dist_n3_4 + 3*Math.sqrt(loc_n3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_4;
				}
				
				currentDistance = dist_4_n3 + 3*Math.sqrt(loc_4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n3;
				}
				
				currentDistance = dist_n4_4 + 3*Math.sqrt(loc_n4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_4;
				}
				
				currentDistance = dist_4_n4 + 3*Math.sqrt(loc_4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n4;
				}
				
				currentDistance = dist_n4_3 + 3*Math.sqrt(loc_n4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_3;
				}
				
				currentDistance = dist_3_n4 + 3*Math.sqrt(loc_3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n4;
				}
				
				currentDistance = dist_n5_3 + 3*Math.sqrt(loc_n5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_3;
				}
				
				currentDistance = dist_3_n5 + 3*Math.sqrt(loc_3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n5;
				}
				
				currentDistance = dist_n5_2 + 3*Math.sqrt(loc_n5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_2;
				}
				
				currentDistance = dist_2_n5 + 3*Math.sqrt(loc_2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_n5;
				}
				
				currentDistance = dist_n5_1 + 3*Math.sqrt(loc_n5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_1;
				}
				
				currentDistance = dist_1_n5 + 3*Math.sqrt(loc_1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_n5;
				}
				
				currentDistance = dist_n5_0 + 3*Math.sqrt(loc_n5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_0;
				}
				
				currentDistance = dist_0_n5 + 3*Math.sqrt(loc_0_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_n5;
				}
				
				currentDistance = dist_n5_n1 + 3*Math.sqrt(loc_n5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n1;
				}
				
				currentDistance = dist_n1_n5 + 3*Math.sqrt(loc_n1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_n5;
				}
				
				currentDistance = dist_n4_n3 + 3*Math.sqrt(loc_n4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n3;
				}
				
				currentDistance = dist_n3_n4 + 3*Math.sqrt(loc_n3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n4;
				}
				
				currentDistance = dist_n5_n2 + 3*Math.sqrt(loc_n5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n2;
				}
				
				currentDistance = dist_n2_n5 + 3*Math.sqrt(loc_n2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_n5;
				}
				
				currentDistance = dist_n4_n4 + 3*Math.sqrt(loc_n4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n4;
				}
				
				currentDistance = dist_n5_n3 + 3*Math.sqrt(loc_n5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n3;
				}
				
				currentDistance = dist_n3_n5 + 3*Math.sqrt(loc_n3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n5;
				}
				
				return bestDirection;
				
			case EAST:
				currentDistance = dist_5_0 + 3*Math.sqrt(loc_5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_0;
				}
				
				currentDistance = dist_5_n1 + 3*Math.sqrt(loc_5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n1;
				}
				
				currentDistance = dist_5_1 + 3*Math.sqrt(loc_5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_1;
				}
				
				currentDistance = dist_5_n2 + 3*Math.sqrt(loc_5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n2;
				}
				
				currentDistance = dist_5_2 + 3*Math.sqrt(loc_5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_2;
				}
				
				currentDistance = dist_5_n3 + 3*Math.sqrt(loc_5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n3;
				}
				
				currentDistance = dist_5_3 + 3*Math.sqrt(loc_5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_3;
				}
				
				currentDistance = dist_4_n3 + 3*Math.sqrt(loc_4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n3;
				}
				
				currentDistance = dist_4_3 + 3*Math.sqrt(loc_4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_3;
				}
				
				currentDistance = dist_4_n4 + 3*Math.sqrt(loc_4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n4;
				}
				
				currentDistance = dist_4_4 + 3*Math.sqrt(loc_4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_4;
				}
				
				currentDistance = dist_3_n4 + 3*Math.sqrt(loc_3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n4;
				}
				
				currentDistance = dist_3_4 + 3*Math.sqrt(loc_3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_4;
				}
				
				currentDistance = dist_3_n5 + 3*Math.sqrt(loc_3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n5;
				}
				
				currentDistance = dist_3_5 + 3*Math.sqrt(loc_3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_5;
				}
				
				currentDistance = dist_2_n5 + 3*Math.sqrt(loc_2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_n5;
				}
				
				currentDistance = dist_2_5 + 3*Math.sqrt(loc_2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_5;
				}
				
				currentDistance = dist_1_n5 + 3*Math.sqrt(loc_1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_n5;
				}
				
				currentDistance = dist_1_5 + 3*Math.sqrt(loc_1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_5;
				}
				
				currentDistance = dist_0_n5 + 3*Math.sqrt(loc_0_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_n5;
				}
				
				currentDistance = dist_0_5 + 3*Math.sqrt(loc_0_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_5;
				}
				
				currentDistance = dist_n1_n5 + 3*Math.sqrt(loc_n1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_n5;
				}
				
				currentDistance = dist_n1_5 + 3*Math.sqrt(loc_n1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_5;
				}
				
				currentDistance = dist_n2_n5 + 3*Math.sqrt(loc_n2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_n5;
				}
				
				currentDistance = dist_n2_5 + 3*Math.sqrt(loc_n2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_5;
				}
				
				currentDistance = dist_n3_n4 + 3*Math.sqrt(loc_n3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n4;
				}
				
				currentDistance = dist_n3_4 + 3*Math.sqrt(loc_n3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_4;
				}
				
				currentDistance = dist_n3_n5 + 3*Math.sqrt(loc_n3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n5;
				}
				
				currentDistance = dist_n3_5 + 3*Math.sqrt(loc_n3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_5;
				}
				
				currentDistance = dist_n4_n3 + 3*Math.sqrt(loc_n4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n3;
				}
				
				currentDistance = dist_n4_3 + 3*Math.sqrt(loc_n4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_3;
				}
				
				currentDistance = dist_n4_n4 + 3*Math.sqrt(loc_n4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n4;
				}
				
				currentDistance = dist_n4_4 + 3*Math.sqrt(loc_n4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_4;
				}
				
				currentDistance = dist_n5_0 + 3*Math.sqrt(loc_n5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_0;
				}
				
				currentDistance = dist_n5_n1 + 3*Math.sqrt(loc_n5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n1;
				}
				
				currentDistance = dist_n5_1 + 3*Math.sqrt(loc_n5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_1;
				}
				
				currentDistance = dist_n5_n2 + 3*Math.sqrt(loc_n5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n2;
				}
				
				currentDistance = dist_n5_2 + 3*Math.sqrt(loc_n5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_2;
				}
				
				currentDistance = dist_n5_n3 + 3*Math.sqrt(loc_n5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n3;
				}
				
				currentDistance = dist_n5_3 + 3*Math.sqrt(loc_n5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_3;
				}
				
				return bestDirection;
				
			case SOUTHEAST:
				currentDistance = dist_4_n4 + 3*Math.sqrt(loc_4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n4;
				}
				
				currentDistance = dist_3_n5 + 3*Math.sqrt(loc_3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n5;
				}
				
				currentDistance = dist_5_n3 + 3*Math.sqrt(loc_5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n3;
				}
				
				currentDistance = dist_3_n4 + 3*Math.sqrt(loc_3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n4;
				}
				
				currentDistance = dist_4_n3 + 3*Math.sqrt(loc_4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n3;
				}
				
				currentDistance = dist_2_n5 + 3*Math.sqrt(loc_2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_n5;
				}
				
				currentDistance = dist_5_n2 + 3*Math.sqrt(loc_5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n2;
				}
				
				currentDistance = dist_1_n5 + 3*Math.sqrt(loc_1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_n5;
				}
				
				currentDistance = dist_5_n1 + 3*Math.sqrt(loc_5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n1;
				}
				
				currentDistance = dist_0_n5 + 3*Math.sqrt(loc_0_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_n5;
				}
				
				currentDistance = dist_5_0 + 3*Math.sqrt(loc_5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_0;
				}
				
				currentDistance = dist_n1_n5 + 3*Math.sqrt(loc_n1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_n5;
				}
				
				currentDistance = dist_5_1 + 3*Math.sqrt(loc_5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_1;
				}
				
				currentDistance = dist_n2_n5 + 3*Math.sqrt(loc_n2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_n5;
				}
				
				currentDistance = dist_5_2 + 3*Math.sqrt(loc_5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_2;
				}
				
				currentDistance = dist_n3_n5 + 3*Math.sqrt(loc_n3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n5;
				}
				
				currentDistance = dist_5_3 + 3*Math.sqrt(loc_5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_3;
				}
				
				currentDistance = dist_n3_n4 + 3*Math.sqrt(loc_n3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n4;
				}
				
				currentDistance = dist_4_3 + 3*Math.sqrt(loc_4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_3;
				}
				
				currentDistance = dist_n4_n4 + 3*Math.sqrt(loc_n4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n4;
				}
				
				currentDistance = dist_4_4 + 3*Math.sqrt(loc_4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_4;
				}
				
				currentDistance = dist_n4_n3 + 3*Math.sqrt(loc_n4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n3;
				}
				
				currentDistance = dist_3_4 + 3*Math.sqrt(loc_3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_4;
				}
				
				currentDistance = dist_n5_n3 + 3*Math.sqrt(loc_n5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n3;
				}
				
				currentDistance = dist_3_5 + 3*Math.sqrt(loc_3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_5;
				}
				
				currentDistance = dist_n5_n2 + 3*Math.sqrt(loc_n5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n2;
				}
				
				currentDistance = dist_2_5 + 3*Math.sqrt(loc_2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_5;
				}
				
				currentDistance = dist_n5_n1 + 3*Math.sqrt(loc_n5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n1;
				}
				
				currentDistance = dist_1_5 + 3*Math.sqrt(loc_1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_5;
				}
				
				currentDistance = dist_n5_0 + 3*Math.sqrt(loc_n5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_0;
				}
				
				currentDistance = dist_0_5 + 3*Math.sqrt(loc_0_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_5;
				}
				
				currentDistance = dist_n5_1 + 3*Math.sqrt(loc_n5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_1;
				}
				
				currentDistance = dist_n1_5 + 3*Math.sqrt(loc_n1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_5;
				}
				
				currentDistance = dist_n4_3 + 3*Math.sqrt(loc_n4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_3;
				}
				
				currentDistance = dist_n3_4 + 3*Math.sqrt(loc_n3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_4;
				}
				
				currentDistance = dist_n5_2 + 3*Math.sqrt(loc_n5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_2;
				}
				
				currentDistance = dist_n2_5 + 3*Math.sqrt(loc_n2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_5;
				}
				
				currentDistance = dist_n4_4 + 3*Math.sqrt(loc_n4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_4;
				}
				
				currentDistance = dist_n5_3 + 3*Math.sqrt(loc_n5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_3;
				}
				
				currentDistance = dist_n3_5 + 3*Math.sqrt(loc_n3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_5;
				}
				
				return bestDirection;
				
			case SOUTH:
				currentDistance = dist_0_n5 + 3*Math.sqrt(loc_0_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_n5;
				}
				
				currentDistance = dist_n1_n5 + 3*Math.sqrt(loc_n1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_n5;
				}
				
				currentDistance = dist_1_n5 + 3*Math.sqrt(loc_1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_n5;
				}
				
				currentDistance = dist_n2_n5 + 3*Math.sqrt(loc_n2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_n5;
				}
				
				currentDistance = dist_2_n5 + 3*Math.sqrt(loc_2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_n5;
				}
				
				currentDistance = dist_n3_n5 + 3*Math.sqrt(loc_n3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n5;
				}
				
				currentDistance = dist_3_n5 + 3*Math.sqrt(loc_3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n5;
				}
				
				currentDistance = dist_n3_n4 + 3*Math.sqrt(loc_n3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n4;
				}
				
				currentDistance = dist_3_n4 + 3*Math.sqrt(loc_3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n4;
				}
				
				currentDistance = dist_n4_n4 + 3*Math.sqrt(loc_n4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n4;
				}
				
				currentDistance = dist_4_n4 + 3*Math.sqrt(loc_4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n4;
				}
				
				currentDistance = dist_n4_n3 + 3*Math.sqrt(loc_n4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n3;
				}
				
				currentDistance = dist_4_n3 + 3*Math.sqrt(loc_4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n3;
				}
				
				currentDistance = dist_n5_n3 + 3*Math.sqrt(loc_n5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n3;
				}
				
				currentDistance = dist_5_n3 + 3*Math.sqrt(loc_5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n3;
				}
				
				currentDistance = dist_n5_n2 + 3*Math.sqrt(loc_n5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n2;
				}
				
				currentDistance = dist_5_n2 + 3*Math.sqrt(loc_5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n2;
				}
				
				currentDistance = dist_n5_n1 + 3*Math.sqrt(loc_n5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n1;
				}
				
				currentDistance = dist_5_n1 + 3*Math.sqrt(loc_5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n1;
				}
				
				currentDistance = dist_n5_0 + 3*Math.sqrt(loc_n5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_0;
				}
				
				currentDistance = dist_5_0 + 3*Math.sqrt(loc_5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_0;
				}
				
				currentDistance = dist_n5_1 + 3*Math.sqrt(loc_n5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_1;
				}
				
				currentDistance = dist_5_1 + 3*Math.sqrt(loc_5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_1;
				}
				
				currentDistance = dist_n5_2 + 3*Math.sqrt(loc_n5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_2;
				}
				
				currentDistance = dist_5_2 + 3*Math.sqrt(loc_5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_2;
				}
				
				currentDistance = dist_n4_3 + 3*Math.sqrt(loc_n4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_3;
				}
				
				currentDistance = dist_4_3 + 3*Math.sqrt(loc_4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_3;
				}
				
				currentDistance = dist_n5_3 + 3*Math.sqrt(loc_n5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_3;
				}
				
				currentDistance = dist_5_3 + 3*Math.sqrt(loc_5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_3;
				}
				
				currentDistance = dist_n3_4 + 3*Math.sqrt(loc_n3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_4;
				}
				
				currentDistance = dist_3_4 + 3*Math.sqrt(loc_3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_4;
				}
				
				currentDistance = dist_n4_4 + 3*Math.sqrt(loc_n4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_4;
				}
				
				currentDistance = dist_4_4 + 3*Math.sqrt(loc_4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_4;
				}
				
				currentDistance = dist_0_5 + 3*Math.sqrt(loc_0_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_5;
				}
				
				currentDistance = dist_n1_5 + 3*Math.sqrt(loc_n1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_5;
				}
				
				currentDistance = dist_1_5 + 3*Math.sqrt(loc_1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_5;
				}
				
				currentDistance = dist_n2_5 + 3*Math.sqrt(loc_n2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_5;
				}
				
				currentDistance = dist_2_5 + 3*Math.sqrt(loc_2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_5;
				}
				
				currentDistance = dist_n3_5 + 3*Math.sqrt(loc_n3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_5;
				}
				
				currentDistance = dist_3_5 + 3*Math.sqrt(loc_3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_5;
				}
				
				return bestDirection;
				
			case SOUTHWEST:
				currentDistance = dist_n4_n4 + 3*Math.sqrt(loc_n4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n4;
				}
				
				currentDistance = dist_n5_n3 + 3*Math.sqrt(loc_n5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n3;
				}
				
				currentDistance = dist_n3_n5 + 3*Math.sqrt(loc_n3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n5;
				}
				
				currentDistance = dist_n4_n3 + 3*Math.sqrt(loc_n4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n3;
				}
				
				currentDistance = dist_n3_n4 + 3*Math.sqrt(loc_n3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n4;
				}
				
				currentDistance = dist_n5_n2 + 3*Math.sqrt(loc_n5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n2;
				}
				
				currentDistance = dist_n2_n5 + 3*Math.sqrt(loc_n2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_n5;
				}
				
				currentDistance = dist_n5_n1 + 3*Math.sqrt(loc_n5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n1;
				}
				
				currentDistance = dist_n1_n5 + 3*Math.sqrt(loc_n1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_n5;
				}
				
				currentDistance = dist_n5_0 + 3*Math.sqrt(loc_n5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_0;
				}
				
				currentDistance = dist_0_n5 + 3*Math.sqrt(loc_0_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_n5;
				}
				
				currentDistance = dist_n5_1 + 3*Math.sqrt(loc_n5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_1;
				}
				
				currentDistance = dist_1_n5 + 3*Math.sqrt(loc_1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_n5;
				}
				
				currentDistance = dist_n5_2 + 3*Math.sqrt(loc_n5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_2;
				}
				
				currentDistance = dist_2_n5 + 3*Math.sqrt(loc_2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_n5;
				}
				
				currentDistance = dist_n5_3 + 3*Math.sqrt(loc_n5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_3;
				}
				
				currentDistance = dist_3_n5 + 3*Math.sqrt(loc_3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n5;
				}
				
				currentDistance = dist_n4_3 + 3*Math.sqrt(loc_n4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_3;
				}
				
				currentDistance = dist_3_n4 + 3*Math.sqrt(loc_3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n4;
				}
				
				currentDistance = dist_n4_4 + 3*Math.sqrt(loc_n4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_4;
				}
				
				currentDistance = dist_4_n4 + 3*Math.sqrt(loc_4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n4;
				}
				
				currentDistance = dist_n3_4 + 3*Math.sqrt(loc_n3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_4;
				}
				
				currentDistance = dist_4_n3 + 3*Math.sqrt(loc_4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n3;
				}
				
				currentDistance = dist_n3_5 + 3*Math.sqrt(loc_n3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_5;
				}
				
				currentDistance = dist_5_n3 + 3*Math.sqrt(loc_5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n3;
				}
				
				currentDistance = dist_n2_5 + 3*Math.sqrt(loc_n2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_5;
				}
				
				currentDistance = dist_5_n2 + 3*Math.sqrt(loc_5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n2;
				}
				
				currentDistance = dist_n1_5 + 3*Math.sqrt(loc_n1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_5;
				}
				
				currentDistance = dist_5_n1 + 3*Math.sqrt(loc_5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n1;
				}
				
				currentDistance = dist_0_5 + 3*Math.sqrt(loc_0_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_5;
				}
				
				currentDistance = dist_5_0 + 3*Math.sqrt(loc_5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_0;
				}
				
				currentDistance = dist_1_5 + 3*Math.sqrt(loc_1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_5;
				}
				
				currentDistance = dist_5_1 + 3*Math.sqrt(loc_5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_1;
				}
				
				currentDistance = dist_3_4 + 3*Math.sqrt(loc_3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_4;
				}
				
				currentDistance = dist_4_3 + 3*Math.sqrt(loc_4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_3;
				}
				
				currentDistance = dist_2_5 + 3*Math.sqrt(loc_2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_5;
				}
				
				currentDistance = dist_5_2 + 3*Math.sqrt(loc_5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_2;
				}
				
				currentDistance = dist_4_4 + 3*Math.sqrt(loc_4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_4;
				}
				
				currentDistance = dist_3_5 + 3*Math.sqrt(loc_3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_5;
				}
				
				currentDistance = dist_5_3 + 3*Math.sqrt(loc_5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_3;
				}
				
				return bestDirection;
				
			case WEST:
				currentDistance = dist_n5_0 + 3*Math.sqrt(loc_n5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_0;
				}
				
				currentDistance = dist_n5_n1 + 3*Math.sqrt(loc_n5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n1;
				}
				
				currentDistance = dist_n5_1 + 3*Math.sqrt(loc_n5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_1;
				}
				
				currentDistance = dist_n5_n2 + 3*Math.sqrt(loc_n5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n2;
				}
				
				currentDistance = dist_n5_2 + 3*Math.sqrt(loc_n5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_2;
				}
				
				currentDistance = dist_n5_n3 + 3*Math.sqrt(loc_n5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n3;
				}
				
				currentDistance = dist_n5_3 + 3*Math.sqrt(loc_n5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_3;
				}
				
				currentDistance = dist_n4_n3 + 3*Math.sqrt(loc_n4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n3;
				}
				
				currentDistance = dist_n4_3 + 3*Math.sqrt(loc_n4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_3;
				}
				
				currentDistance = dist_n4_n4 + 3*Math.sqrt(loc_n4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n4;
				}
				
				currentDistance = dist_n4_4 + 3*Math.sqrt(loc_n4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_4;
				}
				
				currentDistance = dist_n3_n4 + 3*Math.sqrt(loc_n3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n4;
				}
				
				currentDistance = dist_n3_4 + 3*Math.sqrt(loc_n3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_4;
				}
				
				currentDistance = dist_n3_n5 + 3*Math.sqrt(loc_n3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n5;
				}
				
				currentDistance = dist_n3_5 + 3*Math.sqrt(loc_n3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_5;
				}
				
				currentDistance = dist_n2_n5 + 3*Math.sqrt(loc_n2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_n5;
				}
				
				currentDistance = dist_n2_5 + 3*Math.sqrt(loc_n2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_5;
				}
				
				currentDistance = dist_n1_n5 + 3*Math.sqrt(loc_n1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_n5;
				}
				
				currentDistance = dist_n1_5 + 3*Math.sqrt(loc_n1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_5;
				}
				
				currentDistance = dist_0_n5 + 3*Math.sqrt(loc_0_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_n5;
				}
				
				currentDistance = dist_0_5 + 3*Math.sqrt(loc_0_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_5;
				}
				
				currentDistance = dist_1_n5 + 3*Math.sqrt(loc_1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_n5;
				}
				
				currentDistance = dist_1_5 + 3*Math.sqrt(loc_1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_5;
				}
				
				currentDistance = dist_2_n5 + 3*Math.sqrt(loc_2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_n5;
				}
				
				currentDistance = dist_2_5 + 3*Math.sqrt(loc_2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_5;
				}
				
				currentDistance = dist_3_n4 + 3*Math.sqrt(loc_3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n4;
				}
				
				currentDistance = dist_3_4 + 3*Math.sqrt(loc_3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_4;
				}
				
				currentDistance = dist_3_n5 + 3*Math.sqrt(loc_3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n5;
				}
				
				currentDistance = dist_3_5 + 3*Math.sqrt(loc_3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_5;
				}
				
				currentDistance = dist_4_n3 + 3*Math.sqrt(loc_4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n3;
				}
				
				currentDistance = dist_4_3 + 3*Math.sqrt(loc_4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_3;
				}
				
				currentDistance = dist_4_n4 + 3*Math.sqrt(loc_4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n4;
				}
				
				currentDistance = dist_4_4 + 3*Math.sqrt(loc_4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_4;
				}
				
				currentDistance = dist_5_0 + 3*Math.sqrt(loc_5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_0;
				}
				
				currentDistance = dist_5_n1 + 3*Math.sqrt(loc_5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n1;
				}
				
				currentDistance = dist_5_1 + 3*Math.sqrt(loc_5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_1;
				}
				
				currentDistance = dist_5_n2 + 3*Math.sqrt(loc_5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n2;
				}
				
				currentDistance = dist_5_2 + 3*Math.sqrt(loc_5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_2;
				}
				
				currentDistance = dist_5_n3 + 3*Math.sqrt(loc_5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n3;
				}
				
				currentDistance = dist_5_3 + 3*Math.sqrt(loc_5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_3;
				}
				
				return bestDirection;
				
			case NORTHWEST:
				currentDistance = dist_n4_4 + 3*Math.sqrt(loc_n4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_4;
				}
				
				currentDistance = dist_n5_3 + 3*Math.sqrt(loc_n5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_3;
				}
				
				currentDistance = dist_n3_5 + 3*Math.sqrt(loc_n3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_5;
				}
				
				currentDistance = dist_n4_3 + 3*Math.sqrt(loc_n4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_3;
				}
				
				currentDistance = dist_n3_4 + 3*Math.sqrt(loc_n3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_4;
				}
				
				currentDistance = dist_n5_2 + 3*Math.sqrt(loc_n5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_2;
				}
				
				currentDistance = dist_n2_5 + 3*Math.sqrt(loc_n2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_5;
				}
				
				currentDistance = dist_n5_1 + 3*Math.sqrt(loc_n5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_1;
				}
				
				currentDistance = dist_n1_5 + 3*Math.sqrt(loc_n1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_5;
				}
				
				currentDistance = dist_n5_0 + 3*Math.sqrt(loc_n5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_0;
				}
				
				currentDistance = dist_0_5 + 3*Math.sqrt(loc_0_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_5;
				}
				
				currentDistance = dist_n5_n1 + 3*Math.sqrt(loc_n5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n1;
				}
				
				currentDistance = dist_1_5 + 3*Math.sqrt(loc_1_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_5;
				}
				
				currentDistance = dist_n5_n2 + 3*Math.sqrt(loc_n5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n2;
				}
				
				currentDistance = dist_2_5 + 3*Math.sqrt(loc_2_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_5;
				}
				
				currentDistance = dist_n5_n3 + 3*Math.sqrt(loc_n5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n5_n3;
				}
				
				currentDistance = dist_3_5 + 3*Math.sqrt(loc_3_5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_5;
				}
				
				currentDistance = dist_n4_n3 + 3*Math.sqrt(loc_n4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n3;
				}
				
				currentDistance = dist_3_4 + 3*Math.sqrt(loc_3_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_4;
				}
				
				currentDistance = dist_n4_n4 + 3*Math.sqrt(loc_n4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n4_n4;
				}
				
				currentDistance = dist_4_4 + 3*Math.sqrt(loc_4_4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_4;
				}
				
				currentDistance = dist_n3_n4 + 3*Math.sqrt(loc_n3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n4;
				}
				
				currentDistance = dist_4_3 + 3*Math.sqrt(loc_4_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_3;
				}
				
				currentDistance = dist_n3_n5 + 3*Math.sqrt(loc_n3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n3_n5;
				}
				
				currentDistance = dist_5_3 + 3*Math.sqrt(loc_5_3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_3;
				}
				
				currentDistance = dist_n2_n5 + 3*Math.sqrt(loc_n2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n2_n5;
				}
				
				currentDistance = dist_5_2 + 3*Math.sqrt(loc_5_2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_2;
				}
				
				currentDistance = dist_n1_n5 + 3*Math.sqrt(loc_n1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_n1_n5;
				}
				
				currentDistance = dist_5_1 + 3*Math.sqrt(loc_5_1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_1;
				}
				
				currentDistance = dist_0_n5 + 3*Math.sqrt(loc_0_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_0_n5;
				}
				
				currentDistance = dist_5_0 + 3*Math.sqrt(loc_5_0.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_0;
				}
				
				currentDistance = dist_1_n5 + 3*Math.sqrt(loc_1_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_1_n5;
				}
				
				currentDistance = dist_5_n1 + 3*Math.sqrt(loc_5_n1.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n1;
				}
				
				currentDistance = dist_3_n4 + 3*Math.sqrt(loc_3_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n4;
				}
				
				currentDistance = dist_4_n3 + 3*Math.sqrt(loc_4_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n3;
				}
				
				currentDistance = dist_2_n5 + 3*Math.sqrt(loc_2_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_2_n5;
				}
				
				currentDistance = dist_5_n2 + 3*Math.sqrt(loc_5_n2.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n2;
				}
				
				currentDistance = dist_4_n4 + 3*Math.sqrt(loc_4_n4.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_4_n4;
				}
				
				currentDistance = dist_3_n5 + 3*Math.sqrt(loc_3_n5.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_3_n5;
				}
				
				currentDistance = dist_5_n3 + 3*Math.sqrt(loc_5_n3.distanceSquaredTo(dest));
				if (bestDistance > currentDistance) {
					bestDistance = currentDistance;
					bestDirection = direc_5_n3;
				}
				
				return bestDirection;
				
			default:
		}
		
		return null;
		
	}
}
