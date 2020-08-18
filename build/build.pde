/*

Created by Ivan Vlahov
u/spiritcs

If you want to share anything made using this program,
please give me credit by linking my Youtube channel
and the video link below:

https://www.youtube.com/user/ivanvlahov922
https://youtu.be/m561JypiUHM

*/

import java.util.ArrayList;
import java.util.*;

LangtonAntSimulation las;
ArrayList<Ant> ants;

int numOfIterationsPerFrame = 1;
float numOfIterationsPerFrameIncrement = 1.1;
int maxIterationsPerFrame = 10000;

float scl = 1.0;

int step = 0;

void setup() {
	fullScreen();
	// size(640, 480);
	background(0);

	int rectSize = 3;

	ants = new ArrayList<Ant>();

	String[] fileLines = loadStrings("LangtonsAnts.txt");

	for(int i=0; i<fileLines.length; i++){
		if(fileLines[i].length() == 0) continue;
		if(fileLines[i].charAt(0) == '#') continue;

		List<Boolean> rules = new ArrayList<Boolean>();
		String[] splitLine = split(fileLines[i], ',');

		String ruleString = splitLine[0];

		for(int j=0; j<ruleString.length(); j++){
			rules.add(new Boolean(ruleString.charAt(j) == 'R'));
		}

		Direction dir = Direction.UP;

		if(splitLine[4].equals("UP")){
			dir = Direction.UP;
		}else if(splitLine[4].equals("DOWN")){
			dir = Direction.DOWN;
		}else if(splitLine[4].equals("RIGHT")){
			dir = Direction.RIGHT;
		}else if(splitLine[4].equals("LEFT")){
			dir = Direction.LEFT;
		}

		Ant ant = new Ant(rules, unhex("ff" + splitLine[1]), 
						  (int)(float(splitLine[2])*width/rectSize), 
						  (int)(float(splitLine[3])*height/rectSize),
						  dir);

		ants.add(ant);
	}

	las = new LangtonAntSimulation(width/rectSize, height/rectSize, ants);
	noLoop();
	noCursor();
}

void draw() {
	background(0);
	for(int i=0; i<numOfIterationsPerFrame; i++){
		las.updateWorld();
	}

	step+=numOfIterationsPerFrame;

	pushMatrix();

	scale(scl);
	translate(-width*(scl-1)/(2*scl), -height*(scl-1)/(2*scl));

	las.displayWholeWorld();

	popMatrix();

	if(frameCount%30 == 0){
		if(numOfIterationsPerFrame < maxIterationsPerFrame){
    		numOfIterationsPerFrame= (int) ((float)numOfIterationsPerFrame * numOfIterationsPerFrameIncrement) + 1;
    		// println("numOfIterationsPerFrame: "+numOfIterationsPerFrame);
   		}
	}

	if(scl > 1.0){
   		scl/=1.001;
   	}

   	if(scl < 1.0){
   		scl = 1.0;
   	}

   	fill(#ffffff);

   	textSize(32);
   	text("Step " + step, 0.85*width, 0.95*height);

   	if(!las.isRunning()) noLoop();
}

void keyPressed() {
	if(key==' '){
		loop();
	}
}
