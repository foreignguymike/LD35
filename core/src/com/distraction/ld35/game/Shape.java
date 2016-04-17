package com.distraction.ld35.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld35.Res;
import com.distraction.ld35.Vars;

public class Shape {
	
	protected int numSides;
	public static float speed = 50;
	
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	
	protected TextureRegion image;
	
	public Shape(int numSides, float x) {
		this.numSides = numSides;
		this.x = x;
		y = Vars.HEIGHT + Vars.WIDTH / 4;
		refreshImage();
	}
	
	public static void incrementSpeed() {
		speed += 10;
	}
	
	public float getx() {
		return x;
	}
	
	public float gety() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getNumSides() {
		return numSides;
	}
	
	public void setNumSides(int numSides) {
		this.numSides = numSides;
		refreshImage();
	}
	
	public void increment() {
		if(numSides < Vars.MAX_SIDES) {
			numSides++;
			refreshImage();
		}
	}
	
	public void decrement() {
		if(numSides > Vars.MIN_SIDES) {
			numSides--;
			refreshImage();
		}
	}
	
	private void refreshImage() {
		image = Res.getAtlas("pack").findRegion("shape" + numSides);
		width = image.getRegionWidth();
		height = image.getRegionHeight();
	}
	
	public void update(float dt) {
		y -= speed * dt;
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(image, x - width / 2, y - height / 2);
	}
	
}
