package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.bullets.classes.PiercingBullet;

/**
 * This class manages the type of projectile Axe
 *
 * @see PiercingBullet
 */
public class Axe extends PiercingBullet {

    /**
     * Create a new Axe Bullet
     *
     * @param texture texture of Axe
     * @param speed   speed of Axe
     */
    public Axe(Texture texture, float speed) {
        super(texture, speed);
        setSize(20, 20);

    }

    /**
     * Rotation movement to the Axe Bullet
     *
     * @param delta Time in seconds since the last frame.
     * @see com.badlogic.gdx.scenes.scene2d.ui.Image#act(float)
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        rotateBy(25);

    }

    /**
     * Create a shallow copy of the Axe bullet
     *
     * @return the shallowed copy
     */
    @Override
    protected Object clone() {
        return new Axe(getTexture(), getSpeed());
    }
}