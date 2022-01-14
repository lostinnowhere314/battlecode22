
import numpy as np
import sys
from scriptgen import *

def unit(x,y):
    return name('units_val_{}_{}',x,y)
def target(x,y):
    return name('target_{}_{}',x,y)
def targetval(x,y):
    return name('target_val_{}_{}',x,y)

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
    declarations = """
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
    
"""
    
    initializations = ""
    
    for x in range(-1,1+1):
        for y in range(-1,1+1):
            declarations += writeline_s(1, 'public static double {};'.format(unit(x,y)))
            declarations += writeline_s(1, 'public static RobotInfo {};'.format(target(x,y)))
            declarations += writeline_s(1, 'public static double {};'.format(targetval(x,y)))
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
        writeline(file,3,'int mx = me.x;')
        writeline(file,3,'int my = me.y;')
        writeline(file,3,'')
        writeline(file,3,'double dxEnemySum = 0;')
        writeline(file,3,'double dyEnemySum = 0;')
        writeline(file,3,'')
        writeline(file,3,'boolean attackingEnemy = false;')
        writeline(file,3,'')
        
        #Score for enemies
        writeline(file,3,'for (int i = nearbyEnemies.length; --i>=0;) {')
        writeline(file,4,'RobotInfo robot = nearbyEnemies[i];')
        writeline(file,4,'double targetScore = getTargetValue(robot);')
        writeline(file,4,'')
        writeline(file,4,'int dx = robot.location.x-mx;')
        writeline(file,4,'int dy = robot.location.y-my;')
        writeline(file,4,'')
        
        #Update threat values
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
                                writeline(file,10,'{} -= {};'.format(unit(x,y), soldier_d2 - dist_bonus * np.sqrt(dist2(x,y,dx,dy))))
                            elif dist2(x,y,dx,dy) <= vision_r2:
                                writeline(file,10,'{} -= {};'.format(unit(x,y), soldier_d1 - dist_bonus * np.sqrt(dist2(x,y,dx,dy))))
                            
                    writeline(file,10,'break;')
            writeline(file,9,'default:')
            writeline(file,10,'break;')
            writeline(file,8,'}')
            writeline(file,8,'break;')
                
        writeline(file,6,'}')
        writeline(file,6,'dxEnemySum += dx;')
        writeline(file,6,'dyEnemySum += dy;')
        writeline(file,6,'attackingEnemy = true;')
        writeline(file,6,'break;')
        
        writeline(file,5,'case SAGE:')
        writeline(file,5,'case WATCHTOWER:')
        for x in range(-1,2):
            for y in range(-1,2):
                #Increase enemy danger
                writeline(file,6,'{} -= {};'.format(unit(x,y), sage_d - dist_bonus * np.sqrt(dist2(x,y,dx,dy))))
        writeline(file,6,'dxEnemySum += 2*dx;')
        writeline(file,6,'dyEnemySum += 2*dy;')
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
                                writeline(file,8,'if ({} < targetScore) '.format(targetval(x,y))+'{')
                                writeline(file,9,'{} = targetScore;'.format(targetval(x,y)))
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
        writeline(file,5,'return new TargetingResult({}, null, MoveOrder.AFTER);'.format(target(0,0)))
        writeline(file,4,'}')
        writeline(file,3,'}')
        writeline(file,3,'')
        
        #Otherwise, check for nearby allies
        # Normalize the mean-enemy-position vector
        writeline(file,3,'double norm = Math.sqrt(dxEnemySum*dxEnemySum+dyEnemySum*dyEnemySum) / {};'.format(forward_steepness))
        writeline(file,3,'if (norm > 0) {')
        writeline(file,4,'dxEnemySum /= norm;')
        writeline(file,4,'dyEnemySum /= norm;')
        writeline(file,3,'}')
        writeline(file,3,'')
        writeline(file,3,'RobotInfo[] nearbyAllies = rc.senseNearbyRobots(-1, rc.getTeam());')
        writeline(file,3,'')
        writeline(file,3,'for (int i = nearbyAllies.length; --i>=0;) {')
        writeline(file,4,'RobotInfo ally = nearbyAllies[i];')
        writeline(file,4,'double centerVal = dxEnemySum * (mx - ally.location.x) + dyEnemySum * (my - ally.location.y) - 1;')
        writeline(file,4,'double sideVal = dyEnemySum * (mx - ally.location.x) - dxEnemySum * (my - ally.location.y);')
        writeline(file,4,'')
        writeline(file,4,'switch (ally.type) {')
        
        writeline(file,5,'case SOLDIER:')
        for x in range(-1,2):
            for y in range(-1,2):
                writeline(file,6,'{} += {} / ({} + Math.exp({}*Math.sqrt((centerVal{}{})*(centerVal{}{})+{}*(sideVal{}{})*(sideVal{}{}))));'.format(
                        unit(x,y),
                        ally_soldier_w*forward_ratio,
                        forward_ratio,
                        1.5,
                        pm_if_nonzero('dxEnemySum',-x),
                        pm_if_nonzero('dyEnemySum',-y), 
                        pm_if_nonzero('dxEnemySum',-x),
                        pm_if_nonzero('dyEnemySum',-y), 
                        0.25,
                        pm_if_nonzero('dxEnemySum',-y),
                        pm_if_nonzero('dyEnemySum',-x), 
                        pm_if_nonzero('dxEnemySum',y),
                        pm_if_nonzero('dyEnemySum',x)
                        ))
        writeline(file,6,'break;')
        
        writeline(file,5,'case SAGE:')
        writeline(file,5,'case WATCHTOWER:')
        for x in range(-1,2):
            for y in range(-1,2):
                writeline(file,6,'{} += {} / ({} + Math.exp({}*Math.sqrt((centerVal{}{})*(centerVal{}{})+{}*(sideVal{}{})*(sideVal{}{}))));'.format(
                        unit(x,y),
                        ally_sage_w*forward_ratio,
                        forward_ratio,
                        1.5,
                        pm_if_nonzero('dxEnemySum',-x),
                        pm_if_nonzero('dyEnemySum',-y), 
                        pm_if_nonzero('dxEnemySum',-x),
                        pm_if_nonzero('dyEnemySum',-y), 
                        0.25,
                        pm_if_nonzero('dxEnemySum',y),
                        pm_if_nonzero('dyEnemySum',-x), 
                        pm_if_nonzero('dxEnemySum',y),
                        pm_if_nonzero('dyEnemySum',-x)
                        ))
        writeline(file,6,'break;')
        
        writeline(file,5,'default:')
        writeline(file,6,'break;')
        writeline(file,4,'}')
        writeline(file,3,'}')
        writeline(file,3,'')
        
        #Calculate the best direction
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
                
        writeline(file,4,'return new TargetingResult({}, bestDirection, MoveOrder.AFTER);'.format(target(0,0)))
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
        writeline(file,5,'return new TargetingResult({}, bestDirection, MoveOrder.AFTER);'.format(target(0,0)))
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
        
        writeline(file,5,'return new TargetingResult(bestTarget, bestDirection, order);')
        writeline(file,4,'}')
        writeline(file,3,'}')
        
        writeline(file,2,'} else {')
        
        #Code for finding target w/o moving
        writeline(file,3,'RobotInfo bestEnemy = null;')
        writeline(file,3,'double bestScore = 0;')
        writeline(file,3,'')
        writeline(file,3,'for (int i = nearbyEnemies.length; --i>=0;) {')
        writeline(file,4,'RobotInfo enemy = nearbyEnemies[i];')
        writeline(file,4,'if (me.isWithinDistanceSquared(enemy.location, {})) '.format(action_r2)+'{')
        writeline(file,5,'double score = getTargetValue(enemy);')
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
        writeline(file,4,'return new TargetingResult(bestEnemy, null, MoveOrder.NONE);')
        writeline(file,3,'}')
        writeline(file,3,'')
        
        writeline(file,2,'}')
        writeline(file,1,'}')
        writeline(file,0,'}')
    
if __name__=="__main__":
    if len(sys.argv)==2:
        package_name = sys.argv[1]
        generate_script("SoldierTargeting", package_name, 5, 13, 20, [2700, 3000, 5000, 6000, 12000, 1/4, 4, 100, 150])
        print('done.')