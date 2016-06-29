/*
 * Obstacles.java is responsible for generating the green brick walls
 * */
package graphics;

public class Obstacle {
	private int CenterX;//the center X coordinate of the wall
	private int CenterY;//the center y coordinate of the wall
	private int SpeedY;//The speed of the walls
	private int Type;//the type of wall that is contained
	private int height;
	//private int size;
	private boolean active;//walls that are not active will be reset to top of the page
	
	public Obstacle()// Default constructor of the obstacle class
	{
		Type = -1;
		CenterX = 0;
		CenterY = -200;
		SpeedY = 5;
		active = false;
		height = 92;
	}
	
	public int update()//update call for the wall
	{
		//if the wall is active it will continue going down
		//the screen at speedY
		if (active)
			CenterY += SpeedY;
		//if the wall leaves the screen, it is reset to the top of the screen and
		//hidden
		if (CenterY + SpeedY > 970)
		{
			active = false;
			CenterY = -200;
			Type = -1;
			return 1;
		}
		return 0;
	
	}
	public void setType(int n)//sets the type of wall
	{
		Type = n;
	}
	public int getHeight()
	{
		return height;
	}
	public int getType()//returns the type of wall
	{
		return Type;
	}
	public int getCenterX()//returns x coordinate
	{
		return CenterX;
	}
	
	public int getCenterY()//returns y coordinate
	{
		return CenterY;
	}
	public int getSpeedY()//returns speed of the box
	{
		return SpeedY;
	}
	public void setCenterX( int x)//sets the x coordinate
	{
		CenterX = x;
	}
	public void setCenterY(int y)//sets the y coordinate
	{
		CenterY = y;
	}
	public void setSpeedY(int speed)//set the down speed motion of the box
	{
		SpeedY = speed;
	}
	public void activate(int n)//functions turns wall on or off.
	{
		if(n == 0)
		{
			active = false;
		}
		else
			active = true;
	}
	public boolean return_stat()//returns whether wall is visible or not
	{
		return active;
	}
	public void incrementY()//increases the speed of the wall
	{
		SpeedY++;
	}
}
