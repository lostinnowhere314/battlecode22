package w2_b1;

import battlecode.common.*;

public strictfp class RobotPlayer {
	
	static Robot robot = null;
	
	public static void run(RobotController rc) throws GameActionException {
		switch(rc.getType()) {
	        case ARCHON:     robot = new ArchonController(rc);  break;
	        case MINER:      robot = new MinerController(rc);   break;
	        case SOLDIER:    robot = new SoldierController(rc); break;
	        case LABORATORY: 
	        case WATCHTOWER: 
	        case BUILDER:
	        case SAGE:       break;
		}
		
		while(true) {
			try {
				robot.run(rc);
			} catch (GameActionException e) {
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();
                rc.resign(); // these should never happen
            } finally {
                Clock.yield();
            }
		}
	}
}