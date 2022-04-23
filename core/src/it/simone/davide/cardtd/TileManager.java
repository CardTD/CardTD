package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public class TileManager {

    private ArrayList<Rectangle> obstacles, toProtect, path;
    private Rectangle deck;
    List<Image> placed;

    public TileManager(TiledMap tiledMap, List<Image> placed) {
        this.placed = placed;
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
        deck = new Rectangle(0, 0, 480, 180);

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
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.rect(deck.x, deck.y, deck.width, deck.height);
        shapeRenderer.end();

    }

    public boolean canPlace(Rectangle r) {

        for (Rectangle i : path) {

            if (i.overlaps(r)) {

                return false;

            }
        }

        for (Rectangle i : toProtect) {

            if (i.overlaps(r)) {

                return false;

            }
        }
        for (Rectangle i : obstacles) {

            if (i.overlaps(r)) {

                return false;

            }
        }

        for (Image p : placed) {
            Rectangle i = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            if (i.overlaps(r)) {

                return false;

            }
        }
        if (deck.overlaps(r)) {

            return false;

        }

        return true;
    }

}
