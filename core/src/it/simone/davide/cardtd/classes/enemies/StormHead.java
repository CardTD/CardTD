package it.simone.davide.cardtd.classes.enemies;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyState;

/**
 * This class manages the enemy StormHead
 *
 * @see Enemy
 */
public class StormHead extends Enemy {

    /**
     * Create a new enemy StormHead
     *
     * @param hp              the hp of the enemy
     * @param damage          the damage it does to the main tower
     * @param speed           the speed of the enemy
     * @param moneyonkill     the money returned to his death
     * @param attackDimension the size (in pixels) of its attack range
     */
    public StormHead(int hp, int damage, int speed, int moneyonkill, int attackDimension) {
        super(hp, damage, speed, moneyonkill, attackDimension);

    }

    /**
     * Create a shallow copy of the StormHead enemy
     *
     * @return the shallowed copy
     */
    @Override
    public Enemy clone() {
        return new StormHead(getHp(), getDamage(), getSpeed(), getMoneyonkill(), getAttackDimension());
    }

    /**
     * Loading the enemy’s animations (frame by frame)
     */
    @Override
    public void loadAnimations() {
        int rows = 9, cols = 1;

        loadAnimation(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.STORMHEAD_IDLE), rows, cols, EnemyState.IDLE);

        rows = 10;

        loadAnimation(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.STORMHEAD_RUN), rows, cols, EnemyState.RUN);

        rows = 21;

        loadAnimation(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.STORMHEAD_ATTACK), rows, cols, EnemyState.ATTACK);

        rows = 9;

        loadAnimation(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.STORMHEAD_DEATH), rows, cols, EnemyState.DYING);

        rows = 2;

        loadAnimation(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.STORMHEAD_DAMAGED), rows, cols, EnemyState.DAMAGED);

        setCurrentState(EnemyState.RUN);
    }

    /**
     * Returns the height (in pixels) of the enemy
     *
     * @return the height (in pixels) of the enemy
     */
    @Override
    public float getHeight() {
        return 36;
    }

    /**
     * Returns the width (in pixels) of the enemy
     *
     * @return the width (in pixels) of the enemy
     */
    @Override
    public float getWidth() {
        return 105;
    }

    /**
     * Return of the size of the entire frame of the enemy’s animation
     *
     * @return of the size of the entire frame of the enemy’s animation
     */
    @Override
    public float getFrameWidth() {
        return 115;
    }
}
