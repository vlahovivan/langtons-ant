import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.ArrayList; 
import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build extends PApplet {

/*

Created by Ivan Vlahov
u/spiritcs

If you want to share anything made using this program,
please give me credit by linking my Youtube channel
and the video link below:

https://www.youtube.com/user/ivanvlahov922


*/




LangtonAntSimulation las;
ArrayList<Ant> ants;

int numOfIterationsPerFrame = 1;
float numOfIterationsPerFrameIncrement = 1.1f;
int maxIterationsPerFrame = 10000;

float scl = 1.0f;

int step = 0;

public void setup() {
	
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
						  (int)(PApplet.parseFloat(splitLine[2])*width/rectSize), 
						  (int)(PApplet.parseFloat(splitLine[3])*height/rectSize),
						  dir);

		ants.add(ant);
	}

	las = new LangtonAntSimulation(width/rectSize, height/rectSize, ants);
	noLoop();
	noCursor();
}

public void draw() {
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

	if(scl > 1.0f){
   		scl/=1.001f;
   	}

   	if(scl < 1.0f){
   		scl = 1.0f;
   	}

   	fill(0xffffffff);

   	textSize(32);
   	text("Step " + step, 0.85f*width, 0.95f*height);

   	if(!las.isRunning()) noLoop();
}

public void keyPressed() {
	if(key==' '){
		loop();
	}
}
/*

Created by Ivan Vlahov
u/spiritcs

If you want to share anything made using this program,
please give me credit by linking my Youtube channel
and the video link below:

https://www.youtube.com/user/ivanvlahov922


*/

public class Ant {
	public List<Boolean> rules; // true means turn right, false means turn left;
	public int mainColor;
	public int posX;
	public int posY;
	public Direction dir;
	public boolean alive;

	public int state;

	public Ant(List<Boolean> rules, int mainColor, int posX, int posY, Direction dir){
		this.rules = rules;
		this.mainColor = mainColor;
		this.posX = posX;
		this.posY = posY;
		this.dir = dir;
		this.alive = true;

		this.state = 0;
	}

}
/*

Created by Ivan Vlahov
u/spiritcs

If you want to share anything made using this program,
please give me credit by linking my Youtube channel
and the video link below:

https://www.youtube.com/user/ivanvlahov922


*/

public class Cell {
	public int antId;
	public int colorId;

	public Cell(int antId, int colorId){
		this.antId = antId;
		this.colorId = colorId;
	}
}
/*

Created by Ivan Vlahov
u/spiritcs

If you want to share anything made using this program,
please give me credit by linking my Youtube channel
and the video link below:

https://www.youtube.com/user/ivanvlahov922


*/

public class LangtonAntSimulation {
	public int w;
	public int h;

	public Cell[][] world;
	public ArrayList<Ant> ants;


	public LangtonAntSimulation(int w, int h, ArrayList<Ant> ants){
		this.w = w;
		this.h = h;

		this.world = new Cell[w][h];
		this.ants = ants;

		for(int i=0; i<w; i++){
			for(int j=0; j<h; j++){
				world[i][j] = new Cell(-1, -1);
			}
		}
	}

	public void updateWorld(){
		int antId = 0;
		for(Ant ant : ants){
			if(!ant.alive) {
				antId++;
				continue;
			}

			int cellState = world[ant.posX][ant.posY].colorId;

			world[ant.posX][ant.posY].colorId = (cellState+1)%(ant.rules.size());
			world[ant.posX][ant.posY].antId = antId;

			int step;
			if(ant.rules.get((cellState + ant.rules.size()) % ant.rules.size())){
				step = 1;
			}else{
				step = -1;
			}

			if(ant.dir == Direction.UP){
				ant.posX += step;
				ant.dir = step == 1 ? Direction.RIGHT : Direction.LEFT;
			}else if(ant.dir == Direction.RIGHT){
				ant.posY -= step;
				ant.dir = step == 1 ? Direction.DOWN : Direction.UP;
			}else if(ant.dir == Direction.DOWN){
				ant.posX -= step;
				ant.dir = step == 1 ? Direction.LEFT : Direction.RIGHT;
			}else if(ant.dir == Direction.LEFT){
				ant.posY += step;
				ant.dir = step == 1 ? Direction.UP : Direction.DOWN;
			}

			if(ant.posX < 0 || ant.posX > w-1 || ant.posY < 0 || ant.posY > h-1){
				ant.alive = false;
				println("Died: "+ (antId+1));
			}

			antId++;
		}
	}


	
	public void displayWorldChanges(){

		float rectW = (float)width/w;
		float rectH = (float)height/h;

		noStroke();

		for(Ant ant : ants){
			if(!ant.alive) continue;

			int antMainColor = ant.mainColor;
			int antRuleSize = ant.rules.size();
			int cellColorId;

			try {
				cellColorId = world[ant.posX][ant.posY].colorId;
			}catch(Exception e) {
				ant.alive = false;
				println("Died: "+ antMainColor);
				continue;
			}

			int fillColor = lerpColor(antMainColor, color(255, 255, 255), (float)cellColorId/antRuleSize);

			fill(fillColor);
			rect(rectW*ant.posX, rectH*ant.posY, rectW, rectH);
		}
	}

	public void displayWholeWorld(){
		float rectW = (float)width/w;
		float rectH = (float)height/h;

		noStroke();

		for(int i=0; i<w; i++){
			for(int j=0; j<h; j++){
				int cellAntId = world[i][j].antId;

				if(cellAntId != -1){
					Ant ant = ants.get(cellAntId);

					int antMainColor = ant.mainColor;
					int antRuleSize = ant.rules.size();
					int cellColorId;

					try {
						cellColorId = world[i][j].colorId;
					}catch(Exception e) {
						ant.alive = false;
						println("Died: "+ antMainColor);
						continue;
					}

					int fillColor = lerpColor(antMainColor, color(255, 255, 255), (float)cellColorId/antRuleSize);

					fill(fillColor);
					rect(rectW*i, rectH*j, rectW, rectH);
				}

			}
		}
	}

	public boolean isRunning(){
		for(Ant ant : ants){
			if(ant.alive) return true;
		}

		return false;
	}
}
  public void settings() { 	fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
