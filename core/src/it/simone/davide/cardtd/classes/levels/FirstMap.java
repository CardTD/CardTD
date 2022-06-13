package it.simone.davide.cardtd.classes.levels;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.Level;
import it.simone.davide.cardtd.classes.MoveVector2;
import it.simone.davide.cardtd.classes.Path;
import it.simone.davide.cardtd.classes.waves.MonsterWave;
import it.simone.davide.cardtd.classes.waves.TitleWave;
import it.simone.davide.cardtd.classes.waves.WaitWave;
import it.simone.davide.cardtd.classes.waves.Waves;
import it.simone.davide.cardtd.enums.EnemyType;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.screens.MainMenu;

/**
 * This class manages the first map of the game
 *
 * @see Level
 */
public class FirstMap extends Level {
    /**
     * Create a new FirstMap
     *
     * @param map      the texture of the map
     * @param tiledmap the tiledmap of the first map
     */
    public FirstMap(Texture map, TiledMap tiledmap) {
        super(map, tiledmap);

        MainMenu.OPTIONS.setMusic((Music) CardTDGame.ASSETSMANAGER.get(StaticVariables.FIRST_MAP_BGMUSIC));

        setShowTiledMapElem(false);
        setShowEnemyCenter(false);
    }

    /**
     * Return all the waves that will be in game
     *
     * @return all the waves
     * @see Waves
     */
    @Override
    public Waves getWaves() {
        return new Waves(this, new TitleWave("Starting Waves", FontType.LOGO), new MonsterWave(EnemyType.SpiritBoxer, 10), new MonsterWave(EnemyType.StormHead, 10), new WaitWave(15, true), new MonsterWave(EnemyType.StormHead, 10));
    }

    /**
     * Return the start position of the enemies
     *
     * @param enemyType the type of enemy defined (es. ToasterBot)
     * @return the start position of the enemies
     */
    @Override
    public Vector2 getStartPostision(EnemyType enemyType) {
        return new Vector2(770, -100);
    }

    /**
     * Return the path enemies will follow in game
     *
     * @param enemyType the type of enemy defined (es. ToasterBot)
     * @return the path enemies will follow in game
     */
    @Override
    public Path getPath(EnemyType enemyType) {
        return new Path(((Enemy) GameObjects.ENEMIES.get(enemyType)).getSpeed(), new MoveVector2(0, 340), new MoveVector2(75, 60), new MoveVector2(100, 43), new MoveVector2(16, 120), new MoveVector2(-106, 51), new MoveVector2(-180, 0), new MoveVector2(-68, 26), new MoveVector2(-100, -100), new MoveVector2(-500, 0));
    }

    /**
     * Check if an enemy are close enough to attack the tower
     *
     * @param enemy the real enemy to check
     * @param p the polygon of the tower
     * @return if an enemy are close enough to attack the tower
     */
    @Override
    public boolean attackCheck(Enemy enemy, Polygon p) {

        Rectangle r = p.getBoundingRectangle();

        if (enemy.getX() < r.getX() + r.getWidth()) {
            return !enemy.isDead();

        }

        return false;
    }

    /**
     * Check if the enemies that are going to spawn are flipped or not
     *
     * @return if the enemies that are going to spawn are flipped or not
     */
    @Override
    public boolean isFlippedEnemy() {
        return false;
    }


    /**
     * The initial balance that the player will have at the start of the game
     *
     * @return the initial balance
     */
    @Override
    public int getInitialBalance() {
        return 400;
    }
}
