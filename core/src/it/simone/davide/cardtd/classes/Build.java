package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public class Build extends Image {

    private float attackRange, attackSpeed, damage;
    private Texture texture, bulletTexture;
    private Enemy target = null;
    private List<Bullet> bulletList = new ArrayList<>();
    private Circle attackRangeCircle;

    private float time = 0;
    private boolean isPlaced = false;

    public Build(Texture texture, Texture bulletTexture, int attackRange, float attackSpeed, int damage, int x, int y) {
        super(texture);
        debug();
        this.texture = texture;
        this.bulletTexture = bulletTexture;
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;

        this.damage = damage;

        setPosition(x, y);
    }

    @Override
    public void setPosition(float x, float y) {
        x = (int) x / 5 * 5;
        y = (int) y / 5 * 5;
        super.setPosition(x, y);
        attackRangeCircle = new Circle((int) (x + getWidth() / 2), (int) (y + getHeight() / 2), attackRange);

    }

    public Rectangle getRectangle() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void place() {
        isPlaced = true;
    }

    public void setTarget(Enemy target) {
        if (this.target == null) {
            this.target = target;
        }
    }

    @Override
    public void act(float delta) {
        if (isPlaced) {
            super.act(delta);
            time += delta;

            if (target != null && target.isDead()) {
                target = null;
            }

            if (target != null && !Intersector.overlaps(getAttackRangeCircle(), target.getRectangle())) {
                target = null;

            }
            if (target != null) {
                if (time > attackSpeed) {

                    time = 0;
                    Bullet b = new Bullet(bulletTexture, new Vector2((int) (getX() + getWidth() / 2) - bulletTexture.getWidth() / 2, (int) (getY() + getHeight() / 2) - bulletTexture.getHeight() / 2), new Vector2(target.getX() + target.getWidth() / 2, target.getY() + target.getHeight() / 2), 20);
                    getStage().addActor(b);
                    bulletList.add(b);

                }

            }

        }

    }

    public Circle getAttackRangeCircle() {
        return attackRangeCircle;
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

    public Build clone() {

        return new Build(texture, bulletTexture, (int) attackRange, attackSpeed, (int) damage, (int) getX(), (int) getY());

    }
}
