package multiplayer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Connection_Frame extends JFrame{
	private static final long serialVersionUID = 1L;

	public Connection_Frame() {
		setSize(400,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(3, 2));
		JLabel adressLabel = new JLabel("Adresse eingeben: ");
		JLabel portLabel = new JLabel("Port eingeben: ");
		JTextField adressField = new JTextField();
		JTextField portField = new JTextField();
		getContentPane().add(adressLabel);
		getContentPane().add(adressField);
		getContentPane().add(portLabel);
		getContentPane().add(portField);
		JButton delete = new JButton("löschen");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adressField.setText("");
				portField.setText("");
			}
		});
		getContentPane().add(delete);
		JButton submit = new JButton("bestätigen");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
				System.out.println("bestätigt");
			}
		});
		getContentPane().add(submit);
		setVisible(true);
	}
}
