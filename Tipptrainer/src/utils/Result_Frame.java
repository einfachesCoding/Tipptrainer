package utils;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Result_Frame extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public Result_Frame(int cpm, int wpm, int accuracy) {
		super("Ergebnis");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JLabel cpmLabel = new JLabel("Anschläge pro Minute: " + cpm);
		JLabel wpmLabel = new JLabel("Wörter pro Minute: " + wpm);
		JLabel accuracyLabel = new JLabel("Genauigkeit: " + accuracy + "%");
		getContentPane().setLayout(new GridLayout(1, 3));
		getContentPane().add(cpmLabel);
		getContentPane().add(wpmLabel);
		getContentPane().add(accuracyLabel);
		setVisible(true);
	}
	
	public Result_Frame(int cpm, int wpm) {
		super("Ergebnis");
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JLabel cpmLabel = new JLabel("Anschläge pro Minute: " + cpm);
		JLabel wpmLabel = new JLabel("Wörter pro Minute: " + wpm);
		getContentPane().setLayout(new GridLayout(1, 2));
		getContentPane().add(cpmLabel);
		getContentPane().add(wpmLabel);
		setVisible(true);
	}
}
