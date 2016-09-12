package com.psillicoder.technobrick.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.psillicoder.technobrick.TechnoBrickGame;

/**
 * Created by hp on 9/11/2016.
 */
public class StartScreen implements Screen {

    final TechnoBrickGame game;

    OrthographicCamera camera;

    public StartScreen(TechnoBrickGame gam) {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 620);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "TechnoBrick", camera.viewportWidth / 2 - 30, camera.viewportHeight / 2 + 50);
        game.font.draw(game.batch, "Click to Start", camera.viewportWidth / 2 - 30, camera.viewportHeight / 2 + 30);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
