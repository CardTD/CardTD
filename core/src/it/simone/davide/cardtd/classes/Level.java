package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.TileManager;
import it.simone.davide.cardtd.enums.EnemyState;
import it.simone.davide.cardtd.enums.EnemyType;
import it.simone.davide.cardtd.screens.MainMenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Level implements Screen {

    protected final Stage mainStage, fillstage;
    protected TileManager tileManager;
    protected List<Build> placedStructures = new ArrayList<>();
    protected List<Enemy> enemies = new ArrayList<>();

    public Level(Texture map, TiledMap tiledmap) {
        mainStage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        mainStage.addActor(new Image(map));

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Texture bg = CardTDGame.assetManager.get(StaticVariables.DECKBG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));
        fillstage.addActor(table);
        tileManager = new TileManager(tiledmap, placedStructures);

        mainStage.addActor(new Image((Texture) CardTDGame.assetManager.get(StaticVariables.IN_GAME_DECK)));

        for (int i = 0; i < 4; i++) {

            Card c = MainMenu.playerDeck.getCard(i).clone();
            c.setPosition(12 + (c.getWidth() * i + 12 * i), 12);
            mainStage.addActor(c);

            c.addListener(new DragListener() {

                Image building;

                @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    super.dragStart(event, x, y, pointer);
                    Card c = ((Card) event.getTarget());
                    building = new Image(c.getPlacedTexture());
                    building.debug();

                    if (!c.isSelected()) {

                        changePos(Gdx.input.getX(), Gdx.input.getY());
                        mainStage.addActor(building);

                    }
                }

                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    super.drag(event, x, y, pointer);
                    changePos(Gdx.input.getX(), Gdx.input.getY());
                    if (!tileManager.canPlace(new Rectangle(building.getX(), building.getY(), building.getWidth(), building.getHeight()))) {

                        building.setColor(Color.RED);
                    } else {

                        building.setColor(Color.GREEN);
                    }
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    super.dragStop(event, x, y, pointer);
                    if (!tileManager.canPlace(new Rectangle(building.getX(), building.getY(), building.getWidth(), building.getHeight()))) {

                        building.addAction(Actions.removeActor());

                    } else {
                        building.setColor(Color.WHITE);
                        Build b = new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.TOWER), new Texture("bullet.png"), 500, 1, mainStage, 1, (int) building.getX(), (int) building.getY());

                        placedStructures.add(b);
                        mainStage.addActor(b);
                    }

                }

                public void changePos(int x, int y) {
                    Vector3 i = mainStage.getCamera().unproject(new Vector3(x, y, 0));
                    int xx = (int) (i.x - building.getWidth() / 2), yy = (int) (i.y - building.getHeight() / 2);

                    building.setPosition(xx / 5 * 5, yy / 5 * 5);

                }
            });
        }

        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void show() {

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    public void addEnemy(EnemyType enemyType) {
        Enemy s = ((Enemy) StaticVariables.ENEMIES.get(enemyType)).clone();
        s.setPosition(0, 360);
        s.setPath(getPath(enemyType));
        enemies.add(s);
        mainStage.addActor(s);

    }

    public abstract Path getPath(EnemyType enemyType);

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
                if (!e.currentState.equals(EnemyState.DEATH)) {
                    e.setCurrentState(EnemyState.ATTACK);
                }

            }

        }
        mainStage.act(delta);
        mainStage.draw();
        ShapeRenderer s = new ShapeRenderer();
        s.setProjectionMatrix(mainStage.getCamera().combined);
        tileManager.render(s);

        Iterator<Enemy> i = enemies.iterator();

        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.canRemove()) {
                i.remove();
            }
        }

        for (Build b : placedStructures) {

            s.begin(ShapeType.Line);
            s.rect(b.getAttackRangeRect().x, b.getAttackRangeRect().y, b.getAttackRangeRect().width, b.getAttackRangeRect().height);
            s.end();

            for (Enemy e : enemies) {

                if (!e.isDead() && b.getAttackRangeRect().overlaps(e.getRectangle())) {
                    b.setTarget(e);

                    break;
                }
            }

        }


        for (Build b : placedStructures) {

            b.hitEnemies(enemies);
        }

    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
