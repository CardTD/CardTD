package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {

    Texture texture;
    protected Vector2 v2Position, v2Velocity= new Vector2(), to;
    float speed;

    public Bullet(Texture texture, Vector2 position, Vector2 to, float speed) {
        this.texture = texture;
        this.v2Position = position;
        setPosition(position.x, position.y);
        this.speed = speed;
        this.to = to;

        setVelocity();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX() + v2Velocity.x, getY() + v2Velocity.y);

    }

    public void setVelocity() {

// The .set() is setting the distance from the starting position to end position
        v2Velocity.set(to.x - v2Position.x, to.y - v2Position.y);
        v2Velocity.nor(); // Normalizes the value to be used

        v2Velocity.x *= speed;  // Set speed of the object
        v2Velocity.y *= speed;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

}
