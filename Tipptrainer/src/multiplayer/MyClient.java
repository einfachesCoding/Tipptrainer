package multiplayer;

import java.net.Socket;
import java.util.ArrayList;

import com.blogspot.debukkitsblog.net.Client;
import com.blogspot.debukkitsblog.net.Datapackage;
import com.blogspot.debukkitsblog.net.Executable;

public class MyClient extends Client{

	private Multiplayer_Frame frame;
	private Player p;
	
	public MyClient(String hostname, int port, String name) {
		super(hostname, port);
		frame = new Multiplayer_Frame();
		registerMethod("PROGRESS_CHANGED", new Executable() {
			
			@Override
			public void run(Datapackage pack, Socket socket) {
				ArrayList<Player> players = (ArrayList<Player>)pack.get(1);
				frame.drawGame(players);
			}
		});
		start();
		Datapackage data = sendMessage("NAME", name);
		frame.label.setText((String) data.get(1));
	}
	
	public void getPlayer(String name) {
		Datapackage data = sendMessage("GET_PLAYERS", name); 
		frame.drawGame((ArrayList<Player>)data.get(1));
	}
}
