package typing_speed;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import utils.*;

public class Typing_Speed_Frame extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTextField input;
	private String text;
	private boolean wordRemoved = false;
	private static Timer timer = new Timer();
	private boolean timerStarted = false;
	private int seconds = 0;
	private TimerTask secondTimer = new TimerTask() {
		
		@Override
		public void run() {
			if(timerStarted) {
				seconds++;
			}
		}
	};
	private String[] words = {"der", "und", "in", "zu", "den", "das", "nicht", "von", "sie", "ist", 
			"des", "sich", "mit", "dem", "dass", "er", "es", "ein", "ich", "auf", 
			"so", "eine", "auch", "als", "an", "nach", "wie", "im", "für", "man", 
			"aber", "aus", "durch", "wenn", "nur", "war", "noch", "werden", "bei", "hat", 
			"wir", "was", "wird", "sein", "einen", "welche", "sind", "oder", "zur", "um", 
			"haben", "einer", "mir", "über", "ihm", "diese", "einem", "ihr", "uns", "da", 
			"zum", "kann", "doch", "vor", "dieser", "mich", "ihn", "du", "hatte", "seine", 
			"mehr", "am", "denn", "nun", "unter", "sehr", "selbst", "schon", "hier", "bis", 
			"habe", "ihre", "dann", "ihnen", "seiner", "alle", "wieder", "meine", "Zeit", "gegen", 
			"vom", "ganz", "einzelnen", "wo", "muss", "ohne", "eines", "können", "sei", "ja", 
			"wurde", "jetzt", "immer", "seinen", "wohl", "dieses", "ihren", "würde", "diesen", "sondern", 
			"weil", "welcher", "nichts", "diesem", "alles", "waren", "will", "Herr", "viel", "mein", 
			"also", "soll", "worden", "lassen", "dies", "machen", "ihrer", "weiter", "Leben", "recht", 
			"etwas", "keine", "seinem", "ob", "dir", "allen", "großen", "Jahre", "Weise", "müssen", 
			"welches", "wäre", "erst", "einmal", "Mann", "hätte", "zwei", "dich", "allein", "Herren", 
			"während", "Paragraph", "anders", "Liebe", "kein", "damit", "gar", "Hand", "Herrn", "euch", 
			"sollte", "konnte", "ersten", "deren", "zwischen", "wollen", "denen", "dessen", "sagen", "bin", 
			"Menschen", "gut", "darauf", "wurden", "weiß", "gewesen", "Seite", "bald", "weit", "große", 
			"solche", "hatten", "eben", "andern", "beiden", "macht", "sehen", "ganze", "anderen", "lange", 
			"wer", "ihrem", "zwar", "gemacht", "dort", "kommen", "Welt", "heute", "Frau", "werde", 
			"derselben", "ganzen", "deutschen", "lässt", "vielleicht", "meiner", "die"};
	private int textlenght = 0;
	
	public Typing_Speed_Frame() {
		super("Tippgeschwindigkeitstest");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2,1));
		createComponents();
		input.addKeyListener(this);
		input.requestFocus();
		setVisible(true);
		timer.scheduleAtFixedRate(secondTimer, 0, 1000);
	}

	private void createComponents() {
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.BOLD, 100));
		input = new JTextField();
		input.setHorizontalAlignment(SwingConstants.LEFT);
		input.setFont(new Font("Arial", Font.BOLD, 100));
		label.setText(text);
		getContentPane().add(label);
		getContentPane().add(input);
		reset();
	}
	
	public void reset() {
		input.setText("");
		text = TextGenerator.generateTextFromWords(words);
		label.setText(text);
		seconds = 0;
		textlenght = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
				label.setText(text);
				wordRemoved = true;
				if(seconds > 60) {
					end();
				}
			}
		}
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

	@Override
	public void keyReleased(KeyEvent e) {
		if(wordRemoved && e.getKeyCode() == KeyEvent.VK_SPACE) {
			input.setText("");
			wordRemoved = false;
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
