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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.deck.Card;
import it.simone.davide.cardtd.deck.Deck;
import it.simone.davide.cardtd.screens.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class DeckMenu implements Screen, InputProcessor {

    private final Deck playerDeck;
    private Stage deckStage, fillstage;
    private List<Card> allCards;

    public DeckMenu(final Deck playerDeck) {

        this.playerDeck = playerDeck;
        deckStage = new Stage(new FitViewport(StaticVariables.SCREEN_WIDTH, StaticVariables.SCREEN_HEIGHT));
        deckStage.addActor(new Image(CardTDGame.assetManager.<Texture>get(StaticVariables.DECKMENU)));

        fillstage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Texture bg = CardTDGame.assetManager.get(StaticVariables.DECKBG);
        Table table = new Table();
        table.setFillParent(true);
        table.background(new TextureRegionDrawable(new TextureRegion(bg)));
        fillstage.addActor(table);
        allCards = new ArrayList<>();

        for (Card i : StaticVariables.ALL_CARDS) {
            allCards.add(new Card(i.getName(), i.getTexture(), i.getCost()));

        }

        DragListener downToUp = new DragListener() {

            Card hoverCard;

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {

                super.dragStart(event, x, y, pointer);

                Card c = ((Card) event.getTarget());
                if (!c.isSelected()) {

                    hoverCard = c.getHoverCard();

                    changePos(Gdx.input.getX(), Gdx.input.getY());
                    deckStage.addActor(hoverCard);
                    c.setSelected(true);
                }

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);
                if (hoverCard != null) {

                    changePos(Gdx.input.getX(), Gdx.input.getY());
                    Card nu = overlaps();

                    if (nu != null) {

                        int i = playerDeck.getFirstIndexValid();

                        if (i != -1) {

                            hoverCard.setPosition(8 + i * hoverCard.getWidth() + i * 6, 467);
                        }

                    }
                }

            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                super.dragStop(event, x, y, pointer);
                Card c = ((Card) event.getTarget());
                if (hoverCard != null) {

                    hoverCard.remove();

                    Card nu = overlaps();
                    if (nu != null) {

                        nu.changeCard(hoverCard);

                    } else {

                        c.setSelected(false);
                    }
                    hoverCard = null;
                }

            }

            public void changePos(int x, int y) {
                Vector3 i = deckStage.getCamera().unproject(new Vector3(x, y, 0));
                hoverCard.setPosition(i.x - hoverCard.getWidth() / 2, i.y - hoverCard.getHeight() / 2);

            }

            public Card overlaps() {
                for (Card c : playerDeck.getCards()) {
                    if (new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight()).overlaps(new Rectangle(hoverCard.getX(), hoverCard.getY(), hoverCard.getWidth(), hoverCard.getHeight()))) {
                        return c;
                    }
                }
                return null;
            }

        };

        DragListener upToDown = new DragListener() {

            Card hoverCard;

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {

                super.dragStart(event, x, y, pointer);
                Card c = ((Card) event.getTarget());
                if (!c.isSelected() && !c.getName().equals("blank")) {
                    hoverCard = c.getHoverCard();

                    changePos(Gdx.input.getX(), Gdx.input.getY());
                    deckStage.addActor(hoverCard);
                    c.setSelected(true);
                }

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);

                if (hoverCard != null) {

                    changePos(Gdx.input.getX(), Gdx.input.getY());
                }

            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                super.dragStop(event, x, y, pointer);
                Card c = ((Card) event.getTarget());

                if (hoverCard != null) {

                    hoverCard.remove();

                    Card nu = overlaps();
                    if (nu != null) {

                        Card r = getCardFromAll(c);

                        if (r != null) {

                            r.setSelected(false);
                            c.setSelected(false);
                            c.changeCard(StaticVariables.BLANK_CARD.clone());

                        }

                        playerDeck.fixDeck();

                    } else {

                        c.setSelected(false);
                    }
                    hoverCard = null;
                }

            }

            public void changePos(int x, int y) {
                Vector3 i = deckStage.getCamera().unproject(new Vector3(x, y, 0));
                hoverCard.setPosition(i.x - hoverCard.getWidth() / 2, i.y - hoverCard.getHeight() / 2);

            }

            public Card overlaps() {
                for (Card c : allCards) {
                    if (new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight()).overlaps(new Rectangle(hoverCard.getX(), hoverCard.getY(), hoverCard.getWidth(), hoverCard.getHeight()))) {
                        return c;
                    }
                }
                return null;
            }

        };

        for (int i = 0; i < 12; i++) {

            Card c = playerDeck.getCard(i);
            c.setPosition(8 + i * c.getWidth() + i * 6, 467);
            deckStage.addActor(c);

            c.addListener(upToDown);
            c.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                    Card c = ((Card) event.getTarget());
                    if (!c.getName().equals("blank") && !c.isSelected()) {

                        Card r = getCardFromAll(c);
                        r.setSelected(false);

                        c.changeCard(StaticVariables.BLANK_CARD.clone());
                        playerDeck.fixDeck();
                    }

                }
            });

        }

        for (int i = 0; i < allCards.size(); i++) {

            Card c = allCards.get(i);
            c.setPosition(42 + i * c.getWidth() + i * 10, 360 - 150 - 10);
            deckStage.addActor(c);

            c.addListener(downToUp);
            c.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                    Card c = ((Card) event.getTarget());
                    if (!c.getName().equals("blank") && !c.isSelected()) {
                        int r = playerDeck.getFirstIndexValid();
                        if (r != -1) {
                            Card playerCard = playerDeck.getCard(r);
                            playerCard.changeCard(c);
                            c.setSelected(true);
                        }

                    }

                }
            });

        }

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

    public Card getCardFromAll(Card c) {
        for (Card i : allCards) {

            if (c.equals(i)) {

                return i;

            }

        }
        return null;
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
