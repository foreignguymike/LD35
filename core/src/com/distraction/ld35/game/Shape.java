package com.distraction.ld35.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld35.Vars;

public class Shape {
	
	private int numSides;
	private float speed;
	
	private float x;
	private float y;
	
	private TextureRegion image;
	
	public Shape(int numSides, float x) {
		this.numSides = numSides;
		this.x = x;
		y = Vars.HEIGHT + Vars.WIDTH / 4;
		speed = 100;
		
		// TODO get image
	}
	
	public int getNumSides() {
		return numSides;
	}
	
	public void update(float dt) {
		y -= speed;
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(image, x, y);
	}
	
}
