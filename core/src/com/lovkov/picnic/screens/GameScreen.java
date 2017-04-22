package com.lovkov.picnic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.lovkov.picnic.gameworld.GameRenderer;
import com.lovkov.picnic.gameworld.GameWorld;
import com.lovkov.picnic.helpers.InputHandler;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen() {
        this.world = new GameWorld();
        this.renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler(world.getFly()));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
