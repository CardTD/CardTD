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

import java.util.ArrayList;
import java.util.List;

class AllCards {

    private List<Card> allCards = new ArrayList<>();
    private CurrentDeck currentDeck;
    private Stage stage;

    public AllCards(Stage stage) {

        this.stage = stage;

        for (Card i : StaticVariables.ALL_CARDS) {
            allCards.add(new Card(i.getName(), i.getTexture(), i.getCost()));

        }

    }

    public void setIntegrationWith(CurrentDeck currentDeck) {
        this.currentDeck = currentDeck;
        toStage();
    }

    private void toStage() {

        for (int i = 0; i < allCards.size(); i++) {

            Card c = allCards.get(i);
            c.setPosition(42 + i * c.getWidth() + i * 10, 360 - 150 - 10);
            stage.addActor(c);

            c.addListener(getDownToUpListener());
            c.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                    Card c = ((Card) event.getTarget());
                    if (!c.getName().equals("blank") && !c.isSelected()) {
                        int r = currentDeck.getFirstIndexValid();
                        if (r != -1) {
                            Card playerCard = currentDeck.getCard(r);
                            playerCard.changeCard(c);
                            c.setSelected(true);
                        }

                    }

                }
            });

        }
    }

    private DragListener getDownToUpListener() {

        DragListener downToUp = new DragListener() {

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
        return downToUp;
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
