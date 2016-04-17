package com.distraction.ld35;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.ld35.gsm.GSM;
import com.distraction.ld35.gsm.MenuState;

public class LD35 extends ApplicationAdapter {
	
	private SpriteBatch sb;
	private GSM gsm;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		
		Res.addAtlas("pack", "pack.pack");
		Res.addFont("score", "vcr_osd.ttf", 30);
		Res.addFont("bigfont", "vcr_osd.ttf", 80);
		Res.addFont("titlefont", "vcr_osd.ttf", 60);
		Res.addFont("creditfont", "vcr_osd.ttf", 16);
		Res.addSound("laser", "laser.wav");
		Res.addSound("match", "match.wav");
		Res.addSound("miss", "miss.wav");
		Res.addSound("switch", "switch.wav");
		Res.addSound("shift", "shift.wav");
		Res.addSound("level", "level.wav");
		
		sb = new SpriteBatch();
		gsm = new GSM();
		gsm.push(new MenuState(gsm));
		
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
	
}
