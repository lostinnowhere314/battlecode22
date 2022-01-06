package initial;

import battlecode.common.*;

public class SoldierController extends Robot {

	private MapLocation dest = null;
	private Util.RotationDirection bugDirection;
	
	public SoldierController() {
		bugDirection = Math.random()>0.5 ? 
				Util.RotationDirection.CLOCKWISE : Util.RotationDirection.COUNTERCLOCKWISE;
	}
	
	@Override
	public void run(RobotController rc) throws GameActionException {
		//Taken directly from the example player
		int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = enemies[0].location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        }
        
        // Choose locations randomly
        MapLocation me = rc.getLocation();
		if (me.equals(dest) || dest == null) {
			dest = Util.getRandomMapLocation(rc);
		}
        // Use the function
		Util.move(rc, me, dest, bugDirection);
	}

}
