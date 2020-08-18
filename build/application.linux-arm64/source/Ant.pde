/*

Created by Ivan Vlahov
u/spiritcs

If you want to share anything made using this program,
please give me credit by linking my Youtube channel
and the video link below:

https://www.youtube.com/user/ivanvlahov922
https://youtu.be/m561JypiUHM

*/

public class Ant {
	public List<Boolean> rules; // true means turn right, false means turn left;
	public color mainColor;
	public int posX;
	public int posY;
	public Direction dir;
	public boolean alive;

	public int state;

	public Ant(List<Boolean> rules, color mainColor, int posX, int posY, Direction dir){
		this.rules = rules;
		this.mainColor = mainColor;
		this.posX = posX;
		this.posY = posY;
		this.dir = dir;
		this.alive = true;

		this.state = 0;
	}

}
