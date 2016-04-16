package com.distraction.ld35;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.ld35.gsm.GSM;

public class LD35 extends ApplicationAdapter {
	
	private SpriteBatch sb;
	private GSM gsm;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		
		sb = new SpriteBatch();
		gsm = new GSM();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
	
}
