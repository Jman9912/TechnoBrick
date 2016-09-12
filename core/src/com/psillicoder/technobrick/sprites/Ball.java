package com.psillicoder.technobrick.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.psillicoder.technobrick.TechnoBrickGame;

/**
 * Created by hp on 9/11/2016.
 */
public class Ball extends Sprite {

    Texture ballTexture;
    //SpriteBatch batch;

    private int x;
    private int y;

    private Vector2 ballSpeed = new Vector2(3,3);


    public Ball(int tx, int ty) {
        ballTexture = new Texture(Gdx.files.internal("blueball.png"));
        x = tx;
        y = ty;
    }


    public void update(float delta) {
        if (this.x >= TechnoBrickGame.V_WIDTH - 16 || this.x <= 0) {
            ballSpeed.x = -ballSpeed.x;
        }
        if (this.y >= TechnoBrickGame.V_HEIGHT - 16 || this.y <= 0) {
            ballSpeed.y = -ballSpeed.y;
        }
        this.x += ballSpeed.x;
        this.y += ballSpeed.y;
    }


    public void render(SpriteBatch batch) {
        batch.draw(ballTexture,x,y,16,16);

    }

    public Vector2 getSpeed() {
        return ballSpeed;
    }



    public Vector2 getPos() {
        return new Vector2(this.x, this.y);
    }

    public void setPos(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    public Rectangle getRect() {
        return new Rectangle(this.x,this.y, 16,16);
    }

    public void setSpeed (Vector2 dSpeed) {
        ballSpeed = dSpeed;
    }
}
