package it.simone.davide.cardtd.classes.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Build;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.classes.Level;
import it.simone.davide.cardtd.enums.EnemyState;
import it.simone.davide.cardtd.enums.EnemyType;

import java.util.Iterator;

public class FirstMap extends Level {
    public FirstMap(Texture map, TiledMap tiledmap) {
        super(map, tiledmap);
    }

    public void addEnemy(EnemyType enemyType) {
        Enemy s = ((Enemy) StaticVariables.ENEMIES.get(enemyType)).clone();
        s.setPosition(0, 360);
        enemies.add(s);
        mainStage.addActor(s);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fillstage.getViewport().apply();
        fillstage.act(delta);
        fillstage.draw();

        mainStage.getViewport().apply();

        for (Enemy e : enemies) {

            if (e.getX() + e.getAttackDimension() >= tileManager.getToProtect().getX()) {
                e.setCurrentState(EnemyState.ATTACK);

            }

        }
        mainStage.act(delta);
        mainStage.draw();
        ShapeRenderer s = new ShapeRenderer();
        s.setProjectionMatrix(mainStage.getCamera().combined);
        tileManager.render(s);

        for (Build b : placedStructures) {

            s.begin(ShapeType.Line);
            s.rect(b.getAttackRangeRect().x, b.getAttackRangeRect().y, b.getAttackRangeRect().width, b.getAttackRangeRect().height);
            s.end();

            for (Enemy e : enemies) {

                if (b.getAttackRangeRect().overlaps(e.getRectangle())) {
                    b.setTarget(e);

                    break;
                }
                b.setTarget(null);
            }

        }

        Iterator<Enemy> i = enemies.iterator();

        for (; i.hasNext(); ) {
            Enemy e = i.next();
            if (e.canRemove()) {
                e.remove();
            }
        }

        for (Build b : placedStructures) {

            s.begin(ShapeType.Line);
            s.rect(b.getAttackRangeRect().x, b.getAttackRangeRect().y, b.getAttackRangeRect().width, b.getAttackRangeRect().height);
            s.end();

            for (Enemy e : enemies) {

                if (b.getAttackRangeRect().overlaps(e.getRectangle())) {
                    b.setTarget(e);

                    break;
                }
                b.setTarget(null);
            }

        }

        for (Build b : placedStructures) {

            b.hitEnemies(enemies);
        }

    }

}
