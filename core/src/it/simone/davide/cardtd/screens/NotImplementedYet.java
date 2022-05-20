package it.simone.davide.cardtd.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.fontmanagement.FontType;
import it.simone.davide.cardtd.fontmanagement.LabelAdapter;

public class NotImplementedYet implements Screen {


    private final Stage fillstage;

    public NotImplementedYet(final Screen backscreen) {

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Texture bg = CardTDGame.assetManager.get(StaticVariables.MAIN_MENU_IMG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));

        fillstage.addActor(table);

        LabelAdapter select_level = new LabelAdapter("NOT IMPLEMENTED YET", FontType.OPTIONS);
        select_level.toStage(fillstage, fillstage.getWidth() / 2f - select_level.getWidth() / 2, fillstage.getHeight() / 2f - select_level.getHeight() / 2 + 200);

        TextureRegionDrawable b = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.BACKBUTTON));
        TextureRegionDrawable bp = new TextureRegionDrawable(CardTDGame.assetManager.<Texture>get(StaticVariables.BACKBUTTON_PRESSED));
        Button back = new Button(b, bp);
        back.setSize(100, 100);
        back.setPosition(fillstage.getWidth() - 150, fillstage.getHeight() - 150);
        back.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                onExit(backscreen);
            }
        });
        fillstage.addActor(back);

    }

    @Override
    public void show() {

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(fillstage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        fillstage.getViewport().apply();
        fillstage.act(delta);
        fillstage.draw();

    }

    @Override
    public void resize(int width, int height) {

        fillstage.getViewport().update(width, height, true);

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
        fillstage.dispose();
    }
    public void onExit(Screen backscreen) {

        CardTDGame.INSTANCE.setScreen(backscreen);
    }
}
