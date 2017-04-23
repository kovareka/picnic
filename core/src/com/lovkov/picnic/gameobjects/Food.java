package com.lovkov.picnic.gameobjects;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Food extends Scrollable {
    private Rectangle rectangle;
    private boolean isScored = false;
    private Random r;
    private int random;

    public Food(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        this.rectangle = new Rectangle();
        this.r = new Random();
        setRandom();
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
        setRandom();
    }

    private void setRandom() {
        random = r.nextInt(2);
    }

    public int getRandom() {
        return random;
    }

    boolean collides(Swatter swatter) {
        return position.y <= swatter.getY() + swatter.getHeight();
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
