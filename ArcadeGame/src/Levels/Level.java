package Levels;

import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;

public class Level {
	ShootingPlayer player;
	ArrayList<Collider> staticPieces = new ArrayList<>();
	ArrayList<Collider> mobilePieces = new ArrayList<>();
	ArrayList<Collider> bullets = new ArrayList<>();
	ArrayList<Collider> totalPieces = new ArrayList<>();
	ArrayList<Collider> objects = new ArrayList<>();
	ArrayList<Collider> coins = new ArrayList<>();
	int time = 0;
	boolean isFinished = false;
	boolean[] keysPressed = new boolean[4];
	int playerSpeed = 10;
	public void draw(PApplet marker) {
		
	}
	public void move() {
		
	}
}
