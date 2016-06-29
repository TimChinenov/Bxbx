package graphics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;


public class graph extends Applet implements Runnable, KeyListener{
	private square sq1;//The player...is a square
	private Image image;//This holds am image
	private Image character;//Image for players character
	private Image background;//holds image for background
	private Image wallImage;//Image associated with said obstacle
	private URL base;//used to bring in pictures
	private Graphics second;
	private static Background bg1;//background object
	public ArrayList<Obstacle> ArrayOfWalls;//Array holds our obstacle objects
	public Random rnd;
	private Obstacle wall1;// this is the obstacle that will be put into ArrayOfWalls
	private Obstacle wall2;
	private Obstacle wall3;
	private Obstacle wall4;
	private int score;

	
	@Override
	public void init(){
		super.init();
		setSize(760,980);//set the size of the window
		setBackground(Color.LIGHT_GRAY);//set the default background color
		setFocusable(true);//start with focus on window
		Frame frame = (Frame) this.getParent().getParent();
	    frame.setTitle("BxBx Alpha");//Name of the game
	    addKeyListener(this);

	    try{
	    	base = getDocumentBase();
	    }catch(Exception e){
	    	//handle exceptions
	    }
	    background = getImage(base, "data/bg2.png");
	    character = getImage(base, "data/Bxbx_char.png");
	    wallImage = getImage(base,"data/Wall2.png");	}
	@Override
	public void start() {
		wall1 = new Obstacle();//create a new object for each bar
		wall2 = new Obstacle();
		wall3 = new Obstacle();
		wall4 = new Obstacle();
		
		ArrayOfWalls = new ArrayList<Obstacle>();
		bg1 = new Background(0,0);//background class
		sq1 = new square();//main character class
		super.start();
		Thread thread = new Thread(this);
		thread.start();
		
	}
	@Override
	public void stop() {
		super.stop();
	}
	@Override
	public void destroy() {
		super.destroy();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean passed = false;
		boolean speed_up = true;
		int type_num; 
		setSize(760,980);
		wall1.setType(1);
		wall1.activate(1);
		ArrayOfWalls.add(wall1);
		ArrayOfWalls.add(wall2);
		ArrayOfWalls.add(wall3);
		ArrayOfWalls.add(wall4);
		score = 0;
		int prev_num = 0;
		while(true)
		{
			if (!sq1.get_status())
			{
				////state = GameState.DEAD;
			}
			if (score%25 == 1)
			{
				speed_up = true;
			}
			for (int i = 0; i < ArrayOfWalls.size(); i++)
			{
				if (passed == true)
				{
					ArrayOfWalls.get(i).activate(1);
					if(ArrayOfWalls.get(i).getType() == -1)
					{
						type_num = Random();
						if ( (type_num == prev_num) || ((type_num == 0 && prev_num == 3)||(type_num == 3 && prev_num == 0) ))
						{
							type_num = Random();
						}
						ArrayOfWalls.get(i).setType(1);//decides which wall type
						prev_num = type_num;	
					}
					passed = false;
				}
				score += ArrayOfWalls.get(i).update();
				if(ArrayOfWalls.get(i).getCenterY() >= 380)
				{
					passed = true;
				}
			}
			if(score%25 == 0 && speed_up)
			{
				for(int i = 0; i < 4; i++)
				{
					ArrayOfWalls.get(i).incrementY();
				}
				bg1.incrementY();
				speed_up = false;
			}
			if(sq1.get_status())
			{
				sq1.update(ArrayOfWalls);
				bg1.update();
				repaint();
			}
			try{
				Thread.sleep(17);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
		
	
	public void update(Graphics g){
		if(image == null){
			image = createImage(this.getWidth(),this.getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0,0,getWidth(),getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image, 0, 0, this);
	}
	
	public void paint(Graphics g){
		/*if(state == GameState.MENU)
		{
			//g.drawImage(background, bg1.getBgx(),bg1.getBgY(),this);//This colors background should always be first
			g.drawImage(start, 214,202,this);
			g.drawImage(high scores,60, 417, this);
			g.drawImage(setting,140, 632,this);
		}*/
		g.drawImage(background, bg1.getBgx(),bg1.getBgY(),this);//This colors background should always be first
		ImageObserver modalComp = null; // acts as ImageObserver for character
		g.drawImage(character, sq1.getCenterX() -61, sq1.getCenterY()-63, sq1.getWidth(), sq1.getHeight(), modalComp);//this colors in the players should be last
		//Only draw these if they are active
		if(wall1.return_stat() == true)
			{
				if(wall1.getType() == 1)
				{
					g.drawImage(wallImage, wall1.getCenterX(),wall1.getCenterY(),this);
					g.drawImage(wallImage, 190,wall1.getCenterY(),this);
					g.drawImage(wallImage, 380,wall1.getCenterY(),this);
				}
				if(wall1.getType() == 2)
				{
					g.drawImage(wallImage, wall1.getCenterX(),wall1.getCenterY(),this);
					g.drawImage(wallImage, 190,wall1.getCenterY(),this);
					g.drawImage(wallImage, 570,wall1.getCenterY(),this);
				}
				if(wall1.getType() == 3)
				{
					g.drawImage(wallImage, wall1.getCenterX(),wall1.getCenterY(),this);
					g.drawImage(wallImage, 570,wall1.getCenterY(),this);
					g.drawImage(wallImage, 380,wall1.getCenterY(),this);
				}
				if(wall1.getType() == 4)
				{
					g.drawImage(wallImage, 570,wall1.getCenterY(),this);
					g.drawImage(wallImage, 190,wall1.getCenterY(),this);
					g.drawImage(wallImage, 380,wall1.getCenterY(),this);
				}
				if(wall1.getType() == 5)
				{
					g.drawImage(wallImage, wall1.getCenterX(),wall1.getCenterY(),this);
					g.drawImage(wallImage, 570,wall1.getCenterY(),this);
				}
				if(wall1.getType() == 6)
				{
					g.drawImage(wallImage, 190,wall1.getCenterY(),this);
					g.drawImage(wallImage, 380,wall1.getCenterY(),this);
				}
				if(wall1.getType() == 7)
				{
					g.drawImage(wallImage, wall1.getCenterX(),wall1.getCenterY(),this);
					g.drawImage(wallImage, 380,wall1.getCenterY(),this);
				}
				if(wall1.getType() == 8)
				{
					g.drawImage(wallImage, 190,wall1.getCenterY(),this);
					g.drawImage(wallImage, 570,wall1.getCenterY(),this);
				}
			}
		if(wall2.return_stat() == true)
			{
				if(wall2.getType() == 1)
				{
					g.drawImage(wallImage, wall2.getCenterX(),wall2.getCenterY(),this);
					g.drawImage(wallImage, 190,wall2.getCenterY(),this);
					g.drawImage(wallImage, 380,wall2.getCenterY(),this);
				}
				if(wall2.getType() == 2)
				{
					g.drawImage(wallImage, wall2.getCenterX(),wall2.getCenterY(),this);
					g.drawImage(wallImage, 190,wall2.getCenterY(),this);
					g.drawImage(wallImage, 570,wall2.getCenterY(),this);
				}
				if(wall2.getType() == 3)
				{
					g.drawImage(wallImage, wall2.getCenterX(),wall2.getCenterY(),this);
					g.drawImage(wallImage, 570,wall2.getCenterY(),this);
					g.drawImage(wallImage, 380,wall2.getCenterY(),this);
				}
				if(wall2.getType() == 4)
				{
					g.drawImage(wallImage, 570,wall2.getCenterY(),this);
					g.drawImage(wallImage, 190,wall2.getCenterY(),this);
					g.drawImage(wallImage, 380,wall2.getCenterY(),this);
				}
				if(wall2.getType() == 5)
				{
					g.drawImage(wallImage, wall2.getCenterX(),wall2.getCenterY(),this);
					g.drawImage(wallImage, 570,wall2.getCenterY(),this);
				}
				if(wall2.getType() == 6)
				{
					g.drawImage(wallImage, 190,wall2.getCenterY(),this);
					g.drawImage(wallImage, 380,wall2.getCenterY(),this);
				}
				if(wall2.getType() == 7)
				{
					g.drawImage(wallImage, wall2.getCenterX(),wall2.getCenterY(),this);
					g.drawImage(wallImage, 380,wall2.getCenterY(),this);
				}
				if(wall2.getType() == 8)
				{
					g.drawImage(wallImage, 190,wall2.getCenterY(),this);
					g.drawImage(wallImage, 570,wall2.getCenterY(),this);
				}
			}
		if(wall3.return_stat() == true)
			{
				if(wall3.getType() == 1)
				{
					g.drawImage(wallImage, wall3.getCenterX(),wall3.getCenterY(),this);
					g.drawImage(wallImage, 190,wall3.getCenterY(),this);
					g.drawImage(wallImage, 380,wall3.getCenterY(),this);
				}
				if(wall3.getType() == 2)
				{
					g.drawImage(wallImage, wall3.getCenterX(),wall3.getCenterY(),this);
					g.drawImage(wallImage, 190,wall3.getCenterY(),this);
					g.drawImage(wallImage, 570,wall3.getCenterY(),this);
				}
				if(wall3.getType() == 3)
				{
					g.drawImage(wallImage, wall3.getCenterX(),wall3.getCenterY(),this);
					g.drawImage(wallImage, 570,wall3.getCenterY(),this);
					g.drawImage(wallImage, 380,wall3.getCenterY(),this);
				}
				if(wall3.getType() == 4)
				{
					g.drawImage(wallImage, 570,wall3.getCenterY(),this);
					g.drawImage(wallImage, 190,wall3.getCenterY(),this);
					g.drawImage(wallImage, 380,wall3.getCenterY(),this);
				}
				if(wall3.getType() == 5)
				{
					g.drawImage(wallImage, wall3.getCenterX(),wall3.getCenterY(),this);
					g.drawImage(wallImage, 570,wall3.getCenterY(),this);
				}
				if(wall3.getType() == 6)
				{
					g.drawImage(wallImage, 190,wall3.getCenterY(),this);
					g.drawImage(wallImage, 380,wall3.getCenterY(),this);
				}
				if(wall3.getType() == 7)
				{
					g.drawImage(wallImage, wall3.getCenterX(),wall3.getCenterY(),this);
					g.drawImage(wallImage, 380,wall3.getCenterY(),this);
				}
				if(wall3.getType() == 8)
				{
					g.drawImage(wallImage, 190,wall3.getCenterY(),this);
					g.drawImage(wallImage, 570,wall3.getCenterY(),this);
				}
			}
		if(wall4.return_stat() == true)
			{
				if(wall4.getType() == 1)
				{
					g.drawImage(wallImage, wall4.getCenterX(),wall4.getCenterY(),this);
					g.drawImage(wallImage, 190,wall4.getCenterY(),this);
					g.drawImage(wallImage, 380,wall4.getCenterY(),this);
				}
				if(wall4.getType() == 2)
				{
					g.drawImage(wallImage, wall4.getCenterX(),wall4.getCenterY(),this);
					g.drawImage(wallImage, 190,wall4.getCenterY(),this);
					g.drawImage(wallImage, 570,wall4.getCenterY(),this);
				}
				if(wall4.getType() == 3)
				{
					g.drawImage(wallImage, wall4.getCenterX(),wall4.getCenterY(),this);
					g.drawImage(wallImage, 570,wall4.getCenterY(),this);
					g.drawImage(wallImage, 380,wall4.getCenterY(),this);
				}
				if(wall4.getType() == 4)
				{
					g.drawImage(wallImage, 570,wall4.getCenterY(),this);
					g.drawImage(wallImage, 190,wall4.getCenterY(),this);
					g.drawImage(wallImage, 380,wall4.getCenterY(),this);
				}
				if(wall4.getType() == 5)
				{
					g.drawImage(wallImage, wall4.getCenterX(),wall4.getCenterY(),this);
					g.drawImage(wallImage, 570,wall4.getCenterY(),this);
				}
				if(wall4.getType() == 6)
				{
					g.drawImage(wallImage, 190,wall4.getCenterY(),this);
					g.drawImage(wallImage, 380,wall4.getCenterY(),this);
				}
				if(wall4.getType() == 7)
				{
					g.drawImage(wallImage, wall4.getCenterX(),wall4.getCenterY(),this);
					g.drawImage(wallImage, 380,wall4.getCenterY(),this);
				}
				if(wall4.getType() == 8)
				{
					g.drawImage(wallImage, 190,wall4.getCenterY(),this);
					g.drawImage(wallImage, 570,wall4.getCenterY(),this);
				}
			}
			g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
			g.drawString("Points:", 20, 40);
			g.drawString(Integer.toString(score),90, 40);
		}
		/*else if (state == GameState.DEAD)
		{
			g.setColor(Color.BLUE);
			g.fillRect(0, 0,760, 980);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 120)); 
			g.drawString("You Suck",140, 240);
		}*/
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){		
		case KeyEvent.VK_LEFT:
		break;
		
		case KeyEvent.VK_RIGHT:
		break;
		
		case KeyEvent.VK_SPACE:
		break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		sq1.moveLeft();
		System.out.println("move left");
		break;

		case KeyEvent.VK_RIGHT:
		sq1.moveRight();
		System.out.println("move right");
		break;
		
		case KeyEvent.VK_SPACE:
		sq1.changeSize();
		System.out.println("change size");
		break;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static Background getBg1(){
		return bg1;
	}
	
	public int Random()
	{
		Random rnd = new Random();//create a random class
		int number;
		number = rnd.nextInt(8);
		return number;
	}


	


        

	
}
	

