package com.lovkov.picnic.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lovkov.picnic.helpers.AssetLoader;
import com.lovkov.picnic.helpers.Direction;

public class Fly {
    private Vector2 position;
    private Vector2 velocity;
    private boolean flip;
    private boolean flies;
    private boolean isSitsInFood;
    private boolean isAlive;
    private boolean isScroll;
    private Vector2 startPosition;

    private Rectangle rectangle;

    private int width;
    private int height;

    public Fly(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2(x, y);
        this.startPosition = new Vector2(x, y);
        this.velocity = new Vector2(0,0);
        this.rectangle = new Rectangle();
        this.flies = true;
        this.isSitsInFood = false;
        this.isAlive = true;
        this.isScroll = false;
    }

    public void update(float delta) {
        if (!isScroll) {
            if (position.x <= 0) {
                position.x = 0;
            }

            if (position.x >= 730) {
                position.x = 730;
            }

            if (position.y <= 0) {
                position.y = 0;
            }

            if (position.y >= 292) {
                position.y = 292;
                flies = false;
                velocity.y = 0;
            }

            if (isAlive()) {
                position.add(velocity.cpy().scl(delta));

                rectangle.set(position.x, position.y, 62, 44);
            }
        } else {
            Vector2 currentPosition = position;
            float elapsed = 0.01f;

            float distance = Vector2.dst(position.x, position.y, startPosition.x, startPosition.y);
            float directionX = (startPosition.x - position.x) / distance;
            float directionY = (startPosition.y - position.y) / distance;

            position.x += directionX * 400 * elapsed;
            position.y += directionY * 400 * elapsed;
            if (Vector2.dst(position.x, position.y, currentPosition.x, currentPosition.y) >= distance - 5) {
                position.x = startPosition.x;
                position.y = startPosition.y;
                isScroll = false;
            }
        }

        if (isFlies() && isAlive()) {
            AssetLoader.flap.loop(0.5f);
        } else {
            AssetLoader.flap.stop();
        }
    }

    public void move(Direction direction, boolean isMove) {
        if (isAlive) {
            switch (direction) {
                case UP:
                    if (isFlies()) velocity.y = isMove ? -200 : 0;
                    break;
                case DOWN:
                    if (isFlies()) velocity.y = isMove ? 200 : 0;
                    break;
                case RIGHT:
                    velocity.x = isMove ? 200 : 0;
                    flip = false;
                    break;
                case LEFT:
                    velocity.x = isMove ? -200: 0;
                    flip = true;
                    break;
            }
        }
    }

    public void collides(Food food) {
        if (position.x + width >= food.getX() &&
                position.x < 250 && food.getX() < 260 &&
                position.y + height > food.getY() + 5) {
            position.x = food.getX() - width;
        }

        if (position.x <= food.getTailX() &&
                position.x > 550 && position.y + height > food.getY()) {
            position.x = food.getTailX();
        }

        if (position.y + height >= food.getY() &&
                position.x + width > food.getX() && position.x < food
                .getTailX()) {
            position.y = food.getY() - height;
            flies = false;
            isSitsInFood = true;
            velocity.y = 0;
        }

        if (!flies && position.y + height >= food.getY() &&
                position.x + width > food.getX() && position.x < food
                .getTailX()) {
            if (position.x <= food.getX()) {
                position.x = food.getX();
            } else if (position.x + width >= food.getTailX()) {
                position.x = food.getTailX() - width;
            }
        }
    }

    public void onRestart() {
        position.x = startPosition.x;
        position.y = startPosition.y;
        velocity.x = 0;
        velocity.y = 0;
        isAlive = true;
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    public boolean isScroll() {
        return isScroll;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isFlies() {
        return flies;
    }

    public void setFlies(boolean flies) {
        if (isAlive()) {
            this.flies = flies;
            position.y -= 10;
            isSitsInFood = false;
        }
    }

    public boolean isFlip() {
        return flip;
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

    public boolean isSitsInFood() {
        return isSitsInFood;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
