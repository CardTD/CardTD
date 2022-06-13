package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.enums.BulletType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manage the structure of the level
 *
 * @see Image
 */
public class Build extends Image {

    /**
     * The range attack that the structure will have
     */
    private final float attackRange;

    /**
     * The attack speed that the structure will have
     */
    private final float attackSpeed;

    /**
     * The texture of the structure
     */
    private final Texture texture;

    /**
     * The target that the structure will attack
     */
    private Enemy target = null;

    /**
     * The list of all available bullets
     */
    private final List<Bullet> bulletList = new ArrayList<>();

    /**
     * The range of attack of the structure
     */
    private Circle attackRangeCircle;

    /**
     * The bullet that the structure will use
     */
    private final Bullet bullet;

    /**
     * The type of bullet that the structure will use
     */
    private final BulletType bulletType;

    /**
     * The time the structure will wait before attacking again
     */
    private float time = 0;

    /**
     * Specifies whether the structure is placed or not
     */
    private boolean isPlaced = false;

    /**
     * The damage that the structure will do to the enemy
     */
    private final int damage;

    /**
     * The instance of the build
     */
    private Build instance;

    /**
     * Create a new build
     *
     * @param texture     the texture of the build
     * @param bulletType  the bulletType of the structure is going to use
     * @param attackRange the attackRange of the structure
     * @param attackSpeed the attackSpeed of the structure
     * @param damage      the damage that the structure will do to the enemy
     * @param x           the x position of the structure
     * @param y           the u position of the structure
     */
    public Build(Texture texture, BulletType bulletType, int attackRange, float attackSpeed, int damage, int x, int y) {
        super(texture);
        instance = this;
        this.texture = texture;
        this.bulletType = bulletType;
        bullet = (Bullet) ((Bullet) GameObjects.BULLETS.get(bulletType)).clone();
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;

        this.damage = damage;

        setPosition(x, y);
    }

    /**
     * Setting the position of the structure
     *
     * @param x the x position of the structure
     * @param y the y position of the structure
     */
    @Override
    public void setPosition(float x, float y) {
        if (isPlaced)
            return;
        x = (int) x / 5 * 5;
        y = (int) y / 5 * 5;
        super.setPosition(x, y);
        attackRangeCircle = new Circle((int) (x + getWidth() / 2), (int) (y + getHeight() / 2), attackRange);

    }

    /**
     * Returns the outline of the rectangle of the structure
     *
     * @return the rectangle of the structure
     */
    public Rectangle getRectangle() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Return if structure is placed or not
     *
     * @return if structure is placed or not
     */
    public boolean isPlaced() {
        return isPlaced;
    }

    /**
     * Place the structure on the field
     */
    public void place() {
        isPlaced = true;

        addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                Timer timer = new Timer();
                timer.scheduleTask(new Task() {
                    @Override
                    public void run() {

                        Level.SELECTEDBUILDING = instance;
                    }
                }, 0.01f);

            }
        });

    }

    /**
     * Set the target of the structure
     *
     * @param target the enemy that the structure will attack
     */
    public void setTarget(Enemy target) {
        if (this.target == null) {
            this.target = target;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        if (isPlaced) {
            super.act(delta);
            time += delta;

            if (target != null) {

                if (target.isDead()) {
                    target = null;
                    return;
                }

                if (!Intersector.overlaps(getAttackRangeCircle(), target.getRectangle())) {
                    target = null;
                    return;
                }

                if (time > attackSpeed) {

                    time = 0;

                    Bullet b = (Bullet) bullet.clone();
                    b.setVelocity(new Vector2((int) (getX() + getWidth() / 2) - bullet.getWidth() / 2, (int) (getY() + getHeight() / 2) - bullet.getHeight() / 2), new Vector2(target.getCenter().x, target.getCenter().y));
                    getStage().addActor(b);
                    bulletList.add(b);

                }
            }

        }

    }

    /**
     * Returns the attack range of the circle of structure
     *
     * @return the attack range of the circle of structure
     */
    public Circle getAttackRangeCircle() {
        return attackRangeCircle;
    }

    /**
     * {@inheritDoc}
     */
    public int hitEnemies(List<Enemy> enemies) {
        int somma = 0;
        Iterator<Bullet> b = bulletList.iterator();
        while (b.hasNext()) {
            Bullet bullet = b.next();

            somma += bullet.hitEnemies(enemies, damage);
            if (!getStage().getActors().contains(bullet, true)) {
                b.remove();
            }
        }
        return somma;
    }

    /**
     * Create a shallow copy of the structure
     *
     * @return the shallowed copy
     */
    public Build clone() {

        return new Build(texture, bulletType, (int) attackRange, attackSpeed, damage, (int) getX(), (int) getY());

    }
}
