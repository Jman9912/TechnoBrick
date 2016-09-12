package com.psillicoder.technobrick.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.psillicoder.technobrick.TechnoBrickGame;
import com.psillicoder.technobrick.sprites.Brick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 9/11/2016.
 */
public class MapManager {

    TechnoBrickGame game;
    OrthographicCamera camera;

    OrthogonalTiledMapRenderer mRenderer;
    TiledMap map1;
    TiledMapTileLayer brickLayer;

    private List<Brick> brickList = new ArrayList<Brick>();

    public MapManager(TechnoBrickGame gam, OrthographicCamera cam) {
        this.game = gam;
        camera = cam;
        map1 = new TmxMapLoader().load("level1.tmx");
        mRenderer = new OrthogonalTiledMapRenderer(map1);
        mRenderer.setView(camera);
        //camera.position.set(new Vector2(TechnoBrickGame.V_WIDTH, TechnoBrickGame.V_HEIGHT ),0);
        camera.update();

        setupBricks();
    }

    public void setupBricks() {
        brickLayer =  (TiledMapTileLayer)map1.getLayers().get(1);
        for (int j = 0; j < brickLayer.getWidth(); j++) {
            for (int k = 0; k < brickLayer.getHeight(); k++) {
                //System.out.println("j: " + j + " k: " + k);
                TiledMapTileLayer.Cell cell = brickLayer.getCell(j,k);
                    if (cell != null) {
                        short tempType = (short) cell.getTile().getId();
                        Brick tempBrick = new Brick(tempType, j * 32, k * 16);
                        brickList.add(tempBrick);

                    }else continue;
            }
        }
    }

    public void render() {
        mRenderer.setView(camera);
        mRenderer.render();
    }

    public List<Brick> getBricks() {
        return brickList;
    }
}
