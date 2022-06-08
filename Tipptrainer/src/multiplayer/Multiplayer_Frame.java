package multiplayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Multiplayer_Frame extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private JLabel progress;
	public JLabel label;
	private JTextField input;
	private Graphics g;

	public Multiplayer_Frame() {
		super("Mehrspieler");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(3,1));
		progress = new JLabel("", SwingConstants.CENTER);
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD, 100));
		input = new JTextField();
		input.setHorizontalAlignment(SwingConstants.LEFT);
		input.setFont(new Font("Arial", Font.BOLD, 100));
		label.setText("");
		getContentPane().add(progress);
		getContentPane().add(label);
		getContentPane().add(input);
		input.addKeyListener(this);
		setVisible(true);
	}
	
	public void drawGame(ArrayList<Player> players){
		if(g == null) {
			return;
		}
		int yOffset = progress.getHeight() / (players.size()+2);
		int max_namelenght = 0;
		for(int i = 0; i < players.size(); i++) {
			String name = players.get(i).getName();
			g.setColor(players.get(i).getColor());
			g.drawString(name, 5, yOffset*(i+1));
			if(name.length() > max_namelenght) {
				max_namelenght = name.length();
			}
		}
		int xOffset = 5 + (max_namelenght * 10);
		for(int i = 0; i < players.size(); i++) {
			g.setColor(players.get(i).getColor());
			g.fillRect(xOffset, yOffset - (yOffset/2) + (i* yOffset), players.get(i).getProgress()*8, yOffset-10);
		}
		g.setColor(Color.BLACK);
		g.drawLine(xOffset+800, 0, xOffset+800, getHeight());
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		g = progress.getGraphics();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
