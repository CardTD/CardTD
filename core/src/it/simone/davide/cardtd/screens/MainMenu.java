package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Deck;
import it.simone.davide.cardtd.classes.Options;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;
import it.simone.davide.cardtd.screens.deck.DeckMenu;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The main menu screen
 */
public class MainMenu implements Screen {

    /**
     * The stage to set a responsive background
     */
    private final Stage fillstage;

    /**
     * The main stage where there are all the graphic components
     */
    private final Stage fitstage;

    /**
     * The deck of the player
     *
     * @see Deck
     */
    public static Deck PLAYERDECK = new Deck(12);

    /**
     * The player preferences
     */
    public static final Options OPTIONS = new Options();

    private static long getRandomLong(long min, long max) {
        Random rand = new Random();
        return rand.nextLong() % (max - min) + min;
    }

    private static float nextFloat(float min, float max) {
        Random rand = new Random();
        return rand.nextFloat() * (max - min) + min;
    }

    /**
     * Create a new main menu screen
     */
    public MainMenu() {

        MainMenu.OPTIONS.setMusic((Music) CardTDGame.assetManager.get(StaticVariables.BACKGROUND_MUSIC));

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        fitstage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));

        //faccio in modo che il bg si estende su tutto lo schermo e lo setto
        Texture bg = CardTDGame.assetManager.get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));

        fillstage.addActor(table);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                final Image nave = new Image((Texture) CardTDGame.assetManager.get(StaticVariables.SHIP));
                nave.setPosition(-500, 600);
                nave.setScale(nextFloat(0.2f, 0.5f));

                float y = getRandomLong(600, 800), time = nextFloat(10, 20);

                nave.addAction(Actions.sequence(Actions.moveTo(-500, y, 0), Actions.moveTo(Gdx.graphics.getWidth() + nave.getImageWidth(), y, time)));
                fillstage.addActor(nave);
            }
        }, 1, 15000);

        LabelAdapter logo = new LabelAdapter(StaticVariables.GAMENAME, FontType.LOGO);
        logo.toStage(fitstage, StaticVariables.SCREEN_WIDTH / 2f - logo.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - logo.getHeight() / 2 + 200);
        final LabelAdapter options = new LabelAdapter("Options", FontType.OPTIONS);
        options.toStage(fillstage, Gdx.graphics.getWidth() - options.getWidth() - 50, Gdx.graphics.getHeight() / 2f - options.getHeight() / 2);
        options.setOrigin(options.getWidth(), options.getHeight());

        final LabelAdapter deck = new LabelAdapter("Make Deck", FontType.OPTIONS);

        final RepeatAction hoverAction = Actions.forever(Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.5f)));

        Preferences prefs = Gdx.app.getPreferences("deck");

        for (int i = 0; i < 12; i++) {
            String r = prefs.getString(i + "", "blank");
            if (r.equals("blank")) {

                PLAYERDECK.addCard(StaticVariables.BLANK_CARD.clone());
            } else {

                PLAYERDECK.addCard(GameObjects.getCardByName(r).clone());

            }

        }
        prefs.flush();

        deck.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new DeckMenu());
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);

                deck.addAction(hoverAction);

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                deck.getActions().removeValue(hoverAction, true);
                deck.reset();
            }
        });

        options.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                CardTDGame.INSTANCE.setScreen(new OptionsMenu(new MainMenu()));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                options.setFontScale(1.2f);

                options.setPosition(Gdx.graphics.getWidth() - options.getWidth() - 60, Gdx.graphics.getHeight() / 2f - options.getHeight() / 2);

            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                options.setFontScale(1);
                options.setPosition(Gdx.graphics.getWidth() - options.getWidth() - 50, Gdx.graphics.getHeight() / 2f - options.getHeight() / 2);
            }
        });

        deck.toStage(fillstage, 50, Gdx.graphics.getHeight() / 2f - deck.getHeight() / 2);

        final LabelAdapter play = new LabelAdapter("Play", FontType.OPTIONS);
        play.toStage(fitstage, StaticVariables.SCREEN_WIDTH / 2 - play.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2 - play.getHeight() / 2);
        play.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CardTDGame.INSTANCE.setScreen(new LevelSelector(new MainMenu()));
            }
        });

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void show() {

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(fitstage);
        inputMultiplexer.addProcessor(fillstage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * {inheritDoc}
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //render the responsive screen
        fillstage.getViewport().apply();

        fillstage.act(delta);

        fillstage.draw();

        //render the game menu
        fitstage.getViewport().apply();
        fitstage.act(delta);
        fitstage.draw();
    }

    /**
     * {inheritDoc}
     */
    @Override
    public void resize(int width, int height) {

        fillstage.getViewport().update(width, height, true);
        fitstage.getViewport().update(width, height, true);

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void pause() {

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void resume() {

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void hide() {

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void dispose() {

        fitstage.dispose();
        fillstage.dispose();

    }
}
