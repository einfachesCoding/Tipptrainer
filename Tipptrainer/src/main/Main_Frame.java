package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import multiplayer.MyClient;
import multiplayer.MyServer;
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
		new MyServer(8000);
		new MyClient("localhost", 8000, "ajflösakdjf");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new MyClient("localhost", 8000, "asjdflad");
		//new Main_Frame();
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
			int choose = JOptionPane.showConfirmDialog(null, "Server erstellen?");
			if(choose == JOptionPane.YES_OPTION) {
				String portString = JOptionPane.showInputDialog(null, "Gib einen Port ein:");
				int port = 0;
				try {
					port = Integer.parseInt(portString);
				}catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Der Port muss eine Zahl sein!");
					return;
				}
				new MyServer(port);
				String name = JOptionPane.showInputDialog("Gib einen Namen ein");
				new MyClient("localhost", port, name);
			}
			if(choose == JOptionPane.NO_OPTION) {
				String adress = JOptionPane.showInputDialog("Gib eine Serveradresse ein:");
				String portString = JOptionPane.showInputDialog(null, "Gib einen Port ein:");
				int port = 0;
				try {
					port = Integer.parseInt(portString);
				}catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Der Port muss eine Zahl sein!");
					return;
				}
				String name = JOptionPane.showInputDialog("Gib einen Namen ein");
				new MyClient(adress, port, name);
			}
		}
	}
}
