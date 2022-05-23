package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import it.simone.davide.cardtd.classes.bullets.classes.SingularTargetBullet;

public class FireBall extends SingularTargetBullet {

    public FireBall(Animation texture, float speed) {
        super(texture, speed);
        setSize(40, 20);

    }

    @Override
    protected Object clone() {
        return new FireBall(animation, getSpeed());
    }
}

