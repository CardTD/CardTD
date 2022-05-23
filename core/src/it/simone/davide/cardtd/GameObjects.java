package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.*;
import it.simone.davide.cardtd.classes.bullets.type.Axe;
import it.simone.davide.cardtd.classes.bullets.type.RockBullet;
import it.simone.davide.cardtd.classes.bullets.type.FireBall;
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

public class GameObjects {

    public static Map<CardType, Card> CARDS;
    public static Map<EnemyType, ? super Enemy> ENEMIES;

    public static Map<BuildType, Build> BUILDINGS;

    public static Map<BulletType,? super Bullet> BULLETS;

    public GameObjects() {

        ENEMIES = new HashMap<>();
        ENEMIES.put(EnemyType.ToasterBot, new ToasterBot(1, 1, 50, 200, 240));
        ENEMIES.put(EnemyType.StrongToasterBot, new ToasterBot(5, 1, 55, 200, 240));

        ENEMIES.put(EnemyType.StormHead, new StormHead(2, 10, 100, 200, 56));
        ENEMIES.put(EnemyType.SpiritBoxer, new SpiritBoxer(2, 10, 80, 200, 100));

        BULLETS = new HashMap<>();

        BULLETS.put(BulletType.ROCK, new RockBullet(CardTDGame.assetManager.<Texture>get(StaticVariables.ROCK), 10));
        BULLETS.put(BulletType.FIRE, new FireBall(CardTDGame.loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.FIREBALLSHEETS), 6,1), 5));
        BULLETS.put(BulletType.SNOW, new SnowBall(CardTDGame.loadAnimation(CardTDGame.assetManager.<Texture>get(StaticVariables.SNOWBALLSHEETS), 6 ,1), 1));
        BULLETS.put(BulletType.AXE, new Axe((CardTDGame.assetManager.<Texture>get(StaticVariables.AXE)), 10));

        BUILDINGS = new HashMap<>();
        BUILDINGS.put(BuildType.FIRETOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.FIRETOWERPLACED), BulletType.FIRE, 200, 1f, 1, 0, 0));
        BUILDINGS.put(BuildType.ELLETTROTOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.ELETTROTOWERPLACED), BulletType.ROCK, 200, 1f, 1, 0, 0));
        BUILDINGS.put(BuildType.LASERTOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.LASERTOWERPLACED), BulletType.ROCK, 200, 1f, 1, 0, 0));
        BUILDINGS.put(BuildType.SNOWTOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.SNOWTOWERPLACED), BulletType.SNOW, 200, 1f, 1, 0, 0));
        BUILDINGS.put(BuildType.DESERTTOWER, new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.DESERTTOWERPLACED), BulletType.AXE, 200, 1f, 1, 0, 0));

        CARDS = new HashMap<>();
        CARDS.put(CardType.FIRE_TOWER, new Card("FireTower", CardTDGame.assetManager.<Texture>get(StaticVariables.FIRETOWERCARD), BuildType.FIRETOWER,100));
        CARDS.put(CardType.ELETTRO_TOWER, new Card("ElettroTower", CardTDGame.assetManager.<Texture>get(StaticVariables.ELETTROTOWERCARD), BuildType.ELLETTROTOWER,250));
        CARDS.put(CardType.LASER_TOWER, new Card("LaserTower", CardTDGame.assetManager.<Texture>get(StaticVariables.LASERTOWERCARD), BuildType.LASERTOWER,500));
        CARDS.put(CardType.SNOW_TOWER, new Card("SnowTower", CardTDGame.assetManager.<Texture>get(StaticVariables.SNOWTOWERCARD), BuildType.SNOWTOWER,400));
        CARDS.put(CardType.DESERT_TOWER, new Card("DesertTower", CardTDGame.assetManager.<Texture>get(StaticVariables.DESERTTOWERCARD), BuildType.DESERTTOWER,350));


    }

    public static Card getCardByName(String name) {
        for (Card c : CARDS.values()) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

}
