package w2_soldierheuristics;

import battlecode.common.*;
import w2_soldierheuristics.Util.MoveOrder;
import w2_soldierheuristics.Util.TargetingResult;

public class SoldierTargeting {
	

    public static double getTargetValue(RobotInfo robot) {
        //TODO improve this? make improvements in the generating script
        switch (robot.type) {
        case ARCHON:
            // base 1200, max hp 1200
            return 1200;
        case BUILDER:
            // 
            return 1000 - robot.health;
        case LABORATORY:
            return 1000 - robot.health;
        case MINER:
            return 1050 - robot.health;
        case SAGE:
            // 1250; maxhp 100
            return 1350 - robot.health;
        case SOLDIER:
            // 1201; maxhp 50
            return 1251 - robot.health;
        case WATCHTOWER:
            return 1400 - robot.health;
        default:
            return 0;
        }
    }
    
	public static double units_val_n1_n1;
	public static RobotInfo target_n1_n1;
	public static double target_val_n1_n1;
	
	public static double units_val_n1_0;
	public static RobotInfo target_n1_0;
	public static double target_val_n1_0;
	
	public static double units_val_n1_1;
	public static RobotInfo target_n1_1;
	public static double target_val_n1_1;
	
	public static double units_val_0_n1;
	public static RobotInfo target_0_n1;
	public static double target_val_0_n1;
	
	public static double units_val_0_0;
	public static RobotInfo target_0_0;
	public static double target_val_0_0;
	
	public static double units_val_0_1;
	public static RobotInfo target_0_1;
	public static double target_val_0_1;
	
	public static double units_val_1_n1;
	public static RobotInfo target_1_n1;
	public static double target_val_1_n1;
	
	public static double units_val_1_0;
	public static RobotInfo target_1_0;
	public static double target_val_1_0;
	
	public static double units_val_1_1;
	public static RobotInfo target_1_1;
	public static double target_val_1_1;
	
	
	public static TargetingResult getBestTarget(RobotController rc) throws GameActionException {
		
		RobotInfo[] nearbyEnemies;
		if (rc.isMovementReady()) {
			nearbyEnemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
		} else {
			nearbyEnemies = rc.senseNearbyRobots(21, rc.getTeam().opponent());
		}
		
		if (nearbyEnemies.length == 0) {
			return null;
		}
		
		MapLocation me = rc.getLocation();
		
		if (rc.isMovementReady()) {
			units_val_n1_n1 = 0;
			target_n1_n1 = null;
			target_val_n1_n1 = 0;
			
			units_val_n1_0 = 0;
			target_n1_0 = null;
			target_val_n1_0 = 0;
			
			units_val_n1_1 = 0;
			target_n1_1 = null;
			target_val_n1_1 = 0;
			
			units_val_0_n1 = 0;
			target_0_n1 = null;
			target_val_0_n1 = 0;
			
			units_val_0_0 = 0;
			target_0_0 = null;
			target_val_0_0 = 0;
			
			units_val_0_1 = 0;
			target_0_1 = null;
			target_val_0_1 = 0;
			
			units_val_1_n1 = 0;
			target_1_n1 = null;
			target_val_1_n1 = 0;
			
			units_val_1_0 = 0;
			target_1_0 = null;
			target_val_1_0 = 0;
			
			units_val_1_1 = 0;
			target_1_1 = null;
			target_val_1_1 = 0;
			
			int mx = me.x;
			int my = me.y;
			
			double dxEnemySum = 0;
			double dyEnemySum = 0;
			
			boolean attackingEnemy = false;
			
			for (int i = nearbyEnemies.length; --i>=0;) {
				RobotInfo robot = nearbyEnemies[i];
				double targetScore = getTargetValue(robot);
				
				int dx = robot.location.x-mx;
				int dy = robot.location.y-my;
				
				switch (robot.type) {
					case SOLDIER:
						switch (dx) {
							case -5:
								switch (dy) {
									default:
										break;
								}
								break;
							case -4:
								switch (dy) {
									case -2:
										units_val_n1_n1 -= 2525.658350974743;
										units_val_n1_0 -= 2459.1673086804017;
										units_val_n1_1 -= 2063.603896932107;
										units_val_0_n1 -= 2081.534156157351;
										units_val_0_0 -= 2029.1796067500632;
										break;
									case -1:
										units_val_n1_n1 -= 2550.0;
										units_val_n1_0 -= 2525.658350974743;
										units_val_n1_1 -= 2459.1673086804017;
										units_val_0_n1 -= 2100.0;
										units_val_0_0 -= 2081.534156157351;
										units_val_0_1 -= 2029.1796067500632;
										break;
									case 0:
										units_val_n1_n1 -= 2525.658350974743;
										units_val_n1_0 -= 2550.0;
										units_val_n1_1 -= 2525.658350974743;
										units_val_0_n1 -= 2081.534156157351;
										units_val_0_0 -= 2100.0;
										units_val_0_1 -= 2081.534156157351;
										break;
									case 1:
										units_val_n1_n1 -= 2459.1673086804017;
										units_val_n1_0 -= 2525.658350974743;
										units_val_n1_1 -= 2550.0;
										units_val_0_n1 -= 2029.1796067500632;
										units_val_0_0 -= 2081.534156157351;
										units_val_0_1 -= 2100.0;
										break;
									case 2:
										units_val_n1_n1 -= 2063.603896932107;
										units_val_n1_0 -= 2459.1673086804017;
										units_val_n1_1 -= 2525.658350974743;
										units_val_0_0 -= 2029.1796067500632;
										units_val_0_1 -= 2081.534156157351;
										break;
									default:
										break;
								}
								break;
							case -3:
								switch (dy) {
									case -3:
										units_val_n1_n1 -= 2575.7359312880717;
										units_val_n1_0 -= 2459.1673086804017;
										units_val_n1_1 -= 2029.1796067500632;
										units_val_0_n1 -= 2459.1673086804017;
										units_val_0_0 -= 2063.603896932107;
										units_val_1_n1 -= 2029.1796067500632;
										break;
									case -2:
										units_val_n1_n1 -= 2664.5898033750314;
										units_val_n1_0 -= 2575.7359312880717;
										units_val_n1_1 -= 2459.1673086804017;
										units_val_0_n1 -= 2525.658350974743;
										units_val_0_0 -= 2459.1673086804017;
										units_val_0_1 -= 2063.603896932107;
										units_val_1_n1 -= 2081.534156157351;
										units_val_1_0 -= 2029.1796067500632;
										break;
									case -1:
										units_val_n1_n1 -= 2700.0;
										units_val_n1_0 -= 2664.5898033750314;
										units_val_n1_1 -= 2575.7359312880717;
										units_val_0_n1 -= 2550.0;
										units_val_0_0 -= 2525.658350974743;
										units_val_0_1 -= 2459.1673086804017;
										units_val_1_n1 -= 2100.0;
										units_val_1_0 -= 2081.534156157351;
										units_val_1_1 -= 2029.1796067500632;
										break;
									case 0:
										units_val_n1_n1 -= 2664.5898033750314;
										units_val_n1_0 -= 2700.0;
										units_val_n1_1 -= 2664.5898033750314;
										units_val_0_n1 -= 2525.658350974743;
										units_val_0_0 -= 2550.0;
										units_val_0_1 -= 2525.658350974743;
										units_val_1_n1 -= 2081.534156157351;
										units_val_1_0 -= 2100.0;
										units_val_1_1 -= 2081.534156157351;
										break;
									case 1:
										units_val_n1_n1 -= 2575.7359312880717;
										units_val_n1_0 -= 2664.5898033750314;
										units_val_n1_1 -= 2700.0;
										units_val_0_n1 -= 2459.1673086804017;
										units_val_0_0 -= 2525.658350974743;
										units_val_0_1 -= 2550.0;
										units_val_1_n1 -= 2029.1796067500632;
										units_val_1_0 -= 2081.534156157351;
										units_val_1_1 -= 2100.0;
										break;
									case 2:
										units_val_n1_n1 -= 2459.1673086804017;
										units_val_n1_0 -= 2575.7359312880717;
										units_val_n1_1 -= 2664.5898033750314;
										units_val_0_n1 -= 2063.603896932107;
										units_val_0_0 -= 2459.1673086804017;
										units_val_0_1 -= 2525.658350974743;
										units_val_1_0 -= 2029.1796067500632;
										units_val_1_1 -= 2081.534156157351;
										break;
									case 3:
										units_val_n1_n1 -= 2029.1796067500632;
										units_val_n1_0 -= 2459.1673086804017;
										units_val_n1_1 -= 2575.7359312880717;
										units_val_0_0 -= 2063.603896932107;
										units_val_0_1 -= 2459.1673086804017;
										units_val_1_1 -= 2029.1796067500632;
										break;
									default:
										break;
								}
								break;
							case -2:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= 2525.658350974743;
										units_val_n1_0 -= 2081.534156157351;
										units_val_0_n1 -= 2459.1673086804017;
										units_val_0_0 -= 2029.1796067500632;
										units_val_1_n1 -= 2063.603896932107;
										break;
									case -3:
										units_val_n1_n1 -= 2664.5898033750314;
										units_val_n1_0 -= 2525.658350974743;
										units_val_n1_1 -= 2081.534156157351;
										units_val_0_n1 -= 2575.7359312880717;
										units_val_0_0 -= 2459.1673086804017;
										units_val_0_1 -= 2029.1796067500632;
										units_val_1_n1 -= 2459.1673086804017;
										units_val_1_0 -= 2063.603896932107;
										break;
									case -2:
										units_val_n1_n1 -= 2787.867965644036;
										units_val_n1_0 -= 2664.5898033750314;
										units_val_n1_1 -= 2525.658350974743;
										units_val_0_n1 -= 2664.5898033750314;
										units_val_0_0 -= 2575.7359312880717;
										units_val_0_1 -= 2459.1673086804017;
										units_val_1_n1 -= 2525.658350974743;
										units_val_1_0 -= 2459.1673086804017;
										units_val_1_1 -= 2063.603896932107;
										break;
									case -1:
										units_val_n1_n1 -= 2850.0;
										units_val_n1_0 -= 2787.867965644036;
										units_val_n1_1 -= 2664.5898033750314;
										units_val_0_n1 -= 2700.0;
										units_val_0_0 -= 2664.5898033750314;
										units_val_0_1 -= 2575.7359312880717;
										units_val_1_n1 -= 2550.0;
										units_val_1_0 -= 2525.658350974743;
										units_val_1_1 -= 2459.1673086804017;
										break;
									case 0:
										units_val_n1_n1 -= 2787.867965644036;
										units_val_n1_0 -= 2850.0;
										units_val_n1_1 -= 2787.867965644036;
										units_val_0_n1 -= 2664.5898033750314;
										units_val_0_0 -= 2700.0;
										units_val_0_1 -= 2664.5898033750314;
										units_val_1_n1 -= 2525.658350974743;
										units_val_1_0 -= 2550.0;
										units_val_1_1 -= 2525.658350974743;
										break;
									case 1:
										units_val_n1_n1 -= 2664.5898033750314;
										units_val_n1_0 -= 2787.867965644036;
										units_val_n1_1 -= 2850.0;
										units_val_0_n1 -= 2575.7359312880717;
										units_val_0_0 -= 2664.5898033750314;
										units_val_0_1 -= 2700.0;
										units_val_1_n1 -= 2459.1673086804017;
										units_val_1_0 -= 2525.658350974743;
										units_val_1_1 -= 2550.0;
										break;
									case 2:
										units_val_n1_n1 -= 2525.658350974743;
										units_val_n1_0 -= 2664.5898033750314;
										units_val_n1_1 -= 2787.867965644036;
										units_val_0_n1 -= 2459.1673086804017;
										units_val_0_0 -= 2575.7359312880717;
										units_val_0_1 -= 2664.5898033750314;
										units_val_1_n1 -= 2063.603896932107;
										units_val_1_0 -= 2459.1673086804017;
										units_val_1_1 -= 2525.658350974743;
										break;
									case 3:
										units_val_n1_n1 -= 2081.534156157351;
										units_val_n1_0 -= 2525.658350974743;
										units_val_n1_1 -= 2664.5898033750314;
										units_val_0_n1 -= 2029.1796067500632;
										units_val_0_0 -= 2459.1673086804017;
										units_val_0_1 -= 2575.7359312880717;
										units_val_1_0 -= 2063.603896932107;
										units_val_1_1 -= 2459.1673086804017;
										break;
									case 4:
										units_val_n1_0 -= 2081.534156157351;
										units_val_n1_1 -= 2525.658350974743;
										units_val_0_0 -= 2029.1796067500632;
										units_val_0_1 -= 2459.1673086804017;
										units_val_1_1 -= 2063.603896932107;
										break;
									default:
										break;
								}
								break;
							case -1:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= 2550.0;
										units_val_n1_0 -= 2100.0;
										units_val_0_n1 -= 2525.658350974743;
										units_val_0_0 -= 2081.534156157351;
										units_val_1_n1 -= 2459.1673086804017;
										units_val_1_0 -= 2029.1796067500632;
										break;
									case -3:
										units_val_n1_n1 -= 2700.0;
										units_val_n1_0 -= 2550.0;
										units_val_n1_1 -= 2100.0;
										units_val_0_n1 -= 2664.5898033750314;
										units_val_0_0 -= 2525.658350974743;
										units_val_0_1 -= 2081.534156157351;
										units_val_1_n1 -= 2575.7359312880717;
										units_val_1_0 -= 2459.1673086804017;
										units_val_1_1 -= 2029.1796067500632;
										break;
									case -2:
										units_val_n1_n1 -= 2850.0;
										units_val_n1_0 -= 2700.0;
										units_val_n1_1 -= 2550.0;
										units_val_0_n1 -= 2787.867965644036;
										units_val_0_0 -= 2664.5898033750314;
										units_val_0_1 -= 2525.658350974743;
										units_val_1_n1 -= 2664.5898033750314;
										units_val_1_0 -= 2575.7359312880717;
										units_val_1_1 -= 2459.1673086804017;
										break;
									case -1:
										units_val_n1_n1 -= 3000.0;
										units_val_n1_0 -= 2850.0;
										units_val_n1_1 -= 2700.0;
										units_val_0_n1 -= 2850.0;
										units_val_0_0 -= 2787.867965644036;
										units_val_0_1 -= 2664.5898033750314;
										units_val_1_n1 -= 2700.0;
										units_val_1_0 -= 2664.5898033750314;
										units_val_1_1 -= 2575.7359312880717;
										break;
									case 0:
										units_val_n1_n1 -= 2850.0;
										units_val_n1_0 -= 3000.0;
										units_val_n1_1 -= 2850.0;
										units_val_0_n1 -= 2787.867965644036;
										units_val_0_0 -= 2850.0;
										units_val_0_1 -= 2787.867965644036;
										units_val_1_n1 -= 2664.5898033750314;
										units_val_1_0 -= 2700.0;
										units_val_1_1 -= 2664.5898033750314;
										break;
									case 1:
										units_val_n1_n1 -= 2700.0;
										units_val_n1_0 -= 2850.0;
										units_val_n1_1 -= 3000.0;
										units_val_0_n1 -= 2664.5898033750314;
										units_val_0_0 -= 2787.867965644036;
										units_val_0_1 -= 2850.0;
										units_val_1_n1 -= 2575.7359312880717;
										units_val_1_0 -= 2664.5898033750314;
										units_val_1_1 -= 2700.0;
										break;
									case 2:
										units_val_n1_n1 -= 2550.0;
										units_val_n1_0 -= 2700.0;
										units_val_n1_1 -= 2850.0;
										units_val_0_n1 -= 2525.658350974743;
										units_val_0_0 -= 2664.5898033750314;
										units_val_0_1 -= 2787.867965644036;
										units_val_1_n1 -= 2459.1673086804017;
										units_val_1_0 -= 2575.7359312880717;
										units_val_1_1 -= 2664.5898033750314;
										break;
									case 3:
										units_val_n1_n1 -= 2100.0;
										units_val_n1_0 -= 2550.0;
										units_val_n1_1 -= 2700.0;
										units_val_0_n1 -= 2081.534156157351;
										units_val_0_0 -= 2525.658350974743;
										units_val_0_1 -= 2664.5898033750314;
										units_val_1_n1 -= 2029.1796067500632;
										units_val_1_0 -= 2459.1673086804017;
										units_val_1_1 -= 2575.7359312880717;
										break;
									case 4:
										units_val_n1_0 -= 2100.0;
										units_val_n1_1 -= 2550.0;
										units_val_0_0 -= 2081.534156157351;
										units_val_0_1 -= 2525.658350974743;
										units_val_1_0 -= 2029.1796067500632;
										units_val_1_1 -= 2459.1673086804017;
										break;
									default:
										break;
								}
								break;
							case 0:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= 2525.658350974743;
										units_val_n1_0 -= 2081.534156157351;
										units_val_0_n1 -= 2550.0;
										units_val_0_0 -= 2100.0;
										units_val_1_n1 -= 2525.658350974743;
										units_val_1_0 -= 2081.534156157351;
										break;
									case -3:
										units_val_n1_n1 -= 2664.5898033750314;
										units_val_n1_0 -= 2525.658350974743;
										units_val_n1_1 -= 2081.534156157351;
										units_val_0_n1 -= 2700.0;
										units_val_0_0 -= 2550.0;
										units_val_0_1 -= 2100.0;
										units_val_1_n1 -= 2664.5898033750314;
										units_val_1_0 -= 2525.658350974743;
										units_val_1_1 -= 2081.534156157351;
										break;
									case -2:
										units_val_n1_n1 -= 2787.867965644036;
										units_val_n1_0 -= 2664.5898033750314;
										units_val_n1_1 -= 2525.658350974743;
										units_val_0_n1 -= 2850.0;
										units_val_0_0 -= 2700.0;
										units_val_0_1 -= 2550.0;
										units_val_1_n1 -= 2787.867965644036;
										units_val_1_0 -= 2664.5898033750314;
										units_val_1_1 -= 2525.658350974743;
										break;
									case -1:
										units_val_n1_n1 -= 2850.0;
										units_val_n1_0 -= 2787.867965644036;
										units_val_n1_1 -= 2664.5898033750314;
										units_val_0_n1 -= 3000.0;
										units_val_0_0 -= 2850.0;
										units_val_0_1 -= 2700.0;
										units_val_1_n1 -= 2850.0;
										units_val_1_0 -= 2787.867965644036;
										units_val_1_1 -= 2664.5898033750314;
										break;
									case 0:
										units_val_n1_n1 -= 2787.867965644036;
										units_val_n1_0 -= 2850.0;
										units_val_n1_1 -= 2787.867965644036;
										units_val_0_n1 -= 2850.0;
										units_val_0_0 -= 3000.0;
										units_val_0_1 -= 2850.0;
										units_val_1_n1 -= 2787.867965644036;
										units_val_1_0 -= 2850.0;
										units_val_1_1 -= 2787.867965644036;
										break;
									case 1:
										units_val_n1_n1 -= 2664.5898033750314;
										units_val_n1_0 -= 2787.867965644036;
										units_val_n1_1 -= 2850.0;
										units_val_0_n1 -= 2700.0;
										units_val_0_0 -= 2850.0;
										units_val_0_1 -= 3000.0;
										units_val_1_n1 -= 2664.5898033750314;
										units_val_1_0 -= 2787.867965644036;
										units_val_1_1 -= 2850.0;
										break;
									case 2:
										units_val_n1_n1 -= 2525.658350974743;
										units_val_n1_0 -= 2664.5898033750314;
										units_val_n1_1 -= 2787.867965644036;
										units_val_0_n1 -= 2550.0;
										units_val_0_0 -= 2700.0;
										units_val_0_1 -= 2850.0;
										units_val_1_n1 -= 2525.658350974743;
										units_val_1_0 -= 2664.5898033750314;
										units_val_1_1 -= 2787.867965644036;
										break;
									case 3:
										units_val_n1_n1 -= 2081.534156157351;
										units_val_n1_0 -= 2525.658350974743;
										units_val_n1_1 -= 2664.5898033750314;
										units_val_0_n1 -= 2100.0;
										units_val_0_0 -= 2550.0;
										units_val_0_1 -= 2700.0;
										units_val_1_n1 -= 2081.534156157351;
										units_val_1_0 -= 2525.658350974743;
										units_val_1_1 -= 2664.5898033750314;
										break;
									case 4:
										units_val_n1_0 -= 2081.534156157351;
										units_val_n1_1 -= 2525.658350974743;
										units_val_0_0 -= 2100.0;
										units_val_0_1 -= 2550.0;
										units_val_1_0 -= 2081.534156157351;
										units_val_1_1 -= 2525.658350974743;
										break;
									default:
										break;
								}
								break;
							case 1:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= 2459.1673086804017;
										units_val_n1_0 -= 2029.1796067500632;
										units_val_0_n1 -= 2525.658350974743;
										units_val_0_0 -= 2081.534156157351;
										units_val_1_n1 -= 2550.0;
										units_val_1_0 -= 2100.0;
										break;
									case -3:
										units_val_n1_n1 -= 2575.7359312880717;
										units_val_n1_0 -= 2459.1673086804017;
										units_val_n1_1 -= 2029.1796067500632;
										units_val_0_n1 -= 2664.5898033750314;
										units_val_0_0 -= 2525.658350974743;
										units_val_0_1 -= 2081.534156157351;
										units_val_1_n1 -= 2700.0;
										units_val_1_0 -= 2550.0;
										units_val_1_1 -= 2100.0;
										break;
									case -2:
										units_val_n1_n1 -= 2664.5898033750314;
										units_val_n1_0 -= 2575.7359312880717;
										units_val_n1_1 -= 2459.1673086804017;
										units_val_0_n1 -= 2787.867965644036;
										units_val_0_0 -= 2664.5898033750314;
										units_val_0_1 -= 2525.658350974743;
										units_val_1_n1 -= 2850.0;
										units_val_1_0 -= 2700.0;
										units_val_1_1 -= 2550.0;
										break;
									case -1:
										units_val_n1_n1 -= 2700.0;
										units_val_n1_0 -= 2664.5898033750314;
										units_val_n1_1 -= 2575.7359312880717;
										units_val_0_n1 -= 2850.0;
										units_val_0_0 -= 2787.867965644036;
										units_val_0_1 -= 2664.5898033750314;
										units_val_1_n1 -= 3000.0;
										units_val_1_0 -= 2850.0;
										units_val_1_1 -= 2700.0;
										break;
									case 0:
										units_val_n1_n1 -= 2664.5898033750314;
										units_val_n1_0 -= 2700.0;
										units_val_n1_1 -= 2664.5898033750314;
										units_val_0_n1 -= 2787.867965644036;
										units_val_0_0 -= 2850.0;
										units_val_0_1 -= 2787.867965644036;
										units_val_1_n1 -= 2850.0;
										units_val_1_0 -= 3000.0;
										units_val_1_1 -= 2850.0;
										break;
									case 1:
										units_val_n1_n1 -= 2575.7359312880717;
										units_val_n1_0 -= 2664.5898033750314;
										units_val_n1_1 -= 2700.0;
										units_val_0_n1 -= 2664.5898033750314;
										units_val_0_0 -= 2787.867965644036;
										units_val_0_1 -= 2850.0;
										units_val_1_n1 -= 2700.0;
										units_val_1_0 -= 2850.0;
										units_val_1_1 -= 3000.0;
										break;
									case 2:
										units_val_n1_n1 -= 2459.1673086804017;
										units_val_n1_0 -= 2575.7359312880717;
										units_val_n1_1 -= 2664.5898033750314;
										units_val_0_n1 -= 2525.658350974743;
										units_val_0_0 -= 2664.5898033750314;
										units_val_0_1 -= 2787.867965644036;
										units_val_1_n1 -= 2550.0;
										units_val_1_0 -= 2700.0;
										units_val_1_1 -= 2850.0;
										break;
									case 3:
										units_val_n1_n1 -= 2029.1796067500632;
										units_val_n1_0 -= 2459.1673086804017;
										units_val_n1_1 -= 2575.7359312880717;
										units_val_0_n1 -= 2081.534156157351;
										units_val_0_0 -= 2525.658350974743;
										units_val_0_1 -= 2664.5898033750314;
										units_val_1_n1 -= 2100.0;
										units_val_1_0 -= 2550.0;
										units_val_1_1 -= 2700.0;
										break;
									case 4:
										units_val_n1_0 -= 2029.1796067500632;
										units_val_n1_1 -= 2459.1673086804017;
										units_val_0_0 -= 2081.534156157351;
										units_val_0_1 -= 2525.658350974743;
										units_val_1_0 -= 2100.0;
										units_val_1_1 -= 2550.0;
										break;
									default:
										break;
								}
								break;
							case 2:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= 2063.603896932107;
										units_val_0_n1 -= 2459.1673086804017;
										units_val_0_0 -= 2029.1796067500632;
										units_val_1_n1 -= 2525.658350974743;
										units_val_1_0 -= 2081.534156157351;
										break;
									case -3:
										units_val_n1_n1 -= 2459.1673086804017;
										units_val_n1_0 -= 2063.603896932107;
										units_val_0_n1 -= 2575.7359312880717;
										units_val_0_0 -= 2459.1673086804017;
										units_val_0_1 -= 2029.1796067500632;
										units_val_1_n1 -= 2664.5898033750314;
										units_val_1_0 -= 2525.658350974743;
										units_val_1_1 -= 2081.534156157351;
										break;
									case -2:
										units_val_n1_n1 -= 2525.658350974743;
										units_val_n1_0 -= 2459.1673086804017;
										units_val_n1_1 -= 2063.603896932107;
										units_val_0_n1 -= 2664.5898033750314;
										units_val_0_0 -= 2575.7359312880717;
										units_val_0_1 -= 2459.1673086804017;
										units_val_1_n1 -= 2787.867965644036;
										units_val_1_0 -= 2664.5898033750314;
										units_val_1_1 -= 2525.658350974743;
										break;
									case -1:
										units_val_n1_n1 -= 2550.0;
										units_val_n1_0 -= 2525.658350974743;
										units_val_n1_1 -= 2459.1673086804017;
										units_val_0_n1 -= 2700.0;
										units_val_0_0 -= 2664.5898033750314;
										units_val_0_1 -= 2575.7359312880717;
										units_val_1_n1 -= 2850.0;
										units_val_1_0 -= 2787.867965644036;
										units_val_1_1 -= 2664.5898033750314;
										break;
									case 0:
										units_val_n1_n1 -= 2525.658350974743;
										units_val_n1_0 -= 2550.0;
										units_val_n1_1 -= 2525.658350974743;
										units_val_0_n1 -= 2664.5898033750314;
										units_val_0_0 -= 2700.0;
										units_val_0_1 -= 2664.5898033750314;
										units_val_1_n1 -= 2787.867965644036;
										units_val_1_0 -= 2850.0;
										units_val_1_1 -= 2787.867965644036;
										break;
									case 1:
										units_val_n1_n1 -= 2459.1673086804017;
										units_val_n1_0 -= 2525.658350974743;
										units_val_n1_1 -= 2550.0;
										units_val_0_n1 -= 2575.7359312880717;
										units_val_0_0 -= 2664.5898033750314;
										units_val_0_1 -= 2700.0;
										units_val_1_n1 -= 2664.5898033750314;
										units_val_1_0 -= 2787.867965644036;
										units_val_1_1 -= 2850.0;
										break;
									case 2:
										units_val_n1_n1 -= 2063.603896932107;
										units_val_n1_0 -= 2459.1673086804017;
										units_val_n1_1 -= 2525.658350974743;
										units_val_0_n1 -= 2459.1673086804017;
										units_val_0_0 -= 2575.7359312880717;
										units_val_0_1 -= 2664.5898033750314;
										units_val_1_n1 -= 2525.658350974743;
										units_val_1_0 -= 2664.5898033750314;
										units_val_1_1 -= 2787.867965644036;
										break;
									case 3:
										units_val_n1_0 -= 2063.603896932107;
										units_val_n1_1 -= 2459.1673086804017;
										units_val_0_n1 -= 2029.1796067500632;
										units_val_0_0 -= 2459.1673086804017;
										units_val_0_1 -= 2575.7359312880717;
										units_val_1_n1 -= 2081.534156157351;
										units_val_1_0 -= 2525.658350974743;
										units_val_1_1 -= 2664.5898033750314;
										break;
									case 4:
										units_val_n1_1 -= 2063.603896932107;
										units_val_0_0 -= 2029.1796067500632;
										units_val_0_1 -= 2459.1673086804017;
										units_val_1_0 -= 2081.534156157351;
										units_val_1_1 -= 2525.658350974743;
										break;
									default:
										break;
								}
								break;
							case 3:
								switch (dy) {
									case -3:
										units_val_n1_n1 -= 2029.1796067500632;
										units_val_0_n1 -= 2459.1673086804017;
										units_val_0_0 -= 2063.603896932107;
										units_val_1_n1 -= 2575.7359312880717;
										units_val_1_0 -= 2459.1673086804017;
										units_val_1_1 -= 2029.1796067500632;
										break;
									case -2:
										units_val_n1_n1 -= 2081.534156157351;
										units_val_n1_0 -= 2029.1796067500632;
										units_val_0_n1 -= 2525.658350974743;
										units_val_0_0 -= 2459.1673086804017;
										units_val_0_1 -= 2063.603896932107;
										units_val_1_n1 -= 2664.5898033750314;
										units_val_1_0 -= 2575.7359312880717;
										units_val_1_1 -= 2459.1673086804017;
										break;
									case -1:
										units_val_n1_n1 -= 2100.0;
										units_val_n1_0 -= 2081.534156157351;
										units_val_n1_1 -= 2029.1796067500632;
										units_val_0_n1 -= 2550.0;
										units_val_0_0 -= 2525.658350974743;
										units_val_0_1 -= 2459.1673086804017;
										units_val_1_n1 -= 2700.0;
										units_val_1_0 -= 2664.5898033750314;
										units_val_1_1 -= 2575.7359312880717;
										break;
									case 0:
										units_val_n1_n1 -= 2081.534156157351;
										units_val_n1_0 -= 2100.0;
										units_val_n1_1 -= 2081.534156157351;
										units_val_0_n1 -= 2525.658350974743;
										units_val_0_0 -= 2550.0;
										units_val_0_1 -= 2525.658350974743;
										units_val_1_n1 -= 2664.5898033750314;
										units_val_1_0 -= 2700.0;
										units_val_1_1 -= 2664.5898033750314;
										break;
									case 1:
										units_val_n1_n1 -= 2029.1796067500632;
										units_val_n1_0 -= 2081.534156157351;
										units_val_n1_1 -= 2100.0;
										units_val_0_n1 -= 2459.1673086804017;
										units_val_0_0 -= 2525.658350974743;
										units_val_0_1 -= 2550.0;
										units_val_1_n1 -= 2575.7359312880717;
										units_val_1_0 -= 2664.5898033750314;
										units_val_1_1 -= 2700.0;
										break;
									case 2:
										units_val_n1_0 -= 2029.1796067500632;
										units_val_n1_1 -= 2081.534156157351;
										units_val_0_n1 -= 2063.603896932107;
										units_val_0_0 -= 2459.1673086804017;
										units_val_0_1 -= 2525.658350974743;
										units_val_1_n1 -= 2459.1673086804017;
										units_val_1_0 -= 2575.7359312880717;
										units_val_1_1 -= 2664.5898033750314;
										break;
									case 3:
										units_val_n1_1 -= 2029.1796067500632;
										units_val_0_0 -= 2063.603896932107;
										units_val_0_1 -= 2459.1673086804017;
										units_val_1_n1 -= 2029.1796067500632;
										units_val_1_0 -= 2459.1673086804017;
										units_val_1_1 -= 2575.7359312880717;
										break;
									default:
										break;
								}
								break;
							case 4:
								switch (dy) {
									case -2:
										units_val_0_n1 -= 2081.534156157351;
										units_val_0_0 -= 2029.1796067500632;
										units_val_1_n1 -= 2525.658350974743;
										units_val_1_0 -= 2459.1673086804017;
										units_val_1_1 -= 2063.603896932107;
										break;
									case -1:
										units_val_0_n1 -= 2100.0;
										units_val_0_0 -= 2081.534156157351;
										units_val_0_1 -= 2029.1796067500632;
										units_val_1_n1 -= 2550.0;
										units_val_1_0 -= 2525.658350974743;
										units_val_1_1 -= 2459.1673086804017;
										break;
									case 0:
										units_val_0_n1 -= 2081.534156157351;
										units_val_0_0 -= 2100.0;
										units_val_0_1 -= 2081.534156157351;
										units_val_1_n1 -= 2525.658350974743;
										units_val_1_0 -= 2550.0;
										units_val_1_1 -= 2525.658350974743;
										break;
									case 1:
										units_val_0_n1 -= 2029.1796067500632;
										units_val_0_0 -= 2081.534156157351;
										units_val_0_1 -= 2100.0;
										units_val_1_n1 -= 2459.1673086804017;
										units_val_1_0 -= 2525.658350974743;
										units_val_1_1 -= 2550.0;
										break;
									case 2:
										units_val_0_0 -= 2029.1796067500632;
										units_val_0_1 -= 2081.534156157351;
										units_val_1_n1 -= 2063.603896932107;
										units_val_1_0 -= 2459.1673086804017;
										units_val_1_1 -= 2525.658350974743;
										break;
									default:
										break;
								}
								break;
							case 5:
								switch (dy) {
									default:
										break;
								}
								break;
						}
						dxEnemySum += dx;
						dyEnemySum += dy;
						attackingEnemy = true;
						break;
					case SAGE:
					case WATCHTOWER:
						units_val_n1_n1 -= 3727.2077938642146;
						units_val_n1_0 -= 3828.462548614002;
						units_val_n1_1 -= 3918.3346173608033;
						units_val_0_n1 -= 3828.462548614002;
						units_val_0_0 -= 3939.339828220179;
						units_val_0_1 -= 4039.5313643850727;
						units_val_1_n1 -= 3918.3346173608033;
						units_val_1_0 -= 4039.5313643850727;
						units_val_1_1 -= 4151.471862576143;
						dxEnemySum += 2*dx;
						dyEnemySum += 2*dy;
						attackingEnemy = true;
						break;
					default:
						break;
				}
				
				switch (dx) {
					case -5:
						switch (dy) {
							default:
								break;
						}
						break;
					case -4:
						switch (dy) {
							case -2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								break;
								
							case -1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case -3:
						switch (dy) {
							case -3:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								break;
								
							case -2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								break;
								
							case -1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								break;
								
							case 3:
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case -2:
						switch (dy) {
							case -4:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								break;
								
							case -3:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								break;
								
							case -2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								break;
								
							case -1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case -1:
						switch (dy) {
							case -4:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								break;
								
							case -3:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case 0:
						switch (dy) {
							case -4:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								break;
								
							case -3:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case 1:
						switch (dy) {
							case -4:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								break;
								
							case -3:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case 2:
						switch (dy) {
							case -4:
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								break;
								
							case -3:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_n1_n1 < targetScore) {
									target_val_n1_n1 = targetScore;
									target_n1_n1 = robot;
								}
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_n1_0 < targetScore) {
									target_val_n1_0 = targetScore;
									target_n1_0 = robot;
								}
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								if (target_val_n1_1 < targetScore) {
									target_val_n1_1 = targetScore;
									target_n1_1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case 3:
						switch (dy) {
							case -3:
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_0_n1 < targetScore) {
									target_val_0_n1 = targetScore;
									target_0_n1 = robot;
								}
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_0_0 < targetScore) {
									target_val_0_0 = targetScore;
									target_0_0 = robot;
								}
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								if (target_val_0_1 < targetScore) {
									target_val_0_1 = targetScore;
									target_0_1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case 4:
						switch (dy) {
							case -2:
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								break;
								
							case -1:
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								if (target_val_1_n1 < targetScore) {
									target_val_1_n1 = targetScore;
									target_1_n1 = robot;
								}
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								if (target_val_1_0 < targetScore) {
									target_val_1_0 = targetScore;
									target_1_0 = robot;
								}
								if (target_val_1_1 < targetScore) {
									target_val_1_1 = targetScore;
									target_1_1 = robot;
								}
								break;
								
							default:
								break;
						}
						break;
					case 5:
						switch (dy) {
							default:
								break;
						}
						break;
				}
			}
			
			if (!attackingEnemy) {
				if (target_0_0 == null) {
					return null;
				} else {
					return new TargetingResult(target_0_0, null, MoveOrder.AFTER);
				}
			}
			
			double norm = Math.sqrt(dxEnemySum*dxEnemySum+dyEnemySum*dyEnemySum) / 4;
			if (norm > 0) {
				dxEnemySum /= norm;
				dyEnemySum /= norm;
			}
			
			RobotInfo[] nearbyAllies = rc.senseNearbyRobots(-1, rc.getTeam());
			
			for (int i = nearbyAllies.length; --i>=0;) {
				RobotInfo ally = nearbyAllies[i];
				double centerVal = dxEnemySum * (mx - ally.location.x) + dyEnemySum * (my - ally.location.y) - 1;
				double sideVal = dyEnemySum * (mx - ally.location.x) - dxEnemySum * (my - ally.location.y);
				
				switch (ally.type) {
					case SOLDIER:
						units_val_n1_n1 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal+dxEnemySum+dyEnemySum)*(centerVal+dxEnemySum+dyEnemySum)+0.25*(sideVal+dxEnemySum+dyEnemySum)*(sideVal-dxEnemySum-dyEnemySum))));
						units_val_n1_0 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal+dxEnemySum)*(centerVal+dxEnemySum)+0.25*(sideVal+dyEnemySum)*(sideVal-dyEnemySum))));
						units_val_n1_1 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal+dxEnemySum-dyEnemySum)*(centerVal+dxEnemySum-dyEnemySum)+0.25*(sideVal-dxEnemySum+dyEnemySum)*(sideVal+dxEnemySum-dyEnemySum))));
						units_val_0_n1 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal+dyEnemySum)*(centerVal+dyEnemySum)+0.25*(sideVal+dxEnemySum)*(sideVal-dxEnemySum))));
						units_val_0_0 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal)*(centerVal)+0.25*(sideVal)*(sideVal))));
						units_val_0_1 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal-dyEnemySum)*(centerVal-dyEnemySum)+0.25*(sideVal-dxEnemySum)*(sideVal+dxEnemySum))));
						units_val_1_n1 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal-dxEnemySum+dyEnemySum)*(centerVal-dxEnemySum+dyEnemySum)+0.25*(sideVal+dxEnemySum-dyEnemySum)*(sideVal-dxEnemySum+dyEnemySum))));
						units_val_1_0 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal-dxEnemySum)*(centerVal-dxEnemySum)+0.25*(sideVal-dyEnemySum)*(sideVal+dyEnemySum))));
						units_val_1_1 += 1500.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal-dxEnemySum-dyEnemySum)*(centerVal-dxEnemySum-dyEnemySum)+0.25*(sideVal-dxEnemySum-dyEnemySum)*(sideVal+dxEnemySum+dyEnemySum))));
						break;
					case SAGE:
					case WATCHTOWER:
						units_val_n1_n1 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal+dxEnemySum+dyEnemySum)*(centerVal+dxEnemySum+dyEnemySum)+0.25*(sideVal-dxEnemySum+dyEnemySum)*(sideVal-dxEnemySum+dyEnemySum))));
						units_val_n1_0 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal+dxEnemySum)*(centerVal+dxEnemySum)+0.25*(sideVal+dyEnemySum)*(sideVal+dyEnemySum))));
						units_val_n1_1 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal+dxEnemySum-dyEnemySum)*(centerVal+dxEnemySum-dyEnemySum)+0.25*(sideVal+dxEnemySum+dyEnemySum)*(sideVal+dxEnemySum+dyEnemySum))));
						units_val_0_n1 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal+dyEnemySum)*(centerVal+dyEnemySum)+0.25*(sideVal-dxEnemySum)*(sideVal-dxEnemySum))));
						units_val_0_0 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal)*(centerVal)+0.25*(sideVal)*(sideVal))));
						units_val_0_1 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal-dyEnemySum)*(centerVal-dyEnemySum)+0.25*(sideVal+dxEnemySum)*(sideVal+dxEnemySum))));
						units_val_1_n1 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal-dxEnemySum+dyEnemySum)*(centerVal-dxEnemySum+dyEnemySum)+0.25*(sideVal-dxEnemySum-dyEnemySum)*(sideVal-dxEnemySum-dyEnemySum))));
						units_val_1_0 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal-dxEnemySum)*(centerVal-dxEnemySum)+0.25*(sideVal-dyEnemySum)*(sideVal-dyEnemySum))));
						units_val_1_1 += 3000.0 / (0.25 + Math.exp(1.5*Math.sqrt((centerVal-dxEnemySum-dyEnemySum)*(centerVal-dxEnemySum-dyEnemySum)+0.25*(sideVal+dxEnemySum-dyEnemySum)*(sideVal+dxEnemySum-dyEnemySum))));
						break;
					default:
						break;
				}
			}
			
			if (
				target_val_0_0 >= target_val_n1_n1 &&
				target_val_0_0 >= target_val_n1_0 &&
				target_val_0_0 >= target_val_n1_1 &&
				target_val_0_0 >= target_val_0_n1 &&
				target_val_0_0 >= target_val_0_1 &&
				target_val_0_0 >= target_val_1_n1 &&
				target_val_0_0 >= target_val_1_0 &&
				target_val_0_0 >= target_val_1_1
				) {
				Direction bestDirection = Direction.CENTER;
				double bestScore = units_val_0_0 - 100 * rc.senseRubble(me);
				
				double score;
				MapLocation other;
				
				other = me.add(Direction.NORTH);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_0_1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTH;
					}
				}
				
				other = me.add(Direction.NORTHEAST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_1_1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTHEAST;
					}
				}
				
				other = me.add(Direction.EAST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_1_0 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.EAST;
					}
				}
				
				other = me.add(Direction.SOUTHEAST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_1_n1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTHEAST;
					}
				}
				
				other = me.add(Direction.SOUTH);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_0_n1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTH;
					}
				}
				
				other = me.add(Direction.SOUTHWEST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_n1_n1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTHWEST;
					}
				}
				
				other = me.add(Direction.WEST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_n1_0 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.WEST;
					}
				}
				
				other = me.add(Direction.NORTHWEST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_n1_1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTHWEST;
					}
				}
				
				return new TargetingResult(target_0_0, bestDirection, MoveOrder.AFTER);
				
			} else {
				Direction bestDirection = Direction.CENTER;
				double bestScore = units_val_0_0 + target_val_0_0 - 100 * rc.senseRubble(me);
				
				double score;
				MapLocation other;
				
				other = me.add(Direction.NORTH);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_0_1 + target_val_0_1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTH;
					}
				}
				
				other = me.add(Direction.NORTHEAST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_1_1 + target_val_1_1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTHEAST;
					}
				}
				
				other = me.add(Direction.EAST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_1_0 + target_val_1_0 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.EAST;
					}
				}
				
				other = me.add(Direction.SOUTHEAST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_1_n1 + target_val_1_n1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTHEAST;
					}
				}
				
				other = me.add(Direction.SOUTH);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_0_n1 + target_val_0_n1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTH;
					}
				}
				
				other = me.add(Direction.SOUTHWEST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_n1_n1 + target_val_n1_n1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTHWEST;
					}
				}
				
				other = me.add(Direction.WEST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_n1_0 + target_val_n1_0 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.WEST;
					}
				}
				
				other = me.add(Direction.NORTHWEST);
				if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {
					score = units_val_n1_1 + target_val_n1_1 - 100 * rc.senseRubble(other);
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTHWEST;
					}
				}
				
				if (bestDirection == Direction.CENTER) {
					return new TargetingResult(target_0_0, bestDirection, MoveOrder.AFTER);
				} else {
					MoveOrder order;
					RobotInfo bestTarget;
					
					switch (bestDirection) {
						case NORTH:
							if (target_val_0_0 > target_val_0_1) {
								bestTarget = target_0_0;
								order = MoveOrder.AFTER;
							} else {
								bestTarget = target_0_1;
								order = MoveOrder.BEFORE;
							}
							break;
						case NORTHEAST:
							if (target_val_0_0 > target_val_1_1) {
								bestTarget = target_0_0;
								order = MoveOrder.AFTER;
							} else {
								bestTarget = target_1_1;
								order = MoveOrder.BEFORE;
							}
							break;
						case EAST:
							if (target_val_0_0 > target_val_1_0) {
								bestTarget = target_0_0;
								order = MoveOrder.AFTER;
							} else {
								bestTarget = target_1_0;
								order = MoveOrder.BEFORE;
							}
							break;
						case SOUTHEAST:
							if (target_val_0_0 > target_val_1_n1) {
								bestTarget = target_0_0;
								order = MoveOrder.AFTER;
							} else {
								bestTarget = target_1_n1;
								order = MoveOrder.BEFORE;
							}
							break;
						case SOUTH:
							if (target_val_0_0 > target_val_0_n1) {
								bestTarget = target_0_0;
								order = MoveOrder.AFTER;
							} else {
								bestTarget = target_0_n1;
								order = MoveOrder.BEFORE;
							}
							break;
						case SOUTHWEST:
							if (target_val_0_0 > target_val_n1_n1) {
								bestTarget = target_0_0;
								order = MoveOrder.AFTER;
							} else {
								bestTarget = target_n1_n1;
								order = MoveOrder.BEFORE;
							}
							break;
						case WEST:
							if (target_val_0_0 > target_val_n1_0) {
								bestTarget = target_0_0;
								order = MoveOrder.AFTER;
							} else {
								bestTarget = target_n1_0;
								order = MoveOrder.BEFORE;
							}
							break;
						case NORTHWEST:
							if (target_val_0_0 > target_val_n1_1) {
								bestTarget = target_0_0;
								order = MoveOrder.AFTER;
							} else {
								bestTarget = target_n1_1;
								order = MoveOrder.BEFORE;
							}
							break;
						default:
							order = null;
							bestTarget = null;
							break;
					}
					
					return new TargetingResult(bestTarget, bestDirection, order);
				}
			}
		} else {
			RobotInfo bestEnemy = null;
			double bestScore = 0;
			
			for (int i = nearbyEnemies.length; --i>=0;) {
				RobotInfo enemy = nearbyEnemies[i];
				if (me.isWithinDistanceSquared(enemy.location, 13)) {
					double score = getTargetValue(enemy);
					if (score > bestScore) {
						bestEnemy = enemy;
						bestScore = score;
					}
				}
			}
			
			if (bestEnemy == null) {
				return null;
			} else {
				return new TargetingResult(bestEnemy, null, MoveOrder.NONE);
			}
			
		}
	}
}
