package com.distraction.ld35.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld35.Res;

public class End extends Shape {
	
	private TextureRegion bg;
	private int bgWidth;
	private int bgHeight;
	private float ydest;
	
	public End(int numSides, float x) {
		super(numSides, x);
		bg = Res.getAtlas("pack").findRegion("endbg");
		bgWidth = bg.getRegionWidth();
		bgHeight = bg.getRegionHeight();
		setFront();
		y = ydest;
	}
	
	public void sety(float y) {
		this.y = y;
	}
	
	public float getydest() {
		return ydest;
	}
	
	public void setBack() {
		ydest = bgHeight / 2;
	}
	
	public void setFront() {
		ydest = bgHeight + bgHeight / 2 + 10;
	}
	
	@Override
	public void update(float dt) {
		if(y < ydest) {
			y += speed * 10 * dt;
			if(y > ydest) {
				y = ydest;
			}
		}
		if(y > ydest) {
			y -= speed * 10 * dt;
			if(y < ydest) {
				y = ydest;
			}
		}
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(bg, x - bgWidth / 2, y - bgHeight / 2);
		super.render(sb);
	}
	
}
