package com.psillicoder.technobrick.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.psillicoder.technobrick.Map.MapManager;
import com.psillicoder.technobrick.TechnoBrickGame;
import com.psillicoder.technobrick.sprites.Ball;
import com.psillicoder.technobrick.sprites.Brick;
import com.psillicoder.technobrick.sprites.Paddle;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hp on 9/11/2016.
 */
public class PlayScreen implements Screen {


    final TechnoBrickGame game;

    OrthographicCamera camera;
    Viewport viewport;
    MapManager mManager;
    //Objects
    Ball playerBall;
    Paddle playerPaddle;
    List<Brick> bricks = new ArrayList<Brick>();


    //Variables

    //Debug
    Boolean debugON = true;


    public PlayScreen(final TechnoBrickGame gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,TechnoBrickGame.V_WIDTH, TechnoBrickGame.V_HEIGHT);
        viewport = new FitViewport(TechnoBrickGame.V_WIDTH,TechnoBrickGame.V_HEIGHT,camera);
        mManager = new MapManager(game, camera);
        bricks = mManager.getBricks();
        playerBall = new Ball(100,100);
        playerPaddle = new Paddle(TechnoBrickGame.V_WIDTH / 2 - 32, 20);
    }

    private void ProcessInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerPaddle.getPos().x >= 0) {
            playerPaddle.setPos((int)playerPaddle.getPos().x - 5, (int)playerPaddle.getPos().y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerPaddle.getPos().x <= TechnoBrickGame.V_WIDTH - 64) {
            playerPaddle.setPos((int)playerPaddle.getPos().x + 5, (int)playerPaddle.getPos().y);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (debugON) {
                debugON = false;
            }else{debugON = true;}
        }
    }


    @Override
    public void show() {

    }

    public void update(float delta) {
        ProcessInput();
        CheckCollision();




        playerBall.update(delta);
        camera.update();
    }


    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);


        mManager.render();


        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        playerBall.render(game.batch);
        playerPaddle.render(game.batch);
        game.batch.end();

        /*for (int i = 0; i < mManager.getBricks().size(); i++)  {
            Brick tBrick = mManager.getBricks().get(i);
            System.out.println("index: " + mManager.getBricks().size() + " x: " + tBrick.getRect().x + " y: " + tBrick.getRect().y + " width: " + tBrick.getRect().width + " height: " + tBrick.getRect().height);


            rShape.begin(ShapeRenderer.ShapeType.Line);
            rShape.setColor(Color.WHITE);
            //rShape.rect(tBrick.getRect().x,tBrick.getRect().y,tBrick.getWidth(),tBrick.getHeight());
            rShape.end();
        }*/

        if (debugON) {
            ShapeRenderer rShape = new ShapeRenderer();
            camera.update();
            rShape.setProjectionMatrix(camera.combined);
            for (Brick tBrick : mManager.getBricks()) {
                rShape.begin(ShapeRenderer.ShapeType.Line);
                rShape.setColor(1, 1, 1, 1);
                rShape.box(tBrick.getRect().x, tBrick.getRect().y, 0, tBrick.getRect().width, tBrick.getRect().height, 0);
                System.out.println(
                        "Shape Added X: " + tBrick.getRect().x +
                                " Y: " + tBrick.getRect().y +
                                " Width: " + tBrick.getRect().width +
                                " Height: " + tBrick.getRect().height);
                rShape.end();
            }
        }

    }



    public void CheckCollision() {
        if (playerBall.getRect().overlaps(playerPaddle.getRect())) {
            int first = (int) playerPaddle.getPos().x;
            int second = (int) playerPaddle.getPos().x + 16;
            int third = (int) playerPaddle.getPos().x + 32;
            int fourth = (int) playerPaddle.getPos().x + 48;


            if (playerBall.getPos().x + 16 > first && playerBall.getPos().x < second) {
                playerBall.setSpeed(new Vector2(playerBall.getSpeed().x * -1, playerBall.getSpeed().y * -1));
            }

            if (playerBall.getPos().x > second && playerBall.getPos().x < third) {
                playerBall.setSpeed(new Vector2(playerBall.getSpeed().x, playerBall.getSpeed().y * -1));
            }

            if (playerBall.getPos().x > third && playerBall.getPos().x < fourth) {
                playerBall.setSpeed(new Vector2(playerBall.getSpeed().x, playerBall.getSpeed().y * -1));
            }

            if (playerBall.getPos().x > fourth && playerBall.getPos().x < third + 16) {
                playerBall.setSpeed(new Vector2(playerBall.getSpeed().x * -1, playerBall.getSpeed().y * -1));
            }
            //playerBall.setSpeed(new Vector2(playerBall.getSpeed().x, playerBall.getSpeed().y * -1));
        }

        for (Brick tBrick : mManager.getBricks()) {
            if (playerBall.getRect().overlaps(tBrick.getRect())) {
                System.out.println("Collision!");
                int ballCenterX = (int)playerBall.getRect().x + (int)playerBall.getRect().getWidth() / 2;
                int ballCenterY = (int)playerBall.getRect().y + (int)playerBall.getRect().getHeight() / 2;

                Rectangle brickTopLeft = new Rectangle(
                        tBrick.getRect().x,
                        tBrick.getRect().y + tBrick.getRect().getHeight() / 2,
                        tBrick.getRect().getWidth(),
                        tBrick.getRect().getHeight()
                        );

                Rectangle brickTopRight = new Rectangle(
                        tBrick.getRect().x + tBrick.getRect().getWidth() / 2,
                        tBrick.getRect().y + tBrick.getRect().getHeight() / 2,
                        tBrick.getRect().getWidth() / 2,
                        tBrick.getRect().getHeight() / 2
                );

                Rectangle brickBottomLeft = new Rectangle(
                        tBrick.getRect().x,
                        tBrick.getRect().y,
                        tBrick.getRect().getWidth() / 2,
                        tBrick.getRect().getHeight() / 2
                );

                Rectangle brickBottomRight = new Rectangle(
                        tBrick.getRect().x + tBrick.getRect().getWidth() / 2,
                        tBrick.getRect().y,
                        tBrick.getRect().getWidth() / 2,
                        tBrick.getRect().getHeight() / 2
                );

                //Ball collides with top left of brick
                if (playerBall.getRect().overlaps(brickTopLeft)) {
                    if (ballCenterX > brickTopLeft.x && ballCenterY > brickTopLeft.y + brickTopLeft.height) {
                        playerBall.setSpeed(new Vector2(playerBall.getSpeed().x, playerBall.getSpeed().y * -1));
                    } else if (ballCenterY < brickTopLeft.y + brickTopLeft.height && ballCenterX < brickTopLeft.x) {
                        playerBall.setSpeed(new Vector2(playerBall.getSpeed().x * -1, playerBall.getSpeed().y));
                    }
                }

                //Ball collides with top right of brick
                if (playerBall.getRect().overlaps(brickTopRight)) {
                    if (ballCenterX < brickTopRight.x + brickTopRight.width && ballCenterY > brickTopRight.y + brickTopRight.height) {
                        playerBall.setSpeed(new Vector2(playerBall.getSpeed().x, playerBall.getSpeed().y * -1));
                    }else if (ballCenterY < brickTopRight.y + brickTopRight.height && ballCenterX > brickTopRight.x + brickTopRight.width) {
                        playerBall.setSpeed(new Vector2(playerBall.getSpeed().x * -1, playerBall.getSpeed().y));
                    }
                }
                //Ball collides with bottom left of brick
                if (playerBall.getRect().overlaps(brickBottomLeft)) {
                    if (ballCenterX > brickBottomLeft.x && ballCenterY < brickBottomLeft.y) {
                        playerBall.setSpeed(new Vector2(playerBall.getSpeed().x,playerBall.getSpeed().y * -1));
                    }else if (ballCenterX < brickBottomLeft.x && ballCenterY > brickBottomLeft.y) {
                        playerBall.setSpeed(new Vector2(playerBall.getSpeed().x * -1, playerBall.getSpeed().y));
                    }
                }

                //Ball collides with bottom right of brick
                if (playerBall.getRect().overlaps(brickBottomRight)) {
                    if (ballCenterX < brickBottomRight.x + brickBottomRight.width && ballCenterY < brickBottomRight.y) {
                        playerBall.setSpeed(new Vector2(playerBall.getSpeed().x, playerBall.getSpeed().y * - 1));
                    }else if (ballCenterX > brickBottomRight.x + brickBottomRight.width && ballCenterY > brickBottomRight.y) {
                        playerBall.setSpeed(new Vector2(playerBall.getSpeed().x * -1, playerBall.getSpeed().y));
                    }
                }

                }
            }
        }





    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.update();
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
        game.batch.dispose();

    }
}
