package com.lovkov.picnic.gameworld;

import com.lovkov.picnic.gameobjects.Fly;
import com.lovkov.picnic.gameobjects.ScrollHandler;

public class GameWorld {
    private Fly fly;
    private ScrollHandler scroller;
    private int score = 0;
    private long time = System.currentTimeMillis();

    public GameWorld() {
        this.fly = new Fly(33, 195,66, 48);
        this.scroller = new ScrollHandler(340);
    }

    public void update(float delta) {
        fly.update(delta);
        scroller.update(delta);
        fly.collides(scroller.getFood1());
        if (fly.isSitsInFood()) {
            addScore();
        }
    }

    public void addScore() {
        long t = System.currentTimeMillis();
        if (time + 1000 < t) {
            score++;
            time = t;
        }
    }

    public Fly getFly(){
        return fly;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }
}
