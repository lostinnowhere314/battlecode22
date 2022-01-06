package initial;

import battlecode.common.*;

public strictfp class RobotPlayer {
	
	static Robot robot = null;
	
	public static void run(RobotController rc) throws GameActionException {
		switch(rc.getType()) {
	        case ARCHON:     robot = new ArchonController();  break;
	        case MINER:      robot = new MinerController();   break;
	        case SOLDIER:    robot = null; break;
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
            } finally {
                Clock.yield();
            }
		}
	}
}