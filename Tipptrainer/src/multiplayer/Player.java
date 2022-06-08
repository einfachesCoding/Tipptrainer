package multiplayer;

import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable{
	private String name;
	private Color color;
	private int progress;
	
	public Player(String name, int progress, Color color) {
		this.name = name;
		this.color = color;
		this.progress = progress;
	}
	
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
