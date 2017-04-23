package com.lovkov.picnic.gameworld;

import com.lovkov.picnic.gameobjects.Fly;
import com.lovkov.picnic.gameobjects.ScrollHandler;
import com.lovkov.picnic.gameobjects.Swatter;
import com.lovkov.picnic.helpers.AssetLoader;

public class GameWorld {
    private Fly fly;
    private Swatter swatter;
    private ScrollHandler scroller;
    private int score = 0;
    private int level = 1;
    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld() {
        this.fly = new Fly(33, 195,66, 48);
        this.swatter = new Swatter(0, 0, 500, 100);
        this.scroller = new ScrollHandler(340);
        this.currentState = GameState.READY;
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateRunning(float delta) {
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
                    AssetLoader.hit.play(0.2f);
                    fly.setAlive(false);
                    currentState = GameState.GAMEOVER;

                    if (score > AssetLoader.getHighScore()) {
                        AssetLoader.setHighScore(score);
                        currentState = GameState.HIGHSCORE;
                    }
                } else if (scroller.collides(swatter)) {
                    swatter.setActive(false, 0);
                    AssetLoader.hit.play(0.2f);
                }
            }

            if (fly.isAlive()) {
                if (fly.isSitsInFood()) {
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
    }

    private void addScore() {
        score++;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public void start() {
        currentState = GameState.RUNNING;
        scroller.scroll();
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        fly.onRestart();
        swatter.setActive(false, 0);
        scroller.onRestart();
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
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
