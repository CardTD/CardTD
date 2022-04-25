package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import it.simone.davide.cardtd.classes.Build;

import java.util.ArrayList;
import java.util.List;

public class TileManager {

    private ArrayList<Rectangle> obstacles, path;
    private Rectangle deck, toProtect;
    private List<Build> placed;

    public TileManager(TiledMap tiledMap, List<Build> placed) {
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

        for (MapObject m : t) {
            if (m instanceof RectangleMapObject) {
                Rectangle r = ((RectangleMapObject) m).getRectangle();
                r = new Rectangle(r.x, r.y, r.width, r.height);
                toProtect = r;

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
        shapeRenderer.rect(toProtect.x, toProtect.y, toProtect.width, toProtect.height);
        shapeRenderer.end();

    }

    public boolean canPlace(Rectangle r) {

        for (Rectangle i : path) {
            if (i.overlaps(r)) {

                return false;

            }
        }

        for (Rectangle i : obstacles) {

            if (i.overlaps(r)) {

                return false;

            }
        }

        for (Build p : placed) {

            Rectangle i = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            if (p.isPlaced() && i.overlaps(r)) {

                return false;

            }
        }
        if (deck.overlaps(r)) {

            return false;

        }
        if (toProtect.overlaps(r)) {

            return false;

        }

        return true;
    }

    public Rectangle getToProtect() {
        return toProtect;
    }
}
