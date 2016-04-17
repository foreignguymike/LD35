package com.distraction.ld35;

public class Vars {
	
	public static final String TITLE = "Blocko";
	public static final int WIDTH = 240;
	public static final int HEIGHT = 400;
	public static float scale = 1;
	
	public static final int MIN_SIDES = 3;
	public static final int MAX_SIDES = 6;
	public static int BOTTOM_PADDING = 70;
	
	public static float getWidth() {
		return WIDTH * scale;
	}
	
	public static float getHeight() {
		return HEIGHT * scale;
	}
	
}
