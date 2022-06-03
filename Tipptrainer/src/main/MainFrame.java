package main;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		text = TextGenerator.generateText(Section.getChars(0), 100, 7);
		label.setText(text);
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Lektionen");
		JMenuItem[] entry = new JMenuItem[28];
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
							text = TextGenerator.generateText(Section.getChars(i), 100, 7);
							label.setText(text);	
						}
					}
				}
			});
		}
		getContentPane().add(label);
		getContentPane().add(input);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(text.length() == 0)return;
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
