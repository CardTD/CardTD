package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.FontType;
import it.simone.davide.cardtd.LabelAdapter;
import it.simone.davide.cardtd.StaticVariables;

public class MainMenu implements Screen {

    /**
     * Stage che si estende su tutta la schermata
     */
    private final Stage fillstage;

    /**
     * Stage che si estende solo per la dimensione specificata del gioco
     */
    private final Stage fitstage;

    public MainMenu() {
        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        fitstage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));

        //faccio in modo che il bg si estende su tutto lo schermo e lo setto
        Texture bg = CardTDGame.assetManager.get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));
        fillstage.addActor(table);

        //inserisco le 3 label
        LabelAdapter logo = new LabelAdapter(StaticVariables.GAMENAME, FontType.LOGO);
        logo.toStage(fitstage, StaticVariables.SCREEN_WIDTH / 2f - logo.getWidth() / 2, StaticVariables.SCREEN_HEIGHT / 2f - logo.getHeight() / 2 + 200);

        LabelAdapter options = new LabelAdapter("Options", FontType.OPTIONS);
        options.toStage(fillstage, Gdx.graphics.getWidth() - options.getWidth() - 20, Gdx.graphics.getHeight() / 2f - options.getHeight() / 2);

        LabelAdapter deck = new LabelAdapter("Make Deck", FontType.OPTIONS);
        deck.toStage(fillstage, 20, Gdx.graphics.getHeight() / 2 - deck.getHeight() / 2);

    }

    @Override
    public void show() {
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        //render the responsive screen
        fillstage.getViewport().apply();
        fillstage.draw();
        fillstage.act(delta);

        //render the game menu
        fitstage.getViewport().apply();
        fitstage.act(delta);
        fitstage.draw();

    }

    @Override
    public void resize(int width, int height) {

        fillstage.getViewport().update(width, height, true);
        fitstage.getViewport().update(width, height, true);

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
        fitstage.dispose();

    }
}
