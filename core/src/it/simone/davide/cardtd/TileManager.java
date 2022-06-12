package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import it.simone.davide.cardtd.classes.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * TileManager manages the game map
 */
public class TileManager {

    /**
     * The list of obstacles inside the map
     */
    private final ArrayList<Polygon> obstacles;

    /**
     * The list of paths inside the map
     */
    private final ArrayList<Polygon> path;

    /**
     * The place where the cards are on the screen
     */
    private final Rectangle deck;

    /**
     * The place where there is the structure to be defended on screen
     */
    private Polygon toProtect;

    /**
     * The list of all placed buildings
     */
    private final List<Build> placed;

    /**
     * Create a new TileManager
     *
     * @param tiledMap the tiled map
     * @param placed the list contains all placed buildings
     */
    public TileManager(TiledMap tiledMap, List<Build> placed) {
        this.placed = placed;
        MapObjects t = tiledMap.getLayers().get("Path").getObjects();
        path = new ArrayList<>();
        for (MapObject m : t) {
            if (m instanceof PolygonMapObject) {
                Polygon r = ((PolygonMapObject) m).getPolygon();
                r = new Polygon(r.getTransformedVertices());
                path.add(r);

            }

        }

        t = tiledMap.getLayers().get("torre").getObjects();

        for (MapObject m : t) {
            if (m instanceof PolygonMapObject) {
                Polygon r = ((PolygonMapObject) m).getPolygon();
                r = new Polygon(r.getTransformedVertices());
                toProtect = r;

            }

        }

        t = tiledMap.getLayers().get("Ostacoli").getObjects();
        obstacles = new ArrayList<>();
        for (MapObject m : t) {
            if (m instanceof PolygonMapObject) {
                Polygon r = ((PolygonMapObject) m).getPolygon();
                r = new Polygon(r.getTransformedVertices());
                obstacles.add(r);

            }

        }
        deck = new Rectangle(0, 0, 480, 180);

    }

    /**
     * Render the border of all elements in the map
     *
     * @param shapeRenderer the {@link ShapeRenderer} of the screen
     */
    public void render(ShapeRenderer shapeRenderer) {

        for (Polygon r : obstacles) {

            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.polygon(r.getVertices());
            shapeRenderer.end();

        }

        for (Polygon r : path) {

            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.polygon(r.getVertices());

            shapeRenderer.end();

        }
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.rect(deck.x, deck.y, deck.width, deck.height);
        shapeRenderer.polygon(toProtect.getVertices());
        shapeRenderer.end();

    }

    /**
     * Checks if a rectangle is overlapping a polygon
     *
     * @param polygon a polygon
     * @param cardRect the rectangle
     * @return if a rectangle is overlapping a polygon
     */
    boolean isOverlap(Polygon polygon, Rectangle cardRect) {

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x, cardRect.y))
            return true;

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x + cardRect.width, cardRect.y))
            return true;

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x, cardRect.y + cardRect.height))
            return true;

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x + cardRect.width, cardRect.y + cardRect.height))
            return true;

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x + cardRect.getWidth() / 2, cardRect.y))
            return true;

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x, cardRect.y + cardRect.getHeight() / 2))
            return true;

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x + cardRect.getWidth() / 2, cardRect.y + cardRect.getHeight() / 2))
            return true;

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x + cardRect.getWidth(), cardRect.y + cardRect.getHeight() / 2))
            return true;

        if (Intersector.isPointInPolygon(polygon.getTransformedVertices(), 0, polygon.getTransformedVertices().length, cardRect.x + cardRect.getWidth() / 2, cardRect.y + cardRect.getHeight()))
            return true;

        return false;
    }

    /**
     * Checks if a build can be placed in the game map
     *
     * @param r the rectangle of the build
     * @return if a build can be placed in the game map
     */
    public boolean canPlace(Rectangle r) {
        if (r.x < 0 || r.y < 0 || r.x + r.width > StaticVariables.SCREEN_WIDTH || r.y + r.height > StaticVariables.SCREEN_HEIGHT)
            return false;

        for (Polygon i : path) {
            if (isOverlap(i, r)) {
                return false;

            }
        }

        for (Polygon i : obstacles) {

            if (isOverlap(i, r)) {
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
        if (isOverlap(toProtect, r)) {
            return false;

        }

        return true;
    }

    /**
     * Returns the polygon of the structure to be defended on screen
     *
     * @return the polygon of the structure to be defended on screen
     */
    public Polygon getToProtect() {
        return toProtect;
    }
}
