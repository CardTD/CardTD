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
import it.simone.davide.cardtd.classes.Deck;

/**
 * CurrentDeck represents the player’s deck. It also handles interacting with the rest of the cards in the collection ({@link AllCards})
 *
 * @see DeckMenu
 */
class CurrentDeck {

    /**
     * The deck of the player
     */
    private final Deck playerDeck;

    /**
     * The stage where are placed the cards
     */
    private final Stage stage;

    /**
     * The rest of the cards in the collection ({@link AllCards})
     */
    private AllCards allCards;

    /**
     * Create a new CurrentDeck
     *
     * @param playerDeck the deck of the player
     * @param stage      the stage where are placed the cards
     */
    public CurrentDeck(Deck playerDeck, Stage stage) {
        this.playerDeck = playerDeck;
        this.stage = stage;

    }

    /**
     * Set with what object will have to collaborate for moving cards
     *
     * @param allCards the object representing the rest of the cards
     * @see AllCards
     */
    public void setIntegrationWith(AllCards allCards) {
        this.allCards = allCards;
        toStage();
    }

    private void toStage() {

        for (int i = 0; i < 12; i++) {

            Card c = playerDeck.getCard(i);
            int offsetX = 8;
            int cardGap = 6;
            int offsetY = 467;
            c.setPosition(offsetX + i * c.getWidth() + i * cardGap, offsetY);
            stage.addActor(c);

            c.addListener(getUpToDownListener());
            c.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                    final Card c = ((Card) event.getTarget());
                    if (!c.getName().equals("blank") ) {
                        final Card r = allCards.getCard(c);

                        final Card clone = c.clone();
                        stage.addActor(clone);
                        clone.setPosition(c.getX(), c.getY());
                        c.changeCard(StaticVariables.BLANK_CARD.clone());

                        playerDeck.fixDeck();
                        clone.addAction(Actions.sequence(Actions.moveTo(r.getX(), r.getY(), 0.2f), Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                r.setSelected(false);

                            }
                        }), Actions.removeActor()));

                    }

                }
            });

        }

    }

    private DragListener getUpToDownListener() {

        return new DragListener() {

            Card hoverCard;

            @Override
            public void dragStart(InputEvent event, float x, float y, int pointer) {

                super.dragStart(event, x, y, pointer);
                Card c = ((Card) event.getTarget());
                if (!c.isSelected() && !c.getName().equals("blank")) {
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
                }

            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                super.dragStop(event, x, y, pointer);
                Card c = ((Card) event.getTarget());

                if (hoverCard != null) {

                    hoverCard.remove();

                    Card nu = allCards.overlaps(hoverCard);
                    if (nu != null) {

                        Card r = allCards.getCard(c);

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
                Vector3 i = stage.getCamera().unproject(new Vector3(x, y, 0));
                hoverCard.setPosition(i.x - hoverCard.getWidth() / 2, i.y - hoverCard.getHeight() / 2);

            }

        };
    }

    /**
     * Initially the deck consists of blank cards. The method returns the index of the first blank card and then replaceable
     *
     * @return the index of the first blank card or {@code -1}
     */
    public int getFirstIndexValid() {

        for (int i = 0; i < playerDeck.getCards().size(); i++) {

            if (playerDeck.getCards().get(i).getName().equals("blank")) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return the card by its index on the list
     *
     * @param i the idex of the card
     * @return the card
     */
    public Card getCard(int i) {

        return playerDeck.getCards().get(i);

    }

    /**
     * Returns if, during the dragging period, the card is on top of the rest of the cards in the collection
     *
     * @param card the card
     * @return the card on which you are above, or {@code null}
     */
    public Card overlaps(Card card) {
        for (Card c : playerDeck.getCards()) {
            if (new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight()).overlaps(new Rectangle(card.getX(), card.getY(), card.getWidth(), card.getHeight()))) {
                return c;
            }
        }
        return null;
    }

    /**
     * Returns the player deck
     *
     * @return the player deck
     */
    public Deck getPlayerDeck() {
        return playerDeck;
    }

    /**
     * Return the card by its name
     *
     * @param name the name of a card
     * @return the card by its name
     */
    public Card getCardByName(String name) {
        for (Card c : playerDeck.getCards()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
