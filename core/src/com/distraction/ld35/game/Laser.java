package com.distraction.ld35.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld35.Res;
import com.distraction.ld35.Vars;
import com.distraction.ld35.game.Shot.Type;

public class Laser {
	
	private float x;
	private float y;
	private int width;
	private int height;
	
	private TextureRegion[] images;
	private Type type;
	private float fireInterval;
	private float fireTime;
	
	public Laser(float x) {
		this.x = x;
		y = Vars.HEIGHT / 2;
		images = new TextureRegion[] {
				Res.getAtlas("pack").findRegion("laserplus"),
				Res.getAtlas("pack").findRegion("laserminus")	
		};
		setType(Type.PLUS);
		fireInterval = 0.1f;
	}
	
	public void setPosition(float y) {
		this.y = y;
	}
	
	public void setType(Type type) {
		this.type = type;
		width = images[type.ordinal()].getRegionWidth();
		height = images[type.ordinal()].getRegionHeight();
	}
	
	public Type getType() {
		return type;
	}
	
	public float getx() {
		return x;
	}
	
	public float gety() {
		return y;
	}
	
	public boolean shoot() {
		if(fireTime > fireInterval) {
			fireTime = 0;
			return true;
		}
		return false;
	}
	
	public void update(float dt) {
		fireTime += dt;
		if(y < 80) {
			y = 80;
		}
		if(y > Vars.HEIGHT - 60) {
			y = Vars.HEIGHT - 60;
		}
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(images[type.ordinal()], x - width / 2, y - height / 2);
	}
	
}
