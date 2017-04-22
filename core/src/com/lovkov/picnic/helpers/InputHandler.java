package com.lovkov.picnic.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.lovkov.picnic.gameobjects.Fly;

public class InputHandler implements InputProcessor {
    private Fly fly;

    public InputHandler(Fly fly) {
        this.fly = fly;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            fly.move(Direction.UP, true);
        } else if (keycode == Input.Keys.DOWN) {
            fly.move(Direction.DOWN, true);
        } else if (keycode == Input.Keys.LEFT) {
            fly.move(Direction.LEFT, true);
        } else if (keycode == Input.Keys.RIGHT) {
            fly.move(Direction.RIGHT, true);
        } else if (keycode == Input.Keys.SPACE) {
            fly.setFlies(true);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) {
            fly.move(Direction.UP, false);
        } else if (keycode == Input.Keys.DOWN) {
            fly.move(Direction.DOWN, false);
        } else if (keycode == Input.Keys.LEFT) {
            fly.move(Direction.LEFT, false);
        } else if (keycode == Input.Keys.RIGHT) {
            fly.move(Direction.RIGHT, false);
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
