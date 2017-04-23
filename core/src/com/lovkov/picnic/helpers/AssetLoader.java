package com.lovkov.picnic.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture;

    public static TextureRegion tableCloth;
    public static TextureRegion swatter;
    public static Animation flyAnimation;
    public static TextureRegion fly, flyUp, flyDown;
    public static TextureRegion sandwich, cake;
    public static BitmapFont font, shadow;

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        fly = new TextureRegion(texture, 0, 0, 66, 48);
        fly.flip(false, true);

        flyUp = new TextureRegion(texture, 66, 0, 66, 48);
        flyUp.flip(false, true);

        flyDown = new TextureRegion(texture, 132, 0, 66, 48);
        flyDown.flip(false, true);

        TextureRegion[] flies = { flyUp, flyDown };
        flyAnimation = new Animation(0.06f, flies);
        flyAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        tableCloth = new TextureRegion(texture, 0, 48, 420, 60);
        tableCloth.flip(false, true);

        sandwich = new TextureRegion(texture, 0, 108, 300, 70);
        sandwich.flip(false, true);
        cake = new TextureRegion(texture, 300, 108, 300, 70);
        cake.flip(false, true);

        swatter = new TextureRegion(texture, 0, 178, 500, 100);
        swatter.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
        font.getData().setScale(1.25f, -1.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(1.25f, -1.25f);
    }

    public static void dispose() {
        texture.dispose();
        font.dispose();
        shadow.dispose();
    }
}
