package multiplayer;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;


public class Multiplayer_Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel progress;
	private JLabel label;
	private Graphics g;
	private int yOffset;

	public Multiplayer_Frame() {
		super("Mehrspieler");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(2,1));
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Einstellungen");
		JMenuItem connect = new JMenuItem("Verbinden");
		menu.add(connect);
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Connection_Frame();
				g = progress.getGraphics();
				String[] names = {"Bert"};
				drawNames(names);
			}
		});
		setJMenuBar(menuBar);
		menuBar.add(menu);
		progress = new JLabel("Progress", SwingConstants.CENTER);
		label = new JLabel("Text");
		getContentPane().add(progress);
		getContentPane().add(label);
		setVisible(true);
	}
	
	public void drawNames(String[] names){
		yOffset = progress.getHeight() / (names.length+2);
		
		for(int i = 0; i < names.length; i++) {
			g.drawString(names[i], 5, yOffset*(i+1));
		}
	}
}
