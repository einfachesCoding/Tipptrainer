package multiplayer;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Connector {
	
	Multiplayer_Frame frame;
	Socket socket;
	
	public Connector(/*String adress, int port*/) {
		/*
		try {
			socket = new Socket(adress, port);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Keine Verbindung möglich!");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		frame = new Multiplayer_Frame();
	}
}
