package com.lovkov.picnic.gameworld;

import com.lovkov.picnic.gameobjects.Fly;
import com.lovkov.picnic.gameobjects.ScrollHandler;
import com.lovkov.picnic.gameobjects.Swatter;

public class GameWorld {
    private Fly fly;
    private Swatter swatter;
    private ScrollHandler scroller;
    private int score = 0;
    //private long time = System.currentTimeMillis();
    private int level = 1;

    public GameWorld() {
        this.fly = new Fly(33, 195,66, 48);
        this.swatter = new Swatter(0, 0, 500, 100);
        this.scroller = new ScrollHandler(340);
    }

    public void update(float delta) {
        fly.update(delta);
        swatter.update(delta);
        scroller.update(delta);

        if (!scroller.isScroll()) {
            if (fly.isScroll()) {
                fly.setScroll(false);
            }
            fly.collides(scroller.getCurrentFood());

            if (swatter.isActive()) {
                if (swatter.collides(fly)) {
                    fly.setAlive(false);
                } else if (scroller.collides(swatter)) {
                    swatter.setActive(false, 0);
                }
            }

            if (fly.isAlive() && fly.isSitsInFood()) {
                addScore();
                if (!swatter.isActive()) {
                    swatter.setActive(true, fly.getX() + 33);
                }
            }

            if (score >= 1000 * level && !swatter.isActive()) {
                level++;
                scroller.getCurrentFood().setScored(true);
                scroller.scroll();
                fly.setScroll(true);
                swatter.setActive(false, 0);
            }
        }
    }

    private void addScore() {
        /*
        long t = System.currentTimeMillis();
        if (time + 1000 < t) {
            score++;
            time = t;
        }
         */
        score++;
    }

    public Fly getFly(){
        return fly;
    }

    public Swatter getSwatter() {
        return swatter;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }
}
