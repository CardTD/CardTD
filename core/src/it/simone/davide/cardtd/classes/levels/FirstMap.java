package it.simone.davide.cardtd.classes.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.Level;
import it.simone.davide.cardtd.classes.Path;
import it.simone.davide.cardtd.enums.EnemyType;

public class FirstMap extends Level {
    public FirstMap(Texture map, TiledMap tiledmap) {
        super(map, tiledmap);

       Timer timer = new Timer();
        timer.scheduleTask(new Task() {
            @Override
            public void run() {
                addEnemy(EnemyType.StormHead);

            }
        }, 1, 10);

    }

    @Override
    public Vector2 getStartPostision(EnemyType enemyType) {
        return new Vector2(-200, 360);
    }

    @Override
    public Path getPath(EnemyType enemyType) {
        return new Path(((Enemy) GameObjects.ENEMIES.get(enemyType)).getSpeed(), new Vector2(2000, 0));
    }

}
