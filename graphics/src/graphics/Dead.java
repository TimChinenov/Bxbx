//This class is incomplete, clearly.
//Class is responsible for displaying
//the death screen, with the score, and button options
package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Dead {
	private class CloseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public JPanel createDeathScreen(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		try{
			
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("TINYBBA_.TFF")));
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		Font font = new Font("Tiny BoxBitA10", Font.PLAIN, 50);
		
		final int WIDTH = 250;
		final int HEIGHT = 75;
		final int DEFAULT_SCORE = 100;
		JPanel holder = new JPanel(new GridLayout(1, 2));
	
		JPanel scoreSide = new JPanel(new GridLayout(3, 1));
		JPanel buttonSide = new JPanel(new GridLayout(3, 1));
	
		JButton retry = new JButton("Retry");
		retry.setFont(font);
		retry.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		retry.setBorder(BorderFactory.createRaisedBevelBorder());
		retry.setBackground(Color.RED);
		//Adjust opaqueness when you find a background
		retry.setOpaque(true);
		retry.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton menu = new JButton("Menu");
		menu.setFont(font);
		menu.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		menu.setBorder(BorderFactory.createRaisedBevelBorder());
		menu.setBackground(Color.BLUE);
		//Adjust opaqueness when you find a background
		menu.setOpaque(true);
		menu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
		JButton quit = new JButton("Quit");
		quit.setFont(font);
		quit.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		quit.setBorder(BorderFactory.createRaisedBevelBorder());
		quit.setBackground(Color.GREEN);
		//Adjust opaqueness when you find a background
		quit.setOpaque(true);
		quit.addActionListener(new CloseListener());
	
		//	Image background for the entire Menu
		//	ImagePanel background;
	
/*		
	Box vLayout = Box.createVerticalBox();
	vLayout.add(Box.createVerticalGlue());
	
	Box rBox = Box.createHorizontalBox();
	rBox.add(Box.createHorizontalGlue());
	rBox.add(retry);
	rBox.add(Box.createHorizontalGlue());
	
	vLayout.add(rBox);
	vLayout.add(Box.createVerticalGlue());
	
	Box mBox = Box.createHorizontalBox();
	mBox.add(Box.createHorizontalGlue());
	mBox.add(menu);
	mBox.add(Box.createHorizontalGlue());
	
	vLayout.add(mBox);
	vLayout.add(Box.createVerticalGlue());
	
	Box qBox = Box.createHorizontalBox();
	qBox.add(Box.createHorizontalGlue());
	qBox.add(quit);
	qBox.add(Box.createHorizontalGlue());
	
	vLayout.add(qBox);
	vLayout.add(Box.createVerticalGlue());
*/
		buttonSide.add(retry);
		buttonSide.add(menu);
		buttonSide.add(quit);
	
		JLabel hs = new JLabel("High Score");
		hs.setFont(font);
		hs.setHorizontalAlignment(SwingConstants.CENTER);
		hs.setOpaque(true);
		hs.setBackground(Color.BLUE);
		scoreSide.add(hs);
	
		JLabel sc = new JLabel("" + DEFAULT_SCORE);
		sc.setFont(font);
		sc.setHorizontalAlignment(SwingConstants.CENTER);
		sc.setOpaque(true);
		sc.setBackground(Color.GREEN);
		scoreSide.add(sc);
		
		JButton textAdv = new JButton("???");
		textAdv.setFont(font);
		textAdv.setBorder(BorderFactory.createRaisedBevelBorder());
		textAdv.setHorizontalAlignment(SwingConstants.CENTER);
		textAdv.setOpaque(true);
		textAdv.setBackground(Color.RED);
		scoreSide.add(textAdv);
		textAdv.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		holder.add(scoreSide);
		holder.add(buttonSide);
		
		return holder;
	}
}

