package com.psillicoder.technobrick.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hp on 9/11/2016.
 */
public class Paddle extends Sprite {

    Texture paddleTexture;
    private int x;
    private int y;


    public Paddle(int tx,int ty) {
        paddleTexture = new Texture(Gdx.files.internal("paddleimg.png"));
        this.x = tx;
        this.y = ty;

    }

    public void render(SpriteBatch batch) {
        batch.draw(paddleTexture, this.x, this.y);
    }

    public Vector2 getPos() {
        return new Vector2(this.x,this.y);
    }

    public void setPos(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, 64, 16);
    }
}
