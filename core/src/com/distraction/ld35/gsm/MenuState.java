package com.distraction.ld35.gsm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.ld35.Res;
import com.distraction.ld35.Vars;

public class MenuState extends State {
	
	private BitmapFont font;
	private BitmapFont creditFont;
	
	public MenuState(GSM gsm) {
		super(gsm);
		
		font = Res.getFont("titlefont");
		creditFont = Res.getFont("creditfont");
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void update(float dt) {
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		font.draw(sb, "BLOCKO", Vars.WIDTH / 2 - 97, Vars.HEIGHT / 2 + 60);
		creditFont.draw(sb, "for LD35 by mike", 10, 20);
		sb.end();
	}
	
	@Override
	public boolean touchDown(int x, int y, int p, int b) {
		gsm.set(new PlayState(gsm));
		return true;
	}
}
