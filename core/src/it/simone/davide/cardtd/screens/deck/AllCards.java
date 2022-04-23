package it.simone.davide.cardtd.screens.deck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.classes.Card;

import java.util.ArrayList;
import java.util.List;

class AllCards{

    //TODO creare delle mappe con tutte le carte e tutti i nemici
    private final List<Card> allCards = new ArrayList<>();
    private int rows = 1;
    private CurrentDeck currentDeck;
    private final Stage stage;
    private final float offsetX = 36.5f, cardGap = 19, offsetY = 350 + 10 - 150 - 12.5f;

    public AllCards(Stage stage) {

        this.stage = stage;

        for (Card i : StaticVariables.ALL_CARDS) {
            allCards.add(i.clone());

        }

        int i = allCards.size();
        while (i > 10) {
            i -= 10;
            rows++;

        }

    }

    public void setIntegrationWith(CurrentDeck currentDeck) {
        this.currentDeck = currentDeck;
        for (Card c : currentDeck.getPlayerDeck().getCards()) {
            for (Card a : allCards) {

                if (c.equals(a)) {

                    a.setSelected(true);
                }
            }

        }

        toStage();
    }

    private void toStage() {
        float offX = 0;
        for (int i = 0; i < Math.min(20, allCards.size()); i++) {

            Card c = allCards.get(i);
            float offY = offsetY;

            if (i > 9) {
                offY -= 25 + 150;

            }
            if (i == 10) {
                offX = 0;

            }

            c.setPosition(offsetX + offX * c.getWidth() + offX * cardGap, offY);
            stage.addActor(c);

            c.addListener(getDownToUpListener());
            c.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                    final Card c = ((Card) event.getTarget());
                    if (!c.getName().equals("blank") && !c.isSelected()) {
                        int r = currentDeck.getFirstIndexValid();
                        if (r != -1) {
                            final Card playerCard = currentDeck.getCard(r);
                            final Card clone = c.clone();
                            stage.addActor(clone);
                            clone.setPosition(c.getX(), c.getY());
                            playerCard.setName(c.getName());
                            clone.addAction(Actions.sequence(Actions.moveTo(playerCard.getX(), playerCard.getY(), 0.2f), Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    Card r = currentDeck.getCardByName(c.getName());
                                    if (r != null) {

                                        r.changeCard(c);
                                    }

                                }
                            }), Actions.removeActor()));

                            c.setSelected(true);
                        }

                    }

                }
            });
            offX++;
        }
    }

    private DragListener getDownToUpListener() {

        return new DragListener() {

            Card hoverCard;

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {

                super.dragStart(event, x, y, pointer);

                Card c = ((Card) event.getTarget());
                if (!c.isSelected()) {

                    hoverCard = c.getHoverCard();

                    changePos(Gdx.input.getX(), Gdx.input.getY());
                    stage.addActor(hoverCard);
                    c.setSelected(true);
                }

            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                super.drag(event, x, y, pointer);
                if (hoverCard != null) {

                    changePos(Gdx.input.getX(), Gdx.input.getY());
                    Card nu = currentDeck.overlaps(hoverCard);

                    if (nu != null) {

                        int i = currentDeck.getFirstIndexValid();

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

                    Card nu = currentDeck.overlaps(hoverCard);
                    if (nu != null) {

                        nu.changeCard(hoverCard);

                    } else {

                        c.setSelected(false);
                    }
                    hoverCard = null;
                }

            }

            public void changePos(int x, int y) {
                Vector3 i = stage.getCamera().unproject(new Vector3(x, y, 0));
                hoverCard.setPosition(i.x - hoverCard.getWidth() / 2, i.y - hoverCard.getHeight() / 2);

            }

        };
    }

    public Card getCard(Card c) {
        for (Card i : allCards) {

            if (c.equals(i)) {

                return i;

            }

        }
        return null;
    }

    public Card overlaps(Card card) {
        for (Card c : allCards) {
            if (new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight()).overlaps(new Rectangle(card.getX(), card.getY(), card.getWidth(), card.getHeight()))) {
                return c;
            }
        }
        return null;
    }

}
