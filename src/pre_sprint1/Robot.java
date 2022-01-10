package pre_sprint1;

import java.util.Random;

import battlecode.common.*;

public abstract class Robot {
	
	public Random rng;
	
	public Robot(RobotController rc) {
		rng = new Random(rc.getID());
	}
	
	public abstract void run(RobotController rc) throws GameActionException;
}
