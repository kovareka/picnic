package com.lovkov.picnic;

import com.badlogic.gdx.Game;
import com.lovkov.picnic.helpers.AssetLoader;
import com.lovkov.picnic.screens.GameScreen;

public class PicnicGame extends Game {
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
