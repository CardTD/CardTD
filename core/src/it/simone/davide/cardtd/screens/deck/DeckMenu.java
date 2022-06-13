package it.simone.davide.cardtd.screens.deck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Card;
import it.simone.davide.cardtd.screens.MainMenu;

import java.util.List;

/**
 * The deck menu screen
 */
public class DeckMenu implements Screen, InputProcessor {

    /**
     * The main stage where there are all the graphic components
     */
    private final Stage deckStage;

    /**
     * The stage to set a responsive background
     */
    private final Stage fillstage;

    /**
     * The current deck of the player
     *
     * @see CurrentDeck
     */
    private final CurrentDeck currentDeck;

    /**
     * Creates a new deck menu screen
     */
    public DeckMenu() {

        deckStage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        deckStage.addActor(new Image(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.DECKMENU)));

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Texture bg = CardTDGame.ASSETSMANAGER.get(StaticVariables.DECKBG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));
        fillstage.addActor(table);

        CurrentDeck currentDeck = new CurrentDeck(MainMenu.PLAYERDECK, deckStage);
        AllCards allCards = new AllCards(deckStage);

        currentDeck.setIntegrationWith(allCards);
        allCards.setIntegrationWith(currentDeck);

        TextureRegionDrawable b = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.BACKBUTTON));
        TextureRegionDrawable bp = new TextureRegionDrawable(CardTDGame.ASSETSMANAGER.<Texture>get(StaticVariables.BACKBUTTON_PRESSED));

        Button back = new Button(b, bp);
        back.setSize(80, 80);
        back.setPosition(1180, 635);
        back.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                onExit();
            }
        });
        deckStage.addActor(back);

        this.currentDeck = currentDeck;
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(deckStage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        Gdx.input.setCatchKey(Input.Keys.BACK, true);
        Gdx.input.setCatchKey(Keys.ESCAPE, true);

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void show() {

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    /**
     * Before exit save on the game preferences the modified values and go back to the main menu
     */
    public void onExit() {
        CardTDGame.INSTANCE.setScreen(new MainMenu());

        Preferences prefs = Gdx.app.getPreferences("deck");
        List<Card> r = currentDeck.getPlayerDeck().getCards();
        for (int i = 0; i < 12; i++) {

            prefs.putString(i + "", r.get(i).getName());
        }

        prefs.flush();

    }

    /**
     * {inheritDoc}
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fillstage.getViewport().apply();
        fillstage.act(delta);
        fillstage.draw();

        deckStage.getViewport().apply();
        deckStage.act(delta);
        deckStage.draw();

    }

    /**
     * {inheritDoc}
     */

    @Override
    public void resize(int width, int height) {
        deckStage.getViewport().update(width, height, true);
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

    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {

            onExit();

        }

        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
