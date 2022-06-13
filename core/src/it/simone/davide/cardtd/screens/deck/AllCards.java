package it.simone.davide.cardtd.screens.deck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.classes.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * AllCards represents all cards in the collection. It also manages interaction with the current deck ({@link CurrentDeck})
 *
 * @see DeckMenu
 */
class AllCards {

    /**
     * The list of all cards in the collection
     */
    private final List<Card> allCards = new ArrayList<>();

    /**
     * The current deck of the player
     *
     * @see CurrentDeck
     */
    private CurrentDeck currentDeck;

    /**
     * The stage where are placed the cards
     */
    private final Stage stage;

    /**
     * Create a new AllCards
     *
     * @param stage the stage where are placed the cards
     */
    public AllCards(Stage stage) {

        this.stage = stage;

        for (Card i : GameObjects.CARDS.values()) {
            allCards.add(i.clone());

        }

        int i = allCards.size();
        while (i > 10) {
            i -= 10;

        }

    }

    /**
     * Set with what object will have to collaborate for moving cards
     *
     * @param currentDeck the object representing the current deck of the player
     * @see CurrentDeck
     */
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
            float offsetY = 350 + 10 - 150 - 12.5f;
            float offY = offsetY;

            if (i > 9) {
                offY -= 25 + 150;

            }
            if (i == 10) {
                offX = 0;

            }

            float offsetX = 36.5f;
            float cardGap = 19;
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

    /**
     * Given a card from {@link CurrentDeck}, it returns the same card in the collection
     *
     * @param c the card
     * @return the card in the collection
     */
    public Card getCard(Card c) {
        for (Card i : allCards) {
            if (c.getName().equals(i.getName())) {

                return i;

            }

        }
        return null;
    }

    /**
     * Returns if, during the dragging period, the card is on top of the current deck section
     *
     * @param card the card
     * @return the card on which you are above, or {@code null}
     */
    public Card overlaps(Card card) {
        for (Card c : allCards) {
            if (new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight()).overlaps(new Rectangle(card.getX(), card.getY(), card.getWidth(), card.getHeight()))) {
                return c;
            }
        }
        return null;
    }

}
