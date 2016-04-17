package com.distraction.ld35;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Res {
	
	private static Map<String, TextureAtlas> atlases = new HashMap<String, TextureAtlas>();
	private static Map<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
	
	public static void addAtlas(String s, String path) {
		atlases.put(s, new TextureAtlas(path));
	}
	
	public static TextureAtlas getAtlas(String s) {
		return atlases.get(s);
	}
	
	public static void addFont(String s, String path, int size) {
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(path));
		FreeTypeFontParameter params = new FreeTypeFontParameter();
		params.size = size;
		fonts.put(s, gen.generateFont(params));
		gen.dispose();
	}
	
	public static BitmapFont getFont(String s) {
		return fonts.get(s);
	}
	
}
