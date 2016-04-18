package com.distraction.ld35.gsm;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.ld35.Res;
import com.distraction.ld35.Vars;
import com.distraction.ld35.game.End;
import com.distraction.ld35.game.Explosion;
import com.distraction.ld35.game.Health;
import com.distraction.ld35.game.Laser;
import com.distraction.ld35.game.Shape;
import com.distraction.ld35.game.Shot;
import com.distraction.ld35.game.Shot.Type;

public class PlayState extends State {
	
	private int[] xs;
	
	private List<Shape> shapes;
	private End[][] ends;
	private float interval;
	private float time;
	
	private Laser laser;
	private List<Shot> shots;
	private List<Explosion> explosions;
	
	private Health health;
	
	private TextureRegion bg;
	private BitmapFont scoreFont;
	private int score;
	
	private int level;
	
	public PlayState(GSM gsm) {
		super(gsm);
		
		xs = new int[] { Vars.WIDTH / 8 - 1, 3 * Vars.WIDTH / 8 - 2, 5 * Vars.WIDTH / 8 - 3, 7 * Vars.WIDTH / 8 };
		
		shapes = new ArrayList<Shape>();
		ends = new End[][] {
				{ getRandomEnd(0), getRandomEnd(1), getRandomEnd(2) },
				{ getRandomEnd(0), getRandomEnd(1), getRandomEnd(2) }
		};
		ends[0][0].setFront();
		ends[0][1].setFront();
		ends[0][2].setFront();
		ends[1][0].setBack();
		ends[1][1].setBack();
		ends[1][2].setBack();
		ends[1][0].sety(ends[1][0].getydest());
		ends[1][1].sety(ends[1][1].getydest());
		ends[1][2].sety(ends[1][2].getydest());
		
		interval = 4f;
		
		shapes.add(getRandomShape());
		
		laser = new Laser(xs[3]);
		shots = new ArrayList<Shot>();
		explosions = new ArrayList<Explosion>();
		
		Gdx.input.setInputProcessor(this);
		bg = Res.getAtlas("pack").findRegion("bg");
		
		health = new Health();
		
		scoreFont = Res.getFont("score");
		
		Res.getMusic().setLooping(true);
		Res.getMusic().setVolume(0.5f);
		Res.getMusic().play();
		
	}
	
	private int getRandomNumSides() {
		return (int) (Math.random() * (1 + Vars.MAX_SIDES - Vars.MIN_SIDES)) + Vars.MIN_SIDES;
	}
	
	private int getRandomCol() {
		return (int) (Math.random() * 3);
	}
	
	private Shape getRandomShape() {
		int numSides = getRandomNumSides();
		int col = getRandomCol();
		return new Shape(numSides, xs[col]);
	}
	
	private End getRandomEnd(int col) {
		// 3 to 6
		int numSides = getRandomNumSides();
		End end = new End(numSides, xs[col]);
		return end;
	}
	
	@Override
	public void update(float dt) {
		
		time += dt;
		if(time >= interval) {
			time = 0;
			shapes.add(getRandomShape());
		}
		
		for(int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.update(dt);
			float x1 = shape.getx();
			float y1 = shape.gety();
			int w1 = shape.getWidth();
			int h1 = shape.getHeight();
			for(int j = 0; j < shots.size(); j++) {
				Shot shot = shots.get(j);
				if(shot.getx() < -30) {
					shots.remove(j);
					j--;
					continue;
				}
				float x2 = shot.getx();
				float y2 = shot.gety();
				int w2 = shot.getWidth();
				int h2 = shot.getHeight();
				if(x1 < x2 + w2 &&
					x1 + w1 > x2 &&
					y1 < y2 + h2 &&
					h1 + y1 > y2) {
					if(shot.getType() == Type.PLUS) {
						shape.increment();
					}
					else {
						shape.decrement();
					}
					shots.remove(j);
					j--;
					explosions.add(new Explosion(shot.getType() == Type.PLUS ? Res.getAtlas("pack").findRegion("plus") : Res.getAtlas("pack").findRegion("minus"), shot.getx(), shot.gety(), 200, 0.5f));
					Res.getSound("shift").play();
				}
			}
			for(int j = 0; j < ends[0].length; j++) {
				End end = ends[0][j];
				float x2 = end.getx();
				float y2 = end.gety();
				int w2 = end.getWidth();
				int h2 = end.getHeight();
				if(x1 < x2 + w2 &&
					x1 + w1 > x2 &&
					y1 < y2 + h2 &&
					h1 + y1 > y2) {
					shapes.remove(i);
					i--;
					if(shape.getNumSides() == end.getNumSides()) {
						int currentNumSides = end.getNumSides();
						int newNumSides = getRandomNumSides();
						while(currentNumSides == newNumSides) {
							newNumSides = getRandomNumSides();
						}
						ends[0][j] = ends[1][j];
						ends[0][j].setFront();
						explosions.add(new Explosion(Res.getAtlas("pack").findRegion("endbg"), shape.getx(), shape.gety(), 200, 0.5f));
						ends[1][j] = new End(newNumSides, xs[j]);
						ends[1][j].setBack();
						ends[1][j].sety(-50);
						score++;
						if(score % 5 == 0) {
							level++;
							interval *= 0.9f;
							Shape.incrementSpeed();
							Res.getSound("level").play();
						}
						Res.getSound("match").play();
						break;
					}
					else {
						health.hit(5);
						explosions.add(new Explosion(Res.getAtlas("pack").findRegion("shape" + shape.getNumSides()), shape.getx(), shape.gety(), 200, 0.5f));
						Res.getSound("miss").play();
					}
				}
			}
		}
		for(int i = 0; i < shots.size(); i++) {
			Shot shot = shots.get(i);
			shot.update(dt);
		}
		for(int row = 0; row < ends.length; row++) {
			for(int col = 0; col < ends[0].length; col++) {
				End end = ends[row][col];
				end.update(dt);
			}
		}
		for(int i = 0; i < explosions.size(); i++) {
			Explosion explosion = explosions.get(i);
			explosion.update(dt);
		}
		
		
		laser.update(dt);
		
		if(health.getHealth() <= 0) {
			Res.getMusic().stop();
			gsm.set(new GameOverState(gsm, score));
		}
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		sb.draw(bg, 0, 0);
		
		for(Shape shape : shapes) {
			shape.render(sb);
		}
		for(int row = 0; row < ends.length; row++) {
			for(int col = 0; col < ends[0].length; col++) {
				End end = ends[row][col];
				end.render(sb);
			}
		}
		health.render(sb);
		laser.render(sb);
		scoreFont.draw(sb, String.valueOf(score), Vars.WIDTH - 47, 36);
		scoreFont.draw(sb, String.valueOf(level), Vars.WIDTH - 47, Vars.HEIGHT - 11);
		for(Shot shot : shots) {
			shot.render(sb);
		}
		for(Explosion explosion : explosions) {
			explosion.render(sb);
		}
		
		sb.end();
		
	}
	
	@Override
	public boolean mouseMoved(int x, int y) {
		setMouse(m, x, y, cam);
		laser.setPosition(m.y);
		return true;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int p) {
		setMouse(m, x, y, cam);
		laser.setPosition(m.y);
		return true;
	}
	
	@Override
	public boolean touchDown(int x, int y, int p, int b) {
		if(laser.shoot()) {
			shots.add(new Shot(laser.getType(), laser.getx(), laser.gety()));
			Res.getSound("laser").play();
		}
		return true;
	}
	
	@Override
	public boolean keyDown(int k) {
		if(k == Keys.NUM_1 && laser.getType() == Type.MINUS) {
			laser.setType(Type.PLUS);
			Res.getSound("switch").play();
		}
		else if(k == Keys.NUM_2 && laser.getType() == Type.PLUS) {
			laser.setType(Type.MINUS);
			Res.getSound("switch").play();
		}
		return true;
	}
	
}