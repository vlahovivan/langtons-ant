To use the program, you have to enter the data about the Langton's ants in the "LangtonsAnts.txt" file.
Lines should be in the form:
rule,color,normalized x coordinate,normalized y coordinate,direction
rule is a string of Ls and Rs
color is in hex format, without the hashtag
for example, 000000 is black, ff0000 is red
coordinate 0,0 is the top left corner, 1,1 is the bottom right corner
coordinates should be larger or equal to 0, but less than 1
don't put spaces before or after the comma

Examples used in my YouTube video:
Just copy one of them and paste it in the "LangtonsAnts.txt" file in either this folder, or one of the application folders, depending on where you're starting the program from.
When you start the program, press Spacebar to start the simulation.

# Example 1
RL,ffa500,0.425,0.5,UP
RRLRL,40e0d0,0.5,0.5,RIGHT
LRRR,ffc0cb,0.575,0.5,UP

# Example 2
LRLRLLRLR,e3242b,0.35,0.5,DOWN
RL,ffa500,0.425,0.5,UP
RRLRL,40e0d0,0.5,0.5,RIGHT
LRRR,ffc0cb,0.575,0.5,UP
LRRRL,006a4e,0.65,0.5,UP

# Example 3
LRLRLLRLR,e3242b,0.35,0.5,DOWN
RRLRLL,ffa500,0.425,0.5,UP
RRRLRRLRL,40e0d0,0.5,0.5,RIGHT
LRRRLRLRL,ffc0cb,0.575,0.5,UP
LRRRRRRLRLR,006a4e,0.65,0.5,UP

# Example 4
LRLRLLRLR,e3242b,0.35,0.425,DOWN
RRLRLRLRLL,ffa500,0.425,0.425,UP
RRRLRRLLLRL,40e0d0,0.5,0.425,RIGHT
LRRRLRLRL,ffc0cb,0.575,0.425,UP
LRRRRRLRLRLR,006a4e,0.65,0.425,UP
LRLRLLRRRLLR,faca0f,0.35,0.575,UP
RRLRRLRLL,1c4966,0.425,0.575,UP
RRRLRRLLRLRLRRL,7f00ff,0.5,0.575,RIGHT
LRRRLRLRLRLLRL,944743,0.575,0.575,UP
LRRRRRRLRLRLRLRLR,6c4f3d,0.65,0.575,UP

# Example 5
LRLRLLRLR,e3242b,0.35,0.4,DOWN
RRLRLRLRLL,ffa500,0.425,0.4,UP
RRRLRRLLLRL,40e0d0,0.5,0.4,RIGHT
LRRRLRLRL,ffc0cb,0.575,0.4,UP
LRRRRRLRLRLR,006a4e,0.65,0.4,UP
LRLRLLRRRLLR,bfd833,0.35,0.5,UP
RRLRRLRLL,00a68c,0.425,0.5,UP
RRRLRRLLRLRLRRL,ebe1df,0.5,0.5,RIGHT
LRRRLRLRLRLLRL,944743,0.575,0.5,UP
LRRRRRRLRLRLRLRLR,e34132,0.65,0.5,UP
LRLLLLRLRLRLRRRLLR,faca0f,0.35,0.6,UP
RRLRRLRLLRLLL,1c4966,0.425,0.6,UP
RRRLRRLLRLRLRLRLRRL,7f00ff,0.5,0.6,RIGHT
LRRLLRLLLLRL,dbb2d1,0.575,0.6,UP
LRRRLRLRLRLRLR,6c4f3d,0.65,0.6,UP
