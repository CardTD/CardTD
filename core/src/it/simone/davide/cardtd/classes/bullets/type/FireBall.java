package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.g2d.Animation;
import it.simone.davide.cardtd.classes.bullets.classes.SingularTargetBullet;

/**
 * This class manages the type of projectile Fireball
 *
 * @see SingularTargetBullet
 */
public class FireBall extends SingularTargetBullet {

    /**
     * Create a new FireBall Bullet
     *
     * @param texture animated texuture of FireBall bullet
     * @param speed   speed of the bullet
     */
    public FireBall(Animation texture, float speed) {
        super(texture, speed);
        setSize(40, 20);

    }

    /**
     * Create a shallow copy of the FireBall bullet
     *
     * @return the shallowed copy
     */
    @Override
    protected Object clone() {
        return new FireBall(animation, getSpeed());
    }
}

