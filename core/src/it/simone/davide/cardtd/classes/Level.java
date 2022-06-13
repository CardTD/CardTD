package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
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
import it.simone.davide.cardtd.classes.levels.FirstMap;
import it.simone.davide.cardtd.classes.waves.Wave;
import it.simone.davide.cardtd.classes.waves.Waves;
import it.simone.davide.cardtd.enums.EnemyState;
import it.simone.davide.cardtd.enums.EnemyType;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;
import it.simone.davide.cardtd.screens.MainMenu;
import it.simone.davide.cardtd.screens.OptionsMenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The screen that represents a level
 */
public abstract class Level implements Screen, GestureDetector.GestureListener {

    /**
     * The stage of the game
     */
    private final Stage mainStage;

    /**
     * The stage to set a responsive background
     */
    private final Stage fillstage;

    /**
     * The stage to place the overlay
     */
    private final Stage overlaystage;

    /**
     * The stage to place the pause's components
     */
    private final Stage pauseStage;

    /**
     * The stage to place the game over's components
     */
    private final Stage gameOverStage;

    /**
     * The tile manager
     */
    private TileManager tileManager;

    /**
     * The placed buildings on the game map
     */
    private List<Build> placedStructures = new ArrayList<>();

    /**
     * All the in game enemies
     */
    private List<Enemy> enemies = new ArrayList<>();

    /**
     * The selected card
     */
    private Card selectedCard;

    /**
     * If showing the center of enemies, used for debugging
     */
    private boolean showEnemyCenter = false;

    /**
     * If showing the border of the in game map elements, used for debugging
     */
    private boolean showTiledMapElem = false;

    /**
     * If selected card is being dragged
     */
    private boolean isCardDragging = false;

    /**
     * If the game is in pause
     */
    private boolean isPaused = false;

    /**
     * If the game is in game over
     */
    private boolean isGameOver = false;

    /**
     * If the game is in won phase
     */
    private boolean isGameWon = false;

    /**
     * The camera of the game
     */
    private OrthographicCamera gameCam = new OrthographicCamera();

    /**
     * The zoom of the camera
     */
    private float currentZoom = 1;

    /**
     * The building that is being placed dragging the selected card
     */
    private Build building;

    /**
     * ShaderProgram to apply the blur effect
     */
    private ShaderProgram shader;

    /**
     * FrameBuffer to apply the blur effect
     */
    private FrameBuffer fboA;

    /**
     * FrameBuffer to apply the blur effect
     */
    private FrameBuffer fboB;

    /**
     * The pause label shown in pause state
     */
    private LabelAdapter PauseLabel;

    /**
     * The pause button
     */
    private Button option;

    /**
     * The home button to go back to the main menu
     */
    private Button homeB;

    /**
     * The resume button
     */
    private Button resume;

    /**
     * The pause button
     */
    private Button pause;

    /**
     * The restart button
     */
    private Button reload;

    /**
     * The queue of the cards
     */
    private Queue<Card> deckQueue;

    /**
     * The instance of the screen
     */
    private final Screen LevelInstance;

    /**
     * The current balance of the player
     */
    private int balance;

    /**
     * The current balance label
     */
    private LabelAdapter balanceLabel;

    /**
     * The game over label
     */
    private LabelAdapter gameOverLabel;

    /**
     * The timer label
     */
    private LabelAdapter timer;

    /**
     * The in game win label
     */
    private LabelAdapter gameWinLabel;

    /**
     * The health bar manager
     */
    public static HealthBar HEALTHBAR;

    /**
     * The 4 card shown in game
     */
    private List<Card> cards = new ArrayList<>();

    /**
     * The selected building, just to show the building information
     */
    public static Build SELECTEDBUILDING;

    /**
     * The initial countdown before start thw first wave
     */
    private int initialCountDown = 3;

    /**
     * The waves of the level
     *
     * @see Wave
     */
    private Waves waves;

    /**
     * Create a new Level
     *
     * @param map      the image of the map
     * @param tiledmap the tiled map of the level
     */
    public Level(Texture map, TiledMap tiledmap) {

        deckQueue = new LinkedList(MainMenu.PLAYERDECK.getRealDeck());

        balance = getInitialBalance();

        waves = getWaves();

        LevelInstance = this;
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
        gameOverStage = new Stage(new ScreenViewport());

        TextureRegionDrawable resumeButton = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.RESUMEBUTTON));
        TextureRegionDrawable resumeButtonP = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.PAUSEBUTTON_PRESSED));
        resume = new Button(resumeButton, resumeButtonP);
        resume.setSize(100, 100);
        resume.setPosition(pauseStage.getWidth() - 150, pauseStage.getHeight() - 150);
        resume.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                isPaused = !isPaused;
            }
        });
        pauseStage.addActor(resume);

        TextureRegionDrawable reloadButton = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.RELOADBUTTON));
        TextureRegionDrawable reloadButtonP = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.RELOADBUTTON_PRESSED));
        reload = new Button(reloadButton, reloadButtonP);
        reload.setSize(150, 150);
        reload.setPosition(pauseStage.getWidth() / 2f - reload.getWidth() / 2 + 100, pauseStage.getHeight() / 2f - reload.getHeight() / 2);
        reload.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                CardTDGame.INSTANCE.setScreen(new FirstMap(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.FIRSTMAP), CardTDGame.ASSETSMANAGER.<TiledMap>get(StaticVariables.TMXMAP)));

            }
        });
        gameOverStage.addActor(reload);

        TextureRegionDrawable pauseButton = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.PAUSEBUTTON));
        TextureRegionDrawable pauseButtonP = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.PAUSEBUTTON_PRESSED));
        pause = new Button(pauseButton, pauseButtonP);
        pause.setSize(100, 100);
        pause.setPosition(pauseStage.getWidth() - 150, pauseStage.getHeight() - 150);
        pause.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                isPaused = !isPaused;

                if (building != null && !building.isPlaced()) {
                    placedStructures.remove(building);
                    building.remove();
                }
                building = null;

            }
        });
        pauseStage.addActor(pause);

        gameOverLabel = new LabelAdapter("GAME OVER", FontType.LOGO);
        gameOverLabel.toStage(gameOverStage, gameOverStage.getWidth() / 2f - gameOverLabel.getWidth() / 2, gameOverStage.getHeight() / 2f - gameOverLabel.getHeight() / 2 + 200);

        gameWinLabel = new LabelAdapter("YOU WON", FontType.LOGO);
        gameWinLabel.toStage(gameOverStage, gameOverStage.getWidth() / 2f - gameWinLabel.getWidth() / 2, gameOverStage.getHeight() / 2f - gameWinLabel.getHeight() / 2 + 200);

        TextureRegionDrawable optionButton = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.OPTIONBUTTON));
        TextureRegionDrawable optionButtonP = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.OPTIONBUTTON_PRESSED));
        option = new Button(optionButton, optionButtonP);
        option.setSize(150, 150);
        option.setPosition(pauseStage.getWidth() / 2f - option.getWidth() / 2 + 100, pauseStage.getHeight() / 2f - option.getHeight() / 2);
        option.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new OptionsMenu(LevelInstance));

            }
        });
        pauseStage.addActor(option);

        TextureRegionDrawable homeButton = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.HOMEBUTTON));
        TextureRegionDrawable homeButtonP = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.HOMEBUTTON_PRESSED));
        homeB = new Button(homeButton, homeButtonP);
        homeB.setSize(150, 150);
        homeB.setPosition(pauseStage.getWidth() / 2f - homeB.getWidth() / 2 - 100, pauseStage.getHeight() / 2f - homeB.getHeight() / 2);
        homeB.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                CardTDGame.INSTANCE.setScreen(new MainMenu());

            }
        });
        pauseStage.addActor(homeB);

        overlaystage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        overlaystage.addActor(new Image((Texture) CardTDGame.ASSETSMANAGER.get(StaticVariables.MAP_OVERLAY)));

        timer = new LabelAdapter(initialCountDown + "", FontType.MONEY);
        timer.toStage(overlaystage, overlaystage.getWidth() / 2f - timer.getWidth() / 2, overlaystage.getHeight() - timer.getHeight());

        Timer.Task initialTimerTask = new Timer.Task() {
            @Override
            public void run() {
                initialCountDown--;
                timer.setText(initialCountDown);
                if (initialCountDown == 0) {
                    timer.remove();
                    overlaystage.addActor(waves);
                }
            }
        };
        Timer.schedule(initialTimerTask, 1f, 1f);

        balanceLabel = new LabelAdapter(balance + "", FontType.MONEY);
        balanceLabel.toStage(overlaystage, 640, 9);

        AnimatedImage coin = new AnimatedImage(CardTDGame.loadAnimation((Texture) CardTDGame.ASSETSMANAGER.get(StaticVariables.COIN), 8, 1));

        coin.scaleBy(-0.75f);
        coin.setPosition(580, 10);
        overlaystage.addActor(coin);

        HEALTHBAR = new HealthBar(100);
        HEALTHBAR.setPosition(920, 8);

        overlaystage.addActor(HEALTHBAR);

        gameCam.setToOrtho(false, StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT);
        mainStage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT, gameCam));
        mainStage.addActor(new Image(map));

        PauseLabel = new LabelAdapter("GAME IS PAUSED", FontType.LOGO);
        PauseLabel.toStage(pauseStage, pauseStage.getWidth() / 2f - PauseLabel.getWidth() / 2, pauseStage.getHeight() / 2f - PauseLabel.getHeight() / 2 + 200);

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Texture bg = CardTDGame.ASSETSMANAGER.get(StaticVariables.DECKBG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));
        fillstage.addActor(table);
        tileManager = new TileManager(tiledmap, placedStructures);

        mainStage.addListener(new DragListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if (pointer > 0) return false;

                if (!isPaused) {

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
                }

                return true;

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);

                if (!isPaused) {

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

                SELECTEDBUILDING = null;

                if (!isPaused) {

                    if (building != null && selectedCard != null && selectedCard.isSelected()) {
                        if (!tileManager.canPlace(building.getRectangle())) {
                            placedStructures.remove(building);
                            building.remove();

                        } else {
                            building.setColor(Color.WHITE);
                            building.place();
                            selectedCard.setSelected(false);
                            updateBalance(-selectedCard.getCost());
                            deckQueue.add(selectedCard.clone());
                            selectedCard.changeCard(deckQueue.poll());
                            selectedCard = null;
                        }
                        building = null;

                    }
                }

            }

        });

        for (int i = 0; i < 4; i++) {

            Card c = deckQueue.poll();

            cards.add(c);
            c.scaleBy(-0.3f);
            c.setPosition(15 + ((c.getWidth() * 0.7f) * i + 20 * i), 12);

            overlaystage.addActor(c);

            c.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    if (!isPaused) {
                        final Card c = ((Card) event.getTarget());
                        if (c.isCanBuy(balance)) {
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
                }
            });

            c.addListener(new DragListener() {

                @Override
                public void dragStart(InputEvent event, float x, float y, int pointer) {
                    super.dragStart(event, x, y, pointer);
                    if (!isPaused && !isGameOver && !isGameWon) {
                        Card c = ((Card) event.getTarget());
                        if (c.isCanBuy(balance))
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
                    if (!isPaused && !isGameOver && !isGameWon) {
                        if (building != null && selectedCard == null) {

                            Card c = ((Card) event.getTarget());
                            if (c.isCanBuy(balance)) {

                                building.setPosition(event.getStageX() - building.getWidth() / 2, event.getStageY() - building.getHeight() / 2);

                                if (!tileManager.canPlace(new Rectangle(building.getX(), building.getY(), building.getWidth(), building.getHeight()))) {

                                    building.setColor(Color.RED);
                                } else {

                                    building.setColor(Color.GREEN);
                                }
                            }

                        }
                    }
                }

                @Override
                public void dragStop(InputEvent event, float x, float y, int pointer) {
                    super.dragStop(event, x, y, pointer);
                    if (!isPaused && !isGameOver && !isGameWon) {
                        Card c = ((Card) event.getTarget());
                        if (c.isCanBuy(balance))
                            if (building != null && selectedCard == null) {

                                if (!tileManager.canPlace(building.getRectangle())) {

                                    placedStructures.remove(building);
                                    building.remove();
                                    building = null;

                                } else {
                                    building.setColor(Color.WHITE);
                                    building.place();
                                    updateBalance(-c.getCost());
                                    deckQueue.add(c.clone());
                                    c.changeCard(deckQueue.poll());

                                }
                                isCardDragging = false;
                                c.setSelected(false);
                            }
                    }
                }

            });

        }

    }

    /**
     * Returns the level's waves. Used in the constructor to get the level's waves
     *
     * @return the level's waves
     */
    public abstract Waves getWaves();

    /**
     * Update the balance
     *
     * @param addBalance add money to balance
     */
    public void updateBalance(int addBalance) {
        if (addBalance != 0) {

            balance += addBalance;

            balanceLabel.setText(balance);

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

        InputMultiplexer i = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);
        Gdx.input.setCatchKey(Input.Keys.ESCAPE, true);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        i.addProcessor(gd);
        i.addProcessor(gameOverStage);
        i.addProcessor(pauseStage);
        i.addProcessor(overlaystage);
        i.addProcessor(mainStage);
        Gdx.input.setInputProcessor(i);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    /**
     * Returns the overlay stage
     *
     * @return the overlay stage
     */
    public Stage getOverlaystage() {
        return overlaystage;
    }

    /**
     * Add enemy to the game. It will spawn in the {@link #getStartPostision(EnemyType)}
     *
     * @param enemyType enemy to add
     */
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

    /**
     * Return the position where spawn the enemies. The position can be different for enemy type
     *
     * @param enemyType the enemy type
     * @return the position where the enemy have to spawn
     */
    public abstract Vector2 getStartPostision(EnemyType enemyType);

    /**
     * Return the path of the enemy have to do. The path can be different for enemy type
     *
     * @param enemyType the enemy type
     * @return the path of the enemy have to do
     */
    public abstract Path getPath(EnemyType enemyType);

    /**
     * Check if the enemy have to attack the main tower
     *
     * @param enemy the enemy
     * @param p     the polygon of the tower
     * @return if the enemy have to attack the main tower
     */
    public abstract boolean attackCheck(Enemy enemy, Polygon p);

    /**
     * Return if the enemy’s texture needs to be flipped when it spawns
     *
     * @return if the enemy’s texture needs to be flipped
     */
    public abstract boolean isFlippedEnemy();

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(float delta) {
        if (!isGameOver && !isGameWon)
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.BACK)) {

                isPaused = !isPaused;

            }

        if (waves.isTerminated() && enemies.size() == 0) {
            gameWinLabel.setVisible(true);
            reload.setVisible(true);
            homeB.setVisible(true);
            isGameWon = true;
        } else if (isGameOver) {
            resume.setVisible(false);
            pause.setVisible(false);
            reload.setVisible(true);
            homeB.setVisible(true);
        } else if (isPaused) {
            homeB.setVisible(true);
            option.setVisible(true);
            resume.setVisible(true);
            pause.setVisible(false);
            PauseLabel.setVisible(true);

        } else {
            reload.setVisible(false);
            homeB.setVisible(false);
            option.setVisible(false);
            pause.setVisible(true);
            resume.setVisible(false);
            PauseLabel.setVisible(false);
            gameWinLabel.setVisible(false);
        }

        if (isGameOver || isPaused || isGameWon) {

            blur(overlaystage);
            blur(fillstage);
            blur(mainStage);
        } else {
            fillstage.getBatch().setShader(null);
            mainStage.getBatch().setShader(null);
            overlaystage.getBatch().setShader(null);

        }

        gameOverLabel.setVisible(isGameOver);

        fillstage.getViewport().apply();
        if (!isPaused && !isGameOver && !isGameWon) fillstage.act(delta);
        fillstage.draw();

        mainStage.getViewport().apply();

        if (!isPaused && !isGameOver && !isGameWon) mainStage.act(delta);
        mainStage.draw();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        ShapeRenderer s = new ShapeRenderer();
        s.setProjectionMatrix(mainStage.getCamera().combined);
        if (showTiledMapElem) tileManager.render(s);

        if (SELECTEDBUILDING != null) {
            Circle b = SELECTEDBUILDING.getAttackRangeCircle();
            s.begin(ShapeType.Filled);
            s.setColor(0, 100 / 255f, 0, 0.5f);
            s.circle(b.x, b.y, b.radius);
            s.end();

        }

        for (Enemy e : enemies) {

            if (showEnemyCenter) {
                s.begin(ShapeType.Line);
                s.circle(e.getCenter().x, e.getCenter().y, 10);
                Rectangle ss = e.getRectangle();

                s.rect(ss.x, ss.y, ss.width, ss.height);
                s.end();
            }

            if (!isPaused && !isGameOver && !isGameWon)
                if (attackCheck(e, tileManager.getToProtect())) e.setCurrentState(EnemyState.ATTACK);

        }

        if (!isPaused && !isGameOver && !isGameWon) {
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

                updateBalance(b.hitEnemies(enemies));
            }
        }

        overlaystage.getViewport().apply();
        if (!isPaused && !isGameOver) overlaystage.act(delta);
        overlaystage.draw();

        pauseStage.getViewport().apply();
        pauseStage.act(delta);
        pauseStage.draw();

        gameOverStage.getViewport().apply();
        gameOverStage.act(delta);
        gameOverStage.draw();

        if (HEALTHBAR.isDead()) {
            boolean f = isGameOver;
            isGameOver = true;
            if (!f) {
                Music c = (CardTDGame.ASSETSMANAGER.get(StaticVariables.GAMEOVERVOICE));
                c.setVolume(MainMenu.OPTIONS.getFxVolume());
                c.setLooping(false);
                c.play();

            }

        }

        for (Card c : cards) {

            c.isCanBuy(balance);
        }

    }

    /**
     * Apply a blur effect in a stage
     *
     * @param stage a stage
     */
    public void blur(Stage stage) {
        stage.getBatch().begin();
        fboA.begin();
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().setShader(null);
        stage.getBatch().flush();
        fboA.end();
        applyBlur(4.0f, stage.getBatch());
        stage.getBatch().end();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {

    }

    /**
     * Set if the center of the enemy must be shown
     *
     * @param showEnemyCenter if the center of the enemy must be shown
     */
    public void setShowEnemyCenter(boolean showEnemyCenter) {
        this.showEnemyCenter = showEnemyCenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (!isPaused && !isGameOver && !isGameWon) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (!isPaused && !isGameOver && !isGameWon) {
            if (selectedCard == null && !isCardDragging) {
                gameCam.zoom = (initialDistance / distance) * currentZoom;
                if (gameCam.zoom < 0.65f) gameCam.zoom = 0.65f;
                if (gameCam.zoom > 1f) gameCam.zoom = 1f;
                gameCam.update();
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if (!isPaused && !isGameOver && !isGameWon) {
            currentZoom = gameCam.zoom;
            gameCam.update();
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pinchStop() {

        Timer timer = new Timer();
        timer.scheduleTask(new Task() {

            @Override
            public void run() {
                if (currentZoom == 1f) {
                    gameCam.position.set(StaticVariables.SCREEN_WIDTH / 2, StaticVariables.SCREEN_HEIGHT / 2, 0);
                    gameCam.update();
                }
            }
        }, 0.1f);

    }

    private void applyBlur(float blur, Batch batch) {
        // Horizontal blur from FBO A to FBO B
        fboB.begin();
        batch.setShader(shader);
        shader.setUniformf("dir", 1.0f, 0.0f);
        shader.setUniformf("radius", blur);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawTexture(fboA.getColorBufferTexture(), batch);
        batch.flush();
        fboB.end();

        // Vertical blur from FBO B to the screen
        shader.setUniformf("dir", 0.0f, 1.0f);
        shader.setUniformf("radius", blur);
        drawTexture(fboB.getColorBufferTexture(), batch);
        batch.flush();
    }

    private void drawTexture(Texture texture, Batch batch) {
        int width = texture.getWidth();
        int height = texture.getHeight();

        float world_to_screen = 1.0f / 100.0f;

        batch.draw(texture, (float) 0.0, (float) 0.0, 0.0f, 0.0f, width, height, world_to_screen, world_to_screen, 0.0f, 0, 0, width, height, false, false);
    }

    /**
     * Set if the tiled map elements of the map must be shown
     *
     * @param showTiledMapElem if the tiled map elements of the map must be shown
     */
    public void setShowTiledMapElem(boolean showTiledMapElem) {
        this.showTiledMapElem = showTiledMapElem;
    }

    /**
     * Returns the initial balance. Used in the constructor to get the initial balance
     *
     * @return the initial balance
     */
    public abstract int getInitialBalance();
}


