package com.distraction.ld35.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.distraction.ld35.LD35;
import com.distraction.ld35.Vars;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Vars.WIDTH;
		config.height = Vars.HEIGHT;
		config.title = Vars.TITLE;
		new LwjglApplication(new LD35(), config);
	}
}
