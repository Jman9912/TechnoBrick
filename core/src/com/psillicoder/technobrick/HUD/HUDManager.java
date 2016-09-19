package com.psillicoder.technobrick.HUD;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by hp on 9/18/2016.
 */
public class HUDManager {

    BitmapFont font;
    OrthographicCamera hudCam;

    int gameScore = 00000;
    int gameTime = 0;

    private float screenWidth;
    private float screenHeight;

    public HUDManager(float hScreenWidth, float hScreenHeight) {
        screenWidth = hScreenWidth;
        screenHeight = hScreenHeight;
        hudCam = new OrthographicCamera(screenWidth,screenHeight);
        font = new BitmapFont();
    }

    public void render(SpriteBatch batch) {

       font.draw(batch, "Score: " + gameScore, 10f , screenHeight - 25 );

    }

    public void addScore(int scoreToAdd) {
        gameScore += scoreToAdd;
    }

}
