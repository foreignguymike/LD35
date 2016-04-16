package com.distraction.ld35.gsm;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.ld35.Vars;
import com.distraction.ld35.game.End;
import com.distraction.ld35.game.Laser;
import com.distraction.ld35.game.Shape;

public class PlayState extends State {
	
	private int[] xs;
	private List<Shape> shapes;
	private End[] ends;
	private Laser laser;
	
	public PlayState(GSM gsm) {
		super(gsm);
		
		xs = new int[] { Vars.WIDTH / 8, 3 * Vars.WIDTH / 8, 5 * Vars.WIDTH / 8, 7 * Vars.WIDTH / 8 };
		
		shapes = new ArrayList<Shape>();
		ends = new End[] {
				getRandomEnd(0),
				getRandomEnd(1),
				getRandomEnd(2)
		};
		
		laser = new Laser(xs[3]);
	}
	
	private End getRandomEnd(int col) {
		// 3 to 6
		int numSides = (int) (Math.random() * 4) + 3;
		End end = new End(numSides, xs[col]);
		return end;
	}
	
	@Override
	public void update(float dt) {
		
		for(int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.update(dt);
		}
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		
		for(Shape shape : shapes) {
			shape.render(sb);
		}
		
	}
	
	@Override
	public boolean mouseMoved(int x, int y) {
		laser.setPosition(y);
		return true;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int p) {
		laser.setPosition(y);
		return true;
	}
	
}