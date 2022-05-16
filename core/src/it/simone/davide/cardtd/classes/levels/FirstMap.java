package it.simone.davide.cardtd.classes.levels;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.Level;
import it.simone.davide.cardtd.classes.MoveVector2;
import it.simone.davide.cardtd.classes.Path;
import it.simone.davide.cardtd.enums.EnemyType;
import it.simone.davide.cardtd.screens.MainMenu;

import java.util.ArrayList;

public class FirstMap extends Level {
    public FirstMap(Texture map, TiledMap tiledmap) {
        super(map, tiledmap);

        final ArrayList<EnemyType> enemies = new ArrayList<>(GameObjects.ENEMIES.keySet());

        MainMenu.option.setMusic((Music) CardTDGame.assetManager.get(StaticVariables.FirstMapSound));

        Timer timer = new Timer();
        timer.scheduleTask(new Task() {
            @Override
            public void run() {
                addEnemy(enemies.get((int) (Math.random() * enemies.size())));

            }
        }, 1, 15);
setShowTiledMapElem(false);
    }

    @Override
    public Vector2 getStartPostision(EnemyType enemyType) {
        return new Vector2(770, -100);
    }

    @Override
    public Path getPath(EnemyType enemyType) {
        return new Path(((Enemy) GameObjects.ENEMIES.get(enemyType)).getSpeed(), new MoveVector2(0, 340), new MoveVector2(75,60), new MoveVector2(100, 43), new MoveVector2(16, 120), new MoveVector2(-106, 51), new MoveVector2(-180, 0), new MoveVector2(-68, 26), new MoveVector2(-100, -100), new MoveVector2(-500, 0));
    }

    @Override
    public boolean attackCheck(Enemy e, Polygon p) {

        Rectangle r = p.getBoundingRectangle();

        if (e.getX() < r.getX() + r.getWidth()) {
            if (!e.isDead()) {
                return true;

            }

        }

        return false;
    }

    @Override
    public boolean isFlippedEnemy() {
        return false;
    }

    @Override
    public int getInitialBalance() {
        return 200;
    }
}
