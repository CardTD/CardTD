package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Build extends Actor {

    int attackRange, attackSpeed;
    Texture texture, bulletTexture;
    Enemy target = null;

    Rectangle attackRangeRect;
    Stage stage;
    float time = 0;

    public Build(Texture texture, Texture bulletTexture, int attackRange, int attackSpeed, Stage stage) {
        this.texture = texture;
        this.bulletTexture = bulletTexture;
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.stage = stage;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY());
    }

    public void place(int x, int y) {
        setPosition(x, y);
        attackRangeRect = new Rectangle((int) (getX() + getWidth() / 2) - attackRange / 2, (int) (getY() + getHeight() / 2) - attackRange / 2, attackRange, attackRange);

    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if (target != null) {
            if (time > attackSpeed) {
                time = 0;
                stage.addActor(new Bullet(bulletTexture, new Vector2((int) (getX() + getWidth() / 2), (int) (getY() + getHeight() / 2)), new Vector2(target.getX(), target.getY()), 2));

            }

        }

    }

    public Rectangle getAttackRangeRect() {
        return attackRangeRect;
    }
}
