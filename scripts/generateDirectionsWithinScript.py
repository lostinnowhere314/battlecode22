
import numpy as np

def dist2(x1,y1,x2,y2):
    return (x1-x2)**2+(y1-y2)**2

if __name__=="__main__":
    
    directions = ['NORTH','NORTHEAST','EAST','SOUTHEAST','SOUTH','SOUTHWEST','WEST','NORTHWEST']
    dx_list =    [0, 1, 1, 1, 0, -1, -1, -1]
    dy_list =    [1, 1, 0, -1, -1, -1, 0, 1]
    
    vision_r2 = 20
    action_r2 = 13
    dx_max = 4
    
    with open('dir_within_soldier.java', 'w') as file:
        file.write('public static Direction[] soldierGetDirectionsWithin(MapLocation me, MapLocation target) {\n')
        
        file.write('\t'*1 + 'switch (target.x-me.x) {' +'\n')
        for x in range(-dx_max, dx_max+1):
            file.write('\t'*2 + 'case {}:'.format(x) +'\n')
            file.write('\t'*2 + 'switch (target.y-me.y) {' +'\n')
            for y in range(-dx_max, dx_max+1):
                if dist2(0,0,x,y)<=vision_r2:
                    file.write('\t'*3 + 'case {}:'.format(y) +'\n')
                    #Figure out where is close enough
                    valid_directions = []
                    for direc,dx,dy in zip(directions, dx_list, dy_list):
                        d2 = dist2(dx,dy,x,y)
                        if d2<=action_r2:
                            valid_directions.append((direc, d2))
                    #Sort
                    valid_directions.sort(key=lambda c: c[1])
                    dirs_string = ', '.join(["Direction.{}".format(c[0]) for c in valid_directions])
                    file.write('\t'*4 + 'return new Direction[] {'+dirs_string+'};' +'\n')
                
            file.write('\t'*2 + 'default: return null;' +'\n')
            file.write('\t'*2 + '}' +'\n')
        
        file.write('\t'*1 + 'default: return null;' +'\n')
        file.write('\t'*1 + '}' +'\n')
        file.write('}' +'\n')