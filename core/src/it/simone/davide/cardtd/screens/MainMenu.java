package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;

public class MainMenu implements Screen {

    private Stage fillstage;
    private Stage fitstage;

    public MainMenu() {
        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        fitstage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));

        Texture bg = CardTDGame.assetManager.<Texture>get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));

        fillstage.addActor(table);

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
        fillstage.draw();
        Batch stageBatch = fillstage.getBatch();
        stageBatch.begin();
        BitmapFont title = CardTDGame.assetManager.get(StaticVariables.MAIN_FONT, BitmapFont.class);
        GlyphLayout layout = new GlyphLayout(title, "Make Deck");
        title.draw(fillstage.getBatch(), "Make Deck", 10, Gdx.graphics.getHeight() / 2);

        layout = new GlyphLayout(title, "Options");
        title.draw(fillstage.getBatch(), "Options", Gdx.graphics.getWidth() - layout.width - 10, Gdx.graphics.getHeight() / 2);

        stageBatch.end();

        //render the game menu
        fitstage.getViewport().apply();
        fitstage.act(delta);
        fitstage.draw();
        stageBatch = fitstage.getBatch();
        stageBatch.begin();
        layout = new GlyphLayout(title, StaticVariables.GAMENAME);
        title.draw(fitstage.getBatch(), StaticVariables.GAMENAME, StaticVariables.SCREEN_WIDTH / 2 - layout.width / 2, StaticVariables.SCREEN_HEIGHT / 2 - layout.height / 2 + 200);
        stageBatch.end();

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
