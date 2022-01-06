package initial;

import battlecode.common.*;

public class MinerController extends Robot {

	private MapLocation dest = null;
	@SuppressWarnings("unused")
	private Util.RotationDirection bugDirection;
	
	public MinerController() {
		bugDirection = Math.random()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		if (dest == null) {
			dest = Util.getRandomMapLocation(rc);
		}
		
		//TODO determine if there's nearby resources we want to mine
		MapLocation[] lead_locs = rc.senseNearbyLocationsWithLead(34);
		
		move(rc);
		
	}
	
	// tries to move towards the miner's desired location
	private void move(RobotController rc) throws GameActionException {
		MapLocation me = rc.getLocation();
		if (rc.isMovementReady() && !me.equals(dest)) {
			Direction dir = me.directionTo(dest);
			if (rc.canMove(dir)) {
	            rc.move(dir);
			}
			//TODO try adjacent directions if that doesn't work
		}
	}

}
