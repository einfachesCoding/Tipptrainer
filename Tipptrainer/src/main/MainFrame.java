package main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTextField input;
	private String text;
	private boolean wordRemoved = false;
	private int wordlenght = 0;
	
	public MainFrame() {
		super("TippTrainer");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2,1));
		createComponents();
		input.addKeyListener(this);
		input.requestFocus();
		setVisible(true);
	}

	private void createComponents() {
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD, 100));
		input = new JTextField();
		input.setHorizontalAlignment(SwingConstants.CENTER);
		input.setFont(new Font("Arial", Font.BOLD, 100));
		char[] chars = {'a','s','d','f','g','h','j','k','l','ö','ä',' '};
		text = TextGenerator.generateText(chars, 100, 7);
		label.setText(text);
		getContentPane().add(label);
		getContentPane().add(input);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			String inputString = input.getText();
			String word = getWord();
			if(inputString.equals(word)) {
				if(word.length()+1 > text.length()) {
					text = text.substring(word.length(), text.length());
				}else {
					text = text.substring(word.length()+1, text.length());
				}
				label.setText(text);
				wordRemoved = true;
				wordlenght = 0;
				return;
			}
		}
		if(text.charAt(0+wordlenght) == e.getKeyChar()) {
			wordlenght++;
		}else {
			JOptionPane.showMessageDialog(null, "vertippt");
			wordRemoved = false;
			String s = input.getText();
			if(s.length() > 0) {
				s = s.substring(0, s.length()-1);
				input.setText(s);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(wordRemoved && e.getKeyCode() == KeyEvent.VK_SPACE) {
			input.setText("");
		}
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
}
