package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Build;
import it.simone.davide.cardtd.classes.Bullet;
import it.simone.davide.cardtd.classes.Card;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.bullets.type.Axe;
import it.simone.davide.cardtd.classes.bullets.type.FireBall;
import it.simone.davide.cardtd.classes.bullets.type.RockBullet;
import it.simone.davide.cardtd.classes.bullets.type.SnowBall;
import it.simone.davide.cardtd.classes.enemies.SpiritBoxer;
import it.simone.davide.cardtd.classes.enemies.StormHead;
import it.simone.davide.cardtd.classes.enemies.ToasterBot;
import it.simone.davide.cardtd.enums.BuildType;
import it.simone.davide.cardtd.enums.BulletType;
import it.simone.davide.cardtd.enums.CardType;
import it.simone.davide.cardtd.enums.EnemyType;

import java.util.HashMap;
import java.util.Map;

/**
 * The instantiated game objects. Ready for use through the clone method
 *
 * @see Object#clone()
 */
public class GameObjects {

    /**
     * All the card of the game
     */
    public static Map<CardType, Card> CARDS;

    /**
     * All the enemies of the game
     */
    public static Map<EnemyType, ? super Enemy> ENEMIES;

    /**
     * All the buildings of the game
     */
    public static Map<BuildType, Build> BUILDINGS;

    /**
     * All the bullets of the game
     */
    public static Map<BulletType, ? super Bullet> BULLETS;

    /**
     * Instantiates all the game projects
     */
    public GameObjects() {

        ENEMIES = new HashMap<>();
        ENEMIES.put(EnemyType.ToasterBot, new ToasterBot(5, 1, 50, 200, 240));
        ENEMIES.put(EnemyType.StrongToasterBot, new ToasterBot(20, 1, 55, 200, 240));

        ENEMIES.put(EnemyType.StormHead, new StormHead(15, 10, 100, 200, 56));
        ENEMIES.put(EnemyType.SpiritBoxer, new SpiritBoxer(15, 10, 80, 200, 100));

        BULLETS = new HashMap<>();

        BULLETS.put(BulletType.ROCK, new RockBullet(CardTDGame.assetManager.<Texture>get(StaticVariables.ROCK), 10));
        BULLETS.put(BulletType.FIRE, new FireBall(CardTDGame.loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.FIREBALLSHEETS), 6, 1), 5));
        BULLETS.put(BulletType.SNOW, new SnowBall(CardTDGame.loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SNOWBALLSHEETS), 6, 1), 5));
        BULLETS.put(BulletType.AXE, new Axe((CardTDGame.assetManager.<Texture>get(StaticVariables.AXE)), 10));

        BUILDINGS = new HashMap<>();
        BUILDINGS.put(BuildType.FIRETOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.FIRETOWERPLACED), BulletType.FIRE, 200, 1f, 5, 0, 0));
        BUILDINGS.put(BuildType.ELLETTROTOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.ELETTROTOWERPLACED), BulletType.ROCK, 200, 1f, 3, 0, 0));
        BUILDINGS.put(BuildType.LASERTOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.LASERTOWERPLACED), BulletType.ROCK, 200, 1f, 1, 3, 0));
        BUILDINGS.put(BuildType.SNOWTOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.SNOWTOWERPLACED), BulletType.SNOW, 200, 1f, 1, 5, 0));
        BUILDINGS.put(BuildType.DESERTTOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.DESERTTOWERPLACED), BulletType.AXE, 200, 1f, 1, 3, 0));

        CARDS = new HashMap<>();
        CARDS.put(CardType.FIRE_TOWER, new Card("FireTower", CardTDGame.assetManager.<Texture>get(StaticVariables.FIRETOWERCARD), BuildType.FIRETOWER, 100));
        CARDS.put(CardType.ELETTRO_TOWER, new Card("ElettroTower", CardTDGame.assetManager.<Texture>get(StaticVariables.ELETTROTOWERCARD), BuildType.ELLETTROTOWER, 250));
        CARDS.put(CardType.LASER_TOWER, new Card("LaserTower", CardTDGame.assetManager.<Texture>get(StaticVariables.LASERTOWERCARD), BuildType.LASERTOWER, 500));
        CARDS.put(CardType.SNOW_TOWER, new Card("SnowTower", CardTDGame.assetManager.<Texture>get(StaticVariables.SNOWTOWERCARD), BuildType.SNOWTOWER, 400));
        CARDS.put(CardType.DESERT_TOWER, new Card("DesertTower", CardTDGame.assetManager.<Texture>get(StaticVariables.DESERTTOWERCARD), BuildType.DESERTTOWER, 350));

    }

    /**
     * Returns a card by its name
     *
     * @param name the name of a card
     * @return the specified card or {@code null}
     */
    public static Card getCardByName(String name) {
        for (Card c : CARDS.values()) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

}
