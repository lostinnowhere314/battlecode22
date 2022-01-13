
def dist2(x1,y1,x2,y2):
    return (x1-x2)**2+(y1-y2)**2
    
def name(string, x, y):
    if x<0:
        sx = 'n'+str(-x)
    else:
        sx = str(x)
    if y<0:
        sy = 'n'+str(-y)
    else:
        sy = str(y)
    
    return string.format(sx,sy)
    
def writeline(file,tabct,string):
    file.write('\t'*tabct+string+'\n')
    
def writeline_s(tabct,string):
    return '\t'*tabct+string+'\n'