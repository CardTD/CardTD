package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.bullets.classes.PiercingBullet;

/**
 * This class manages the type of projectile RockBullet
 *
 * @see PiercingBullet
 */
public class RockBullet extends PiercingBullet {

    /**
     * Create a new RockBullet Bullet
     *
     * @param texture texuture of RockBullet bullet
     * @param speed   speed of the bullet
     */
    public RockBullet(Texture texture, float speed) {
        super(texture, speed);
        setSize(20, 20);


    }

    /**
     * Create a shallow copy of the FireBall bullet
     *
     * @return the shallowed copy
     */
    @Override
    protected Object clone() {
        return new RockBullet(getTexture(), getSpeed());
    }
}
