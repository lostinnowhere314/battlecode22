
import numpy as np
import sys
from scriptgen import *

def unit(x,y): #This variable denotes the combat score; + is in favor of us, - in favor of opponent
    return name('units_val_{}_{}',x,y)
def target(x,y):
    return name('target_{}_{}',x,y)
def targetval(x,y):
    return name('target_val_{}_{}',x,y)
def dpt(x,y):
    return name('self_dpt_{}_{}',x,y)

def pm_if_nonzero(name, v):
    if v==0:
        return ""
    elif v>0:
        return "+"+name
    else:
        return "-"+name

def generate_script(classname, package_name, n, action_r2, vision_r2, weights):
    """
    Return conventions for resulting function:
    -null if there are no targets in range
    -a TargetingResult object otherwise
        -MoveOrder field is MoveOrder.NONE if movement cooldown applies
        -direction field is null if no preferred direction; otherwise, contains preferred direction
    """
    direction_list = ['NORTH','NORTHEAST','EAST','SOUTHEAST','SOUTH','SOUTHWEST','WEST','NORTHWEST']
    dx_list =    [0, 1, 1, 1, 0, -1, -1, -1]
    dy_list =    [1, 1, 0, -1, -1, -1, 0, 1]
    
    soldier_d1, soldier_d2, sage_d, ally_soldier_w, ally_sage_w, forward_ratio, forward_steepness, rubble_cost, dist_bonus = weights
    
    #Declare variables
    #This function is only used by the end
    declarations = """
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
    
"""
    
    initializations = ""
    
    for x in range(-1,1+1):
        for y in range(-1,1+1):
            declarations += writeline_s(1, 'public static double {};'.format(unit(x,y)))
            declarations += writeline_s(1, 'public static RobotInfo {};'.format(target(x,y)))
            declarations += writeline_s(1, 'public static double {};'.format(targetval(x,y)))
            declarations += writeline_s(1, 'public static double {};'.format(dpt(x,y)))
            declarations += writeline_s(1, '')
            initializations += writeline_s(3, '{} = 0;'.format(unit(x,y)))
            initializations += writeline_s(3, '{} = null;'.format(target(x,y)))
            initializations += writeline_s(3, '{} = 0;'.format(targetval(x,y)))
            initializations += writeline_s(3, '')

    with open('../src/{}/{}.java'.format(package_name, classname),'w') as file:
        #write class header
        writeline(file,0,'package {};'.format(package_name))
        writeline(file,0,'')
        writeline(file,0,'import battlecode.common.*;')
        writeline(file,0,'import {}.Util.MoveOrder;'.format(package_name))
        writeline(file,0,'import {}.Util.TargetingResult;'.format(package_name))
        writeline(file,0,'')
        writeline(file,0,'public class {} '.format(classname)+'{')
        writeline(file,1,'')
        
        #Declarations
        file.write(declarations)
        writeline(file,1,'')
        writeline(file,1,'public static double currentScore;')
        writeline(file,1,'public static double allyValue;')
        writeline(file,1,'')
        writeline(file,1,'public static int mx;')
        writeline(file,1,'public static int my;')
        writeline(file,1,'')
        
        #start of function
        writeline(file,1,'public static TargetingResult getBestTarget(RobotController rc) throws GameActionException {')
        writeline(file,2,'')
        
        # Get enemies
        writeline(file,2,'RobotInfo[] nearbyEnemies;')
        writeline(file,2,'if (rc.isMovementReady()) {')
        writeline(file,3,'nearbyEnemies = rc.senseNearbyRobots(-1, rc.getTeam().opponent());')
        writeline(file,2,'} else {')
        writeline(file,3,'nearbyEnemies = rc.senseNearbyRobots(21, rc.getTeam().opponent());')
        writeline(file,2,'}')
        writeline(file,2,'')
        
        #Make sure there are enemies nearby for this
        writeline(file,2,'if (nearbyEnemies.length == 0) {')
        writeline(file,3,'return null;')
        writeline(file,2,'}')
        writeline(file,2,'')
        
        writeline(file,2,'MapLocation me = rc.getLocation();')
        writeline(file,2,'')
        
        #Check if we can move, because that complicates things
        writeline(file,2,'if (rc.isMovementReady()) {')
        #Initialize everything else
        file.write(initializations)
        writeline(file,3,'mx = me.x;')
        writeline(file,3,'my = me.y;')
        writeline(file,3,'')
        #writeline(file,3,'double dxEnemySum = 0;')
        #writeline(file,3,'double dyEnemySum = 0;')
        #writeline(file,3,'')
        writeline(file,3,'boolean attackingEnemy = false;')
        writeline(file,3,'')
        
        #Initialize DPT variables
        writeline(file,3,'{} = 0.3 / (1 + rc.senseRubble(me));'.format(dpt(0,0)))
        writeline(file,3,'')
        writeline(file,3,'MapLocation temp;')
        writeline(file,3,'')
        for dx, dy, dname in zip(dx_list, dy_list, direction_list):
            writeline(file,3,'temp = me.add(Direction.{});'.format(dname))
            writeline(file,3,'if (rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) {')
            writeline(file,4,'{} = 0.3/(0.1+rc.senseRubble(temp));'.format(dpt(dx,dy)))
            writeline(file,3,'} else {')
            writeline(file,4,'{} = 0;'.format(dpt(dx,dy)))
            writeline(file,3,'}')
            writeline(file,3,'')
        writeline(file,3,'')
        
        
        ######################### Score for enemies ###
        writeline(file,3,'for (int i = nearbyEnemies.length; --i>=0;) {')
        writeline(file,4,'RobotInfo robot = nearbyEnemies[i];')
        writeline(file,4,'double health = robot.getHealth();')
        writeline(file,4,'')
        writeline(file,4,'double targetScore;')
        writeline(file,4,'double targetDangerScore = 1;')
        writeline(file,4,'double combatScore = 0;')
        writeline(file,4,'')
        ############## Get base values
        writeline(file,4,'switch(robot.type) {')
        #Combat types
        writeline(file,5,'case SOLDIER:')
        writeline(file,6,'{')
        writeline(file,7,'double rubbleFactor = 10+rc.senseRubble(robot.location);')
        writeline(file,7,'targetScore = 10;')
        writeline(file,7,'targetDangerScore = {}/rubbleFactor;'.format(3))
        writeline(file,7,'combatScore = targetDangerScore*health;')
        writeline(file,6,'}')
        writeline(file,6,'break;')
        
        writeline(file,5,'case SAGE:')
        writeline(file,6,'{')
        writeline(file,7,'double rubbleFactor = 10+rc.senseRubble(robot.location);')
        writeline(file,7,'targetScore = 10;')
        writeline(file,7,'targetDangerScore = {}/rubbleFactor;'.format(45/12))
        writeline(file,7,'combatScore = targetDangerScore*health;')
        writeline(file,6,'}')
        writeline(file,6,'break;')
        
        writeline(file,5,'case WATCHTOWER:')
        writeline(file,6,'switch (robot.mode) {')
        writeline(file,7,'case TURRET:')
        writeline(file,8,'{')
        writeline(file,9,'double rubbleFactor = 10+rc.senseRubble(robot.location);')
        writeline(file,9,'targetScore = 10;')
        writeline(file,9,'targetDangerScore = {}*robot.level/rubbleFactor;'.format(4))
        writeline(file,9,'combatScore = targetDangerScore * health;')
        writeline(file,8,'}')
        writeline(file,8,'break;')
        writeline(file,7,'default:')
        writeline(file,8,'{')
        writeline(file,9,'targetScore = 10;')
        writeline(file,9,'targetDangerScore = 1;')
        writeline(file,9,'combatScore = 0;')
        writeline(file,8,'}')
        writeline(file,8,'break;')
        writeline(file,6,'}')
        writeline(file,6,'break;')
        #Non-combat
        writeline(file,5,'case ARCHON:')
        writeline(file,6,'{')
        writeline(file,7,'targetScore = 8;')
        writeline(file,7,'combatScore = 1;')
        writeline(file,6,'}')
        writeline(file,6,'break;')
        
        writeline(file,5,'case BUILDER:')
        writeline(file,6,'{')
        writeline(file,7,'targetScore = 9;')
        writeline(file,6,'}')
        writeline(file,6,'break;')
        
        writeline(file,5,'case LABORATORY:')
        writeline(file,6,'{')
        writeline(file,7,'targetScore = 7;')
        writeline(file,6,'}')
        writeline(file,6,'break;')
        
        writeline(file,5,'case MINER:')
        writeline(file,5,'default:')
        writeline(file,6,'{')
        writeline(file,7,'targetScore = 5;')
        writeline(file,6,'}')
        writeline(file,6,'break;')
        writeline(file,4,'}')
        
        writeline(file,4,'')
        writeline(file,4,'targetDangerScore /= health;')
        writeline(file,4,'')
        ####################################################################
        
        writeline(file,4,'int dx = robot.location.x-mx;')
        writeline(file,4,'int dy = robot.location.y-my;')
        writeline(file,4,'')
        
        #Update threat values - TODO change
        writeline(file,4,'switch (robot.type) {')
        writeline(file,5,'case SOLDIER:')
        writeline(file,6,'switch (dx) {')
        for dx in range(-n,n+1):
            writeline(file,7,'case {}:'.format(dx))
            writeline(file,8,'switch (dy) {')
            for dy in range(-n,n+1):
                if dist2(0,0,dx,dy) <= vision_r2:
                    writeline(file,9,'case {}:'.format(dy))
                    for x in range(-1,2):
                        for y in range(-1,2):
                            #Check to increase enemy danger
                            if dist2(x,y,dx,dy) <= action_r2:
                                writeline(file, 10, '{} -= combatScore;'.format(unit(x,y)))
                            elif dist2(x,y,dx,dy) <= vision_r2:
                                writeline(file, 10, '{} -= {}*combatScore;'.format(unit(x,y), 10/16))
                            
                    writeline(file,10,'break;')
            writeline(file,9,'default:')
            writeline(file,10,'break;')
            writeline(file,8,'}')
            writeline(file,8,'break;')
                
        writeline(file,6,'}')
        #writeline(file,6,'dxEnemySum += dx;')
        #writeline(file,6,'dyEnemySum += dy;')
        writeline(file,6,'attackingEnemy = true;')
        writeline(file,6,'break;')
        
        writeline(file,5,'case SAGE:')
        writeline(file,6,'switch (dx) {')
        for dx in range(-n,n+1):
            writeline(file,7,'case {}:'.format(dx))
            writeline(file,8,'switch (dy) {')
            for dy in range(-n,n+1):
                if dist2(0,0,dx,dy) <= vision_r2:
                    writeline(file,9,'case {}:'.format(dy))
                    for x in range(-1,2):
                        for y in range(-1,2):
                            #Check to increase enemy danger
                            if dist2(x,y,dx,dy) <= 20:
                                writeline(file, 10, '{} -= combatScore;'.format(unit(x,y)))
                            elif dist2(x,y,dx,dy) <= 34:
                                writeline(file, 10, '{} -= {}*combatScore;'.format(unit(x,y), 10/16))
                    writeline(file,10,'break;')
            writeline(file,9,'default:')
            writeline(file,10,'break;')
            writeline(file,8,'}')
            writeline(file,8,'break;')
        writeline(file,6,'}')
        #writeline(file,6,'dxEnemySum += 2*dx;')
        #writeline(file,6,'dyEnemySum += 2*dy;')
        writeline(file,6,'attackingEnemy = true;')
        writeline(file,6,'break;')
        
        writeline(file,5,'case WATCHTOWER:')
        writeline(file,6,'switch (dx) {')
        for dx in range(-n,n+1):
            writeline(file,7,'case {}:'.format(dx))
            writeline(file,8,'switch (dy) {')
            for dy in range(-n,n+1):
                if dist2(0,0,dx,dy) <= vision_r2:
                    writeline(file,9,'case {}:'.format(dy))
                    for x in range(-1,2):
                        for y in range(-1,2):
                            #Check to increase enemy danger
                            if dist2(x,y,dx,dy) <= 20:
                                writeline(file, 10, '{} -= combatScore;'.format(unit(x,y)))
                    writeline(file,10,'break;')
            writeline(file,9,'default:')
            writeline(file,10,'break;')
            writeline(file,8,'}')
            writeline(file,8,'break;')
        writeline(file,6,'}')
        #writeline(file,6,'dxEnemySum += 2*dx;')
        #writeline(file,6,'dyEnemySum += 2*dy;')
        writeline(file,6,'attackingEnemy = true;')
        writeline(file,6,'break;')
        
        writeline(file,5,'default:')
        writeline(file,6,'break;')
        writeline(file,4,'}')
        writeline(file,4,'')
        ########
        
        #Update target values
        writeline(file,4,'switch (dx) {')
        for dx in range(-n,n+1):
            if dist2(0,0,dx,0) <= vision_r2:
                writeline(file,5,'case {}:'.format(dx))
                writeline(file,6,'switch (dy) {')
                for dy in range(-n,n+1):
                    if dist2(0,0,dx,dy) <= vision_r2:
                        writeline(file,7,'case {}:'.format(dy))
                        for x in range(-1,2):
                            for y in range(-1,2):
                                #Check to increase enemy danger
                                if dist2(x,y,dx,dy) <= action_r2:
                                    #print("({},{}),({},{}): {}".format(x,y,dx,dy,dist2(x,y,dx,dy)))
                                    writeline(file,8,'currentScore = targetScore + ({}+targetDangerScore)*{};'.format(dpt(x,y),dpt(x,y)))
                                    writeline(file,8,'if ({} < currentScore) '.format(targetval(x,y))+'{')
                                    writeline(file,9,'{} = currentScore;'.format(targetval(x,y)))
                                    writeline(file,9,'{} = robot;'.format(target(x,y)))
                                    writeline(file,8,'}')
                                
                        writeline(file,8,'break;')
                        writeline(file,8,'')
                writeline(file,7,'default:')
                writeline(file,8,'break;')
                writeline(file,6,'}')
                writeline(file,6,'break;')
        writeline(file,4,'}')
        ###########
        writeline(file,3,'}')
        writeline(file,3,'')
        #### End enemy scan ####
        
        # If there are no visible enemies that can attack us, return early
        writeline(file,3,'if (!attackingEnemy) {')
        writeline(file,4,'if ({} == null) '.format(target(0,0))+'{')
        writeline(file,5,'return null;')
        writeline(file,4,'} else {')
        writeline(file,5,'return new TargetingResult({}, null, MoveOrder.AFTER, rc.isActionReady(), true, false);'.format(target(0,0)))
        writeline(file,4,'}')
        writeline(file,3,'}')
        writeline(file,3,'')
        
        # Normalize the mean-enemy-position vector - from old heuristic
        #writeline(file,3,'double norm = Math.sqrt(dxEnemySum*dxEnemySum+dyEnemySum*dyEnemySum) / {};'.format(forward_steepness))
        #writeline(file,3,'if (norm > 0) {')
        #writeline(file,4,'dxEnemySum /= norm;')
        #writeline(file,4,'dyEnemySum /= norm;')
        #writeline(file,3,'}')
        #writeline(file,3,'')
        
        #Otherwise, check for nearby allies
        writeline(file,3,'RobotInfo[] nearbyAllies = rc.senseNearbyRobots(-1, rc.getTeam());')
        writeline(file,3,'')
        
        writeline(file,3,'for (int i = nearbyAllies.length; --i>=0;) {')
        writeline(file,4,'calculateAllyValue(rc, nearbyAllies[i]);')
        writeline(file,3,'}')
        writeline(file,3,'')
        
        #Calculate our own contribution
        for x in range(-1,2):
            for y in range(-1,2):
                writeline(file,3,'{} += {} * rc.getHealth();'.format(unit(x,y), dpt(x,y)))
        
        writeline(file,3,'')
        
        writeline(file,3,'')
        writeline(file,3,'Direction bestDirection = Direction.CENTER;')
        writeline(file,3,'boolean retreat = false;')
        writeline(file,3,'')
        
        #Calculate the best direction
        #First decide if we need to retreat
        writeline(file,3,'if ({} < 0) '.format(unit(0,0))+'{')
        # If we do, only check for the unit odds of each location
        # If the best one is negative, we mark 'retreat' mode and don't return a direction
        writeline(file,4,'double bestScore = {};'.format(unit(0,0)))
        writeline(file,4,'')
        
        for x,y,dname in zip(dx_list, dy_list, direction_list):
            writeline(file,4,'temp = me.add(Direction.{});'.format(dname))
            writeline(file,4,'if (bestScore < {} && rc.canSenseLocation(temp) && !rc.canSenseRobotAtLocation(temp)) '.format(unit(x,y)) + '{')
            writeline(file,5,'bestScore = {};'.format(unit(x,y)))
            writeline(file,5,'bestDirection = Direction.{};'.format(dname))
            writeline(file,4,'}')
            writeline(file,4,'')
        writeline(file,4,'if (bestScore < 0) {')
        writeline(file,5,'retreat = true;')
        #writeline(file,5,'bestDirection = Direction.CENTER;')
        writeline(file,4,'}')
            
        
        ######################################
        
        writeline(file,3,'} else {')
        #Otherwise, we find the best location with positive odds
        writeline(file,4,'double bestScore = ({} + {})*{};'.format(unit(0,0),targetval(0,0),dpt(0,0)))
        writeline(file,4,'double score;')
        writeline(file,4,'')
        for x,y,dname in zip(dx_list, dy_list, direction_list):
            writeline(file,4,'if ({} > 0) '.format(unit(x,y)) + '{')
            writeline(file,5,'score = ({} + {})*{};'.format(unit(x,y),targetval(x,y),dpt(x,y)))
            writeline(file,5,'')
            writeline(file,5,'if (score > bestScore) {')
            writeline(file,6,'bestScore = score;')
            writeline(file,6,'bestDirection = Direction.{};'.format(dname))
            writeline(file,5,'}')
            writeline(file,4,'}')
            writeline(file,4,'')
        writeline(file,3,'}')
        ######################################
        
        #Figure out which the best enemy we can attack is
        writeline(file,3,'')
        writeline(file,3,'MoveOrder order;')
        writeline(file,3,'RobotInfo bestTarget;')
        writeline(file,3,'')
        writeline(file,3,'if (retreat) {')
        writeline(file,4,'bestTarget = {};'.format(target(0,0)))
        writeline(file,4,'order = MoveOrder.BEFORE;')
        writeline(file,3,'} else {')
        writeline(file,3,'switch (bestDirection) {')
        for x,y,dname in zip(dx_list, dy_list, direction_list):
            writeline(file,4,'case {}:'.format(dname))
            writeline(file,5,'if ({} > {}) '.format(targetval(0,0),targetval(x,y))+'{')
            writeline(file,6,'bestTarget = {};'.format(target(0,0)))
            writeline(file,6,'order = MoveOrder.AFTER;')
            writeline(file,5,'} else {')
            writeline(file,6,'bestTarget = {};'.format(target(x,y)))
            writeline(file,6,'order = MoveOrder.BEFORE;')
            writeline(file,5,'}')
            writeline(file,5,'break;')
            
        writeline(file,4,'default:')
        writeline(file,5,'order = MoveOrder.AFTER;')
        writeline(file,5,'bestTarget = {};'.format(target(0,0)))
        writeline(file,5,'break;')
        writeline(file,3,'}')
        writeline(file,3,'}')
        writeline(file,3,'')
        writeline(file,3,'if (retreat)')
        writeline(file,4,'bestDirection = null;')
        writeline(file,3,'')
        
        #Movement ready is guaranteed in this part of the code
        writeline(file,3,'return new TargetingResult(bestTarget, bestDirection, order, '
                         'bestTarget != null && rc.isActionReady(), bestDirection != Direction.CENTER, retreat);')
        
        writeline(file,3,'')
        
        if False:
            ############# OLD VERSION BELOW ###################################
            #Determine if the best enemy is attackable from where we are currently
            writeline(file,3,'if (')
            for x in range(-1,2):
                for y in range(-1,2):
                    if x==0 and y==0:
                        continue
                    if x==1 and y==1:
                        writeline(file,4,'{} >= {}'.format(targetval(0,0),targetval(x,y)))
                    else:
                        writeline(file,4,'{} >= {} &&'.format(targetval(0,0),targetval(x,y)))
                        
            writeline(file,3,'\t) {')
            #Then, we want to attack before moving
            
            writeline(file,4,'Direction bestDirection = Direction.CENTER;')
            writeline(file,4,'double bestScore = {} - {} * rc.senseRubble(me);'.format(
                            unit(0,0), rubble_cost
                            ))
            writeline(file,4,'')
            writeline(file,4,'double score;')
            writeline(file,4,'MapLocation other;')
            writeline(file,4,'')
            #Check all the others
            for x,y,dname in zip(dx_list, dy_list, direction_list):
                writeline(file,4,'other = me.add(Direction.{});'.format(dname))
                writeline(file,4,'if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {')
                writeline(file,5,'score = {} - {} * rc.senseRubble(other);'.format(
                                unit(x,y), rubble_cost
                                ))
                writeline(file,5,'if (score > bestScore) {')
                writeline(file,6,'bestScore = score;')
                writeline(file,6,'bestDirection = Direction.{};'.format(dname))
                writeline(file,5,'}')
                writeline(file,4,'}')
                writeline(file,4,'')
                    
            writeline(file,4,'return new TargetingResult({}, bestDirection, MoveOrder.AFTER, true, true, bestScore < 0);'.format(target(0,0)))
            writeline(file,4,'')
            ########
            
            writeline(file,3,'} else {')
            #Check values for which spot we want to move to, factoring in allies and rubble
            
            writeline(file,4,'Direction bestDirection = Direction.CENTER;')
            writeline(file,4,'double bestScore = {} + {} - {} * rc.senseRubble(me);'.format(
                            unit(0,0), targetval(0,0), rubble_cost
                            ))
            writeline(file,4,'')
            writeline(file,4,'double score;')
            writeline(file,4,'MapLocation other;')
            writeline(file,4,'')
            #Check all the others
            for x,y,dname in zip(dx_list, dy_list, direction_list):
                writeline(file,4,'other = me.add(Direction.{});'.format(dname))
                writeline(file,4,'if (!rc.canSenseRobotAtLocation(other) && rc.canSenseLocation(other)) {')
                writeline(file,5,'score = {} + {} - {} * rc.senseRubble(other);'.format(
                                unit(x,y), targetval(x,y), rubble_cost
                                ))
                writeline(file,5,'if (score > bestScore) {')
                writeline(file,6,'bestScore = score;')
                writeline(file,6,'bestDirection = Direction.{};'.format(dname))
                writeline(file,5,'}')
                writeline(file,4,'}')
                writeline(file,4,'')
            
            #Determine the best target of the (at most) two we will be able to attack
            writeline(file,4,'if (bestDirection == Direction.CENTER) {')
            writeline(file,5,'return new TargetingResult({}, bestDirection, MoveOrder.AFTER, true, false, false);'.format(target(0,0)))
            writeline(file,4,'} else {')
            writeline(file,5,'MoveOrder order;')
            writeline(file,5,'RobotInfo bestTarget;')
            writeline(file,5,'')
            writeline(file,5,'switch (bestDirection) {')
            for x,y,dname in zip(dx_list, dy_list, direction_list):
                writeline(file,6,'case {}:'.format(dname))
                writeline(file,7,'if ({} > {}) '.format(targetval(0,0),targetval(x,y))+'{')
                writeline(file,8,'bestTarget = {};'.format(target(0,0)))
                writeline(file,8,'order = MoveOrder.AFTER;')
                writeline(file,7,'} else {')
                writeline(file,8,'bestTarget = {};'.format(target(x,y)))
                writeline(file,8,'order = MoveOrder.BEFORE;')
                writeline(file,7,'}')
                writeline(file,7,'break;')
                
            writeline(file,6,'default:')
            writeline(file,7,'order = null;')
            writeline(file,7,'bestTarget = null;')
            writeline(file,7,'break;')
            writeline(file,5,'}')
            writeline(file,5,'')
            
            writeline(file,5,'return new TargetingResult(bestTarget, bestDirection, order, true, true, bestScore < 0);')
            writeline(file,4,'}')
            writeline(file,3,'}')
            ############################################### End old code
        
        writeline(file,2,'} else {')
        
        #Code for finding target w/o moving
        writeline(file,3,'RobotInfo bestEnemy = null;')
        writeline(file,3,'double bestScore = 0;')
        writeline(file,3,'')
        writeline(file,3,'for (int i = nearbyEnemies.length; --i>=0;) {')
        writeline(file,4,'RobotInfo enemy = nearbyEnemies[i];')
        writeline(file,4,'if (me.isWithinDistanceSquared(enemy.location, {})) '.format(action_r2)+'{')
        writeline(file,5,'double score = getTargetValue(rc, enemy);')
        writeline(file,5,'if (score > bestScore) {')
        writeline(file,6,'bestEnemy = enemy;')
        writeline(file,6,'bestScore = score;')
        writeline(file,5,'}')
        writeline(file,4,'}')
        writeline(file,3,'}')
        writeline(file,3,'')
        writeline(file,3,'if (bestEnemy == null) {')
        writeline(file,4,'return null;')
        writeline(file,3,'} else {')
        writeline(file,4,'return new TargetingResult(bestEnemy, null, MoveOrder.NONE, rc.isActionReady(), false, false);')
        writeline(file,3,'}')
        writeline(file,3,'')
        
        writeline(file,2,'}')
        writeline(file,1,'}')
        ########################################################### Function for calculating ally scores (hit function bytecode limit before)
        writeline(file,1,'')
        writeline(file,1,'public static void calculateAllyValue(RobotController rc, RobotInfo ally) throws GameActionException {')
        writeline(file,2,'switch (ally.type) {')
        
        writeline(file,3,'case SOLDIER:')
        writeline(file,4,'allyValue = 3*ally.health / (10 + rc.senseRubble(ally.location));')
        writeline(file,4,'switch (ally.location.x - mx) {')
        for dx in range(-n,n+1):
            if dist2(0,0,dx,0) <= vision_r2:
                writeline(file,5,'case {}:'.format(dx))
                writeline(file,6,'switch (ally.location.y - my) {')
                for dy in range(-n,n+1):
                    if dist2(0,0,dx,dy) <= vision_r2:
                        writeline(file,7,'case {}:'.format(dy))
                        for x in range(-1,2):
                            for y in range(-1,2):
                                #Make a heuristic about whether we think they can see enemies
                                #TODO improve this
                                dist = np.sqrt(dist2(x,y,dx,dy))
                                if (dist <= 1.9):
                                    writeline(file,8,'{} += allyValue;'.format(unit(x,y)))
                                else:
                                    factor = (np.sqrt(vision_r2)-dist)/(np.sqrt(vision_r2)-1.9)
                                    if factor > 0.1:
                                        writeline(file,8,'{} += {} * allyValue;'.format(unit(x,y), factor))
                        writeline(file,8,'return;')
                writeline(file,6,'}')
                writeline(file,6,'break;')
        writeline(file,3,'}')
        writeline(file,4,'break;')
        
        writeline(file,3,'case SAGE:')
        writeline(file,4,'allyValue = {}*ally.health / (10 + rc.senseRubble(ally.location));'.format(45/12))
        writeline(file,4,'switch (ally.location.x - mx) {')
        for dx in range(-n,n+1):
            if dist2(0,0,dx,0) <= vision_r2:
                writeline(file,5,'case {}:'.format(dx))
                writeline(file,6,'switch (ally.location.y - my) {')
                for dy in range(-n,n+1):
                    if dist2(0,0,dx,dy) <= vision_r2:
                        writeline(file,7,'case {}:'.format(dy))
                        for x in range(-1,2):
                            for y in range(-1,2):
                                #Make a heuristic about whether we think they can see enemies
                                #TODO improve this
                                dist = np.sqrt(dist2(x,y,dx,dy))
                                if (dist <= 2.9):
                                    writeline(file,8,'{} += allyValue;'.format(unit(x,y)))
                                else:
                                    factor = (np.sqrt(34)-dist)/(np.sqrt(34)-2.9)
                                    if factor > 0.1:
                                        writeline(file,8,'{} += {} * allyValue;'.format(unit(x,y), factor))
                        writeline(file,8,'return;')
                writeline(file,6,'}')
                writeline(file,6,'break;')
        writeline(file,4,'}')
        writeline(file,4,'break;')
        
        writeline(file,3,'case WATCHTOWER:')
        writeline(file,4,'if (ally.mode == RobotMode.TURRET) {')
        writeline(file,5,'allyValue = 4*ally.health*ally.health / (10 + rc.senseRubble(ally.location));')
        writeline(file,5,'switch (ally.location.x - mx) {')
        for dx in range(-n,n+1):
            if dist2(0,0,dx,0) <= vision_r2:
                writeline(file,6,'case {}:'.format(dx))
                writeline(file,7,'switch (ally.location.y - my) {')
                for dy in range(-n,n+1):
                    if dist2(0,0,dx,dy) <= vision_r2:
                        writeline(file,8,'case {}:'.format(dy))
                        for x in range(-1,2):
                            for y in range(-1,2):
                                #Make a heuristic about whether we think they can see enemies
                                #TODO improve this
                                dist = np.sqrt(dist2(x,y,dx,dy))
                                if (dist <= 1.9):
                                    writeline(file,9,'{} += allyValue;'.format(unit(x,y)))
                                else:
                                    factor = (np.sqrt(20)-dist)/(np.sqrt(20)-1.9)
                                    if factor > 0.1:
                                        writeline(file,9,'{} += {} * allyValue;'.format(unit(x,y), factor))
                        writeline(file,9,'return;')
                writeline(file,7,'}')
                writeline(file,7,'break;')
        writeline(file,5,'}')
        writeline(file,5,'break;')
        writeline(file,4,'}')
        
        writeline(file,3,'default:')
        writeline(file,4,'break;')
        writeline(file,2,'}')
        writeline(file,1,'}')
        writeline(file,0,'}')
    
if __name__=="__main__":
    if len(sys.argv)==2:
        package_name = sys.argv[1]
        generate_script("SoldierTargeting", package_name, 5, 13, 20, [2700, 3000, 5000, 6000, 12000, 1/4, 4, 100, 150])
        print('done.')
    elif len(sys.argv) > 2:
        package_name = sys.argv[1]
        generate_script("SoldierTargeting", package_name, 5, 13, 20, sys.argv[2:])
        print('done.')