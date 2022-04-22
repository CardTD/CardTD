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

    public static final String CARDSLOT = "cardSlot.png";

    public static final List<Card> ALL_CARDS;

    public static final Card BLANK_CARD;

    static {
        Texture i = CardTDGame.assetManager.get(CARDSLOT);
        BLANK_CARD = new Card("blank", i, 10);

        ALL_CARDS = new ArrayList<>();

        ALL_CARDS.add(new Card("un", new Texture("cards/1.png"), 10));
        ALL_CARDS.add(new Card("1", new Texture("cards/2.png"), 10));
        ALL_CARDS.add(new Card("4", new Texture("cards/3.png"), 10));
        ALL_CARDS.add(new Card("3", new Texture("cards/4.png"), 10));
        ALL_CARDS.add(new Card("4", new Texture("cards/1.png"), 10));
        ALL_CARDS.add(new Card("5", new Texture("cards/2.png"), 10));
        ALL_CARDS.add(new Card("6", new Texture("cards/3.png"), 10));
        ALL_CARDS.add(new Card("7", new Texture("cards/4.png"), 10));
        ALL_CARDS.add(new Card("8", new Texture("cards/1.png"), 10));
        ALL_CARDS.add(new Card("9", new Texture("cards/2.png"), 10));
        ALL_CARDS.add(new Card("10", new Texture("cards/3.png"), 10));
        ALL_CARDS.add(new Card("11", new Texture("cards/4.png"), 10));
        ALL_CARDS.add(new Card("12", new Texture("cards/4.png"), 10));
        ALL_CARDS.add(new Card("13", new Texture("cards/4.png"), 10));
        ALL_CARDS.add(new Card("14", new Texture("cards/4.png"), 10));
        ALL_CARDS.add(new Card("15", new Texture("cards/4.png"), 10));

    }





}
