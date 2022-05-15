package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.TileManager;
import it.simone.davide.cardtd.enums.EnemyState;
import it.simone.davide.cardtd.enums.EnemyType;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;
import it.simone.davide.cardtd.screens.MainMenu;
import it.simone.davide.cardtd.screens.OptionsMenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Level implements Screen, GestureDetector.GestureListener {
    private static final float WORLD_TO_SCREEN = 1.0f / 100.0f;
    protected final Stage mainStage, fillstage, overlaystage, pauseStage;
    protected TileManager tileManager;
    protected List<Build> placedStructures = new ArrayList<>();
    protected List<Enemy> enemies = new ArrayList<>();
    private Card selectedCard;
    private boolean showEnemyCenter = false, showTiledMapElem = false, isCardDragging = false, isPaused = false;
    private OrthographicCamera gameCam = new OrthographicCamera();
    private float currentZoom = 1;
    Build building;
    private ShaderProgram shader;
    private FrameBuffer fboA;
    private FrameBuffer fboB;
    private LabelAdapter PauseLabel;
    private Button option;

    private final Screen screen;

    public Level(Texture map, TiledMap tiledmap) {
        screen = this;
        shader = new ShaderProgram(Gdx.files.internal("shaders/blur.vert"), Gdx.files.internal("shaders/blur.frag"));
        fboA = new FrameBuffer(Pixmap.Format.RGBA8888, StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT, false);
        fboB = new FrameBuffer(Pixmap.Format.RGBA8888, StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT, false);

        if (!shader.isCompiled()) {
            Gdx.app.error("Shader", shader.getLog());
            Gdx.app.exit();
        }
        shader.bind();
        shader.setUniformf("resolution", StaticVariables.SCREEN_WIDTH);

        pauseStage = new Stage(new ScreenViewport());

        TextureRegionDrawable pauseButton = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.PAUSEBUTTON));
        TextureRegionDrawable pauseButtonP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.PAUSEBUTTON_PRESSED));
        Button pause = new Button(pauseButton, pauseButtonP);
        pause.setSize(100, 100);
        pause.setPosition(pauseStage.getWidth() - 150, pauseStage.getHeight() - 150);
        pause.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                isPaused = !isPaused;

                if(building != null && !building.isPlaced()) {
                    placedStructures.remove(building);
                    building.remove();
                }
                building = null;


            }
        });
        pauseStage.addActor(pause);

        TextureRegionDrawable optionButton = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.OPTIONBUTTON));
        TextureRegionDrawable optionButtonP = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.OPTIONBUTTON_PRESSED));
        option = new Button(optionButton, optionButtonP);
        option.setSize(100, 100);
        option.setPosition(pauseStage.getWidth() / 2f - option.getWidth() / 2, pauseStage.getHeight() / 2f - option.getHeight() / 2);
        option.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                CardTDGame.INSTANCE.setScreen(new OptionsMenu(screen));

            }
        });
        pauseStage.addActor(option);

        overlaystage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        overlaystage.addActor(new Image((Texture) CardTDGame.assetManager.get(StaticVariables.MAP_OVERLAY)));

        gameCam.setToOrtho(false, StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT);
        mainStage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT, gameCam));
        mainStage.addActor(new Image(map));

        PauseLabel = new LabelAdapter("GAME IS PAUSED", FontType.LOGO);
        PauseLabel.toStage(pauseStage, pauseStage.getWidth() / 2f - PauseLabel.getWidth() / 2, pauseStage.getHeight() / 2f - PauseLabel.getHeight() / 2 + 200);

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Texture bg = CardTDGame.assetManager.get(StaticVariables.DECKBG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));
        fillstage.addActor(table);
        tileManager = new TileManager(tiledmap, placedStructures);

        //  mainStage.addActor(new Image((Texture) CardTDGame.assetManager.get(StaticVariables.IN_GAME_DECK)));

        mainStage.addListener(new DragListener() {


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);


                if (pointer > 0) return false;

                if(!isPaused) {

                    if (selectedCard != null && selectedCard.isSelected()) {
                        building = selectedCard.getBuild().clone();
                        building.debug();
                        building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);


                        placedStructures.add(building);
                        mainStage.addActor(building);
                        if (!tileManager.canPlace(building.getRectangle())) {

                            building.setColor(Color.RED);
                        } else {

                            building.setColor(Color.GREEN);
                        }
                    }
                }

                return true;

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);

                if(!isPaused) {

                    if (building != null && selectedCard != null && selectedCard.isSelected()) {
                        building.setPosition((event.getStageX() - building.getWidth() / 2), (event.getStageY() - building.getHeight() / 2));
                        if (!tileManager.canPlace(building.getRectangle())) {

                            building.setColor(Color.RED);
                        } else {

                            building.setColor(Color.GREEN);
                        }
                    }
                }

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                if(!isPaused) {

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

            }

            //TODO elimina tutti i changepostion utilizzati

        });


        for (int i = 0; i < 4; i++) {

            Card c = MainMenu.playerDeck.getCard(i).clone();
            c.scaleBy(-0.3f);
            c.setPosition(15 + ((c.getWidth() * 0.7f) * i + 20 * i), 12);
            overlaystage.addActor(c);

            c.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    if(!isPaused) {
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
                }
            });

            c.addListener(new DragListener() {

             /*   @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    super.dragStart(event, x, y, pointer);


                    if (pointer > 0) return;


                    Card c = ((Card) event.getTarget());

                    if (!c.isSelected() && selectedCard == null) {
                        selectedCard = c;
                        c.setSelected(true);
                        building = selectedCard.getBuild().clone();
                        building.debug();

                       building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);


                        placedStructures.add(building);
                        mainStage.addActor(building);
                        if (!tileManager.canPlace(building.getRectangle())) {

                            building.setColor(Color.RED);
                        } else {

                            building.setColor(Color.GREEN);
                        }
                    }

                }

              */



                @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    super.dragStart(event, x, y, pointer);
                    if(!isPaused) {
                        Card c = ((Card) event.getTarget());

                        if (!c.isSelected() && selectedCard == null) {

                            building = c.getBuild().clone();
                            building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);
                            placedStructures.add(building);
                            mainStage.addActor(building);
                            isCardDragging = true;
                            c.setSelected(true);
                        }
                    }
                }



              @Override
                public void drag(InputEvent event, float x, float y, int pointer) {
                    super.drag(event, x, y, pointer);
                  if(!isPaused) {
                      if (building != null && selectedCard == null) {

                          building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);
                          // building.setPosition((v.x - building.getWidth() / 2 ) , (v.y - building.getHeight() / 2) );

                          if (!tileManager.canPlace(new Rectangle(building.getX(), building.getY(), building.getWidth(), building.getHeight()))) {

                              building.setColor(Color.RED);
                          } else {

                              building.setColor(Color.GREEN);
                          }
                      }
                  }
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    super.dragStop(event, x, y, pointer);
                    if(!isPaused) {
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
                            isCardDragging = false;
                            c.setSelected(false);
                        }
                    }
                }



            });


        }


    }

    @Override
    public void show() {

        InputMultiplexer i = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setCatchKey(Input.Keys.ESCAPE, true);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        i.addProcessor(gd);
        i.addProcessor(pauseStage);
        i.addProcessor(overlaystage);
        i.addProcessor(mainStage);
        Gdx.input.setInputProcessor(i);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    public void addEnemy(EnemyType enemyType) {
        Enemy s = ((Enemy) GameObjects.ENEMIES.get(enemyType)).clone();
        Vector2 i = getStartPostision(enemyType);

        s.setPath(getPath(enemyType));
        enemies.add(s);
        mainStage.addActor(s);
        if (isFlippedEnemy()) {


            s.flip(i.x - s.getFrameWidth() + s.getWidth(), i.y);

        } else {
            s.setPosition(i.x, i.y);
        }

    }

    public abstract Vector2 getStartPostision(EnemyType enemyType);

    public abstract Path getPath(EnemyType enemyType);

    public abstract boolean attackCheck(Enemy e, Polygon p);

    public abstract boolean isFlippedEnemy();

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.BACK)){

            isPaused = !isPaused;

        }

        if(isPaused){
            option.setVisible(true);
            PauseLabel.setVisible(true);
            blur(fillstage);
            blur(mainStage);
            blur(overlaystage);

        }else{
            option.setVisible(false);
            PauseLabel.setVisible(false);
            fillstage.getBatch().setShader(null);
            mainStage.getBatch().setShader(null);
            overlaystage.getBatch().setShader(null);
        }

            fillstage.getViewport().apply();
        if(!isPaused)
            fillstage.act(delta);
            fillstage.draw();


            mainStage.getViewport().apply();

        if(!isPaused)
            mainStage.act(delta);
            mainStage.draw();
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            ShapeRenderer s = new ShapeRenderer();
            s.setProjectionMatrix(mainStage.getCamera().combined);
            if (showTiledMapElem)
                tileManager.render(s);

            for (Enemy e : enemies) {


                if (showEnemyCenter) {
                    s.begin(ShapeType.Line);
                    s.circle(e.getCenter().x, e.getCenter().y, 10);
                    s.end();
                }

                if(!isPaused)
                if (attackCheck(e, tileManager.getToProtect()))
                    e.setCurrentState(EnemyState.ATTACK);


            }

        if(!isPaused) {
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

            overlaystage.getViewport().apply();
        if(!isPaused)
            overlaystage.act(delta);
            overlaystage.draw();


        pauseStage.getViewport().apply();
        pauseStage.act(delta);
        pauseStage.draw();


    }

    public void blur(Stage a){
        a.getBatch().begin();
        fboA.begin();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        a.getBatch().setShader(null);
        a.getBatch().flush();
        fboA.end();
        applyBlur(4.0f, a.getBatch());
        a.getBatch().end();


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

    public void setShowEnemyCenter(boolean showEnemyCenter) {
        this.showEnemyCenter = showEnemyCenter;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(!isPaused){
        if (selectedCard == null && !isCardDragging) {
            gameCam.translate(-deltaX * currentZoom, deltaY * currentZoom);
            gameCam.update();

            float camX = gameCam.position.x;
            float camY = gameCam.position.y;
            Vector2 camMin = new Vector2(gameCam.viewportWidth, gameCam.viewportHeight);
            camMin.scl(gameCam.zoom / 2); //bring to center and scale by the zoom level
            Vector2 camMax = new Vector2(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT);
            camMax.sub(camMin); //bring to center



            //keep camera within borders
            camX = Math.min(camMax.x, Math.max(camX, camMin.x));
            camY = Math.min(camMax.y, Math.max(camY, camMin.y));

            gameCam.position.set(camX, camY, gameCam.position.z);
        }
        }
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if(!isPaused) {
            if (selectedCard == null && !isCardDragging) {
                gameCam.zoom = (initialDistance / distance) * currentZoom;
                if (gameCam.zoom < 0.65f)
                    gameCam.zoom = 0.65f;
                if (gameCam.zoom > 1f)
                    gameCam.zoom = 1f;
                gameCam.update();
            }
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if(!isPaused) {
            currentZoom = gameCam.zoom;
            gameCam.update();
        }

        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    private void applyBlur(float blur, Batch batch) {
        // Horizontal blur from FBO A to FBO B
        fboB.begin();
        batch.setShader(shader);
        shader.setUniformf("dir", 1.0f, 0.0f);
        shader.setUniformf("radius", blur);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawTexture(fboA.getColorBufferTexture(),  0.0f, 0.0f, batch);
        batch.flush();
        fboB.end();

        // Vertical blur from FBO B to the screen
        shader.setUniformf("dir", 0.0f, 1.0f);
        shader.setUniformf("radius", blur);
        drawTexture(fboB.getColorBufferTexture(), 0.0f, 0.0f, batch);
        batch.flush();
    }

    private void drawTexture(Texture texture, float x, float y, Batch batch) {
        int width = texture.getWidth();
        int height = texture.getHeight();

        batch.draw(texture,
                x, y,
                0.0f, 0.0f,
                width, height,
                WORLD_TO_SCREEN, WORLD_TO_SCREEN,
                0.0f,
                0, 0,
                width, height,
                false, false);
    }

    public void setShowTiledMapElem(boolean showTiledMapElem) {
        this.showTiledMapElem = showTiledMapElem;
    }
}


