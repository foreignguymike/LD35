package com.distraction.ld35;

public class Vars {
	
	public static final String TITLE = "TBD";
	public static final int WIDTH = 240;
	public static final int HEIGHT = 400;
	public static float scale = 1;
	
	public static float getWidth() {
		return WIDTH * scale;
	}
	
	public static float getHeight() {
		return HEIGHT * scale;
	}
	
}
