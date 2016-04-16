package com.distraction.ld35.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld35.Vars;

public class Laser {
	
	private float x;
	private float y;
	
	private TextureRegion image;
	
	public Laser(float x) {
		this.x = x;
		y = Vars.HEIGHT / 2;
	}
	
	public void setPosition(float y) {
		this.y = y;
	}
	
	public void update(float dt) {
		
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(image, x, y);
	}
	
}
