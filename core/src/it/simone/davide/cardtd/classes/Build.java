package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

public class Build extends Image {

    private int attackRange, attackSpeed, damage;
    private Texture texture, bulletTexture;
    private Enemy target = null;
    private List<Bullet> bulletList = new ArrayList<>();
    private Rectangle attackRangeRect;
    private Stage stage;
    private float time = 0;

    public Build(Texture texture, Texture bulletTexture, int attackRange, int attackSpeed, Stage stage, int damage, int x, int y) {
       super(texture);

        this.texture = texture;
        this.bulletTexture = bulletTexture;
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.stage = stage;
        this.damage = damage;

        place(x, y);
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
            if (target.isDead()) {
                target = null;

            } else if (time > attackSpeed) {

                time = 0;
                Bullet b = new Bullet(bulletTexture, new Vector2((int) (getX() + getWidth() / 2), (int) (getY() + getHeight() / 2)), new Vector2(target.getX(), target.getY()), 10);
                stage.addActor(b);
                bulletList.add(b);
            }

        }

    }

    public Rectangle getAttackRangeRect() {
        return attackRangeRect;
    }

    public void hitEnemies(List<Enemy> enemies) {

        for (Bullet b : bulletList) {
            if (!b.hasCollided) {
                for (Enemy e : enemies) {

                    if (b.getRectangle().overlaps(e.getRectangle())) {
                        e.damage(damage);
                        b.hasCollided = true;
                    }

                }
            }

        }
    }
}
