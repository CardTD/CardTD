package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import it.simone.davide.cardtd.classes.bullets.classes.SingularTargetBullet;

public class SnowBall extends SingularTargetBullet {

    public SnowBall(Animation texture, float speed) {
        super(texture, speed);
        setSize(33, 26);

    }

    @Override
    protected Object clone() {
        return new SnowBall(animation, getSpeed());
    }
}