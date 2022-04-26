package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.enums.BulletType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Build extends Image {

    private final float attackRange;
    private final float attackSpeed;
    private final Texture texture;
    private Enemy target = null;
    private final List<Bullet> bulletList = new ArrayList<>();
    private Circle attackRangeCircle;
    private final Bullet bullet;
    private final BulletType bulletType;
    private float time = 0;
    private boolean isPlaced = false;
    private final int damage;

    public Build(Texture texture, BulletType bulletType, int attackRange, float attackSpeed, int damage, int x, int y) {
        super(texture);
        this.texture = texture;
        this.bulletType = bulletType;
        bullet = (Bullet) GameObjects.BULLETS.get(bulletType).clone();
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;

        this.damage = damage;

        setPosition(x, y);
    }

    //TODO creare le wave
    @Override
    public void setPosition(float x, float y) {
        if (isPlaced)
            return;
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

                    Bullet b = (Bullet) bullet.clone();
                    b.setVelocity(new Vector2((int) (getX() + getWidth() / 2) - bullet.getWidth() / 2, (int) (getY() + getHeight() / 2) - bullet.getHeight() / 2), new Vector2(target.getX() + target.getWidth() / 2, target.getY() + target.getHeight() / 2));
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

        Iterator<Bullet> b = bulletList.iterator();
        while (b.hasNext()) {
            Bullet bullet = b.next();
            bullet.hitEnemies(enemies, damage);

            if (!getStage().getActors().contains(bullet, true)) {
                b.remove();
            }
        }
    }

    public Build clone() {

        return new Build(texture, bulletType, (int) attackRange, attackSpeed, damage, (int) getX(), (int) getY());

    }
}
