package multiplayer;

import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;

import com.blogspot.debukkitsblog.net.Datapackage;
import com.blogspot.debukkitsblog.net.Executable;
import com.blogspot.debukkitsblog.net.Server;

public class MyServer extends Server{
	
	ArrayList<Player> players = new ArrayList<>(); 

	public MyServer(int port) {
		super(port);
	}

	@Override
	public void preStart() {
		this.registerMethod("NAME", new Executable() {
			
			@Override
			public void run(Datapackage pack, Socket socket) {
				players.add(new Player((String)pack.get(1), 0, Color.red));
				sendReply(socket, "Dies ist ein test Text!");
			}
		});
		this.registerMethod("PROGRESS_CHANGED", new Executable() {
			
			@Override
			public void run(Datapackage pack, Socket socket) {
				String playername = (String)pack.get(1);
				for (int i = 0; i < players.size(); i++) {
					if(players.get(i).getName().equals(playername)) {
						players.get(i).setProgress((int)pack.get(2));
						sendReply(socket, "OK");
						broadcastMessage(new Datapackage("PROGRESS_CHANGED", players));
					}
				}
			}
		});
		this.registerMethod("GET_PLAYERS", new Executable() {
			
			@Override
			public void run(Datapackage pack, Socket socket) {
				sendReply(socket, players);
			}
		});
	}
}
