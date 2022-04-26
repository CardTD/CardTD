package it.simone.davide.cardtd.classes.enemies;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyState;

public class ToasterBot extends Enemy {
    public ToasterBot(int hp, int damage, int speed, int moneyonkill, int attackdim) {

        super(hp, damage, speed, moneyonkill, attackdim);
    }

    @Override
    public void loadAnimations() {
        int rows = 1, cols = 5;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorIDLE), rows, cols, EnemyState.IDLE);

        cols = 8;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorRun), rows, cols, EnemyState.RUN);

        cols = 11;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorAttack), rows, cols, EnemyState.ATTACK);

        cols = 8;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorDeath), rows, cols, EnemyState.DYING);

        cols = 2;

        loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.ToasterBorDamaged), rows, cols, EnemyState.DAMAGED);

        setCurrentState(EnemyState.RUN);
    }

    @Override
    public float getHeight() {
        return 66;
    }

    @Override
    public float getWidth() {
        return 100;
    }

    @Override
    public Enemy clone() {
        return new ToasterBot(getHp(), getDamage(), getSpeed(), getMoneyonkill(), getAttackDimension());
    }

}
