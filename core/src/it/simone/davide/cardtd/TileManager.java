package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class TileManager {

    private TiledMap tiledMap;

    private ArrayList<Rectangle> obstacles, toProtect, path;
    private Rectangle selected;

    public TileManager() {

    }

    public void load(String filename) {
        this.tiledMap = new TmxMapLoader().load(filename);

        MapObjects t = tiledMap.getLayers().get("percorso").getObjects();
        path = new ArrayList<>();
        for (MapObject m : t) {
            if (m instanceof RectangleMapObject) {
                Rectangle r = ((RectangleMapObject) m).getRectangle();
                r = new Rectangle(r.x, r.y, r.width, r.height);
                path.add(r);

            }

        }

        t = tiledMap.getLayers().get("toprotect").getObjects();
        toProtect = new ArrayList<>();
        for (MapObject m : t) {
            if (m instanceof RectangleMapObject) {
                Rectangle r = ((RectangleMapObject) m).getRectangle();
                r = new Rectangle(r.x, r.y, r.width, r.height);
                toProtect.add(r);

            }

        }

        t = tiledMap.getLayers().get("ostacoli").getObjects();
        obstacles = new ArrayList<>();
        for (MapObject m : t) {
            if (m instanceof RectangleMapObject) {
                Rectangle r = ((RectangleMapObject) m).getRectangle();
                r = new Rectangle(r.x, r.y, r.width, r.height);
                obstacles.add(r);

            }

        }

    }

    public void render(ShapeRenderer shapeRenderer) {

        for (Rectangle r : toProtect) {

            shapeRenderer.begin(ShapeType.Line);

            shapeRenderer.rect(r.x, r.y, r.width, r.height);
            shapeRenderer.end();

        }
        for (Rectangle r : obstacles) {

            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
            shapeRenderer.end();

        }

        for (Rectangle r : path) {

            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
            shapeRenderer.end();

        }

    }

    public boolean canPlace(Rectangle r) {

        for (Rectangle i : path) {

            if (i.overlaps(r)) {
                selected = i;
                return false;

            }
        }

        for (Rectangle i : toProtect) {

            if (i.overlaps(r)) {
                selected = i;
                return false;

            }
        }
        for (Rectangle i : obstacles) {

            if (i.overlaps(r)) {
                selected = i;
                return false;

            }
        }

        selected = null;
        return true;
    }

}
