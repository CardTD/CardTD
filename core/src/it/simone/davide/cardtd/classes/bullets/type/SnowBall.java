package it.simone.davide.cardtd.classes.bullets.type;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.bullets.classes.SnowBallBullet;

public class SnowBall extends SnowBallBullet {

    public SnowBall(Texture texture, float speed) {
        super(texture, speed);
        setSize(20, 20);

    }

    @Override
    protected Object clone() {
        return new SnowBall(getTexture(), getSpeed());
    }
}