package trainer;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import utils.Result_Frame;
import utils.Section;
import utils.TextGenerator;
import utils.WPM_Calculator;

public class Trainer_Frame extends JFrame implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	private static JLabel label;
	private static String text;
	private static int currentLektion = 0;
	private static int textlenght = 200;
	private static int chartype = 0;
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
	private int misstakes = 0;
	private boolean lastKeyWasAMisstake = false;
	
	public Trainer_Frame() {
		super("TippTrainer");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1,1));
		label = new JLabel("", SwingConstants.LEFT);
		label.setFont(new Font("Arial", Font.PLAIN, 100));
		getContentPane().add(label);
		createLections();
		generateText();
		addKeyListener(this);
		requestFocus();
		setVisible(true);
		timer.scheduleAtFixedRate(secondTimer, 0, 1000);
	}
	
	private void createLections() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Lektionen");
		JMenuItem[] entry = new JMenuItem[29];
		entry[0] = new JMenuItem("1. Lektion: f,j");
		entry[1] = new JMenuItem("2. Lektion: d,k");
		entry[2] = new JMenuItem("3. Lektion: f,j,d,k");
		entry[3] = new JMenuItem("4. Lektion: s,l");
		entry[4] = new JMenuItem("5. Lektion: a,ö,ä");
		entry[5] = new JMenuItem("6. Lektion: s,l,a,ö,ä");
		entry[6] = new JMenuItem("7. Lektion: a,s,d,f,j,k,l,ö,ä");
		entry[7] = new JMenuItem("8. Lektion: g,h");
		entry[8] = new JMenuItem("9. Lektion: a,s,d,f,g,h,j,k,l,ö,ä");
		entry[9] = new JMenuItem("10. Lektion: r,u");
		entry[10] = new JMenuItem("11. Lektion: e,i");
		entry[11] = new JMenuItem("12. Lektion: r,u,e,i");
		entry[12] = new JMenuItem("13. Lektion: w,o");
		entry[13] = new JMenuItem("14. Lektion: q,p,ü");
		entry[14] = new JMenuItem("15. Lektion: w,o,q,p,ü");
		entry[15] = new JMenuItem("16. Lektion: q,w,e,r,u,i,o,p,ü");
		entry[16] = new JMenuItem("17. Lektion: t,z");
		entry[17] = new JMenuItem("18. Lektion: q,w,e,r,t,z,u,i,o,p,ü");
		entry[18] = new JMenuItem("19. Lektion: v,m");
		entry[19] = new JMenuItem("20. Lektion: c ,");
		entry[20] = new JMenuItem("21. Lektion: v,m,c ,");
		entry[21] = new JMenuItem("22. Lektion: x,.");
		entry[22] = new JMenuItem("23. Lektion: y,-");
		entry[23] = new JMenuItem("24. Lektion: x,.,y,-");
		entry[24] = new JMenuItem("25. Lektion: y,x,c,v,b,n,m,.-");
		entry[25] = new JMenuItem("26. Lektion: a,s,d,f,g,h,j,k,l,ö,ä,q,w,e,r,t,z,u,i,o,p,ü");
		entry[26] = new JMenuItem("27. Lektion: a,s,d,f,g,h,j,k,l,ö,ä,y,x,c,v,b,n,m,.-");
		entry[27] = new JMenuItem("28. Lektion: a,s,d,f,g,h,j,k,l,ö,ä,q,w,e,r,t,z,u,i,o,p,ü,y,x,c,v,b,n,m,.-");
		entry[28] = new JMenuItem("Einstellungen");
		setJMenuBar(menuBar);
		menuBar.add(menu);
		for(int i = 0; i < entry.length; i++) {
			menu.add(entry[i]);
			entry[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Object source = e.getSource();
					for(int i = 0; i < entry.length; i++) {
						if(source == entry[i]) {
							if(i == 28) {
								new Setting_Frame();
								return;
							}
							currentLektion = i;
							generateText();
							seconds = 0;
							misstakes = 0;
						}
					}
				}
			});
		}
	}
	
	public static void setTextlenght(int newTextlenght) {
		textlenght = newTextlenght;
		generateText();
	}
	
	public static void setChartype(int newChartype) {
		chartype = newChartype;
		generateText();
	}
	
	public static int getTextlenght() {
		return textlenght;
	}
	
	public static int getChartype() {
		return chartype;
	}
	
	private static void generateText() {
		text = TextGenerator.generateTextFromChars(Section.getChars(currentLektion), textlenght, 7, chartype);
		label.setText(text);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!timerStarted && text.length() != 0) {
			timerStarted = true;
		}
		if (e.getKeyChar() == text.charAt(0)) {
			lastKeyWasAMisstake = false;
			text = text.substring(1, text.length());
			label.setText(text);
			if(text.length() == 0) {
				end();
			}
		}else {
			if(!lastKeyWasAMisstake) {
				misstakes++;
				lastKeyWasAMisstake = true;
			}
		}
	}
	
	public void end() {
		timerStarted = false;
		double keysPerMinte = WPM_Calculator.getKeysPerMinute(seconds, textlenght);
		new Result_Frame((int)(keysPerMinte), (int)(WPM_Calculator.getWordsPerMinute(keysPerMinte)), (int)WPM_Calculator.getAccuracy(misstakes, textlenght));
	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
