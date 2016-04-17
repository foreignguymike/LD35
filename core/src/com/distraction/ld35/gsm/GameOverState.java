package com.distraction.ld35.gsm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.ld35.Res;
import com.distraction.ld35.Vars;

public class GameOverState extends State {
	
	private BitmapFont font;
	private int score;
	private BitmapFont scoreFont;
	
	public GameOverState(GSM gsm, int score) {
		super(gsm);
		
		font = Res.getFont("bigfont");
		scoreFont = Res.getFont("score");
		this.score = score;
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void update(float dt) {
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		font.draw(sb, "GAME", Vars.WIDTH / 2 - 85, Vars.HEIGHT / 2 + 60);
		font.draw(sb, "OVER", Vars.WIDTH / 2 - 85, Vars.HEIGHT / 2);
		scoreFont.draw(sb, "score: " + score, Vars.WIDTH / 2 - 60, Vars.HEIGHT / 2 - 60);
		sb.end();
	}
	
	@Override
	public boolean touchDown(int x, int y, int p, int b) {
		gsm.set(new MenuState(gsm));
		return true;
	}
	
}
