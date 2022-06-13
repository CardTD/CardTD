package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.enums.EnemyState;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents an enemy
 */
public abstract class Enemy extends Actor implements Cloneable, Damageable {

    /**
     * The animations, one for each state
     */
    private Map<EnemyState, Animation<TextureRegion>> animations;

    /**
     * Current state of the enemy
     */
    private EnemyState currentState;

    /**
     * The region of the enemy
     */
    private TextureRegion currentRegion;

    /**
     * The time that defines the frame of the animations
     */
    private float time = 0f;

    /**
     * Health points of the enemy
     */
    private int hp;

    /**
     * The damage the enemy can inflict
     */
    private final int damage;

    /**
     * The speed of the enemy
     */
    private final int speed;

    /**
     * Money to be added in balance once the enemy is dead
     */
    private final int moneyonkill;

    /**
     * The dimension, in pixels, of the attack
     */
    private final int attackDimension;

    /**
     * If the enemy has been removed
     */
    private boolean remove = false;

    /**
     * The path of the enemy
     */
    private Path path;

    /**
     * The center of the enemy
     */
    private Vector2 center = new Vector2(0, 0);

    /**
     * If the enemy texture is flipped
     */
    private boolean isFlipped = false;

    /**
     * Create a new enemy
     *
     * @param hp Health points of the enemy
     * @param damage The damage the enemy can inflict
     * @param speed The speed of the enemy
     * @param moneyonkill Money to be added in balance once the enemy is dead
     * @param attackDimension The dimension, in pixels, of the attack
     */
    public Enemy(int hp, int damage, int speed, int moneyonkill, int attackDimension) {

        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.moneyonkill = moneyonkill;
        animations = new HashMap<>();
        this.attackDimension = attackDimension;
        loadAnimations();

    }

    /**
     * Returns the center of the enemy
     * @return the center of the enemy
     */
    public Vector2 getCenter() {
        return center;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(float x) {
        super.setX(x);
        center.x = getX() + getFrameWidth() / 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(float y) {
        super.setY(y);
        center.y = getY() + getHeight() / 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if (isFlipped)
            center.set(getX() - getWidth() / 2 + getFrameWidth(), getY() + getHeight() / 2);
        else
            center.set(getX() + getWidth() / 2, getY() + getHeight() / 2);

    }

    /**
     * Flip the enemy animation
     *
     * @param newX the new coordinate x
     * @param newY the new coordinate y
     */
    public void flip(float newX, float newY) {

        isFlipped = !isFlipped;

        for (Map.Entry<EnemyState, Animation<TextureRegion>> i : animations.entrySet()) {

            for (TextureRegion f : i.getValue().getKeyFrames()) {

                f.flip(true, false);

            }

        }
        setPosition(newX, newY);

    }

    /**
     * Change the state of the enemy
     *
     * @param currentState the new state of the enemy
     */
    public void setCurrentState(EnemyState currentState) {

        if (!currentState.equals(this.currentState)) {
            time = 0f;

            this.currentState = currentState;
        }

    }

    /**
     * Set the path of the enemy
     *
     * @param path the path of the enemy
     */
    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if (currentState != null)
            switch (currentState) {
                case IDLE:

                    currentRegion = animations.get(EnemyState.IDLE).getKeyFrame(time, true);
                    break;
                case RUN:

                    path.move(delta, this);

                    currentRegion = animations.get(EnemyState.RUN).getKeyFrame(time, true);
                    break;
                case DYING:
                    if (animations.get(EnemyState.DYING).isAnimationFinished(time)) {
                        remove();

                        remove = true;

                    }

                    currentRegion = animations.get(EnemyState.DYING).getKeyFrame(time, true);

                    break;

                case ATTACK:
                    if (animations.get(EnemyState.ATTACK).isAnimationFinished(time)) {

                        Level.HEALTHBAR.damage(damage);
                        time = 0;
                    }

                    currentRegion = animations.get(EnemyState.ATTACK).getKeyFrame(time, true);
                    break;

                case DAMAGED:
                    if (animations.get(EnemyState.DAMAGED).isAnimationFinished(time)) {
                        if (!isDead())
                            setCurrentState(EnemyState.RUN);
                    }
                    path.move(delta, this);

                    currentRegion = animations.get(EnemyState.DAMAGED).getKeyFrame(time, false);

                    break;

            }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
        if (currentRegion != null) {

            batch.draw(currentRegion, getX(), getY());
        }

    }

    /**
     * Gets the overlay of the enemy rectangle
     *
     * @return the overlay of the enemy rectangle
     */
    public Rectangle getRectangle() {

        return new Rectangle(center.x - getHeight() / 2, center.y - getHeight() / 2, getHeight(), getHeight());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean damage(float damage) {

        int oldHp = hp;
        hp -= damage;
        if (oldHp > 0 && hp <= 0) {

            die();

            return true;
        } else {

            if (currentState.equals(EnemyState.RUN)) {

                setCurrentState(EnemyState.DAMAGED);
            }
        }
        return false;
    }

    /**
     * Check if the enemy is dead
     *
     * @return if the enemy is dead
     */
    public boolean isDead() {
        return currentState.equals(EnemyState.DYING);
    }

    /**
     * Loads the animation of the enemy
     *
     * @param texture the texture of the enemy
     * @param rows the rows of the animated tile
     * @param cols the cols of the animated tile
     * @param enemyState the enemy state (Es. Dying)
     */
    public void loadAnimation(Texture texture, int rows, int cols, EnemyState enemyState) {

        animations.put(enemyState, CardTDGame.loadAnimation(texture, rows, cols));
    }

    /**
     * Sets che current state of the enemy to DYING
     */
    public void die() {

        setCurrentState(EnemyState.DYING);

        hp = -1;
    }

    /**
     * Checks if it can remove the enemy
     *
     * @return if it can remove the enemy
     */
    public boolean canRemove() {
        return remove;
    }

    /**
     * Return the shallow copy of the enemy
     *
     * @return the shallowed copy of the enemy
     */
    @Override
    public abstract Enemy clone();

    /**
     * Loads the animation of the enemy
     */
    public abstract void loadAnimations();

    /**
     * Gets the real height of the enemy
     *
     * @return the real height of the enemy
     */
    @Override
    public abstract float getHeight();

    /**
     * Gets the real width of the enemy
     *
     * @return the real width of the enemy
     */
    @Override
    public abstract float getWidth();

    /**
     * Gets all frame with (in pixels) of the enemy
     *
     * @return
     */
    public abstract float getFrameWidth();

    /**
     * Gets the hp of the enemy
     *
     * @return the hp of the enemy
     */
    public int getHp() {
        return hp;
    }

    /**
     * Gets the damage the enemy does to the tower
     *
     * @return the damage the enemy does to the tower
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gets the speed of the enemy
     *
     * @return the speed of the enemy
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets the money on death of enemy
     *
     * @return the money on death of enemy
     */
    public int getMoneyonkill() {
        return moneyonkill;
    }

    /**
     * Gets the attack dimension of the enemy
     *
     * @return the attack dimension of the enemy
     */
    public int getAttackDimension() {
        return attackDimension;
    }

}
