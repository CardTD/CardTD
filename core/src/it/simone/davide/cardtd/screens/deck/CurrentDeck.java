package it.simone.davide.cardtd.screens.deck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import it.simone.davide.cardtd.StaticVariables;
import it.simone.davide.cardtd.deck.Card;
import it.simone.davide.cardtd.deck.Deck;

class CurrentDeck {

    private Deck playerDeck;
    private Stage stage;
    private AllCards allCards;

    public CurrentDeck(Deck playerDeck, Stage stage) {
        this.playerDeck = playerDeck;
        this.stage = stage;

    }

    public void setIntegrationWith(AllCards allCards) {
        this.allCards = allCards;
        toStage();
    }

    private void toStage() {

        for (int i = 0; i < 12; i++) {

            Card c = playerDeck.getCard(i);
            c.setPosition(8 + i * c.getWidth() + i * 6, 467);
            stage.addActor(c);

            c.addListener(getUpToDownListener());
            c.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                    Card c = ((Card) event.getTarget());
                    if (!c.getName().equals("blank") && !c.isSelected()) {

                        Card r = allCards.getCard(c);
                        r.setSelected(false);

                        c.changeCard(StaticVariables.BLANK_CARD.clone());
                        playerDeck.fixDeck();
                    }

                }
            });

        }

    }

    private DragListener getUpToDownListener() {

        DragListener upToDown = new DragListener() {

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
        return upToDown;
    }

    public int getFirstIndexValid() {

        for (int i = 0; i < playerDeck.getCards().size(); i++) {

            if (playerDeck.getCards().get(i).getName().equals("blank")) {
                return i;
            }
        }
        return -1;
    }

    public Card getCard(int i) {

        return playerDeck.getCards().get(i);

    }

    public Card overlaps(Card card) {
        for (Card c : playerDeck.getCards()) {
            if (new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight()).overlaps(new Rectangle(card.getX(), card.getY(), card.getWidth(), card.getHeight()))) {
                return c;
            }
        }
        return null;
    }
}
