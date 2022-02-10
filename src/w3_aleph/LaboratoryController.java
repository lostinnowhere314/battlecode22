package w3_aleph;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class LaboratoryController extends Robot {

	public LaboratoryController(RobotController rc) {
		super(rc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(RobotController rc) throws GameActionException {

		// TODO have a way to determine if we have enough lead with other things we want to do
		if (rc.canTransmute()) {
			rc.transmute();
		}
	}

}
