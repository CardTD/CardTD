package it.simone.davide.cardtd.classes.enemies;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyState;

/**
 * This class manages the enemy SpiritBoxer
 *
 * @see Enemy
 */
public class SpiritBoxer extends Enemy {

    /**
     * Create a new enemy SpiritBoxer
     *
     * @param hp              the hp of the enemy
     * @param damage          the damage it does to the main tower
     * @param speed           the speed of the enemy
     * @param moneyonkill     the money returned to his death
     * @param attackDimension the size (in pixels) of its attack range
     */
    public SpiritBoxer(int hp, int damage, int speed, int moneyonkill, int attackDimension) {
        super(hp, damage, speed, moneyonkill, attackDimension);
    }

    /**
     * Create a shallow copy of the SpiritBoxer enemy
     *
     * @return the shallowed copy
     */
    @Override
    public Enemy clone() {
        return new SpiritBoxer(getHp(), getDamage(), getSpeed(), getMoneyonkill(), getAttackDimension());
    }

    /**
     * Loading the enemy’s animations (frame by frame)
     */
    @Override
    public void loadAnimations() {
        int rows = 4, cols = 1;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerIDLE), rows, cols, EnemyState.IDLE);

        rows = 6;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerRun), rows, cols, EnemyState.RUN);

        rows = 10;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerAttack), rows, cols, EnemyState.ATTACK);

        rows = 8;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerDeath), rows, cols, EnemyState.DYING);

        rows = 4;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SpiritBoxerDamaged), rows, cols, EnemyState.DAMAGED);

        setCurrentState(EnemyState.RUN);
    }

    /**
     * Returns the height (in pixels) of the enemy
     *
     * @return the height (in pixels) of the enemy
     */
    @Override
    public float getHeight() {
        return 26;
    }

    /**
     * Returns the width (in pixels) of the enemy
     *
     * @return the width (in pixels) of the enemy
     */
    @Override
    public float getWidth() {
        return 36;
    }

    /**
     * Return of the size of the entire frame of the enemy’s animation
     *
     * @return of the size of the entire frame of the enemy’s animation
     */
    @Override
    public float getFrameWidth() {
        return 137;
    }
}
