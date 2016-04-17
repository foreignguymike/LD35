package com.distraction.ld35.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld35.Res;

public class Shot {
	
	private float x;
	private float y;
	private int width;
	private int height;
	private float speed;
	
	public enum Type {
		PLUS,
		MINUS
	}
	private TextureRegion image;
	private Type type;
	
	public Shot(Type type, float x, float y) {
		this.type = type;
		this.x = x;
		this.y = y;
		speed = 800;
		
		if(type == Type.PLUS) {
			image = Res.getAtlas("pack").findRegion("plus");
		}
		else {
			image = Res.getAtlas("pack").findRegion("minus");
		}
		width = image.getRegionWidth();
		height = image.getRegionHeight();
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
	
	public Type getType() {
		return type;
	}
	
	public void update(float dt) {
		x -= speed * dt;
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(image, x - width / 2, y - height / 2);
	}
	
}
