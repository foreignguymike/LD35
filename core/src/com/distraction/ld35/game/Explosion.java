package com.distraction.ld35.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {
	
	private TextureRegion image;
	private float xs[];
	private float ys[];
	private int width;
	private int height;
	private float speed;
	private float alpha;
	private float interval;
	private float time;
	
	public Explosion(TextureRegion image, float x, float y, float speed, float interval) {
		this.image = image;
		xs = new float[8];
		ys = new float[8];
		for(int i = 0; i < 8; i++) {
			xs[i] = x;
			ys[i] = y;
		}
		this.speed = speed;
		this.interval = interval;
		width = image.getRegionWidth();
		height = image.getRegionHeight();
	}
	
	public void update(float dt) {
		time += dt;
		
		alpha = (interval - time / interval);
		if(alpha < 0) {
			alpha = 0;
		}
		if(alpha > 1) {
			alpha = 1;
		}
		
		float speed = this.speed * dt;
		xs[0] += speed;
		xs[1] += speed;
		ys[1] += speed;
		ys[2] += speed;
		xs[3] -= speed;
		ys[3] += speed;
		xs[4] -= speed;
		xs[5] -= speed;
		ys[5] -= speed;
		ys[6] -= speed;
		xs[7] += speed;
		ys[7] -= speed;
	}
	
	public void render(SpriteBatch sb) {
		sb.setColor(1, 1, 1, alpha);
		for(int i = 0; i < xs.length; i++) {
			sb.draw(image, xs[i] - width / 2, ys[i] - height / 2);
		}
		sb.setColor(1, 1, 1, 1);
	}
	
}
