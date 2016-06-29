/*
 * This object class is responsible for the control of our
 * brave hero Bxbx!*/
package graphics;
import java.awt.Graphics;
import java.util.ArrayList;

public class square {
	private int centerX; // center X coordinate of Bxbx
	private int centerY; // center y coordinate of Bxbx
	private boolean alive; // boolean signifies if Bxbx is still alive
	private int speedX; // speedX speed of Bxbx
	private int height; //not used but eclipse gives error when value is removed not quite sure why
	private int width;
	int currentX;//This value holds the x coordinate value that occurs after addition is 
	//done to centerX. This variable was added to deal with weird movements that occured after
	//bxbx entered a new quadrant 
	
	public square()
	{
		height = 133;
		width = 133;
		centerX = 284;
		centerY = 890;
		alive = true;
		speedX = 0;
		currentX = centerX;
	}
	//change this later to just pass wall type, x, and y
	public boolean collision_detection(Obstacle wall)
	{
		int walltype = wall.getType();
		if (walltype == 1)
		{
			if ((centerX - 63 < 569) && (centerY-63 <= wall.getCenterY()+ 87))
			{
				return collision_true(wall);
			}
		}
		else if (walltype == 2)
		{
			if ((centerX-63 <= 379 || centerX+63 >= 569) && (centerY-63 <= wall.getCenterY()+ 87) )
			{
				return collision_true(wall);
			}
		}
		else if (walltype == 3)
		{
			if((centerX-63  <= 189 || centerX + 63 >= 379) && (centerY-63 <= wall.getCenterY()+ 87))
			{
				return collision_true(wall);
			}
		}
		else if (walltype == 4)
		{
			if((centerX + 63 >= 189) && (centerY-63 <= wall.getCenterY()+ 87))
			{
				return collision_true(wall);
			}
		}
		else if (walltype == 5)
		{
			if((centerX-63  <= 189 || centerX +63 >= 569) && (centerY-63 <= wall.getCenterY()+ 87))
			{
				return collision_true(wall);
			}
		}
		else if (walltype == 6)
		{
			if((centerX+63 >= 189 && centerX-63  <= 569) && (centerY-63 < wall.getCenterY()+ 87))
			{
				return collision_true(wall);
			}
		}
		else if (walltype == 7)
		{
			if(((centerX-63 <= 189) || ((centerX+63 >= 379) && (centerX + 63 <= 569)))
					&& (centerY-63 <= wall.getCenterY()+ 87) )
			{
				return collision_true(wall);
			}
		}
		else if (walltype == 8)
		{
			if(((centerX+63 >= 189 && centerX-63  <= 379) || (centerX+63 >= 569)) 
					&& (centerY-63 <= wall.getCenterY()+ 87)  )
			{
				return collision_true(wall);
			}
		}
		return false;
	}
	
	public boolean isInside(Obstacle wall)
	{//This code checks if bxbx is bigger than the wall
	 //or if the wall is bigger than the bxbx.
	 //Afterwards the function checks if the box is currently inside the height of an active wall
	 //or if a wall is currently inside the height of the active wall
		if(wall.getHeight() > height)//checks if wall is bigger than bxbx
		{		
			if((wall.getCenterY() < centerY - height/2) && (wall.getCenterY() + 109 > centerY + height/2))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else//otherwise the wall is smaller than bxbx
		{	
			if((wall.getCenterY() > centerY - height/2) && (wall.getCenterY() + 87 < centerY +height/2))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	//The function makes sure that the square doesn't go through the side of the wall 
	//as it is passing by
	public boolean Back_against_the_wall(Obstacle wall)
	{
		int walltype = wall.getType();
		if(speedX == 0)//if the square is not moving, don't waste time checking other cases
			{return false;}
		else if(walltype == 1)
		{
			if((speedX < 0) && (centerX-63 < 575) && (isInside(wall)))
			{
				currentX = centerX;
			}
		}
		else if(walltype == 2)
		{
		
		}
		else if(walltype == 3)
		{
			
		}
		else if(walltype == 4)
		{
			
		}
		else if(walltype == 5)
		{
			
		}
		else if(walltype == 6)
		{
			
		}
		else if(walltype == 7)
		{
			
		}
		else if(walltype == 8)
		{
			
		}
		return true;//Will output if their is not collision detected.
	}
	
	private boolean collision_true(Obstacle wall)
	{
		height -= wall.getSpeedY();
		centerY += wall.getSpeedY();
		return true;
	}
	
	public boolean update( ArrayList<Obstacle> ArrayOfWalls) {
		
		centerX += speedX;
				
		if (currentX + 3 > centerX && currentX - 3 < centerX)
		{
			centerX = currentX;
			speedX = 0;
		}
		else if(centerX + speedX <= 94){
			centerX = 94;
		}
		else if(centerX + speedX >= 664){
			centerX = 664;
		}
		//either move the character or move the background;
		for(int i = 0; i < ArrayOfWalls.size(); i++)
		{
			if (ArrayOfWalls.get(i).return_stat())
			{
				if(collision_detection(ArrayOfWalls.get(i)))
				{
					if(dead())
					{
						System.out.println("you supa ded");
						alive = false;
					}
				}
				Back_against_the_wall(ArrayOfWalls.get(i));
				/*if(Back_against_the_wall(ArrayOfWalls.get(i)))
				{
					
				}*/
			}
		}
		return false;
		
	}
	public void moveLeft(){
		if(currentX > 94)
			{
				if (!(centerX > currentX))
				{	
					currentX -= 190;
				}
				speedX = -6;
			}
		System.out.println(centerX);
	}
	public void moveRight(){
		if(currentX < 664)
			{
				if(!(centerX < currentX))
				{	
					currentX += 190;
				}
				speedX = 6;
			}
		System.out.println(centerX);
		
	}
		public void changeSize(){
		if (width < 133)
		{
			width = 133;
		}
		else 
		{
			width = 66;
		}
	}
	public boolean dead()
	{
		if(height <= 23)
			return true;
		else 
			return false;
	}
	public void stop(){
		speedX = 0;
	}
	public int getSpeedX(){
		return speedX;
	}
	public void setSpeedX(int speedX){
		this.height = speedX;
	}
	public int getCenterX(){
		return centerX;
	}
	public int getCenterY(){
		return centerY;
	}

	public boolean get_status()
	{
		return alive;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth()
	{
		return width;
	}
}
