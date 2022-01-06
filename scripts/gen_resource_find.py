#Generates a bytecode-efficient script in java for battlecode2022 resource sensing

import sys
import numpy as np

def gen_script(filename, lead_r2, gold_r2):
    lead_r2 = int(lead_r2)
    gold_r2 = int(gold_r2)
    lead_r = int(np.sqrt(lead_r2))
    gold_r = int(np.sqrt(gold_r2))
    
    with open(filename, 'w') as file:
        file.write('public ')

if __name__ == "__main__":
    if len(sys.argv) == 3:
        gen_script('get_resources.java', *sys.argv[1:])
    else:
        print("Incorrect number of arguments!")