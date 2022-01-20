package w2_soldierheuristics;

import battlecode.common.*;
import w2_soldierheuristics.Util.MoveOrder;
import w2_soldierheuristics.Util.TargetingResult;

public class SoldierTargeting {
	

    public static double getTargetValue(RobotController rc, RobotInfo robot) throws GameActionException {
		double health = robot.getHealth();
		double rubbleFactor = 10+rc.senseRubble(robot.location);
		
		switch(robot.type) {
		case ARCHON:
			return 8 + 1/health;
		case BUILDER:
			return 9 + 1/health;
		case LABORATORY:
			return 7 + 1/health;
		case MINER:
			return 5 + 1/health;
		case SAGE:
			// actual cooldown is 200; we use a smaller number (120) because we figure that if we can see it,
			// it'll be on lower cooldown
			return 10 + (4/(health*rubbleFactor));
		case SOLDIER:
			return 10 + (3/(health*rubbleFactor));
		case WATCHTOWER:
			switch (robot.mode) { 
				case TURRET:
					// watchtowers are dangerous
					return 10 + (4*robot.level/(health*rubbleFactor));
				default:
					return 10;
			}
		default:
			return 0;
		}
	}
    
	public static double units_val_n1_n1;
	public static RobotInfo target_n1_n1;
	public static double target_val_n1_n1;
	public static double self_dpt_n1_n1;
	
	public static double units_val_n1_0;
	public static RobotInfo target_n1_0;
	public static double target_val_n1_0;
	public static double self_dpt_n1_0;
	
	public static double units_val_n1_1;
	public static RobotInfo target_n1_1;
	public static double target_val_n1_1;
	public static double self_dpt_n1_1;
	
	public static double units_val_0_n1;
	public static RobotInfo target_0_n1;
	public static double target_val_0_n1;
	public static double self_dpt_0_n1;
	
	public static double units_val_0_0;
	public static RobotInfo target_0_0;
	public static double target_val_0_0;
	public static double self_dpt_0_0;
	
	public static double units_val_0_1;
	public static RobotInfo target_0_1;
	public static double target_val_0_1;
	public static double self_dpt_0_1;
	
	public static double units_val_1_n1;
	public static RobotInfo target_1_n1;
	public static double target_val_1_n1;
	public static double self_dpt_1_n1;
	
	public static double units_val_1_0;
	public static RobotInfo target_1_0;
	public static double target_val_1_0;
	public static double self_dpt_1_0;
	
	public static double units_val_1_1;
	public static RobotInfo target_1_1;
	public static double target_val_1_1;
	public static double self_dpt_1_1;
	
	
	public static double currentScore;
	public static double allyValue;
	
	public static int mx;
	public static int my;
	
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
			
			mx = me.x;
			my = me.y;
			
			boolean attackingEnemy = false;
			
			self_dpt_0_0 = 0.3 / (1 + rc.senseRubble(me));
			
			MapLocation temp;
			
			temp = me.add(Direction.NORTH);
			if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
				self_dpt_0_1 = 0.3/(0.1+rc.senseRubble(temp));
			} else {
				self_dpt_0_1 = 0;
			}
			
			temp = me.add(Direction.NORTHEAST);
			if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
				self_dpt_1_1 = 0.3/(0.1+rc.senseRubble(temp));
			} else {
				self_dpt_1_1 = 0;
			}
			
			temp = me.add(Direction.EAST);
			if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
				self_dpt_1_0 = 0.3/(0.1+rc.senseRubble(temp));
			} else {
				self_dpt_1_0 = 0;
			}
			
			temp = me.add(Direction.SOUTHEAST);
			if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
				self_dpt_1_n1 = 0.3/(0.1+rc.senseRubble(temp));
			} else {
				self_dpt_1_n1 = 0;
			}
			
			temp = me.add(Direction.SOUTH);
			if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
				self_dpt_0_n1 = 0.3/(0.1+rc.senseRubble(temp));
			} else {
				self_dpt_0_n1 = 0;
			}
			
			temp = me.add(Direction.SOUTHWEST);
			if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
				self_dpt_n1_n1 = 0.3/(0.1+rc.senseRubble(temp));
			} else {
				self_dpt_n1_n1 = 0;
			}
			
			temp = me.add(Direction.WEST);
			if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
				self_dpt_n1_0 = 0.3/(0.1+rc.senseRubble(temp));
			} else {
				self_dpt_n1_0 = 0;
			}
			
			temp = me.add(Direction.NORTHWEST);
			if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
				self_dpt_n1_1 = 0.3/(0.1+rc.senseRubble(temp));
			} else {
				self_dpt_n1_1 = 0;
			}
			
			
			for (int i = nearbyEnemies.length; --i>=0;) {
				RobotInfo robot = nearbyEnemies[i];
				double health = robot.getHealth();
				
				double targetScore;
				double targetDangerScore = 1;
				double combatScore = 0;
				
				switch(robot.type) {
					case SOLDIER:
						{
							double rubbleFactor = 10+rc.senseRubble(robot.location);
							targetScore = 10;
							targetDangerScore = 3/rubbleFactor;
							combatScore = targetDangerScore*health;
						}
						break;
					case SAGE:
						{
							double rubbleFactor = 10+rc.senseRubble(robot.location);
							targetScore = 10;
							targetDangerScore = 3.75/rubbleFactor;
							combatScore = targetDangerScore*health;
						}
						break;
					case WATCHTOWER:
						switch (robot.mode) {
							case TURRET:
								{
									double rubbleFactor = 10+rc.senseRubble(robot.location);
									targetScore = 10;
									targetDangerScore = 4*robot.level/rubbleFactor;
									combatScore = targetDangerScore * health;
								}
								break;
							default:
								{
									targetScore = 10;
									targetDangerScore = 1;
									combatScore = 0;
								}
								break;
						}
						break;
					case ARCHON:
						{
							targetScore = 8;
							combatScore = 1;
						}
						break;
					case BUILDER:
						{
							targetScore = 9;
						}
						break;
					case LABORATORY:
						{
							targetScore = 7;
						}
						break;
					case MINER:
					default:
						{
							targetScore = 5;
						}
						break;
				}
				
				targetDangerScore /= health;
				
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
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= 0.625*combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= 0.625*combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= 0.625*combatScore;
										break;
									case 2:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= 0.625*combatScore;
										break;
									default:
										break;
								}
								break;
							case -3:
								switch (dy) {
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case 3:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									default:
										break;
								}
								break;
							case -2:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									default:
										break;
								}
								break;
							case -1:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 0:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 1:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 2:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 3:
								switch (dy) {
									case -3:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 4:
								switch (dy) {
									case -2:
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -1:
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_0_0 -= 0.625*combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
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
						attackingEnemy = true;
						break;
					case SAGE:
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
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									default:
										break;
								}
								break;
							case -3:
								switch (dy) {
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case -2:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= 0.625*combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case -1:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 0:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 1:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 2:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= 0.625*combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= 0.625*combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 3:
								switch (dy) {
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 4:
								switch (dy) {
									case -2:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= 0.625*combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= 0.625*combatScore;
										units_val_n1_0 -= 0.625*combatScore;
										units_val_n1_1 -= 0.625*combatScore;
										units_val_0_n1 -= 0.625*combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
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
						attackingEnemy = true;
						break;
					case WATCHTOWER:
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
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case -3:
								switch (dy) {
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_1_n1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case -2:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_1_n1 -= combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case -1:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 0:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 1:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 2:
								switch (dy) {
									case -4:
										units_val_n1_n1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										break;
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 4:
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 3:
								switch (dy) {
									case -3:
										units_val_n1_n1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -2:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_n1_n1 -= combatScore;
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_n1_0 -= combatScore;
										units_val_n1_1 -= combatScore;
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 3:
										units_val_n1_1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									default:
										break;
								}
								break;
							case 4:
								switch (dy) {
									case -2:
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case -1:
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 0:
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 1:
										units_val_0_n1 -= combatScore;
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
										break;
									case 2:
										units_val_0_0 -= combatScore;
										units_val_0_1 -= combatScore;
										units_val_1_n1 -= combatScore;
										units_val_1_0 -= combatScore;
										units_val_1_1 -= combatScore;
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
						attackingEnemy = true;
						break;
					default:
						break;
				}
				
				switch (dx) {
					case -4:
						switch (dy) {
							case -2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
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
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								break;
								
							case -2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								break;
								
							case 3:
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
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
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								break;
								
							case -3:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								break;
								
							case -2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
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
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								break;
								
							case -3:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
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
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								break;
								
							case -3:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
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
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								break;
								
							case -3:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
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
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								break;
								
							case -3:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_n1_n1+targetDangerScore)*self_dpt_n1_n1;
								if (target_val_n1_n1 < currentScore) {
									target_val_n1_n1 = currentScore;
									target_n1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_n1_0+targetDangerScore)*self_dpt_n1_0;
								if (target_val_n1_0 < currentScore) {
									target_val_n1_0 = currentScore;
									target_n1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								currentScore = targetScore + (self_dpt_n1_1+targetDangerScore)*self_dpt_n1_1;
								if (target_val_n1_1 < currentScore) {
									target_val_n1_1 = currentScore;
									target_n1_1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 4:
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
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
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								break;
								
							case -2:
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_0_n1+targetDangerScore)*self_dpt_0_n1;
								if (target_val_0_n1 < currentScore) {
									target_val_0_n1 = currentScore;
									target_0_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_0_0+targetDangerScore)*self_dpt_0_0;
								if (target_val_0_0 < currentScore) {
									target_val_0_0 = currentScore;
									target_0_0 = robot;
								}
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 3:
								currentScore = targetScore + (self_dpt_0_1+targetDangerScore)*self_dpt_0_1;
								if (target_val_0_1 < currentScore) {
									target_val_0_1 = currentScore;
									target_0_1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
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
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								break;
								
							case -1:
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 0:
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 1:
								currentScore = targetScore + (self_dpt_1_n1+targetDangerScore)*self_dpt_1_n1;
								if (target_val_1_n1 < currentScore) {
									target_val_1_n1 = currentScore;
									target_1_n1 = robot;
								}
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
							case 2:
								currentScore = targetScore + (self_dpt_1_0+targetDangerScore)*self_dpt_1_0;
								if (target_val_1_0 < currentScore) {
									target_val_1_0 = currentScore;
									target_1_0 = robot;
								}
								currentScore = targetScore + (self_dpt_1_1+targetDangerScore)*self_dpt_1_1;
								if (target_val_1_1 < currentScore) {
									target_val_1_1 = currentScore;
									target_1_1 = robot;
								}
								break;
								
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
					return new TargetingResult(target_0_0, null, MoveOrder.AFTER, rc.isActionReady(), true, false);
				}
			}
			
			RobotInfo[] nearbyAllies = rc.senseNearbyRobots(-1, rc.getTeam());
			
			for (int i = nearbyAllies.length; --i>=0;) {
				calculateAllyValue(rc, nearbyAllies[i]);
			}
			
			units_val_n1_n1 += self_dpt_n1_n1 * rc.getHealth();
			units_val_n1_0 += self_dpt_n1_0 * rc.getHealth();
			units_val_n1_1 += self_dpt_n1_1 * rc.getHealth();
			units_val_0_n1 += self_dpt_0_n1 * rc.getHealth();
			units_val_0_0 += self_dpt_0_0 * rc.getHealth();
			units_val_0_1 += self_dpt_0_1 * rc.getHealth();
			units_val_1_n1 += self_dpt_1_n1 * rc.getHealth();
			units_val_1_0 += self_dpt_1_0 * rc.getHealth();
			units_val_1_1 += self_dpt_1_1 * rc.getHealth();
			
			
			Direction bestDirection = Direction.CENTER;
			boolean retreat = false;
			
			if (units_val_0_0 < 0) {
				double bestScore = units_val_0_0;
				
				temp = me.add(Direction.NORTH);
				if (bestScore < units_val_0_1 && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
					bestScore = units_val_0_1;
					bestDirection = Direction.NORTH;
				}
				
				temp = me.add(Direction.NORTHEAST);
				if (bestScore < units_val_1_1 && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
					bestScore = units_val_1_1;
					bestDirection = Direction.NORTHEAST;
				}
				
				temp = me.add(Direction.EAST);
				if (bestScore < units_val_1_0 && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
					bestScore = units_val_1_0;
					bestDirection = Direction.EAST;
				}
				
				temp = me.add(Direction.SOUTHEAST);
				if (bestScore < units_val_1_n1 && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
					bestScore = units_val_1_n1;
					bestDirection = Direction.SOUTHEAST;
				}
				
				temp = me.add(Direction.SOUTH);
				if (bestScore < units_val_0_n1 && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
					bestScore = units_val_0_n1;
					bestDirection = Direction.SOUTH;
				}
				
				temp = me.add(Direction.SOUTHWEST);
				if (bestScore < units_val_n1_n1 && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
					bestScore = units_val_n1_n1;
					bestDirection = Direction.SOUTHWEST;
				}
				
				temp = me.add(Direction.WEST);
				if (bestScore < units_val_n1_0 && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
					bestScore = units_val_n1_0;
					bestDirection = Direction.WEST;
				}
				
				temp = me.add(Direction.NORTHWEST);
				if (bestScore < units_val_n1_1 && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {
					bestScore = units_val_n1_1;
					bestDirection = Direction.NORTHWEST;
				}
				
				if (bestScore < 0) {
					retreat = true;
				}
			} else {
				double bestScore = (units_val_0_0 + target_val_0_0)*self_dpt_0_0;
				double score;
				
				if (units_val_0_1 > 0) {
					score = (units_val_0_1 + target_val_0_1)*self_dpt_0_1;
					
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTH;
					}
				}
				
				if (units_val_1_1 > 0) {
					score = (units_val_1_1 + target_val_1_1)*self_dpt_1_1;
					
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTHEAST;
					}
				}
				
				if (units_val_1_0 > 0) {
					score = (units_val_1_0 + target_val_1_0)*self_dpt_1_0;
					
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.EAST;
					}
				}
				
				if (units_val_1_n1 > 0) {
					score = (units_val_1_n1 + target_val_1_n1)*self_dpt_1_n1;
					
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTHEAST;
					}
				}
				
				if (units_val_0_n1 > 0) {
					score = (units_val_0_n1 + target_val_0_n1)*self_dpt_0_n1;
					
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTH;
					}
				}
				
				if (units_val_n1_n1 > 0) {
					score = (units_val_n1_n1 + target_val_n1_n1)*self_dpt_n1_n1;
					
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.SOUTHWEST;
					}
				}
				
				if (units_val_n1_0 > 0) {
					score = (units_val_n1_0 + target_val_n1_0)*self_dpt_n1_0;
					
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.WEST;
					}
				}
				
				if (units_val_n1_1 > 0) {
					score = (units_val_n1_1 + target_val_n1_1)*self_dpt_n1_1;
					
					if (score > bestScore) {
						bestScore = score;
						bestDirection = Direction.NORTHWEST;
					}
				}
				
			}
			
			MoveOrder order;
			RobotInfo bestTarget;
			
			if (retreat) {
				bestTarget = target_0_0;
				order = MoveOrder.BEFORE;
			} else {
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
					order = MoveOrder.AFTER;
					bestTarget = target_0_0;
					break;
			}
			}
			
			if (retreat)
				bestDirection = null;
			
			return new TargetingResult(bestTarget, bestDirection, order, bestTarget != null && rc.isActionReady(), bestDirection != Direction.CENTER, retreat);
			
		} else {
			RobotInfo bestEnemy = null;
			double bestScore = 0;
			
			for (int i = nearbyEnemies.length; --i>=0;) {
				RobotInfo enemy = nearbyEnemies[i];
				if (me.isWithinDistanceSquared(enemy.location, 13)) {
					double score = getTargetValue(rc, enemy);
					if (score > bestScore) {
						bestEnemy = enemy;
						bestScore = score;
					}
				}
			}
			
			if (bestEnemy == null) {
				return null;
			} else {
				return new TargetingResult(bestEnemy, null, MoveOrder.NONE, rc.isActionReady(), false, false);
			}
			
		}
	}
	
	public static void calculateAllyValue(RobotController rc, RobotInfo ally) throws GameActionException {
		switch (ally.type) {
			case SOLDIER:
				allyValue = 3*ally.health / (10 + rc.senseRubble(ally.location));
				switch (ally.location.x - mx) {
					case -4:
						switch (ally.location.y - my) {
							case -2:
								units_val_n1_n1 += 0.5092492456649377 * allyValue;
								units_val_n1_0 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.1356966876900471 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.5723398687919745 * allyValue;
								units_val_n1_0 += 0.5092492456649377 * allyValue;
								units_val_n1_1 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.18355793133013326 * allyValue;
								units_val_0_0 += 0.1356966876900471 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.5092492456649377 * allyValue;
								units_val_n1_0 += 0.5723398687919745 * allyValue;
								units_val_n1_1 += 0.5092492456649377 * allyValue;
								units_val_0_n1 += 0.1356966876900471 * allyValue;
								units_val_0_0 += 0.18355793133013326 * allyValue;
								units_val_0_1 += 0.1356966876900471 * allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.33691247068459573 * allyValue;
								units_val_n1_0 += 0.5092492456649377 * allyValue;
								units_val_n1_1 += 0.5723398687919745 * allyValue;
								units_val_0_0 += 0.1356966876900471 * allyValue;
								units_val_0_1 += 0.18355793133013326 * allyValue;
								return;
							case 2:
								units_val_n1_0 += 0.33691247068459573 * allyValue;
								units_val_n1_1 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.1356966876900471 * allyValue;
								return;
						}
						break;
					case -3:
						switch (ally.location.y - my) {
							case -3:
								units_val_n1_n1 += 0.6390443036490495 * allyValue;
								units_val_n1_0 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.33691247068459573 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += 0.8693428405887492 * allyValue;
								units_val_n1_0 += 0.6390443036490495 * allyValue;
								units_val_n1_1 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.1356966876900471 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.9611218062538158 * allyValue;
								units_val_n1_0 += 0.8693428405887492 * allyValue;
								units_val_n1_1 += 0.6390443036490495 * allyValue;
								units_val_0_n1 += 0.5723398687919745 * allyValue;
								units_val_0_0 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.18355793133013326 * allyValue;
								units_val_1_0 += 0.1356966876900471 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.8693428405887492 * allyValue;
								units_val_n1_0 += 0.9611218062538158 * allyValue;
								units_val_n1_1 += 0.8693428405887492 * allyValue;
								units_val_0_n1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.5723398687919745 * allyValue;
								units_val_0_1 += 0.5092492456649377 * allyValue;
								units_val_1_n1 += 0.1356966876900471 * allyValue;
								units_val_1_0 += 0.18355793133013326 * allyValue;
								units_val_1_1 += 0.1356966876900471 * allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.6390443036490495 * allyValue;
								units_val_n1_0 += 0.8693428405887492 * allyValue;
								units_val_n1_1 += 0.9611218062538158 * allyValue;
								units_val_0_n1 += 0.33691247068459573 * allyValue;
								units_val_0_0 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.5723398687919745 * allyValue;
								units_val_1_0 += 0.1356966876900471 * allyValue;
								units_val_1_1 += 0.18355793133013326 * allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.33691247068459573 * allyValue;
								units_val_n1_0 += 0.6390443036490495 * allyValue;
								units_val_n1_1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += 0.33691247068459573 * allyValue;
								units_val_0_1 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.1356966876900471 * allyValue;
								return;
							case 3:
								units_val_n1_0 += 0.33691247068459573 * allyValue;
								units_val_n1_1 += 0.6390443036490495 * allyValue;
								units_val_0_1 += 0.33691247068459573 * allyValue;
								return;
						}
						break;
					case -2:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.5092492456649377 * allyValue;
								units_val_n1_0 += 0.1356966876900471 * allyValue;
								units_val_0_n1 += 0.33691247068459573 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += 0.8693428405887492 * allyValue;
								units_val_n1_0 += 0.5092492456649377 * allyValue;
								units_val_n1_1 += 0.1356966876900471 * allyValue;
								units_val_0_n1 += 0.6390443036490495 * allyValue;
								units_val_0_0 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.33691247068459573 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += 0.8693428405887492 * allyValue;
								units_val_n1_1 += 0.5092492456649377 * allyValue;
								units_val_0_n1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += 0.6390443036490495 * allyValue;
								units_val_0_1 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.33691247068459573 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += 0.8693428405887492 * allyValue;
								units_val_0_n1 += 0.9611218062538158 * allyValue;
								units_val_0_0 += 0.8693428405887492 * allyValue;
								units_val_0_1 += 0.6390443036490495 * allyValue;
								units_val_1_n1 += 0.5723398687919745 * allyValue;
								units_val_1_0 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.33691247068459573 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += 0.9611218062538158 * allyValue;
								units_val_0_1 += 0.8693428405887492 * allyValue;
								units_val_1_n1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.5723398687919745 * allyValue;
								units_val_1_1 += 0.5092492456649377 * allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.8693428405887492 * allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.6390443036490495 * allyValue;
								units_val_0_0 += 0.8693428405887492 * allyValue;
								units_val_0_1 += 0.9611218062538158 * allyValue;
								units_val_1_n1 += 0.33691247068459573 * allyValue;
								units_val_1_0 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.5723398687919745 * allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.5092492456649377 * allyValue;
								units_val_n1_0 += 0.8693428405887492 * allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.33691247068459573 * allyValue;
								units_val_0_0 += 0.6390443036490495 * allyValue;
								units_val_0_1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += 0.33691247068459573 * allyValue;
								units_val_1_1 += 0.5092492456649377 * allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.1356966876900471 * allyValue;
								units_val_n1_0 += 0.5092492456649377 * allyValue;
								units_val_n1_1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += 0.33691247068459573 * allyValue;
								units_val_0_1 += 0.6390443036490495 * allyValue;
								units_val_1_1 += 0.33691247068459573 * allyValue;
								return;
							case 4:
								units_val_n1_0 += 0.1356966876900471 * allyValue;
								units_val_n1_1 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.33691247068459573 * allyValue;
								return;
						}
						break;
					case -1:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.5723398687919745 * allyValue;
								units_val_n1_0 += 0.18355793133013326 * allyValue;
								units_val_0_n1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.1356966876900471 * allyValue;
								units_val_1_n1 += 0.33691247068459573 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += 0.9611218062538158 * allyValue;
								units_val_n1_0 += 0.5723398687919745 * allyValue;
								units_val_n1_1 += 0.18355793133013326 * allyValue;
								units_val_0_n1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.1356966876900471 * allyValue;
								units_val_1_n1 += 0.6390443036490495 * allyValue;
								units_val_1_0 += 0.33691247068459573 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += 0.9611218062538158 * allyValue;
								units_val_n1_1 += 0.5723398687919745 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += 0.8693428405887492 * allyValue;
								units_val_0_1 += 0.5092492456649377 * allyValue;
								units_val_1_n1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += 0.6390443036490495 * allyValue;
								units_val_1_1 += 0.33691247068459573 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += 0.9611218062538158 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += 0.8693428405887492 * allyValue;
								units_val_1_n1 += 0.9611218062538158 * allyValue;
								units_val_1_0 += 0.8693428405887492 * allyValue;
								units_val_1_1 += 0.6390443036490495 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += 0.9611218062538158 * allyValue;
								units_val_1_1 += 0.8693428405887492 * allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.9611218062538158 * allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.6390443036490495 * allyValue;
								units_val_1_0 += 0.8693428405887492 * allyValue;
								units_val_1_1 += 0.9611218062538158 * allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.5723398687919745 * allyValue;
								units_val_n1_0 += 0.9611218062538158 * allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.8693428405887492 * allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.33691247068459573 * allyValue;
								units_val_1_0 += 0.6390443036490495 * allyValue;
								units_val_1_1 += 0.8693428405887492 * allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.18355793133013326 * allyValue;
								units_val_n1_0 += 0.5723398687919745 * allyValue;
								units_val_n1_1 += 0.9611218062538158 * allyValue;
								units_val_0_n1 += 0.1356966876900471 * allyValue;
								units_val_0_0 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += 0.33691247068459573 * allyValue;
								units_val_1_1 += 0.6390443036490495 * allyValue;
								return;
							case 4:
								units_val_n1_0 += 0.18355793133013326 * allyValue;
								units_val_n1_1 += 0.5723398687919745 * allyValue;
								units_val_0_0 += 0.1356966876900471 * allyValue;
								units_val_0_1 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.33691247068459573 * allyValue;
								return;
						}
						break;
					case 0:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.5092492456649377 * allyValue;
								units_val_n1_0 += 0.1356966876900471 * allyValue;
								units_val_0_n1 += 0.5723398687919745 * allyValue;
								units_val_0_0 += 0.18355793133013326 * allyValue;
								units_val_1_n1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.1356966876900471 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += 0.8693428405887492 * allyValue;
								units_val_n1_0 += 0.5092492456649377 * allyValue;
								units_val_n1_1 += 0.1356966876900471 * allyValue;
								units_val_0_n1 += 0.9611218062538158 * allyValue;
								units_val_0_0 += 0.5723398687919745 * allyValue;
								units_val_0_1 += 0.18355793133013326 * allyValue;
								units_val_1_n1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.1356966876900471 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += 0.8693428405887492 * allyValue;
								units_val_n1_1 += 0.5092492456649377 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += 0.9611218062538158 * allyValue;
								units_val_0_1 += 0.5723398687919745 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += 0.8693428405887492 * allyValue;
								units_val_1_1 += 0.5092492456649377 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += 0.8693428405887492 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += 0.9611218062538158 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += 0.8693428405887492 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.8693428405887492 * allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.9611218062538158 * allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.5092492456649377 * allyValue;
								units_val_n1_0 += 0.8693428405887492 * allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.5723398687919745 * allyValue;
								units_val_0_0 += 0.9611218062538158 * allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.8693428405887492 * allyValue;
								units_val_1_1 += allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.1356966876900471 * allyValue;
								units_val_n1_0 += 0.5092492456649377 * allyValue;
								units_val_n1_1 += 0.8693428405887492 * allyValue;
								units_val_0_n1 += 0.18355793133013326 * allyValue;
								units_val_0_0 += 0.5723398687919745 * allyValue;
								units_val_0_1 += 0.9611218062538158 * allyValue;
								units_val_1_n1 += 0.1356966876900471 * allyValue;
								units_val_1_0 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.8693428405887492 * allyValue;
								return;
							case 4:
								units_val_n1_0 += 0.1356966876900471 * allyValue;
								units_val_n1_1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.18355793133013326 * allyValue;
								units_val_0_1 += 0.5723398687919745 * allyValue;
								units_val_1_0 += 0.1356966876900471 * allyValue;
								units_val_1_1 += 0.5092492456649377 * allyValue;
								return;
						}
						break;
					case 1:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.1356966876900471 * allyValue;
								units_val_1_n1 += 0.5723398687919745 * allyValue;
								units_val_1_0 += 0.18355793133013326 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += 0.6390443036490495 * allyValue;
								units_val_n1_0 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.1356966876900471 * allyValue;
								units_val_1_n1 += 0.9611218062538158 * allyValue;
								units_val_1_0 += 0.5723398687919745 * allyValue;
								units_val_1_1 += 0.18355793133013326 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += 0.8693428405887492 * allyValue;
								units_val_n1_0 += 0.6390443036490495 * allyValue;
								units_val_n1_1 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += 0.8693428405887492 * allyValue;
								units_val_0_1 += 0.5092492456649377 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += 0.9611218062538158 * allyValue;
								units_val_1_1 += 0.5723398687919745 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.9611218062538158 * allyValue;
								units_val_n1_0 += 0.8693428405887492 * allyValue;
								units_val_n1_1 += 0.6390443036490495 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += 0.8693428405887492 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += 0.9611218062538158 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.8693428405887492 * allyValue;
								units_val_n1_0 += 0.9611218062538158 * allyValue;
								units_val_n1_1 += 0.8693428405887492 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.6390443036490495 * allyValue;
								units_val_n1_0 += 0.8693428405887492 * allyValue;
								units_val_n1_1 += 0.9611218062538158 * allyValue;
								units_val_0_n1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.9611218062538158 * allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.33691247068459573 * allyValue;
								units_val_n1_0 += 0.6390443036490495 * allyValue;
								units_val_n1_1 += 0.8693428405887492 * allyValue;
								units_val_0_n1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.8693428405887492 * allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.5723398687919745 * allyValue;
								units_val_1_0 += 0.9611218062538158 * allyValue;
								units_val_1_1 += allyValue;
								return;
							case 3:
								units_val_n1_0 += 0.33691247068459573 * allyValue;
								units_val_n1_1 += 0.6390443036490495 * allyValue;
								units_val_0_n1 += 0.1356966876900471 * allyValue;
								units_val_0_0 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.8693428405887492 * allyValue;
								units_val_1_n1 += 0.18355793133013326 * allyValue;
								units_val_1_0 += 0.5723398687919745 * allyValue;
								units_val_1_1 += 0.9611218062538158 * allyValue;
								return;
							case 4:
								units_val_n1_1 += 0.33691247068459573 * allyValue;
								units_val_0_0 += 0.1356966876900471 * allyValue;
								units_val_0_1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.18355793133013326 * allyValue;
								units_val_1_1 += 0.5723398687919745 * allyValue;
								return;
						}
						break;
					case 2:
						switch (ally.location.y - my) {
							case -4:
								units_val_0_n1 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.1356966876900471 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.6390443036490495 * allyValue;
								units_val_0_0 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.1356966876900471 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += 0.5092492456649377 * allyValue;
								units_val_n1_0 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += 0.6390443036490495 * allyValue;
								units_val_0_1 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += 0.8693428405887492 * allyValue;
								units_val_1_1 += 0.5092492456649377 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.5723398687919745 * allyValue;
								units_val_n1_0 += 0.5092492456649377 * allyValue;
								units_val_n1_1 += 0.33691247068459573 * allyValue;
								units_val_0_n1 += 0.9611218062538158 * allyValue;
								units_val_0_0 += 0.8693428405887492 * allyValue;
								units_val_0_1 += 0.6390443036490495 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += 0.8693428405887492 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.5092492456649377 * allyValue;
								units_val_n1_0 += 0.5723398687919745 * allyValue;
								units_val_n1_1 += 0.5092492456649377 * allyValue;
								units_val_0_n1 += 0.8693428405887492 * allyValue;
								units_val_0_0 += 0.9611218062538158 * allyValue;
								units_val_0_1 += 0.8693428405887492 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.33691247068459573 * allyValue;
								units_val_n1_0 += 0.5092492456649377 * allyValue;
								units_val_n1_1 += 0.5723398687919745 * allyValue;
								units_val_0_n1 += 0.6390443036490495 * allyValue;
								units_val_0_0 += 0.8693428405887492 * allyValue;
								units_val_0_1 += 0.9611218062538158 * allyValue;
								units_val_1_n1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 2:
								units_val_n1_0 += 0.33691247068459573 * allyValue;
								units_val_n1_1 += 0.5092492456649377 * allyValue;
								units_val_0_n1 += 0.33691247068459573 * allyValue;
								units_val_0_0 += 0.6390443036490495 * allyValue;
								units_val_0_1 += 0.8693428405887492 * allyValue;
								units_val_1_n1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.8693428405887492 * allyValue;
								units_val_1_1 += allyValue;
								return;
							case 3:
								units_val_n1_1 += 0.33691247068459573 * allyValue;
								units_val_0_0 += 0.33691247068459573 * allyValue;
								units_val_0_1 += 0.6390443036490495 * allyValue;
								units_val_1_n1 += 0.1356966876900471 * allyValue;
								units_val_1_0 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.8693428405887492 * allyValue;
								return;
							case 4:
								units_val_0_1 += 0.33691247068459573 * allyValue;
								units_val_1_0 += 0.1356966876900471 * allyValue;
								units_val_1_1 += 0.5092492456649377 * allyValue;
								return;
						}
						break;
					case 3:
						switch (ally.location.y - my) {
							case -3:
								units_val_0_n1 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.6390443036490495 * allyValue;
								units_val_1_0 += 0.33691247068459573 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += 0.1356966876900471 * allyValue;
								units_val_0_n1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += 0.6390443036490495 * allyValue;
								units_val_1_1 += 0.33691247068459573 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.18355793133013326 * allyValue;
								units_val_n1_0 += 0.1356966876900471 * allyValue;
								units_val_0_n1 += 0.5723398687919745 * allyValue;
								units_val_0_0 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.33691247068459573 * allyValue;
								units_val_1_n1 += 0.9611218062538158 * allyValue;
								units_val_1_0 += 0.8693428405887492 * allyValue;
								units_val_1_1 += 0.6390443036490495 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.1356966876900471 * allyValue;
								units_val_n1_0 += 0.18355793133013326 * allyValue;
								units_val_n1_1 += 0.1356966876900471 * allyValue;
								units_val_0_n1 += 0.5092492456649377 * allyValue;
								units_val_0_0 += 0.5723398687919745 * allyValue;
								units_val_0_1 += 0.5092492456649377 * allyValue;
								units_val_1_n1 += 0.8693428405887492 * allyValue;
								units_val_1_0 += 0.9611218062538158 * allyValue;
								units_val_1_1 += 0.8693428405887492 * allyValue;
								return;
							case 1:
								units_val_n1_0 += 0.1356966876900471 * allyValue;
								units_val_n1_1 += 0.18355793133013326 * allyValue;
								units_val_0_n1 += 0.33691247068459573 * allyValue;
								units_val_0_0 += 0.5092492456649377 * allyValue;
								units_val_0_1 += 0.5723398687919745 * allyValue;
								units_val_1_n1 += 0.6390443036490495 * allyValue;
								units_val_1_0 += 0.8693428405887492 * allyValue;
								units_val_1_1 += 0.9611218062538158 * allyValue;
								return;
							case 2:
								units_val_n1_1 += 0.1356966876900471 * allyValue;
								units_val_0_0 += 0.33691247068459573 * allyValue;
								units_val_0_1 += 0.5092492456649377 * allyValue;
								units_val_1_n1 += 0.33691247068459573 * allyValue;
								units_val_1_0 += 0.6390443036490495 * allyValue;
								units_val_1_1 += 0.8693428405887492 * allyValue;
								return;
							case 3:
								units_val_0_1 += 0.33691247068459573 * allyValue;
								units_val_1_0 += 0.33691247068459573 * allyValue;
								units_val_1_1 += 0.6390443036490495 * allyValue;
								return;
						}
						break;
					case 4:
						switch (ally.location.y - my) {
							case -2:
								units_val_0_n1 += 0.1356966876900471 * allyValue;
								units_val_1_n1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.33691247068459573 * allyValue;
								return;
							case -1:
								units_val_0_n1 += 0.18355793133013326 * allyValue;
								units_val_0_0 += 0.1356966876900471 * allyValue;
								units_val_1_n1 += 0.5723398687919745 * allyValue;
								units_val_1_0 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.33691247068459573 * allyValue;
								return;
							case 0:
								units_val_0_n1 += 0.1356966876900471 * allyValue;
								units_val_0_0 += 0.18355793133013326 * allyValue;
								units_val_0_1 += 0.1356966876900471 * allyValue;
								units_val_1_n1 += 0.5092492456649377 * allyValue;
								units_val_1_0 += 0.5723398687919745 * allyValue;
								units_val_1_1 += 0.5092492456649377 * allyValue;
								return;
							case 1:
								units_val_0_0 += 0.1356966876900471 * allyValue;
								units_val_0_1 += 0.18355793133013326 * allyValue;
								units_val_1_n1 += 0.33691247068459573 * allyValue;
								units_val_1_0 += 0.5092492456649377 * allyValue;
								units_val_1_1 += 0.5723398687919745 * allyValue;
								return;
							case 2:
								units_val_0_1 += 0.1356966876900471 * allyValue;
								units_val_1_0 += 0.33691247068459573 * allyValue;
								units_val_1_1 += 0.5092492456649377 * allyValue;
								return;
						}
						break;
			}
				break;
			case SAGE:
				allyValue = 3.75*ally.health / (10 + rc.senseRubble(ally.location));
				switch (ally.location.x - mx) {
					case -4:
						switch (ally.location.y - my) {
							case -2:
								units_val_n1_n1 += 0.9105145121522975 * allyValue;
								units_val_n1_0 += 0.7592757231175133 * allyValue;
								units_val_n1_1 += 0.5419096814653961 * allyValue;
								units_val_0_n1 += 0.5826933810245228 * allyValue;
								units_val_0_0 += 0.4636090896733879 * allyValue;
								units_val_0_1 += 0.2835092231662708 * allyValue;
								units_val_1_n1 += 0.24972514306351265 * allyValue;
								units_val_1_0 += 0.1520963508458832 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.9658813915793462 * allyValue;
								units_val_n1_0 += 0.9105145121522975 * allyValue;
								units_val_n1_1 += 0.7592757231175133 * allyValue;
								units_val_0_n1 += 0.6246953073728085 * allyValue;
								units_val_0_0 += 0.5826933810245228 * allyValue;
								units_val_0_1 += 0.4636090896733879 * allyValue;
								units_val_1_n1 += 0.2835092231662708 * allyValue;
								units_val_1_0 += 0.24972514306351265 * allyValue;
								units_val_1_1 += 0.1520963508458832 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.9105145121522975 * allyValue;
								units_val_n1_0 += 0.9658813915793462 * allyValue;
								units_val_n1_1 += 0.9105145121522975 * allyValue;
								units_val_0_n1 += 0.5826933810245228 * allyValue;
								units_val_0_0 += 0.6246953073728085 * allyValue;
								units_val_0_1 += 0.5826933810245228 * allyValue;
								units_val_1_n1 += 0.24972514306351265 * allyValue;
								units_val_1_0 += 0.2835092231662708 * allyValue;
								units_val_1_1 += 0.24972514306351265 * allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.7592757231175133 * allyValue;
								units_val_n1_0 += 0.9105145121522975 * allyValue;
								units_val_n1_1 += 0.9658813915793462 * allyValue;
								units_val_0_n1 += 0.4636090896733879 * allyValue;
								units_val_0_0 += 0.5826933810245228 * allyValue;
								units_val_0_1 += 0.6246953073728085 * allyValue;
								units_val_1_n1 += 0.1520963508458832 * allyValue;
								units_val_1_0 += 0.24972514306351265 * allyValue;
								units_val_1_1 += 0.2835092231662708 * allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.5419096814653961 * allyValue;
								units_val_n1_0 += 0.7592757231175133 * allyValue;
								units_val_n1_1 += 0.9105145121522975 * allyValue;
								units_val_0_n1 += 0.2835092231662708 * allyValue;
								units_val_0_0 += 0.4636090896733879 * allyValue;
								units_val_0_1 += 0.5826933810245228 * allyValue;
								units_val_1_0 += 0.1520963508458832 * allyValue;
								units_val_1_1 += 0.24972514306351265 * allyValue;
								return;
						}
						break;
					case -3:
						switch (ally.location.y - my) {
							case -3:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += 0.7592757231175133 * allyValue;
								units_val_n1_1 += 0.4636090896733879 * allyValue;
								units_val_0_n1 += 0.7592757231175133 * allyValue;
								units_val_0_0 += 0.5419096814653961 * allyValue;
								units_val_0_1 += 0.2835092231662708 * allyValue;
								units_val_1_n1 += 0.4636090896733879 * allyValue;
								units_val_1_0 += 0.2835092231662708 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += 0.7592757231175133 * allyValue;
								units_val_0_n1 += 0.9105145121522975 * allyValue;
								units_val_0_0 += 0.7592757231175133 * allyValue;
								units_val_0_1 += 0.5419096814653961 * allyValue;
								units_val_1_n1 += 0.5826933810245228 * allyValue;
								units_val_1_0 += 0.4636090896733879 * allyValue;
								units_val_1_1 += 0.2835092231662708 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.9658813915793462 * allyValue;
								units_val_0_0 += 0.9105145121522975 * allyValue;
								units_val_0_1 += 0.7592757231175133 * allyValue;
								units_val_1_n1 += 0.6246953073728085 * allyValue;
								units_val_1_0 += 0.5826933810245228 * allyValue;
								units_val_1_1 += 0.4636090896733879 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.9105145121522975 * allyValue;
								units_val_0_0 += 0.9658813915793462 * allyValue;
								units_val_0_1 += 0.9105145121522975 * allyValue;
								units_val_1_n1 += 0.5826933810245228 * allyValue;
								units_val_1_0 += 0.6246953073728085 * allyValue;
								units_val_1_1 += 0.5826933810245228 * allyValue;
								return;
							case 1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.7592757231175133 * allyValue;
								units_val_0_0 += 0.9105145121522975 * allyValue;
								units_val_0_1 += 0.9658813915793462 * allyValue;
								units_val_1_n1 += 0.4636090896733879 * allyValue;
								units_val_1_0 += 0.5826933810245228 * allyValue;
								units_val_1_1 += 0.6246953073728085 * allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.7592757231175133 * allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.5419096814653961 * allyValue;
								units_val_0_0 += 0.7592757231175133 * allyValue;
								units_val_0_1 += 0.9105145121522975 * allyValue;
								units_val_1_n1 += 0.2835092231662708 * allyValue;
								units_val_1_0 += 0.4636090896733879 * allyValue;
								units_val_1_1 += 0.5826933810245228 * allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.4636090896733879 * allyValue;
								units_val_n1_0 += 0.7592757231175133 * allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.2835092231662708 * allyValue;
								units_val_0_0 += 0.5419096814653961 * allyValue;
								units_val_0_1 += 0.7592757231175133 * allyValue;
								units_val_1_0 += 0.2835092231662708 * allyValue;
								units_val_1_1 += 0.4636090896733879 * allyValue;
								return;
						}
						break;
					case -2:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.9105145121522975 * allyValue;
								units_val_n1_0 += 0.5826933810245228 * allyValue;
								units_val_n1_1 += 0.24972514306351265 * allyValue;
								units_val_0_n1 += 0.7592757231175133 * allyValue;
								units_val_0_0 += 0.4636090896733879 * allyValue;
								units_val_0_1 += 0.1520963508458832 * allyValue;
								units_val_1_n1 += 0.5419096814653961 * allyValue;
								units_val_1_0 += 0.2835092231662708 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += 0.9105145121522975 * allyValue;
								units_val_n1_1 += 0.5826933810245228 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += 0.7592757231175133 * allyValue;
								units_val_0_1 += 0.4636090896733879 * allyValue;
								units_val_1_n1 += 0.7592757231175133 * allyValue;
								units_val_1_0 += 0.5419096814653961 * allyValue;
								units_val_1_1 += 0.2835092231662708 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += 0.9105145121522975 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += 0.7592757231175133 * allyValue;
								units_val_1_n1 += 0.9105145121522975 * allyValue;
								units_val_1_0 += 0.7592757231175133 * allyValue;
								units_val_1_1 += 0.5419096814653961 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.9658813915793462 * allyValue;
								units_val_1_0 += 0.9105145121522975 * allyValue;
								units_val_1_1 += 0.7592757231175133 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.9105145121522975 * allyValue;
								units_val_1_0 += 0.9658813915793462 * allyValue;
								units_val_1_1 += 0.9105145121522975 * allyValue;
								return;
							case 1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.7592757231175133 * allyValue;
								units_val_1_0 += 0.9105145121522975 * allyValue;
								units_val_1_1 += 0.9658813915793462 * allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.9105145121522975 * allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.7592757231175133 * allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.5419096814653961 * allyValue;
								units_val_1_0 += 0.7592757231175133 * allyValue;
								units_val_1_1 += 0.9105145121522975 * allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.5826933810245228 * allyValue;
								units_val_n1_0 += 0.9105145121522975 * allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.4636090896733879 * allyValue;
								units_val_0_0 += 0.7592757231175133 * allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.2835092231662708 * allyValue;
								units_val_1_0 += 0.5419096814653961 * allyValue;
								units_val_1_1 += 0.7592757231175133 * allyValue;
								return;
							case 4:
								units_val_n1_n1 += 0.24972514306351265 * allyValue;
								units_val_n1_0 += 0.5826933810245228 * allyValue;
								units_val_n1_1 += 0.9105145121522975 * allyValue;
								units_val_0_n1 += 0.1520963508458832 * allyValue;
								units_val_0_0 += 0.4636090896733879 * allyValue;
								units_val_0_1 += 0.7592757231175133 * allyValue;
								units_val_1_0 += 0.2835092231662708 * allyValue;
								units_val_1_1 += 0.5419096814653961 * allyValue;
								return;
						}
						break;
					case -1:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.9658813915793462 * allyValue;
								units_val_n1_0 += 0.6246953073728085 * allyValue;
								units_val_n1_1 += 0.2835092231662708 * allyValue;
								units_val_0_n1 += 0.9105145121522975 * allyValue;
								units_val_0_0 += 0.5826933810245228 * allyValue;
								units_val_0_1 += 0.24972514306351265 * allyValue;
								units_val_1_n1 += 0.7592757231175133 * allyValue;
								units_val_1_0 += 0.4636090896733879 * allyValue;
								units_val_1_1 += 0.1520963508458832 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += 0.9658813915793462 * allyValue;
								units_val_n1_1 += 0.6246953073728085 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += 0.9105145121522975 * allyValue;
								units_val_0_1 += 0.5826933810245228 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += 0.7592757231175133 * allyValue;
								units_val_1_1 += 0.4636090896733879 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += 0.9658813915793462 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += 0.9105145121522975 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += 0.7592757231175133 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 0:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.9658813915793462 * allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.9105145121522975 * allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.7592757231175133 * allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.6246953073728085 * allyValue;
								units_val_n1_0 += 0.9658813915793462 * allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.5826933810245228 * allyValue;
								units_val_0_0 += 0.9105145121522975 * allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.4636090896733879 * allyValue;
								units_val_1_0 += 0.7592757231175133 * allyValue;
								units_val_1_1 += allyValue;
								return;
							case 4:
								units_val_n1_n1 += 0.2835092231662708 * allyValue;
								units_val_n1_0 += 0.6246953073728085 * allyValue;
								units_val_n1_1 += 0.9658813915793462 * allyValue;
								units_val_0_n1 += 0.24972514306351265 * allyValue;
								units_val_0_0 += 0.5826933810245228 * allyValue;
								units_val_0_1 += 0.9105145121522975 * allyValue;
								units_val_1_n1 += 0.1520963508458832 * allyValue;
								units_val_1_0 += 0.4636090896733879 * allyValue;
								units_val_1_1 += 0.7592757231175133 * allyValue;
								return;
						}
						break;
					case 0:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.9105145121522975 * allyValue;
								units_val_n1_0 += 0.5826933810245228 * allyValue;
								units_val_n1_1 += 0.24972514306351265 * allyValue;
								units_val_0_n1 += 0.9658813915793462 * allyValue;
								units_val_0_0 += 0.6246953073728085 * allyValue;
								units_val_0_1 += 0.2835092231662708 * allyValue;
								units_val_1_n1 += 0.9105145121522975 * allyValue;
								units_val_1_0 += 0.5826933810245228 * allyValue;
								units_val_1_1 += 0.24972514306351265 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += 0.9105145121522975 * allyValue;
								units_val_n1_1 += 0.5826933810245228 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += 0.9658813915793462 * allyValue;
								units_val_0_1 += 0.6246953073728085 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += 0.9105145121522975 * allyValue;
								units_val_1_1 += 0.5826933810245228 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += 0.9105145121522975 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += 0.9658813915793462 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += 0.9105145121522975 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 0:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.9105145121522975 * allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.9658813915793462 * allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.9105145121522975 * allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.5826933810245228 * allyValue;
								units_val_n1_0 += 0.9105145121522975 * allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.6246953073728085 * allyValue;
								units_val_0_0 += 0.9658813915793462 * allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.5826933810245228 * allyValue;
								units_val_1_0 += 0.9105145121522975 * allyValue;
								units_val_1_1 += allyValue;
								return;
							case 4:
								units_val_n1_n1 += 0.24972514306351265 * allyValue;
								units_val_n1_0 += 0.5826933810245228 * allyValue;
								units_val_n1_1 += 0.9105145121522975 * allyValue;
								units_val_0_n1 += 0.2835092231662708 * allyValue;
								units_val_0_0 += 0.6246953073728085 * allyValue;
								units_val_0_1 += 0.9658813915793462 * allyValue;
								units_val_1_n1 += 0.24972514306351265 * allyValue;
								units_val_1_0 += 0.5826933810245228 * allyValue;
								units_val_1_1 += 0.9105145121522975 * allyValue;
								return;
						}
						break;
					case 1:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.7592757231175133 * allyValue;
								units_val_n1_0 += 0.4636090896733879 * allyValue;
								units_val_n1_1 += 0.1520963508458832 * allyValue;
								units_val_0_n1 += 0.9105145121522975 * allyValue;
								units_val_0_0 += 0.5826933810245228 * allyValue;
								units_val_0_1 += 0.24972514306351265 * allyValue;
								units_val_1_n1 += 0.9658813915793462 * allyValue;
								units_val_1_0 += 0.6246953073728085 * allyValue;
								units_val_1_1 += 0.2835092231662708 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += 0.7592757231175133 * allyValue;
								units_val_n1_1 += 0.4636090896733879 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += 0.9105145121522975 * allyValue;
								units_val_0_1 += 0.5826933810245228 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += 0.9658813915793462 * allyValue;
								units_val_1_1 += 0.6246953073728085 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += 0.7592757231175133 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += 0.9105145121522975 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += 0.9658813915793462 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 0:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 1:
								units_val_n1_n1 += allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.7592757231175133 * allyValue;
								units_val_n1_0 += allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.9105145121522975 * allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.9658813915793462 * allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.4636090896733879 * allyValue;
								units_val_n1_0 += 0.7592757231175133 * allyValue;
								units_val_n1_1 += allyValue;
								units_val_0_n1 += 0.5826933810245228 * allyValue;
								units_val_0_0 += 0.9105145121522975 * allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.6246953073728085 * allyValue;
								units_val_1_0 += 0.9658813915793462 * allyValue;
								units_val_1_1 += allyValue;
								return;
							case 4:
								units_val_n1_n1 += 0.1520963508458832 * allyValue;
								units_val_n1_0 += 0.4636090896733879 * allyValue;
								units_val_n1_1 += 0.7592757231175133 * allyValue;
								units_val_0_n1 += 0.24972514306351265 * allyValue;
								units_val_0_0 += 0.5826933810245228 * allyValue;
								units_val_0_1 += 0.9105145121522975 * allyValue;
								units_val_1_n1 += 0.2835092231662708 * allyValue;
								units_val_1_0 += 0.6246953073728085 * allyValue;
								units_val_1_1 += 0.9658813915793462 * allyValue;
								return;
						}
						break;
					case 2:
						switch (ally.location.y - my) {
							case -4:
								units_val_n1_n1 += 0.5419096814653961 * allyValue;
								units_val_n1_0 += 0.2835092231662708 * allyValue;
								units_val_0_n1 += 0.7592757231175133 * allyValue;
								units_val_0_0 += 0.4636090896733879 * allyValue;
								units_val_0_1 += 0.1520963508458832 * allyValue;
								units_val_1_n1 += 0.9105145121522975 * allyValue;
								units_val_1_0 += 0.5826933810245228 * allyValue;
								units_val_1_1 += 0.24972514306351265 * allyValue;
								return;
							case -3:
								units_val_n1_n1 += 0.7592757231175133 * allyValue;
								units_val_n1_0 += 0.5419096814653961 * allyValue;
								units_val_n1_1 += 0.2835092231662708 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += 0.7592757231175133 * allyValue;
								units_val_0_1 += 0.4636090896733879 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += 0.9105145121522975 * allyValue;
								units_val_1_1 += 0.5826933810245228 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += 0.9105145121522975 * allyValue;
								units_val_n1_0 += 0.7592757231175133 * allyValue;
								units_val_n1_1 += 0.5419096814653961 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += 0.7592757231175133 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += 0.9105145121522975 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.9658813915793462 * allyValue;
								units_val_n1_0 += 0.9105145121522975 * allyValue;
								units_val_n1_1 += 0.7592757231175133 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.9105145121522975 * allyValue;
								units_val_n1_0 += 0.9658813915793462 * allyValue;
								units_val_n1_1 += 0.9105145121522975 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.7592757231175133 * allyValue;
								units_val_n1_0 += 0.9105145121522975 * allyValue;
								units_val_n1_1 += 0.9658813915793462 * allyValue;
								units_val_0_n1 += allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.5419096814653961 * allyValue;
								units_val_n1_0 += 0.7592757231175133 * allyValue;
								units_val_n1_1 += 0.9105145121522975 * allyValue;
								units_val_0_n1 += 0.7592757231175133 * allyValue;
								units_val_0_0 += allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.9105145121522975 * allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 3:
								units_val_n1_n1 += 0.2835092231662708 * allyValue;
								units_val_n1_0 += 0.5419096814653961 * allyValue;
								units_val_n1_1 += 0.7592757231175133 * allyValue;
								units_val_0_n1 += 0.4636090896733879 * allyValue;
								units_val_0_0 += 0.7592757231175133 * allyValue;
								units_val_0_1 += allyValue;
								units_val_1_n1 += 0.5826933810245228 * allyValue;
								units_val_1_0 += 0.9105145121522975 * allyValue;
								units_val_1_1 += allyValue;
								return;
							case 4:
								units_val_n1_0 += 0.2835092231662708 * allyValue;
								units_val_n1_1 += 0.5419096814653961 * allyValue;
								units_val_0_n1 += 0.1520963508458832 * allyValue;
								units_val_0_0 += 0.4636090896733879 * allyValue;
								units_val_0_1 += 0.7592757231175133 * allyValue;
								units_val_1_n1 += 0.24972514306351265 * allyValue;
								units_val_1_0 += 0.5826933810245228 * allyValue;
								units_val_1_1 += 0.9105145121522975 * allyValue;
								return;
						}
						break;
					case 3:
						switch (ally.location.y - my) {
							case -3:
								units_val_n1_n1 += 0.4636090896733879 * allyValue;
								units_val_n1_0 += 0.2835092231662708 * allyValue;
								units_val_0_n1 += 0.7592757231175133 * allyValue;
								units_val_0_0 += 0.5419096814653961 * allyValue;
								units_val_0_1 += 0.2835092231662708 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += 0.7592757231175133 * allyValue;
								units_val_1_1 += 0.4636090896733879 * allyValue;
								return;
							case -2:
								units_val_n1_n1 += 0.5826933810245228 * allyValue;
								units_val_n1_0 += 0.4636090896733879 * allyValue;
								units_val_n1_1 += 0.2835092231662708 * allyValue;
								units_val_0_n1 += 0.9105145121522975 * allyValue;
								units_val_0_0 += 0.7592757231175133 * allyValue;
								units_val_0_1 += 0.5419096814653961 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += 0.7592757231175133 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.6246953073728085 * allyValue;
								units_val_n1_0 += 0.5826933810245228 * allyValue;
								units_val_n1_1 += 0.4636090896733879 * allyValue;
								units_val_0_n1 += 0.9658813915793462 * allyValue;
								units_val_0_0 += 0.9105145121522975 * allyValue;
								units_val_0_1 += 0.7592757231175133 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.5826933810245228 * allyValue;
								units_val_n1_0 += 0.6246953073728085 * allyValue;
								units_val_n1_1 += 0.5826933810245228 * allyValue;
								units_val_0_n1 += 0.9105145121522975 * allyValue;
								units_val_0_0 += 0.9658813915793462 * allyValue;
								units_val_0_1 += 0.9105145121522975 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.4636090896733879 * allyValue;
								units_val_n1_0 += 0.5826933810245228 * allyValue;
								units_val_n1_1 += 0.6246953073728085 * allyValue;
								units_val_0_n1 += 0.7592757231175133 * allyValue;
								units_val_0_0 += 0.9105145121522975 * allyValue;
								units_val_0_1 += 0.9658813915793462 * allyValue;
								units_val_1_n1 += allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 2:
								units_val_n1_n1 += 0.2835092231662708 * allyValue;
								units_val_n1_0 += 0.4636090896733879 * allyValue;
								units_val_n1_1 += 0.5826933810245228 * allyValue;
								units_val_0_n1 += 0.5419096814653961 * allyValue;
								units_val_0_0 += 0.7592757231175133 * allyValue;
								units_val_0_1 += 0.9105145121522975 * allyValue;
								units_val_1_n1 += 0.7592757231175133 * allyValue;
								units_val_1_0 += allyValue;
								units_val_1_1 += allyValue;
								return;
							case 3:
								units_val_n1_0 += 0.2835092231662708 * allyValue;
								units_val_n1_1 += 0.4636090896733879 * allyValue;
								units_val_0_n1 += 0.2835092231662708 * allyValue;
								units_val_0_0 += 0.5419096814653961 * allyValue;
								units_val_0_1 += 0.7592757231175133 * allyValue;
								units_val_1_n1 += 0.4636090896733879 * allyValue;
								units_val_1_0 += 0.7592757231175133 * allyValue;
								units_val_1_1 += allyValue;
								return;
						}
						break;
					case 4:
						switch (ally.location.y - my) {
							case -2:
								units_val_n1_n1 += 0.24972514306351265 * allyValue;
								units_val_n1_0 += 0.1520963508458832 * allyValue;
								units_val_0_n1 += 0.5826933810245228 * allyValue;
								units_val_0_0 += 0.4636090896733879 * allyValue;
								units_val_0_1 += 0.2835092231662708 * allyValue;
								units_val_1_n1 += 0.9105145121522975 * allyValue;
								units_val_1_0 += 0.7592757231175133 * allyValue;
								units_val_1_1 += 0.5419096814653961 * allyValue;
								return;
							case -1:
								units_val_n1_n1 += 0.2835092231662708 * allyValue;
								units_val_n1_0 += 0.24972514306351265 * allyValue;
								units_val_n1_1 += 0.1520963508458832 * allyValue;
								units_val_0_n1 += 0.6246953073728085 * allyValue;
								units_val_0_0 += 0.5826933810245228 * allyValue;
								units_val_0_1 += 0.4636090896733879 * allyValue;
								units_val_1_n1 += 0.9658813915793462 * allyValue;
								units_val_1_0 += 0.9105145121522975 * allyValue;
								units_val_1_1 += 0.7592757231175133 * allyValue;
								return;
							case 0:
								units_val_n1_n1 += 0.24972514306351265 * allyValue;
								units_val_n1_0 += 0.2835092231662708 * allyValue;
								units_val_n1_1 += 0.24972514306351265 * allyValue;
								units_val_0_n1 += 0.5826933810245228 * allyValue;
								units_val_0_0 += 0.6246953073728085 * allyValue;
								units_val_0_1 += 0.5826933810245228 * allyValue;
								units_val_1_n1 += 0.9105145121522975 * allyValue;
								units_val_1_0 += 0.9658813915793462 * allyValue;
								units_val_1_1 += 0.9105145121522975 * allyValue;
								return;
							case 1:
								units_val_n1_n1 += 0.1520963508458832 * allyValue;
								units_val_n1_0 += 0.24972514306351265 * allyValue;
								units_val_n1_1 += 0.2835092231662708 * allyValue;
								units_val_0_n1 += 0.4636090896733879 * allyValue;
								units_val_0_0 += 0.5826933810245228 * allyValue;
								units_val_0_1 += 0.6246953073728085 * allyValue;
								units_val_1_n1 += 0.7592757231175133 * allyValue;
								units_val_1_0 += 0.9105145121522975 * allyValue;
								units_val_1_1 += 0.9658813915793462 * allyValue;
								return;
							case 2:
								units_val_n1_0 += 0.1520963508458832 * allyValue;
								units_val_n1_1 += 0.24972514306351265 * allyValue;
								units_val_0_n1 += 0.2835092231662708 * allyValue;
								units_val_0_0 += 0.4636090896733879 * allyValue;
								units_val_0_1 += 0.5826933810245228 * allyValue;
								units_val_1_n1 += 0.5419096814653961 * allyValue;
								units_val_1_0 += 0.7592757231175133 * allyValue;
								units_val_1_1 += 0.9105145121522975 * allyValue;
								return;
						}
						break;
				}
				break;
			case WATCHTOWER:
				if (ally.mode == RobotMode.TURRET) {
					allyValue = 4*ally.health*ally.health / (10 + rc.senseRubble(ally.location));
					switch (ally.location.x - mx) {
						case -4:
							switch (ally.location.y - my) {
								case -2:
									units_val_n1_n1 += 0.5092492456649377 * allyValue;
									units_val_n1_0 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.1356966876900471 * allyValue;
									return;
								case -1:
									units_val_n1_n1 += 0.5723398687919745 * allyValue;
									units_val_n1_0 += 0.5092492456649377 * allyValue;
									units_val_n1_1 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.18355793133013326 * allyValue;
									units_val_0_0 += 0.1356966876900471 * allyValue;
									return;
								case 0:
									units_val_n1_n1 += 0.5092492456649377 * allyValue;
									units_val_n1_0 += 0.5723398687919745 * allyValue;
									units_val_n1_1 += 0.5092492456649377 * allyValue;
									units_val_0_n1 += 0.1356966876900471 * allyValue;
									units_val_0_0 += 0.18355793133013326 * allyValue;
									units_val_0_1 += 0.1356966876900471 * allyValue;
									return;
								case 1:
									units_val_n1_n1 += 0.33691247068459573 * allyValue;
									units_val_n1_0 += 0.5092492456649377 * allyValue;
									units_val_n1_1 += 0.5723398687919745 * allyValue;
									units_val_0_0 += 0.1356966876900471 * allyValue;
									units_val_0_1 += 0.18355793133013326 * allyValue;
									return;
								case 2:
									units_val_n1_0 += 0.33691247068459573 * allyValue;
									units_val_n1_1 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.1356966876900471 * allyValue;
									return;
							}
							break;
						case -3:
							switch (ally.location.y - my) {
								case -3:
									units_val_n1_n1 += 0.6390443036490495 * allyValue;
									units_val_n1_0 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.33691247068459573 * allyValue;
									return;
								case -2:
									units_val_n1_n1 += 0.8693428405887492 * allyValue;
									units_val_n1_0 += 0.6390443036490495 * allyValue;
									units_val_n1_1 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.1356966876900471 * allyValue;
									return;
								case -1:
									units_val_n1_n1 += 0.9611218062538158 * allyValue;
									units_val_n1_0 += 0.8693428405887492 * allyValue;
									units_val_n1_1 += 0.6390443036490495 * allyValue;
									units_val_0_n1 += 0.5723398687919745 * allyValue;
									units_val_0_0 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.18355793133013326 * allyValue;
									units_val_1_0 += 0.1356966876900471 * allyValue;
									return;
								case 0:
									units_val_n1_n1 += 0.8693428405887492 * allyValue;
									units_val_n1_0 += 0.9611218062538158 * allyValue;
									units_val_n1_1 += 0.8693428405887492 * allyValue;
									units_val_0_n1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.5723398687919745 * allyValue;
									units_val_0_1 += 0.5092492456649377 * allyValue;
									units_val_1_n1 += 0.1356966876900471 * allyValue;
									units_val_1_0 += 0.18355793133013326 * allyValue;
									units_val_1_1 += 0.1356966876900471 * allyValue;
									return;
								case 1:
									units_val_n1_n1 += 0.6390443036490495 * allyValue;
									units_val_n1_0 += 0.8693428405887492 * allyValue;
									units_val_n1_1 += 0.9611218062538158 * allyValue;
									units_val_0_n1 += 0.33691247068459573 * allyValue;
									units_val_0_0 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.5723398687919745 * allyValue;
									units_val_1_0 += 0.1356966876900471 * allyValue;
									units_val_1_1 += 0.18355793133013326 * allyValue;
									return;
								case 2:
									units_val_n1_n1 += 0.33691247068459573 * allyValue;
									units_val_n1_0 += 0.6390443036490495 * allyValue;
									units_val_n1_1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += 0.33691247068459573 * allyValue;
									units_val_0_1 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.1356966876900471 * allyValue;
									return;
								case 3:
									units_val_n1_0 += 0.33691247068459573 * allyValue;
									units_val_n1_1 += 0.6390443036490495 * allyValue;
									units_val_0_1 += 0.33691247068459573 * allyValue;
									return;
							}
							break;
						case -2:
							switch (ally.location.y - my) {
								case -4:
									units_val_n1_n1 += 0.5092492456649377 * allyValue;
									units_val_n1_0 += 0.1356966876900471 * allyValue;
									units_val_0_n1 += 0.33691247068459573 * allyValue;
									return;
								case -3:
									units_val_n1_n1 += 0.8693428405887492 * allyValue;
									units_val_n1_0 += 0.5092492456649377 * allyValue;
									units_val_n1_1 += 0.1356966876900471 * allyValue;
									units_val_0_n1 += 0.6390443036490495 * allyValue;
									units_val_0_0 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.33691247068459573 * allyValue;
									return;
								case -2:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += 0.8693428405887492 * allyValue;
									units_val_n1_1 += 0.5092492456649377 * allyValue;
									units_val_0_n1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += 0.6390443036490495 * allyValue;
									units_val_0_1 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.33691247068459573 * allyValue;
									return;
								case -1:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += 0.8693428405887492 * allyValue;
									units_val_0_n1 += 0.9611218062538158 * allyValue;
									units_val_0_0 += 0.8693428405887492 * allyValue;
									units_val_0_1 += 0.6390443036490495 * allyValue;
									units_val_1_n1 += 0.5723398687919745 * allyValue;
									units_val_1_0 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.33691247068459573 * allyValue;
									return;
								case 0:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += 0.9611218062538158 * allyValue;
									units_val_0_1 += 0.8693428405887492 * allyValue;
									units_val_1_n1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.5723398687919745 * allyValue;
									units_val_1_1 += 0.5092492456649377 * allyValue;
									return;
								case 1:
									units_val_n1_n1 += 0.8693428405887492 * allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += 0.6390443036490495 * allyValue;
									units_val_0_0 += 0.8693428405887492 * allyValue;
									units_val_0_1 += 0.9611218062538158 * allyValue;
									units_val_1_n1 += 0.33691247068459573 * allyValue;
									units_val_1_0 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.5723398687919745 * allyValue;
									return;
								case 2:
									units_val_n1_n1 += 0.5092492456649377 * allyValue;
									units_val_n1_0 += 0.8693428405887492 * allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += 0.33691247068459573 * allyValue;
									units_val_0_0 += 0.6390443036490495 * allyValue;
									units_val_0_1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += 0.33691247068459573 * allyValue;
									units_val_1_1 += 0.5092492456649377 * allyValue;
									return;
								case 3:
									units_val_n1_n1 += 0.1356966876900471 * allyValue;
									units_val_n1_0 += 0.5092492456649377 * allyValue;
									units_val_n1_1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += 0.33691247068459573 * allyValue;
									units_val_0_1 += 0.6390443036490495 * allyValue;
									units_val_1_1 += 0.33691247068459573 * allyValue;
									return;
								case 4:
									units_val_n1_0 += 0.1356966876900471 * allyValue;
									units_val_n1_1 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.33691247068459573 * allyValue;
									return;
							}
							break;
						case -1:
							switch (ally.location.y - my) {
								case -4:
									units_val_n1_n1 += 0.5723398687919745 * allyValue;
									units_val_n1_0 += 0.18355793133013326 * allyValue;
									units_val_0_n1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.1356966876900471 * allyValue;
									units_val_1_n1 += 0.33691247068459573 * allyValue;
									return;
								case -3:
									units_val_n1_n1 += 0.9611218062538158 * allyValue;
									units_val_n1_0 += 0.5723398687919745 * allyValue;
									units_val_n1_1 += 0.18355793133013326 * allyValue;
									units_val_0_n1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.1356966876900471 * allyValue;
									units_val_1_n1 += 0.6390443036490495 * allyValue;
									units_val_1_0 += 0.33691247068459573 * allyValue;
									return;
								case -2:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += 0.9611218062538158 * allyValue;
									units_val_n1_1 += 0.5723398687919745 * allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += 0.8693428405887492 * allyValue;
									units_val_0_1 += 0.5092492456649377 * allyValue;
									units_val_1_n1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += 0.6390443036490495 * allyValue;
									units_val_1_1 += 0.33691247068459573 * allyValue;
									return;
								case -1:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += 0.9611218062538158 * allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += 0.8693428405887492 * allyValue;
									units_val_1_n1 += 0.9611218062538158 * allyValue;
									units_val_1_0 += 0.8693428405887492 * allyValue;
									units_val_1_1 += 0.6390443036490495 * allyValue;
									return;
								case 0:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += 0.9611218062538158 * allyValue;
									units_val_1_1 += 0.8693428405887492 * allyValue;
									return;
								case 1:
									units_val_n1_n1 += 0.9611218062538158 * allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += 0.6390443036490495 * allyValue;
									units_val_1_0 += 0.8693428405887492 * allyValue;
									units_val_1_1 += 0.9611218062538158 * allyValue;
									return;
								case 2:
									units_val_n1_n1 += 0.5723398687919745 * allyValue;
									units_val_n1_0 += 0.9611218062538158 * allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.8693428405887492 * allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += 0.33691247068459573 * allyValue;
									units_val_1_0 += 0.6390443036490495 * allyValue;
									units_val_1_1 += 0.8693428405887492 * allyValue;
									return;
								case 3:
									units_val_n1_n1 += 0.18355793133013326 * allyValue;
									units_val_n1_0 += 0.5723398687919745 * allyValue;
									units_val_n1_1 += 0.9611218062538158 * allyValue;
									units_val_0_n1 += 0.1356966876900471 * allyValue;
									units_val_0_0 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += 0.33691247068459573 * allyValue;
									units_val_1_1 += 0.6390443036490495 * allyValue;
									return;
								case 4:
									units_val_n1_0 += 0.18355793133013326 * allyValue;
									units_val_n1_1 += 0.5723398687919745 * allyValue;
									units_val_0_0 += 0.1356966876900471 * allyValue;
									units_val_0_1 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.33691247068459573 * allyValue;
									return;
							}
							break;
						case 0:
							switch (ally.location.y - my) {
								case -4:
									units_val_n1_n1 += 0.5092492456649377 * allyValue;
									units_val_n1_0 += 0.1356966876900471 * allyValue;
									units_val_0_n1 += 0.5723398687919745 * allyValue;
									units_val_0_0 += 0.18355793133013326 * allyValue;
									units_val_1_n1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.1356966876900471 * allyValue;
									return;
								case -3:
									units_val_n1_n1 += 0.8693428405887492 * allyValue;
									units_val_n1_0 += 0.5092492456649377 * allyValue;
									units_val_n1_1 += 0.1356966876900471 * allyValue;
									units_val_0_n1 += 0.9611218062538158 * allyValue;
									units_val_0_0 += 0.5723398687919745 * allyValue;
									units_val_0_1 += 0.18355793133013326 * allyValue;
									units_val_1_n1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.1356966876900471 * allyValue;
									return;
								case -2:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += 0.8693428405887492 * allyValue;
									units_val_n1_1 += 0.5092492456649377 * allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += 0.9611218062538158 * allyValue;
									units_val_0_1 += 0.5723398687919745 * allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += 0.8693428405887492 * allyValue;
									units_val_1_1 += 0.5092492456649377 * allyValue;
									return;
								case -1:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += 0.8693428405887492 * allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += 0.9611218062538158 * allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += 0.8693428405887492 * allyValue;
									return;
								case 0:
									units_val_n1_n1 += allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += allyValue;
									return;
								case 1:
									units_val_n1_n1 += 0.8693428405887492 * allyValue;
									units_val_n1_0 += allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += 0.9611218062538158 * allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += allyValue;
									return;
								case 2:
									units_val_n1_n1 += 0.5092492456649377 * allyValue;
									units_val_n1_0 += 0.8693428405887492 * allyValue;
									units_val_n1_1 += allyValue;
									units_val_0_n1 += 0.5723398687919745 * allyValue;
									units_val_0_0 += 0.9611218062538158 * allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.8693428405887492 * allyValue;
									units_val_1_1 += allyValue;
									return;
								case 3:
									units_val_n1_n1 += 0.1356966876900471 * allyValue;
									units_val_n1_0 += 0.5092492456649377 * allyValue;
									units_val_n1_1 += 0.8693428405887492 * allyValue;
									units_val_0_n1 += 0.18355793133013326 * allyValue;
									units_val_0_0 += 0.5723398687919745 * allyValue;
									units_val_0_1 += 0.9611218062538158 * allyValue;
									units_val_1_n1 += 0.1356966876900471 * allyValue;
									units_val_1_0 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.8693428405887492 * allyValue;
									return;
								case 4:
									units_val_n1_0 += 0.1356966876900471 * allyValue;
									units_val_n1_1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.18355793133013326 * allyValue;
									units_val_0_1 += 0.5723398687919745 * allyValue;
									units_val_1_0 += 0.1356966876900471 * allyValue;
									units_val_1_1 += 0.5092492456649377 * allyValue;
									return;
							}
							break;
						case 1:
							switch (ally.location.y - my) {
								case -4:
									units_val_n1_n1 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.1356966876900471 * allyValue;
									units_val_1_n1 += 0.5723398687919745 * allyValue;
									units_val_1_0 += 0.18355793133013326 * allyValue;
									return;
								case -3:
									units_val_n1_n1 += 0.6390443036490495 * allyValue;
									units_val_n1_0 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.1356966876900471 * allyValue;
									units_val_1_n1 += 0.9611218062538158 * allyValue;
									units_val_1_0 += 0.5723398687919745 * allyValue;
									units_val_1_1 += 0.18355793133013326 * allyValue;
									return;
								case -2:
									units_val_n1_n1 += 0.8693428405887492 * allyValue;
									units_val_n1_0 += 0.6390443036490495 * allyValue;
									units_val_n1_1 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += 0.8693428405887492 * allyValue;
									units_val_0_1 += 0.5092492456649377 * allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += 0.9611218062538158 * allyValue;
									units_val_1_1 += 0.5723398687919745 * allyValue;
									return;
								case -1:
									units_val_n1_n1 += 0.9611218062538158 * allyValue;
									units_val_n1_0 += 0.8693428405887492 * allyValue;
									units_val_n1_1 += 0.6390443036490495 * allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += 0.8693428405887492 * allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += 0.9611218062538158 * allyValue;
									return;
								case 0:
									units_val_n1_n1 += 0.8693428405887492 * allyValue;
									units_val_n1_0 += 0.9611218062538158 * allyValue;
									units_val_n1_1 += 0.8693428405887492 * allyValue;
									units_val_0_n1 += allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += allyValue;
									return;
								case 1:
									units_val_n1_n1 += 0.6390443036490495 * allyValue;
									units_val_n1_0 += 0.8693428405887492 * allyValue;
									units_val_n1_1 += 0.9611218062538158 * allyValue;
									units_val_0_n1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += 0.9611218062538158 * allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += allyValue;
									return;
								case 2:
									units_val_n1_n1 += 0.33691247068459573 * allyValue;
									units_val_n1_0 += 0.6390443036490495 * allyValue;
									units_val_n1_1 += 0.8693428405887492 * allyValue;
									units_val_0_n1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.8693428405887492 * allyValue;
									units_val_0_1 += allyValue;
									units_val_1_n1 += 0.5723398687919745 * allyValue;
									units_val_1_0 += 0.9611218062538158 * allyValue;
									units_val_1_1 += allyValue;
									return;
								case 3:
									units_val_n1_0 += 0.33691247068459573 * allyValue;
									units_val_n1_1 += 0.6390443036490495 * allyValue;
									units_val_0_n1 += 0.1356966876900471 * allyValue;
									units_val_0_0 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.8693428405887492 * allyValue;
									units_val_1_n1 += 0.18355793133013326 * allyValue;
									units_val_1_0 += 0.5723398687919745 * allyValue;
									units_val_1_1 += 0.9611218062538158 * allyValue;
									return;
								case 4:
									units_val_n1_1 += 0.33691247068459573 * allyValue;
									units_val_0_0 += 0.1356966876900471 * allyValue;
									units_val_0_1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.18355793133013326 * allyValue;
									units_val_1_1 += 0.5723398687919745 * allyValue;
									return;
							}
							break;
						case 2:
							switch (ally.location.y - my) {
								case -4:
									units_val_0_n1 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.1356966876900471 * allyValue;
									return;
								case -3:
									units_val_n1_n1 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.6390443036490495 * allyValue;
									units_val_0_0 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.1356966876900471 * allyValue;
									return;
								case -2:
									units_val_n1_n1 += 0.5092492456649377 * allyValue;
									units_val_n1_0 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += 0.6390443036490495 * allyValue;
									units_val_0_1 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += 0.8693428405887492 * allyValue;
									units_val_1_1 += 0.5092492456649377 * allyValue;
									return;
								case -1:
									units_val_n1_n1 += 0.5723398687919745 * allyValue;
									units_val_n1_0 += 0.5092492456649377 * allyValue;
									units_val_n1_1 += 0.33691247068459573 * allyValue;
									units_val_0_n1 += 0.9611218062538158 * allyValue;
									units_val_0_0 += 0.8693428405887492 * allyValue;
									units_val_0_1 += 0.6390443036490495 * allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += 0.8693428405887492 * allyValue;
									return;
								case 0:
									units_val_n1_n1 += 0.5092492456649377 * allyValue;
									units_val_n1_0 += 0.5723398687919745 * allyValue;
									units_val_n1_1 += 0.5092492456649377 * allyValue;
									units_val_0_n1 += 0.8693428405887492 * allyValue;
									units_val_0_0 += 0.9611218062538158 * allyValue;
									units_val_0_1 += 0.8693428405887492 * allyValue;
									units_val_1_n1 += allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += allyValue;
									return;
								case 1:
									units_val_n1_n1 += 0.33691247068459573 * allyValue;
									units_val_n1_0 += 0.5092492456649377 * allyValue;
									units_val_n1_1 += 0.5723398687919745 * allyValue;
									units_val_0_n1 += 0.6390443036490495 * allyValue;
									units_val_0_0 += 0.8693428405887492 * allyValue;
									units_val_0_1 += 0.9611218062538158 * allyValue;
									units_val_1_n1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += allyValue;
									units_val_1_1 += allyValue;
									return;
								case 2:
									units_val_n1_0 += 0.33691247068459573 * allyValue;
									units_val_n1_1 += 0.5092492456649377 * allyValue;
									units_val_0_n1 += 0.33691247068459573 * allyValue;
									units_val_0_0 += 0.6390443036490495 * allyValue;
									units_val_0_1 += 0.8693428405887492 * allyValue;
									units_val_1_n1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.8693428405887492 * allyValue;
									units_val_1_1 += allyValue;
									return;
								case 3:
									units_val_n1_1 += 0.33691247068459573 * allyValue;
									units_val_0_0 += 0.33691247068459573 * allyValue;
									units_val_0_1 += 0.6390443036490495 * allyValue;
									units_val_1_n1 += 0.1356966876900471 * allyValue;
									units_val_1_0 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.8693428405887492 * allyValue;
									return;
								case 4:
									units_val_0_1 += 0.33691247068459573 * allyValue;
									units_val_1_0 += 0.1356966876900471 * allyValue;
									units_val_1_1 += 0.5092492456649377 * allyValue;
									return;
							}
							break;
						case 3:
							switch (ally.location.y - my) {
								case -3:
									units_val_0_n1 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.6390443036490495 * allyValue;
									units_val_1_0 += 0.33691247068459573 * allyValue;
									return;
								case -2:
									units_val_n1_n1 += 0.1356966876900471 * allyValue;
									units_val_0_n1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += 0.6390443036490495 * allyValue;
									units_val_1_1 += 0.33691247068459573 * allyValue;
									return;
								case -1:
									units_val_n1_n1 += 0.18355793133013326 * allyValue;
									units_val_n1_0 += 0.1356966876900471 * allyValue;
									units_val_0_n1 += 0.5723398687919745 * allyValue;
									units_val_0_0 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.33691247068459573 * allyValue;
									units_val_1_n1 += 0.9611218062538158 * allyValue;
									units_val_1_0 += 0.8693428405887492 * allyValue;
									units_val_1_1 += 0.6390443036490495 * allyValue;
									return;
								case 0:
									units_val_n1_n1 += 0.1356966876900471 * allyValue;
									units_val_n1_0 += 0.18355793133013326 * allyValue;
									units_val_n1_1 += 0.1356966876900471 * allyValue;
									units_val_0_n1 += 0.5092492456649377 * allyValue;
									units_val_0_0 += 0.5723398687919745 * allyValue;
									units_val_0_1 += 0.5092492456649377 * allyValue;
									units_val_1_n1 += 0.8693428405887492 * allyValue;
									units_val_1_0 += 0.9611218062538158 * allyValue;
									units_val_1_1 += 0.8693428405887492 * allyValue;
									return;
								case 1:
									units_val_n1_0 += 0.1356966876900471 * allyValue;
									units_val_n1_1 += 0.18355793133013326 * allyValue;
									units_val_0_n1 += 0.33691247068459573 * allyValue;
									units_val_0_0 += 0.5092492456649377 * allyValue;
									units_val_0_1 += 0.5723398687919745 * allyValue;
									units_val_1_n1 += 0.6390443036490495 * allyValue;
									units_val_1_0 += 0.8693428405887492 * allyValue;
									units_val_1_1 += 0.9611218062538158 * allyValue;
									return;
								case 2:
									units_val_n1_1 += 0.1356966876900471 * allyValue;
									units_val_0_0 += 0.33691247068459573 * allyValue;
									units_val_0_1 += 0.5092492456649377 * allyValue;
									units_val_1_n1 += 0.33691247068459573 * allyValue;
									units_val_1_0 += 0.6390443036490495 * allyValue;
									units_val_1_1 += 0.8693428405887492 * allyValue;
									return;
								case 3:
									units_val_0_1 += 0.33691247068459573 * allyValue;
									units_val_1_0 += 0.33691247068459573 * allyValue;
									units_val_1_1 += 0.6390443036490495 * allyValue;
									return;
							}
							break;
						case 4:
							switch (ally.location.y - my) {
								case -2:
									units_val_0_n1 += 0.1356966876900471 * allyValue;
									units_val_1_n1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.33691247068459573 * allyValue;
									return;
								case -1:
									units_val_0_n1 += 0.18355793133013326 * allyValue;
									units_val_0_0 += 0.1356966876900471 * allyValue;
									units_val_1_n1 += 0.5723398687919745 * allyValue;
									units_val_1_0 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.33691247068459573 * allyValue;
									return;
								case 0:
									units_val_0_n1 += 0.1356966876900471 * allyValue;
									units_val_0_0 += 0.18355793133013326 * allyValue;
									units_val_0_1 += 0.1356966876900471 * allyValue;
									units_val_1_n1 += 0.5092492456649377 * allyValue;
									units_val_1_0 += 0.5723398687919745 * allyValue;
									units_val_1_1 += 0.5092492456649377 * allyValue;
									return;
								case 1:
									units_val_0_0 += 0.1356966876900471 * allyValue;
									units_val_0_1 += 0.18355793133013326 * allyValue;
									units_val_1_n1 += 0.33691247068459573 * allyValue;
									units_val_1_0 += 0.5092492456649377 * allyValue;
									units_val_1_1 += 0.5723398687919745 * allyValue;
									return;
								case 2:
									units_val_0_1 += 0.1356966876900471 * allyValue;
									units_val_1_0 += 0.33691247068459573 * allyValue;
									units_val_1_1 += 0.5092492456649377 * allyValue;
									return;
							}
							break;
					}
					break;
				}
			default:
				break;
		}
	}
}
