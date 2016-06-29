package graphics;

public class Background {
	private int bgX,bgY,speedY;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedY = 5;
	}
	public void update(){
		bgY+= speedY;
		
		if(bgY >= -113){
			bgY = -350;
		}
	}
	public int getBgx(){
		return bgX;
	}
	public int getSpeedY() {
		return speedY;
	}
	public int getBgY(){
		return bgY;
	}
	public void setBgx(int x)
	{
		bgX = x;
	}
	public void setBgy(int y)
	{
		bgY = y;
	}
	public void setSpeedy(int speed)
	{
		speedY  = speed;
	}
	public void incrementY()
	{
		speedY++;
	}
}
