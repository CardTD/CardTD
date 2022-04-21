package it.simone.davide.cardtd.screens.deck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.deck.Deck;
import it.simone.davide.cardtd.screens.MainMenu;

public class DeckMenu implements Screen, InputProcessor {

    //TODO add button to back, and add other keys
    //add animation when select the cards
    private final Stage deckStage, fillstage;

    public DeckMenu(final Deck playerDeck) {

        deckStage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        deckStage.addActor(new Image(CardTDGame.assetManager.<Texture>get(StaticVariables.DECKMENU)));

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Texture bg = CardTDGame.assetManager.get(StaticVariables.DECKBG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));
        fillstage.addActor(table);

        CurrentDeck currentDeck = new CurrentDeck(playerDeck, deckStage);
        AllCards allCards = new AllCards(deckStage);

        currentDeck.setIntegrationWith(allCards);
        allCards.setIntegrationWith(currentDeck);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(deckStage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        Gdx.input.setCatchKey(Input.Keys.BACK, true);

    }

    @Override
    public void show() {

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

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

    @Override
    public void resize(int width, int height) {
        deckStage.getViewport().update(width, height, true);
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

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.BACK) {

            CardTDGame.INSTANCE.setScreen(new MainMenu());

        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
