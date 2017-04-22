package com.lovkov.picnic.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lovkov.picnic.gameobjects.Fly;
import com.lovkov.picnic.gameobjects.Food;
import com.lovkov.picnic.gameobjects.ScrollHandler;
import com.lovkov.picnic.gameobjects.TableCloth;
import com.lovkov.picnic.helpers.AssetLoader;

public class GameRenderer {
    private GameWorld world;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    //Game Objects
    private Fly fly;
    private ScrollHandler scroller;
    private TableCloth tableCloth1, tableCloth2, tableCloth3;
    private Food food1, food2;

    // Assets
    private TextureRegion tableClothTexture;
    private Animation flyAnimation;
    private TextureRegion flyMid, food;

    private SpriteBatch batcher;

    public GameRenderer(GameWorld world) {
        this.world = world;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(true, 800, 400);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
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
        batcher.draw(food, food1.getX(), food1.getY(), food1.getWidth(), food1.getHeight());
        batcher.draw(food, food2.getX(), food2.getY(), food2.getWidth(), food2.getHeight());
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(169/255.0f, 214/255.0f, 183/255.0f, 1f);
        shapeRenderer.rect(0, 0, 800, 400);
        shapeRenderer.end();

        batcher.begin();

        drawTableCloth();

        drawFood();

        if (fly.isFlies()) {
            batcher.draw((TextureRegion) flyAnimation.getKeyFrame(runTime),
                    fly.isFlip() ? fly.getX()+ fly.getWidth() : fly.getX(),
                    fly.getY(), fly.isFlip() ? - fly.getWidth() : fly.getWidth(),
                    fly.getHeight());
        } else {
            batcher.draw(flyMid, fly.isFlip() ? fly.getX()+ fly.getWidth() : fly.getX(),
                    fly.getY(), fly.isFlip() ? - fly.getWidth() : fly.getWidth(),
                    fly.getHeight());
        }

        AssetLoader.shadow.draw(batcher, "" + world.getScore(), 0, 0);
        AssetLoader.font.draw(batcher, "" + world.getScore(), 0, 0);

        batcher.end();

        /*
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(fly.getRectangle().x + 2, fly.getRectangle().y + 2,
                fly.getRectangle().width, fly.getRectangle().height);
        shapeRenderer.rect(food1.getRectangle().x, food1.getRectangle().y,
                food1.getRectangle().width, food1.getRectangle().height);
        shapeRenderer.rect(food2.getRectangle().x, food2.getRectangle().y,
                food2.getRectangle().width, food2.getRectangle().height);
        shapeRenderer.end();
         */
    }

    private void initGameObjects() {
        this.fly = world.getFly();
        this.scroller = world.getScroller();
        this.tableCloth1 = scroller.getTableCloth1();
        this.tableCloth2 = scroller.getTableCloth2();
        this.tableCloth3 = scroller.getTableCloth3();
        this.food1 = scroller.getFood1();
        this.food2 = scroller.getFood2();
    }

    private void initAssets() {
        this.tableClothTexture = AssetLoader.tableCloth;
        this.flyMid = AssetLoader.fly;
        this.flyAnimation = AssetLoader.flyAnimation;
        this.food = AssetLoader.food;
    }
}
