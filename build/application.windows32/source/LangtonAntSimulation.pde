/*

Created by Ivan Vlahov
u/spiritcs

If you want to share anything made using this program,
please give me credit by linking my Youtube channel
and the video link below:

https://www.youtube.com/user/ivanvlahov922
https://youtu.be/m561JypiUHM

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

			color antMainColor = ant.mainColor;
			int antRuleSize = ant.rules.size();
			int cellColorId;

			try {
				cellColorId = world[ant.posX][ant.posY].colorId;
			}catch(Exception e) {
				ant.alive = false;
				println("Died: "+ antMainColor);
				continue;
			}

			color fillColor = lerpColor(antMainColor, color(255, 255, 255), (float)cellColorId/antRuleSize);

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

					color antMainColor = ant.mainColor;
					int antRuleSize = ant.rules.size();
					int cellColorId;

					try {
						cellColorId = world[i][j].colorId;
					}catch(Exception e) {
						ant.alive = false;
						println("Died: "+ antMainColor);
						continue;
					}

					color fillColor = lerpColor(antMainColor, color(255, 255, 255), (float)cellColorId/antRuleSize);

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
