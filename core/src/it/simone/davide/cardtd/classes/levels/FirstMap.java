package it.simone.davide.cardtd.classes.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.Level;
import it.simone.davide.cardtd.classes.Path;
import it.simone.davide.cardtd.enums.EnemyType;

public class FirstMap extends Level {
    public FirstMap(Texture map, TiledMap tiledmap) {
        super(map, tiledmap);
    }

    @Override
    public Path getPath(EnemyType enemyType) {
        return new Path(((Enemy) StaticVariables.ENEMIES.get(enemyType)).getSpeed(), new Vector2(500, 0));
    }

}
