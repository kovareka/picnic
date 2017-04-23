package com.lovkov.picnic.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Swatter {
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle rectangle;
    private boolean isFlip;
    private boolean isShow;
    private boolean isActive;
    private long time;
    private Random r;

    private int width, height;

    public Swatter(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.isFlip = false;
        this.isShow = false;
        this.isActive = false;
        this.rectangle = new Rectangle();
        this.r = new Random();
    }

    public void update(float delta) {
        if (isActive()) {
            if (!isShow() && time <= System.currentTimeMillis()) {
                isShow = true;
            }

            if (isShow() && time + 300 <= System.currentTimeMillis()) {
                attack();
                position.add(velocity.cpy().scl(delta));
                rectangle.set(isFlip ? position.x + 309 : position.x, position.y + 67, 191, 31);
            }
        }
    }

    private void attack() {
        velocity.y = 100;
    }

    public boolean collides(Fly fly) {
        return Intersector.overlaps(fly.getRectangle(), rectangle);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isFlip() {
        return isFlip;
    }

    public boolean isShow() {
        return isShow;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active, float x) {
        isActive = active;
        if (active) {
            time = System.currentTimeMillis() + r.nextInt(4) * 1000;
            position.x = x - 95;
            if (!isFlip() && position.x > 300) {
                isFlip = true;
                position.x = position.x - width + 191;
            } else if (isFlip() && position.x < 500) {
                isFlip = false;
                position.x = position.x + width - 191;
            }
        } else {
            isShow = false;
            isFlip = false;
            position.x = 0;
            position.y = 0;
            velocity.y = 0;
            time = 0;
            rectangle = new Rectangle();
        }
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
}
