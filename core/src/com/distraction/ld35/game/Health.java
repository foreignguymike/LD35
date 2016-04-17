package com.distraction.ld35.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld35.Res;
import com.distraction.ld35.Vars;

public class Health {
	
	private int health;
	private int maxHealth;
	private int width;
	private int height;
	
	private TextureRegion image;
	
	public Health() {
		health = maxHealth = 100;
		image = Res.getAtlas("pack").findRegion("health");
		width = image.getRegionWidth();
		height = image.getRegionHeight();
	}
	
	public void hit(int damage) {
		health -= damage;
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(image, Vars.WIDTH - 54, 67, width, height * (1f * health / maxHealth));
	}
	
}
