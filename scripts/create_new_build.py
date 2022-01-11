# Script to create a new build

import re
import os
import subprocess
import sys
from glob import glob

if __name__=="__main__":
    if (len(sys.argv)==3):
        source_name = sys.argv[1]
        dest_name = sys.argv[2]
        
        if os.path.isdir('src'):
            prefix = ""
        else:
            prefix = "../"
        
        file_regex = re.compile("({})(?=.*public [\w\s]*class)".format(source_name), flags=re.DOTALL)
        
        subprocess.run(['mkdir', prefix+'src/{}'.format(dest_name)])
        
        for path in glob(prefix+"src/{}/*".format(source_name)):
            path = path.replace('\\','/')
            with open(path,'r') as file:
                file_contents = file.read()
                if path.endswith('.java'):
                    #Process it for the package change
                    file_contents = file_regex.sub(dest_name, file_contents)
            filename = path.split('/')[-1]
            with open(prefix+"src/{}/{}".format(dest_name,filename),'w') as file:
                file.write(file_contents)
        
        print('Done.')
        