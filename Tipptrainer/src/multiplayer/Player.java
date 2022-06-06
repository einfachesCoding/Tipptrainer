package multiplayer;

import java.awt.Color;

public class Player {
	private String name;
	private Color color;
	private int progress;
	private String id;
	
	public Player(String name, String id, int progress, Color color) {
		this.name = name;
		this.color = color;
		this.progress = progress;
		this.id = id;
	}
	
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
