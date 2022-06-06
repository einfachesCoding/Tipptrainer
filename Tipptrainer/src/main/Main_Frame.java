package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import trainer.Trainer_Frame;
import typing_speed.Typing_Speed_Frame;

public class Main_Frame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JButton typing_speed;
	private JButton trainer;
	private JButton multiplayer;
	
	public Main_Frame() {
		super("Hauptmenü");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(3,1));
		typing_speed = new JButton("Tippgeschwindigkeitstest");
		typing_speed.addActionListener(this);
		trainer = new JButton("Tipptrainer");
		trainer.addActionListener(this);
		multiplayer = new JButton("Mehrspieler");
		multiplayer.addActionListener(this);
		getContentPane().add(typing_speed);
		getContentPane().add(trainer);
		getContentPane().add(multiplayer);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main_Frame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object Source = e.getSource();
		if(Source == typing_speed) {
			new Typing_Speed_Frame();
			return;
		}
		if(Source == trainer) {
			new Trainer_Frame();
			return;
		}
		if(Source == multiplayer) {
			return;
		}
	}
}
