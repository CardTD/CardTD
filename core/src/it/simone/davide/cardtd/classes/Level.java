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
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.GameObjects;
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
    private Card selectedCard;

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

        mainStage.addListener(new DragListener() {
            Build building;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if (selectedCard != null && selectedCard.isSelected()) {
                    building = selectedCard.getBuild().clone();
                    building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);

                    placedStructures.add(building);
                    mainStage.addActor(building);
                    if (!tileManager.canPlace(building.getRectangle())) {

                        building.setColor(Color.RED);
                    } else {

                        building.setColor(Color.GREEN);
                    }
                }

                return true;

            }

            //TODO non ha senso che si possano piazzare le cose di fuori dal campo
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);

                if (building != null && selectedCard != null && selectedCard.isSelected()) {
                    building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);
                    if (!tileManager.canPlace(building.getRectangle())) {

                        building.setColor(Color.RED);
                    } else {

                        building.setColor(Color.GREEN);
                    }
                }

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                if (building != null && selectedCard != null && selectedCard.isSelected()) {
                    if (!tileManager.canPlace(building.getRectangle())) {
                        placedStructures.remove(building);
                        building.remove();

                    } else {
                        building.setColor(Color.WHITE);
                        building.place();
                        selectedCard.setSelected(false);
                        selectedCard = null;
                    }
                    building = null;

                }

            }

            //TODO elimina tutti i changepostion utilizzati
            //TODO non ha senso che le torri sparino sui mob non ancora in mappa

        });
        for (int i = 0; i < 4; i++) {

            Card c = MainMenu.playerDeck.getCard(i).clone();
            c.setPosition(12 + (c.getWidth() * i + 12 * i), 12);
            mainStage.addActor(c);

            c.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    final Card c = ((Card) event.getTarget());

                    Timer timer = new Timer();
                    timer.scheduleTask(new Task() {
                        @Override
                        public void run() {

                            if (selectedCard != null) {

                                if (selectedCard.equals(c)) {
                                    selectedCard.setSelected(!selectedCard.isSelected());
                                    if (!selectedCard.isSelected()) {
                                        selectedCard = null;
                                    }

                                } else {

                                    selectedCard.setSelected(false);
                                    selectedCard = c;
                                    selectedCard.setSelected(true);
                                }

                            } else {

                                selectedCard = c;
                                selectedCard.setSelected(true);
                            }

                        }
                    }, 0.01f);

                }
            });

            c.addListener(new DragListener() {

                Build building;

                @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    super.dragStart(event, x, y, pointer);
                    Card c = ((Card) event.getTarget());

                    if (!c.isSelected() && selectedCard == null) {

                        building = c.getBuild().clone();
                        building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);
                        placedStructures.add(building);
                        mainStage.addActor(building);

                        c.setSelected(true);
                    }
                }

                @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    super.drag(event, x, y, pointer);
                    if (building != null && selectedCard == null) {
                        building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);

                        if (!tileManager.canPlace(new Rectangle(building.getX(), building.getY(), building.getWidth(), building.getHeight()))) {

                            building.setColor(Color.RED);
                        } else {

                            building.setColor(Color.GREEN);
                        }
                    }

                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    super.dragStop(event, x, y, pointer);
                    Card c = ((Card) event.getTarget());
                    if (building != null && selectedCard == null) {

                        if (!tileManager.canPlace(building.getRectangle())) {

                            placedStructures.remove(building);
                            building.remove();
                            building = null;
                        } else {
                            building.setColor(Color.WHITE);
                            building.place();

                        }
                        c.setSelected(false);
                    }

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
        Enemy s = ((Enemy) GameObjects.ENEMIES.get(enemyType)).clone();
        Vector2 i = getStartPostision(enemyType);
        s.setPosition(i.x, i.y);
        s.setPath(getPath(enemyType));
        enemies.add(s);
        mainStage.addActor(s);

    }

    public abstract Vector2 getStartPostision(EnemyType enemyType);

    public abstract Path getPath(EnemyType enemyType);

    @Override
    public void render(float delta) {

        fillstage.getViewport().apply();
        fillstage.act(delta);
        fillstage.draw();

        mainStage.getViewport().apply();

        for (Enemy e : enemies) {

            if (e.getX() + e.getAttackDimension() >= tileManager.getToProtect().getX()) {
                if (!e.isDead()) {
                    e.setCurrentState(EnemyState.ATTACK);

                }

            }

        }
        mainStage.act(delta);
        mainStage.draw();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
            if (!b.isPlaced()) {
                s.begin(ShapeType.Filled);
                s.setColor(0, 100 / 255f, 0, 0.5f);
                s.circle(b.getAttackRangeCircle().x, b.getAttackRangeCircle().y, b.getAttackRangeCircle().radius);
                s.end();
            }

            for (Enemy e : enemies) {

                if (!e.isDead() && Intersector.overlaps(b.getAttackRangeCircle(), e.getRectangle())) {
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
