package com.psillicoder.technobrick.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by hp on 9/11/2016.
 */
public class Brick extends Sprite {

    private Rectangle rect;
    public boolean isAlive;

    private short type;

    final static int WIDTH = 32;
    final static int HEIGHT = 16;
    final static int left = 0;
    final static int right = 16;

    public Brick(short typeIndex, int x, int y) {
        this.type = typeIndex;
        rect = new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public Rectangle getRect() {
        return rect;
    }



}
