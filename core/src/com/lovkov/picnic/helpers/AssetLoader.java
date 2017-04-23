package com.lovkov.picnic.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    private static Texture texture;

    private static Preferences prefs;

    public static TextureRegion bg;
    public static TextureRegion tableCloth;
    public static TextureRegion swatter;
    public static Animation flyAnimation;
    public static TextureRegion fly, flyUp, flyDown;
    public static TextureRegion sandwich, cake, mud;
    public static BitmapFont font, shadow, fontHeaders, headersShadow;
    public static Sound flap, hit;

    public static void load() {
        prefs = Gdx.app.getPreferences("Picnic");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 30, 348, 800, 340);
        bg.flip(false, true);

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
        cake = new TextureRegion(texture, 420, 38, 300, 70);
        cake.flip(false, true);
        mud = new TextureRegion(texture, 300, 108, 300, 70);
        mud.flip(false, true);

        swatter = new TextureRegion(texture, 0, 178, 500, 100);
        swatter.flip(false, true);

        font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
        font.getData().setScale(1f, -1f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(1f, -1f);
        fontHeaders = new BitmapFont(Gdx.files.internal("data/headers.fnt"));
        fontHeaders.getData().setScale(1.5f, -1.5f);
        headersShadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        headersShadow.getData().setScale(1.5f, -1.5f);

        flap = Gdx.audio.newSound(Gdx.files.internal("data/fly.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("data/hit.wav"));
    }

    public static void setHighScore(int score) {
        prefs.putInteger("highScore", score);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        texture.dispose();
        font.dispose();
        shadow.dispose();
        fontHeaders.dispose();
        headersShadow.dispose();
        flap.dispose();
        hit.dispose();
    }
}
