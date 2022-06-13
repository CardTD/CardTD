package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import it.simone.davide.cardtd.StaticVariables;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage the bullet that the structures are going to use
 *
 * @see Image
 */
public abstract class Bullet extends Image {

    /**
     * The texture of the bullet
     */
    private final Texture texture;
    /**
     * The bullet speed set by x and y
     */
    private final Vector2 v2Velocity = new Vector2();

    /**
     * The speed of the bullet
     */
    private final float speed;

    /**
     * The list of alla hitted enemies
     */
    private final List<Enemy> hitted = new ArrayList<>();

    /**
     * Create a new Bullet
     *
     * @param texture the texture of the bullet
     * @param speed   the speed of the bullet
     */
    public Bullet(Texture texture, float speed) {
        super(texture);
        this.texture = texture;
        this.speed = speed;
    }


    /**
     * {@inheritDoc}
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX() + v2Velocity.x, getY() + v2Velocity.y);

        if (getX() < 0 || getY() < 0 || getX() + getWidth() > StaticVariables.SCREEN_WIDTH || getY() + getHeight() > StaticVariables.SCREEN_HEIGHT) {

            remove();

        }

    }

    /**
     * Returns the list of alla hitted enemies
     *
     * @return the list of alla hitted enemies
     */
    public List<Enemy> getHitted() {
        return hitted;
    }

    /**
     * Setting the start position and the end position of the bullet
     *
     * @param position the current position of the bullet
     * @param to       the end position of the bullet
     */
    public void setVelocity(Vector2 position, Vector2 to) {
        setPosition(position.x, position.y);

        // The .set() is setting the distance from the starting position to end position
        v2Velocity.set(to.x - position.x, to.y - position.y);
        v2Velocity.nor(); // Normalizes the value to be used

        v2Velocity.x *= speed;  // Set speed of the object
        v2Velocity.y *= speed;

        int touchDegree = (int) (new Vector2(position.x, position.y).sub(new Vector2(to.x, to.y))).angleDeg();
        rotateBy(touchDegree);
    }

    /**
     * Check if enemies have been hit by the structure
     *
     * @param enemies the list of the enemies
     * @return the sum of money
     */
    public abstract int hitEnemies(List<Enemy> enemies, int damage);

    /**
     * Check if the enemy can be hitted by bullet
     *
     * @param enemy the enemy that be checked
     * @return if the enemy can be hitted by bullet
     */
    public boolean canHit(Enemy enemy) {

        return !(enemy.getX() < 0) && !(enemy.getY() < 0) && !(enemy.getX() + enemy.getWidth() > StaticVariables.SCREEN_WIDTH) && !(enemy.getY() + enemy.getHeight() > StaticVariables.SCREEN_HEIGHT);
    }

    /**
     * Returns the outlines of the Rectangle of the bullet
     *
     * @return the outlines of the Rectangle of the bullet
     */
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), texture.getWidth(), texture.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract Object clone();

    /**
     * Get the texture of the bullet
     *
     * @return the texture of the bullet
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Get the speed of the bullet
     *
     * @return the speed of the bullet
     */
    public float getSpeed() {
        return speed;
    }


}
