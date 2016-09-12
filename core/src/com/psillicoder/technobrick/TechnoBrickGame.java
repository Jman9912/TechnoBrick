package com.psillicoder.technobrick;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psillicoder.technobrick.screens.StartScreen;

public class TechnoBrickGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	//Constant Variables
	public final static int V_WIDTH = 640;
	public final static int V_HEIGHT = 480;
	public final static int TILE_WIDTH = 32;
	public final static int TILE_HEIGHT = 16;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new StartScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
