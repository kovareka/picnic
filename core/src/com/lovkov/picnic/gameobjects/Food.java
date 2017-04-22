package com.lovkov.picnic.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Food extends Scrollable {
    private Rectangle rectangle;
    private boolean isScored = false;

    public Food(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        this.rectangle = new Rectangle();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        rectangle.set(position.x, position.y, width, height);
    }

    @Override
    void reset(float newX) {
        super.reset(newX);
        isScored = false;
    }

    boolean collides(Fly fly) {
        return position.x < fly.getX() + fly.getWidth() && Intersector.overlaps(fly.getRectangle(), rectangle);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean scored) {
        isScored = scored;
    }
}
