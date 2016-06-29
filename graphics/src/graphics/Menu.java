/*
 * This Class is responsible for the construction of the 
 * main menu that the player interfaces with at the beginning of the game.
 * Currently it is set to have a start, highscores, and setting buttons.
 * */
package graphics;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class Menu extends Applet {
	private static Image start; //Image variable for start button
	private static Image highscores;//Image variable for high scores button
	private static Image setting;//Image variable for settings button
	private Image background;//Image variable for the background picture
	private static Graphics g; //graphics object
	private URL base;
	
	public Menu()
	{
		//Gets the URL of the document in which the applet is embedded. 
		try{
	    	base = getDocumentBase();//
	    }catch(Exception e){
	    	//handle exceptions
	    }
		
		//variables grab images from addressed locations. You can navigate to them
		//using the side panel yourself as well!
		start = getImage(base,"data/srt.png");
	    highscores = getImage(base, "data/HS.png");
	    setting = getImage(base, "data/SETTING.png");
	}
	public static void update()//When update is called. The following images are drawn at the given coordinates.
	{
		//g.drawImage(background, bg1.getBgx(),bg1.getBgY(),this);//This colors background should always be first
		g.drawImage(start, 214,202, null);
		g.drawImage(highscores,60, 417, null);
		g.drawImage(setting,140, 632,null);
	}
}
