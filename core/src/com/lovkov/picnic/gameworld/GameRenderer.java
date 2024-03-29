package com.lovkov.picnic.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lovkov.picnic.gameobjects.*;
import com.lovkov.picnic.helpers.AssetLoader;

public class GameRenderer {
    private static final String TITLE = "RUIN A PICNIC!";
    private static final String START = "Press SPACE for start";
    private static final String GAMEOVER = "Game Over!";
    private static final String TRY_AGAIN = "Press SPACE for restart";
    private static final String CONTROLS = "SPACE - take off, ARROWS - move";

    private GameWorld world;

    //Game Objects
    private Fly fly;
    private Swatter swatter;
    private TableCloth tableCloth1, tableCloth2, tableCloth3;
    private Food food1, food2;

    // Assets
    private TextureRegion tableClothTexture;
    private Animation flyAnimation;
    private TextureRegion flyMid, sandwich, cake, swatterTexture, mud, bg;

    private SpriteBatch batcher;

    public GameRenderer(GameWorld world) {
        this.world = world;
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, 800, 400);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    private void drawTableCloth() {
        batcher.draw(tableClothTexture, tableCloth1.getX(), tableCloth1.getY(),
                tableCloth1.getWidth(), tableCloth1.getHeight());
        batcher.draw(tableClothTexture, tableCloth2.getX(), tableCloth2.getY(),
                tableCloth2.getWidth(), tableCloth2.getHeight());
        batcher.draw(tableClothTexture, tableCloth3.getX(), tableCloth3.getY(),
                tableCloth3.getWidth(), tableCloth3.getHeight());
    }

    private void drawFood() {
        batcher.draw(food1.getRandom() == 1 ? sandwich : cake, food1.getX(), food1.getY(), food1.getWidth(), food1.getHeight());
        batcher.draw(food2.getRandom() == 1 ? sandwich : cake, food2.getX(), food2.getY(), food2.getWidth(), food2.getHeight());
        if (food1.isMud()) {
            batcher.draw(mud, food1.getX(), food1.getY(), food1.getWidth(), food1.getHeight());
        } else if (food2.isMud()) {
            batcher.draw(mud, food2.getX(), food2.getY(), food2.getWidth(), food2.getHeight());
        }
    }

    private void initGameObjects() {
        this.fly = world.getFly();
        this.swatter = world.getSwatter();
        ScrollHandler scroller = world.getScroller();
        this.tableCloth1 = scroller.getTableCloth1();
        this.tableCloth2 = scroller.getTableCloth2();
        this.tableCloth3 = scroller.getTableCloth3();
        this.food1 = scroller.getFood1();
        this.food2 = scroller.getFood2();
    }

    private void initAssets() {
        this.tableClothTexture = AssetLoader.tableCloth;
        this.flyMid = AssetLoader.fly;
        this.swatterTexture = AssetLoader.swatter;
        this.flyAnimation = AssetLoader.flyAnimation;
        this.sandwich = AssetLoader.sandwich;
        this.cake = AssetLoader.cake;
        this.mud = AssetLoader.mud;
        this.bg = AssetLoader.bg;
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();

        batcher.disableBlending();
        batcher.draw(bg, 0, 0, 800, 340);
        batcher.enableBlending();

        drawTableCloth();

        drawFood();

        if (swatter.isShow()) {
            batcher.draw(swatterTexture,
                    swatter.isFlip() ? swatter.getX() + swatter.getWidth() : swatter.getX(),
                    swatter.getY(),
                    swatter.isFlip() ? - swatter.getWidth() : swatter.getWidth(),
                    swatter.getHeight());
        }

        if (!world.isReady()) {
            if (fly.isFlies() && fly.isAlive()) {
                batcher.draw((TextureRegion) flyAnimation.getKeyFrame(runTime),
                        fly.isFlip() ? fly.getX() + fly.getWidth() : fly.getX(),
                        fly.getY(), fly.isFlip() ? - fly.getWidth() : fly.getWidth(),
                        fly.getHeight());
            } else {
                batcher.draw(flyMid, fly.isFlip() ? fly.getX() + fly.getWidth() : fly.getX(),
                        fly.getY(), fly.isFlip() ? - fly.getWidth() : fly.getWidth(),
                        fly.getHeight());
            }
        }

        if (world.isRunning()) {
            AssetLoader.shadow.draw(batcher, String.valueOf(world.getScore()), 0, 0);
            AssetLoader.font.draw(batcher, String.valueOf(world.getScore()), 0, 0);
        }

        if (world.isReady()) {
            AssetLoader.shadow.getData().setScale(1.5f, -1.5f);
            AssetLoader.shadow.draw(batcher, TITLE, 400 - TITLE.length() * 37 / 2, 110);
            AssetLoader.fontHeaders.draw(batcher, TITLE, 400 - TITLE.length() * 37 / 2, 110);
            AssetLoader.shadow.getData().setScale(1f, -1f);

            AssetLoader.shadow.draw(batcher, START, 400 - START.length() * 26 / 2, 190);
            AssetLoader.font.draw(batcher, START, 400 - START.length() * 26 / 2, 190);

            AssetLoader.shadow.getData().setScale(0.5f, -0.5f);
            AssetLoader.font.getData().setScale(0.5f, -0.5f);
            AssetLoader.shadow.draw(batcher, CONTROLS, 400 - CONTROLS.length() * 13 / 2, 300);
            AssetLoader.font.draw(batcher, CONTROLS, 400 - CONTROLS.length() * 13 / 2, 300);
            AssetLoader.shadow.getData().setScale(1f, -1f);
            AssetLoader.font.getData().setScale(1f, -1f);
        } else {
            if (world.isGameOver() || world.isHighScore()) {
                AssetLoader.flap.stop();
                AssetLoader.shadow.draw(batcher, GAMEOVER, 400 - GAMEOVER.length() * 26 / 2, 90);
                AssetLoader.font.draw(batcher, GAMEOVER, 400 - GAMEOVER.length() * 26 / 2, 90);

                String score = "Score: " + world.getScore() + " Top: " + AssetLoader.getHighScore();

                AssetLoader.shadow.draw(batcher, score, 400 - score.length() * 26 / 2, 130);
                AssetLoader.font.draw(batcher, score, 400 - score.length() * 26 / 2, 130);

                AssetLoader.shadow.draw(batcher, TRY_AGAIN, 400 - TRY_AGAIN.length() * 26 / 2, 170);
                AssetLoader.font.draw(batcher, TRY_AGAIN, 400 - TRY_AGAIN.length() * 26 / 2, 170);
            }
        }

        batcher.end();
    }
}
