package com.lovkov.picnic.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lovkov.picnic.PicnicGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Picnic";
		config.width = 800;
		config.height = 400;
		new LwjglApplication(new PicnicGame(), config);
	}
}
