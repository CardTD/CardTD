package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import it.simone.davide.cardtd.StaticVariables;

import javax.swing.plaf.TextUI;
import java.util.ArrayList;
import java.util.List;

public abstract class Bullet extends Image {

    private final Texture texture;
    private final Vector2 v2Velocity = new Vector2();
    private final float speed;
    private final List<Enemy> hitted = new ArrayList<>();

    public Bullet(Texture texture, float speed) {
        super(texture);
        this.texture = texture;
        this.speed = speed;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX() + v2Velocity.x, getY() + v2Velocity.y);

        if (getX() < 0 || getY() < 0 || getX() + getWidth() > StaticVariables.SCREEN_WIDTH || getY() + getHeight() > StaticVariables.SCREEN_HEIGHT) {

            remove();

        }

    }

    public List<Enemy> getHitted() {
        return hitted;
    }

    public void setVelocity(Vector2 position, Vector2 to) {
        setPosition(position.x, position.y);

        // The .set() is setting the distance from the starting position to end position
        v2Velocity.set(to.x - position.x, to.y - position.y);
        v2Velocity.nor(); // Normalizes the value to be used

        v2Velocity.x *= speed;  // Set speed of the object
        v2Velocity.y *= speed;
    }

    public abstract int hitEnemies(List<Enemy> enemies, int damage);

    public boolean canHit(Enemy enemy) {

        return !(enemy.getX() < 0) && !(enemy.getY() < 0) && !(enemy.getX() + enemy.getWidth() > StaticVariables.SCREEN_WIDTH) && !(enemy.getY() + enemy.getHeight() > StaticVariables.SCREEN_HEIGHT);
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), texture.getWidth(), texture.getHeight());
    }

    @Override
    protected abstract Object clone();

    public Texture getTexture() {
        return texture;
    }


    public float getSpeed() {
        return speed;
    }


}
