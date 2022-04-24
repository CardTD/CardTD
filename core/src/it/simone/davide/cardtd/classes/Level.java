package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import it.simone.davide.cardtd.enums.EnemyType;
import it.simone.davide.cardtd.screens.MainMenu;

import java.util.ArrayList;
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

            Card c = MainMenu.playerDeck.getCard(i);
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
                       Build b= new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.TOWER), new Texture("bullet.png"), 500, 1, mainStage,1);
                       b.place((int)building.getX(),(int) building.getY());
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
        addEnemy(EnemyType.ToasterBot);

        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void show() {

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    public abstract void addEnemy(EnemyType enemyType);

    public abstract void render(float delta);

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
