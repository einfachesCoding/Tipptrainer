package multiplayer;

import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;

import com.blogspot.debukkitsblog.net.Datapackage;
import com.blogspot.debukkitsblog.net.Executable;
import com.blogspot.debukkitsblog.net.Server;

public class MyServer extends Server{
	
	ArrayList<Player> players = new ArrayList<>();
	ArrayList<Color> colors = new ArrayList();

	public MyServer(int port) {
		super(port);
		colors.add(Color.green);
		colors.add(Color.red);
		colors.add(Color.blue);
		colors.add(Color.black);
	}

	@Override
	public void preStart() {
		this.registerMethod("NAME", new Executable() {
			
			@Override
			public void run(Datapackage pack, Socket socket) {
				if(colors.size() == 0) {
					sendReply(socket, "OK");
					return;
				}
				int nr = (int)(Math.random()*colors.size());
				players.add(new Player((String)pack.get(1), 0, colors.get(nr)));
				colors.remove(nr);
				broadcastMessage(new Datapackage("PROGRESS_CHANGED", players));
				sendReply(socket, "Dies ist ein test Text!", 5);
			}
		});
		this.registerMethod("PROGRESS_CHANGED", new Executable() {
			
			@Override
			public void run(Datapackage pack, Socket socket) {
				String playername = (String)pack.get(1);
				for (int i = 0; i < players.size(); i++) {
					if(players.get(i).getName().equals(playername)) {
						players.get(i).setProgress((int)pack.get(2));
						broadcastMessage(new Datapackage("PROGRESS_CHANGED", players));
						sendReply(socket, "OK");
					}
				}
			}
		});
	}
}
