package com.distraction.ld35.gsm;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.distraction.ld35.Vars;

public abstract class State extends InputAdapter {
	
	protected GSM gsm;
	protected OrthographicCamera cam;
	protected Vector3 m;
	
	protected State(GSM gsm) {
		this.gsm = gsm;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Vars.getWidth(), Vars.getHeight());
		m = new Vector3();
	}
	
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	
}
