package multiplayer;

import java.net.Socket;
import java.util.ArrayList;

import com.blogspot.debukkitsblog.net.Client;
import com.blogspot.debukkitsblog.net.Datapackage;
import com.blogspot.debukkitsblog.net.Executable;

public class MyClient extends Client{

	private Multiplayer_Frame frame;
	private String name;
	
	public MyClient(String hostname, int port, String name) {
		super(hostname, port);
		frame = new Multiplayer_Frame(this);
		registerMethod("PROGRESS_CHANGED", new Executable() {
			
			@Override
			public void run(Datapackage pack, Socket socket) {
				if(frame.g == null) {
					frame.g = frame.progress.getGraphics();
					frame.input.setEnabled(true);
				}
				ArrayList<Player> players = (ArrayList<Player>)pack.get(1);
				frame.drawGame(players);
			}
		});
		Datapackage data = sendMessage("NAME", name);
		frame.text = (String) data.get(1);
		frame.label.setText((String)data.get(1));
		frame.words = (int)data.get(2);
		this.name = name;
		start();
	}

	public void onProgressChanged(int newProgress) {
		sendMessage("PROGRESS_CHANGED", name, newProgress);
	}
}
