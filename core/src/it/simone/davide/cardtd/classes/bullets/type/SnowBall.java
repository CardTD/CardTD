package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.g2d.Animation;
import it.simone.davide.cardtd.classes.bullets.classes.SingularTargetBullet;

/**
 * This class manages the type of projectile SnowBall
 *
 * @see SingularTargetBullet
 */
public class SnowBall extends SingularTargetBullet {

    /**
     * Create a new SnowBall Bullet
     *
     * @param texture animated texuture of SnowBall bullet
     * @param speed   speed of the bullet
     */
    public SnowBall(Animation texture, float speed) {
        super(texture, speed);
        setSize(33, 26);

    }

    /**
     * Create a shallow copy of the SnowBall bullet
     *
     * @return the shallowed copy
     */
    @Override
    protected Object clone() {
        return new SnowBall(animation, getSpeed());
    }
}