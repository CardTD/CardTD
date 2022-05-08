package it.simone.davide.cardtd.classes.enemies;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyState;

public class StormHead extends Enemy {
    public StormHead(int hp, int damage, int speed, int moneyonkill, int attackDimension) {
        super(hp, damage, speed, moneyonkill, attackDimension);

    }

    @Override
    public Enemy clone() {
        return new StormHead(getHp(), getDamage(), getSpeed(), getMoneyonkill(), getAttackDimension());
    }

    @Override
    public void loadAnimations() {
        int rows = 9, cols = 1;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.StormHeadBorIDLE), rows, cols, EnemyState.IDLE);

        rows = 10;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.StormHeadRun), rows, cols, EnemyState.RUN);

        rows = 21;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.StormHeadAttack), rows, cols, EnemyState.ATTACK);

        rows = 9;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.StormHeadDeath), rows, cols, EnemyState.DYING);

        rows = 2;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.StormHeadDamaged), rows, cols, EnemyState.DAMAGED);

        setCurrentState(EnemyState.RUN);
    }

    @Override
    public float getHeight() {
        return 36;
    }

    @Override
    public float getWidth() {
        return 80;
    }

    @Override
    public float getFrameWidth() {
        return 119;
    }
}
