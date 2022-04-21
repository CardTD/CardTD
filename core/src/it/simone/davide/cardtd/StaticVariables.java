package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.deck.Card;

import java.util.ArrayList;
import java.util.List;

public class StaticVariables {

    //screen settings
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;

    //main menu variables
    public static final String GAMENAME = "CardTD";

    public static final String MAIN_MENU_IMG = "mainmenu.png";
    public static final String NAVE = "nave.png";
    public static final String DECKMENU = "deckmenu.png";
    public static final String CARDSLOT = "blank.png";
    public static final String DECKBG = "assi.png";

    public static final List<Card> ALL_CARDS;

    public static final Card BLANK_CARD;

    static {
        Texture i = CardTDGame.assetManager.get(CARDSLOT);
        BLANK_CARD = new Card("blank", i, 10);

        ALL_CARDS = new ArrayList<>();

        ALL_CARDS.add(new Card("un", new Texture("cardsample.png"), 10));
        ALL_CARDS.add(new Card("1", new Texture("cardsample.png"), 10));
        ALL_CARDS.add(new Card("4", new Texture("cardsample.png"), 10));
        ALL_CARDS.add(new Card("3", new Texture("cardsample.png"), 10));
        ALL_CARDS.add(new Card("2", new Texture("cardsample.png"), 10));
        ALL_CARDS.add(new Card("5", new Texture("cardsample.png"), 10));
        ALL_CARDS.add(new Card("6", new Texture("cardsample.png"), 10));
        ALL_CARDS.add(new Card("7", new Texture("cardsample.png"), 10));

    }

}
