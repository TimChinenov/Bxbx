package com.aaron;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Image;


public class Project1 extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//screen dimensions
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    // Tim's dimension
    //frame coordinates
    private static int x = 0;
    private static int y = 0;
    //Mouse Position
    private static double X = 0;
    private static double Y = 0;
	Color c1 = new Color(153, 222, 255);
	Color c2 = new Color(51, 222, 255);	
	Color c3 = new Color(0, 160, 160); //these are the colors of my bxbx concept art

    public Project1() {
    	;//empty constructer in case I want to do something later
    }

    public void init() {//just gives the size and components of the frame
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void drawMenu() {//it does what it says
        MenuPanel panel = new MenuPanel();//Menu Panel extends JPanel, paint method is called as a result
        this.getContentPane().add(panel);//add this panel to the Frame
        panel.addMouseListener(new MouseHandler());//adds the mouse click
    }
    public void drawGamePanel() {
        GamePanel gamePanel = new GamePanel();//Game Panel extends JPanel
        this.getContentPane().remove(0);//removes previous panel
        this.getContentPane().add(gamePanel);//adds the new panel
        this.revalidate();//some dudes on StackOverflow said this was necessary to work
        this.repaint();//because it wasn't working without it for some reason


    }
    public static void main(String[] args) {
        Project1 frame = new Project1();//starts a new instance of the class, which is a JFrame
        frame.init();//makes the JFrame nice
        frame.drawMenu();//draws the menu
    }

    class MouseHandler extends MouseAdapter {
    	//Class to handle Mouse Actions
        public void mouseClicked(MouseEvent event) {
        X = event.getX();
        Y = event.getY();
        	if(X>0 && X<=200 && Y>200 && Y<=400){
        		System.out.println("here");
        		drawGamePanel();
        	}
        		//drawGamePanel();
        }
    }

    class MenuPanel extends JPanel {
		@Override
        public void paint(Graphics g) {
			//draws the rectangular boxes
			try {
				g.setFont(loadFont());
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.setColor(c1);
            g.fillRect(x, y, 400, 200);
            g.setColor(c2);
            g.fillRect(x, y + 200, 200, 200);
            g.fillRect(x + 200, y + 200, 199, 200);
            g.setColor(c3);
            g.fillRect(x, y + 400, 200, 199);
            g.fillRect(x + 200, y + 400, 199, 199);
            //set font, and draw the strings for boxes
            g.drawString("BXbx", 175, 100);
            g.setFont(new Font("Curls MT", Font.ITALIC, 14));
            g.drawString("START", 75, 300);
            g.drawString("High Score", 275, 300);
            g.drawString("Settings", 75, 500);
            g.drawString("Credits", 275, 500);
        }
		public Font loadFont() throws FontFormatException, IOException {
			InputStream in = new FileInputStream("TINYBBA_.TTF");
			Font Tiny = Font.createFont(Font.TRUETYPE_FONT,in);
			return Tiny;
		}
    }

    class GamePanel extends JPanel {
		@Override
        public void paint(Graphics g) {
			String str = "Still in development, but do you have time to talk about your Lord and Savior?";
            g.fillRect(x, y, 400, 600);
            g.setColor(Color.GREEN);
            g.setFont(new Font("SansSerif",Font.BOLD, 10));
            g.drawString(str,0,300);

            //Image img = drawRec();
           // g.drawImage(img, 0,0, this);
        }

        private Image drawRec() {
            //it's an old design i use to test the new panel repainting properly
            BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.BLACK);
            g.drawRect(x, y, 200, 200);
            g.drawRect(x + 200, y, 199, 200);
            g.drawRect(x, y + 200, 200, 200);
            g.drawRect(x + 200, y + 200, 199, 200);
            g.drawRect(x, y + 400, 200, 199);
            g.drawRect(x + 200, y + 400, 199, 199);
            return img;
        }
    }

}