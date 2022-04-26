package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Build;
import it.simone.davide.cardtd.classes.Bullet;
import it.simone.davide.cardtd.classes.Card;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.bullets.SingularTargetBullet;
import it.simone.davide.cardtd.classes.enemies.ToasterBot;
import it.simone.davide.cardtd.enums.BuildType;
import it.simone.davide.cardtd.enums.BulletType;
import it.simone.davide.cardtd.enums.CardType;
import it.simone.davide.cardtd.enums.EnemyType;

import java.util.HashMap;
import java.util.Map;

public class GameObjects {

    public static Map<CardType, Card> CARDS;
    public static Map<EnemyType, ? super Enemy> ENEMIES;

    public static Map<BuildType, Build> BUILDINGS;

    public static Map<BulletType, Bullet> BULLETS;

    public GameObjects() {

        ENEMIES = new HashMap<>();
        ENEMIES.put(EnemyType.ToasterBot, new ToasterBot(1, 1, 50, 200, 240));
        ENEMIES.put(EnemyType.StrongToasterBot, new ToasterBot(5, 1, 55, 200, 240));

        BULLETS = new HashMap<>();

        BULLETS.put(BulletType.BLACK_CIRCLE, new SingularTargetBullet(CardTDGame.assetManager.<Texture>get(StaticVariables.BLACK_CIRCLE), 20));

        BUILDINGS = new HashMap<>();
        BUILDINGS.put(BuildType.TOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.PLACEDTOWER_PNG), BulletType.BLACK_CIRCLE, 200, 1f, 1, 0, 0));

        CARDS = new HashMap<>();
        CARDS.put(CardType.TOWER, new Card("tower", CardTDGame.assetManager.<Texture>get(StaticVariables.TOWER), BuildType.TOWER));
        CARDS.put(CardType.TOWER1, new Card("towerq", CardTDGame.assetManager.<Texture>get(StaticVariables.TOWER), BuildType.TOWER));
        CARDS.put(CardType.TOWER2, new Card("tower2", CardTDGame.assetManager.<Texture>get(StaticVariables.TOWER), BuildType.TOWER));
        CARDS.put(CardType.TOWER3, new Card("tower3", CardTDGame.assetManager.<Texture>get(StaticVariables.TOWER), BuildType.TOWER));

    }

    public static Card getCardByName(String name) {
        for (Card c : CARDS.values()) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

}
