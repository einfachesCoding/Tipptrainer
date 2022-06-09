package multiplayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utils.Result_Frame;
import utils.TextGenerator;
import utils.WPM_Calculator;


public class Multiplayer_Frame extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	public  JLabel progress;
	public JLabel label;
	public JTextField input;
	public Graphics g;
	private boolean wordRemoved = false;
	private static Timer timer = new Timer();
	private boolean timerStarted = false;
	private int seconds = 0;
	public String text;
	private int textlenght = 0;
	private TimerTask secondTimer = new TimerTask() {
		
		@Override
		public void run() {
			if(timerStarted) {
				seconds++;
			}
		}
	};
	private int progressValue = 0;
	public int words = 0;
	private int words_typed = 0;
	MyClient c;

	public Multiplayer_Frame(MyClient c) {
		super("Mehrspieler");
		this.c = c;
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(3,1));
		progress = new JLabel("", SwingConstants.CENTER);
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD, 100));
		input = new JTextField();
		input.setHorizontalAlignment(SwingConstants.LEFT);
		input.setFont(new Font("Arial", Font.BOLD, 100));
		input.setEnabled(false);
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
		if(text == null) {
			return;
		}
		if(!timerStarted) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				reset();
				input.setFont(new Font("Arial", Font.BOLD, 100));
				input.setText("");
				input.setEditable(true);
			}else {
				timerStarted = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(checkInput()) {
				words_typed++;
				label.setText(text);
				wordRemoved = true;
				input.setText("");
				if(seconds > 60) {
					end();
				}
				double wordsTyped = (double)words_typed / words; 
				System.out.println(wordsTyped);
				double wordsTypedValue = multi(wordsTyped, 100.0);
				System.out.println(wordsTypedValue);
				if(wordsTypedValue > progressValue) {
					progressValue = (int)wordsTypedValue;
					c.onProgressChanged(progressValue);
				}
			}
		}
	}
	
	
	public double multi(double a, double b) {
		return (a*b);
	}
	
	
	public void reset() {
		input.setText("");
		seconds = 0;
		textlenght = 0;
	}
	
	public void end() {
		input.setText("");
		timerStarted = false;
		double keysPerMinte = WPM_Calculator.getKeysPerMinute(seconds, textlenght);
		input.setEditable(false);
		input.setFont(new Font("Arial", Font.BOLD, 50));
		input.setText("Zum Neustarten Leertaste drücken!");
		new Result_Frame((int)(keysPerMinte), (int)(WPM_Calculator.getWordsPerMinute(keysPerMinte)));
	}
	
	public boolean checkInput(){
		String inputString = input.getText();
		String word = getWord();
		if(inputString.equals(word)) {
			if(word.length()+1 > text.length()) {
				text = text.substring(word.length(), text.length());
			}else {
				text = text.substring(word.length()+1, text.length());
			}
			textlenght = textlenght + word.length()+1;
			return true;
		}
		return false;
	}

	private String getWord() {
		char[] letters = text.toCharArray();
		String word = "";
		for(int i = 0; i < letters.length; i++) {
			if(letters[i] == ' ') {
				break;
			}else {
				word += letters[i];
			}
		}
		return word;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(wordRemoved && e.getKeyCode() == KeyEvent.VK_SPACE) {
			input.setText("");
			wordRemoved = false;
		}
	}
}
