package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Bullet extends Image {

    private Texture texture;
    private Vector2 v2Position, v2Velocity = new Vector2(), to;
    private float speed;
    private boolean hasCollided = false;
    private List<Enemy> hitted = new ArrayList<>();

    public Bullet(Texture texture, float speed) {
        super(texture);
        this.texture = texture;
        this.speed = speed;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX() + v2Velocity.x, getY() + v2Velocity.y);

    }

    public List<Enemy> getHitted() {
        return hitted;
    }

    public void setVelocity(Vector2 position, Vector2 to) {
        this.v2Position = position;
        setPosition(position.x, position.y);

        this.to = to;
// The .set() is setting the distance from the starting position to end position
        v2Velocity.set(to.x - v2Position.x, to.y - v2Position.y);
        v2Velocity.nor(); // Normalizes the value to be used

        v2Velocity.x *= speed;  // Set speed of the object
        v2Velocity.y *= speed;
    }

    public abstract boolean hitEnemies(List<Enemy> enemies, int damage);

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), texture.getWidth(), texture.getHeight());
    }

    @Override
    protected abstract Object clone();

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getV2Position() {
        return v2Position;
    }

    public Vector2 getV2Velocity() {
        return v2Velocity;
    }

    public Vector2 getTo() {
        return to;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isHasCollided() {
        return hasCollided;
    }
}
