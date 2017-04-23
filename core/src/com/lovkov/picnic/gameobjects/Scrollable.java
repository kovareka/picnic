package com.lovkov.picnic.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {
    Vector2 position;
    private Vector2 velocity;
    int width;
    int height;
    private boolean isScrolledLeft;
    private float scrollSpeed;

    Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        this.isScrolledLeft = false;
        this.scrollSpeed = scrollSpeed;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    void scroll() {
        velocity.x = scrollSpeed;
    }

    void stop() {
        velocity.x = 0;
    }

    void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    boolean isScrolledLeft() {
        return isScrolledLeft;
    }
}
