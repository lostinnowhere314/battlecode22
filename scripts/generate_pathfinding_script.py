
from scriptgen import *
import numpy as np
import sys

def loc(x,y):
    return name('loc_{}_{}',x,y)

def pas(x,y):
    return name('pass_{}_{}',x,y)
    
def direc(x,y):
    return name('direc_{}_{}',x,y)
    
def dist(x,y):
    return name('dist_{}_{}',x,y)


def is_on_boundary(x,y,radius2):
    #Determine if a point is on the boundary of the circle;
    #that is, if there is an adjacent tile that is not on the circle
    if dist2(0,0,x,y)<=radius2:
        ct = ((dist2(0,0,x+1,y+1) > radius2)
            +(dist2(0,0,x+1,y) > radius2)
            +(dist2(0,0,x+1,y-1) > radius2)
            +(dist2(0,0,x+1,y) > radius2)
            +(dist2(0,0,x-1,y) > radius2)
            +(dist2(0,0,x-1,y+1) > radius2)
            +(dist2(0,0,x-1,y) > radius2)
            +(dist2(0,0,x-1,y-1) > radius2))
        return (ct > 0)
    return False

def generate_script(file, classname, package_name, n, max_r2, tile_cost, multiple_passes):
    direction_list = ['NORTH','NORTHEAST','EAST','SOUTHEAST','SOUTH','SOUTHWEST','WEST','NORTHWEST']
    dx_list =    [0, 1, 1, 1, 0, -1, -1, -1]
    dy_list =    [1, 1, 0, -1, -1, -1, 0, 1]
    
    
    #Declare variables
    declarations = ""
    initializations = ""
    
    #Generate needed points
    points = []
    for x in range(-n,n+1):
        for y in range(-n,n+1):
            d2 = dist2(0,0,x,y)
            if d2<=max_r2 and d2!=0:
                points.append((x,y,d2))
                # Write needed variable names to strings
                declarations += writeline_s(1, 'public static double {};'.format(dist(x,y)))
                declarations += writeline_s(1, 'public static MapLocation {};'.format(loc(x,y)))
                declarations += writeline_s(1, 'public static Direction {};'.format(direc(x,y)))
                if d2>2:
                    declarations += writeline_s(1, 'public static double {};'.format(pas(x,y)))
                declarations += writeline_s(1, '')
                initializations += writeline_s(2, '{} = 100000;'.format(dist(x,y)))
                initializations += writeline_s(2, '{} = me.translate({},{});'.format(loc(x,y),x,y))
                initializations += writeline_s(2, '{} = null;'.format(direc(x,y)))
                initializations += writeline_s(2, '')
    points.sort(key=lambda c:c[2])
    
    declarations += writeline_s(1, 'public static double bestDistance;')
    declarations += writeline_s(1, 'public static double currentDistance;')
    declarations += writeline_s(1, 'public static Direction bestDirection;')
    
    #write class header
    writeline(file,0,'package {};'.format(package_name))
    writeline(file,0,'')
    writeline(file,0,'import battlecode.common.*;')
    writeline(file,0,'')
    writeline(file,0,'public class {} '.format(classname)+'{')
    writeline(file,1,'')
    
    #Declarations
    file.write(declarations)
    writeline(file,1,'')
    
    #start of function
    writeline(file,1,'public static Direction getBestDirection(RobotController rc, MapLocation dest, Direction lastMoveDirection) throws GameActionException {')
    
    writeline(file,2,'')
    writeline(file,2,'MapLocation me = rc.getLocation();')
    writeline(file,2,'')
    writeline(file,2,'if (me.equals(dest)) {')
    writeline(file,3,'return null;')
    writeline(file,2,'}')
    writeline(file,2,'')
    writeline(file,2,'if (me.isAdjacentTo(dest)) {')
    writeline(file,3,'if (!rc.canSenseRobotAtLocation(dest)) {')
    writeline(file,4,'return me.directionTo(dest);')
    writeline(file,3,'} else {')
    writeline(file,4,'return null;')
    writeline(file,3,'}')
    writeline(file,2,'}')
    writeline(file,2,'')
    
    file.write(initializations)
    writeline(file,2,'')
    
    all_lt2_done = False
    
    #With just the first pass, it's kind of a priority-ignoring BFS
    for x,y,_ in points:
        
        if dist2(0,0,x,y) <= 2:
            writeline(file,2,'if (rc.canSenseLocation({})) '.format(loc(x,y))+'{')
            
            #writeline(file,3,'if (!rc.canSenseRobotAtLocation({})) '.format(loc(x,y))+'{')
            writeline(file,3,'if (!rc.isLocationOccupied({})) '.format(loc(x,y))+'{')
            
            #adjacent to the center point
            writeline(file,4,'{} = 2+rc.senseRubble({});'.format(dist(x,y),loc(x,y)))
            for (dx,dy,dname) in zip(dx_list,dy_list,direction_list):
                if dx==x and dy==y:
                    the_direction = dname
                    break
            writeline(file,4,'{} = Direction.{};'.format(direc(x,y),the_direction))
            writeline(file,3,'}')
        else:
            #Put in a check to not go in the previous direction
            #This part breaks things for some reason
            if not all_lt2_done:
                all_lt2_done = True;
                writeline(file,3,'')
                writeline(file,3,'switch (lastMoveDirection) {')
                for i,(dx,dy,dname) in enumerate(zip(dx_list,dy_list,direction_list)):
                    writeline(file,4,'case {}:'.format(dname))
                    writeline(file,5,'{} = 100000;'.format(dist(-dx,-dy)))
                    i2 = (i-1)%8
                    writeline(file,5,'{} = 100000;'.format(dist(-dx_list[i2],-dy_list[i2])))
                    i2 = (i+1)%8
                    writeline(file,5,'{} = 100000;'.format(dist(-dx_list[i2],-dy_list[i2])))
                    writeline(file,5,'break;')
                writeline(file,4,'default:')
                writeline(file,5,'break;')
                writeline(file,3,'}')
                writeline(file,3,'')
                
            writeline(file,2,'if (rc.canSenseLocation({})) '.format(loc(x,y))+'{')
            writeline(file,3,'{} = {}+rc.senseRubble({});'.format(pas(x,y), tile_cost, loc(x,y)))
            for (dx,dy) in zip(dx_list, dy_list):
                #only check things that have already been checked
                if -n<=x+dx<=n and -n<=y+dy<=n:
                    if dist2(0,0,x+dx,y+dy)<dist2(0,0,x,y):
                        #check if the weight of any of them is better
                        writeline(file,3,'if ({} > {} + {}) '.format(dist(x,y),dist(x+dx,y+dy),pas(x,y))+'{')
                        #Assign distance
                        writeline(file,4,'{} = {} + {};'.format(dist(x,y),dist(x+dx,y+dy),pas(x,y)))
                        #Assign direction
                        writeline(file,4,'{} = {};'.format(direc(x,y),direc(x+dx,y+dy)))
                        writeline(file,3,'}')
                        writeline(file,3,'')
                            
        writeline(file,2,'}')
        writeline(file,2,'')
    
    
    #Additional passes are Bellman-Ford algorithm
    if multiple_passes:
        #but currently not done
        raise NotImplementedError()
        
    #Next: figure out where the best path lies in all of this
    writeline(file,2,'')
    
    #First decide if the tile we want is one we can see
    writeline(file,2,'int dx = dest.x - me.x;')
    writeline(file,2,'int dy = dest.y - me.y;')
    writeline(file,2,'')
    
    writeline(file,2,'switch(dx) {')
    for dx in range(-n,n+1):
        if dist2(0,0,dx,0)>max_r2:
            continue
            
        writeline(file,3,'case {}:'.format(dx))
        writeline(file,4,'switch(dy) {')
        for dy in range(-n,n+1):
            if dist2(0,0,dx,dy)<=max_r2+0.01 and dist2(0,0,dx,dy)!=0:
                writeline(file,5,'case {}:'.format(dy))
                writeline(file,6,'return {};'.format(direc(dx,dy)))
        writeline(file,4,'}')
        writeline(file,2,'break;')
        
    writeline(file,2,'}')
    writeline(file,2,'')
    
    #Otherwise use a heuristic on the boundary
    writeline(file,2,'bestDistance = 1000000;')
    writeline(file,2,'bestDirection = null;')
    writeline(file,2,'')
    writeline(file,2,'switch(me.directionTo(dest)) {')
    for dx, dy, dname in zip(dx_list, dy_list, direction_list):
        #We only will use points that are within [something]*max_r2 distance squared of a point in the direction on our boundary
        diff_vect = np.array([dx,dy], dtype=float)
        diff_vect /= np.linalg.norm(diff_vect)
        diff_vect *= np.sqrt(max_r2)*1.15
        cx, cy = diff_vect
        
        #Assemble list of boundary points, sort by distance to direction
        boundary_points = []
        for x in range(-n,n+1):
            for y in range(-n,n+1):
                if is_on_boundary(x,y, max_r2):
                    boundary_points.append((x,y, dist2(x,y,cx,cy)))
        boundary_points.sort(key=lambda c:c[2])
        
        writeline(file,3,'case {}:'.format(dname))
        for x,y,_ in boundary_points:
            #Then, check if that point is better than our running total
            writeline(file,4,'currentDistance = {} + 3*Math.sqrt({}.distanceSquaredTo(dest));'.format(dist(x,y), loc(x,y)))
            writeline(file,4,'if (bestDistance > currentDistance) {')
            writeline(file,5,'bestDistance = currentDistance;')
            writeline(file,5,'bestDirection = {};'.format(direc(x,y)))
            writeline(file,4,'}')
            writeline(file,4,'')
        writeline(file,4,'return bestDirection;')
        writeline(file,4,'')
        
    
    writeline(file,3,'default:')
    writeline(file,2,'}')
    writeline(file,2,'')
    writeline(file,2,'return null;')
    writeline(file,2,'')
    
    writeline(file,1,'}')
    writeline(file,0,'}')
    


if __name__=="__main__":
    if len(sys.argv)==2:
        package_name = sys.argv[1]
        #Do the stuff
        with open('../src/{}/SagePathfinding.java'.format(package_name),'w') as file:
            generate_script(file, 'SagePathfinding', package_name, 5, 34, 10, False)
        with open('../src/{}/SoldierPathfinding.java'.format(package_name),'w') as file:
            generate_script(file, 'SoldierPathfinding', package_name, 4, 20, 10, False)
        print('Done.')
    