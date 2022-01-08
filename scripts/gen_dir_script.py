

if __name__ == "__main__":
    directions = ['NORTH','NORTHEAST','EAST','SOUTHEAST','SOUTH','SOUTHWEST','WEST','NORTHWEST']

    with open('direction_funcs.java','w') as file:
        file.write('public static Direction getPerpendicularDir(Direction dir, RotationDirection clk)\n')
        file.write('\tswitch(clk) {\n')
        file.write('\tcase CLOCKWISE:\n')
        file.write('\t\tswitch(dir) {\n')
        
        for i,name in enumerate(directions):
            file.write('\t\t\tcase {}: return Direction.{};\n'.format(name,directions[(i+2)%8]))
        
        file.write('\t\t\tdefault: return dir;\n')
        file.write('\t\t}\n')
        file.write('\tcase COUNTERCLOCKWISE:\n')
        file.write('\t\tswitch(dir) {\n')
        for i,name in enumerate(directions):
            file.write('\t\t\tcase {}: return Direction.{};\n'.format(name,directions[(i+6)%8]))
        
        file.write('\t\t\tdefault: return dir;\n')
        file.write('\t\t}\n')
        file.write('\t}\n')
        file.write('}\n')