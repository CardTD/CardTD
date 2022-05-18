package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import it.simone.davide.cardtd.classes.Build;
import sun.security.provider.certpath.Vertex;

import java.util.ArrayList;
import java.util.List;

public class TileManager {

    private final ArrayList<Polygon> obstacles;
    private final ArrayList<Polygon> path;
    private final Rectangle deck;
    private Polygon toProtect;
    private final List<Build> placed;

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

    boolean isOverlap(Polygon A, Rectangle B){

      if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x, B.y))
          return true;

        if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x+B.width, B.y))
            return true;

        if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x, B.y+B.height))
            return true;

        if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x+B.width, B.y+B.height))
            return true;

        if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x + B.getWidth()/2 , B.y))
            return true;

        if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x, B.y + B.getHeight()/2))
            return true;

        if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x + B.getWidth()/2, B.y + B.getHeight()/2))
            return true;

        if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x + B.getWidth(), B.y + B.getHeight()/2))
            return true;

        if( Intersector.isPointInPolygon(A.getTransformedVertices(),0,A.getTransformedVertices().length,B.x + B.getWidth()/2, B.y + B.getHeight()))
            return true;

        return false;
    }

    public boolean canPlace(Rectangle r) {
        if (r.x < 0 || r.y < 0 || r.x + r.width > StaticVariables.SCREEN_WIDTH || r.y + r.height > StaticVariables.SCREEN_HEIGHT)
            return false;



        for (Polygon i : path) {
            if (isOverlap(i,r)) {
                return false;

            }
        }

        for (Polygon i : obstacles) {

            if (isOverlap(i,r)) {
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
        if (isOverlap(toProtect,r)) {
            return false;

        }

        return true;
    }

    public Polygon getToProtect() {
        return toProtect;
    }
}
