package it.simone.davide.cardtd;

import com.badlogic.gdx.graphics.Texture;
import it.simone.davide.cardtd.classes.Card;
import it.simone.davide.cardtd.classes.Enemy;
import it.simone.davide.cardtd.enums.EnemyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static final String BACKBUTTON = "back.png";
    public static final String BACKBUTTON_PRESSED = "backpressend.png";
    public static final String FIRSTMAP = "mappa1.png";
    public static final String TOWER = "placedtower.png";
    public static final String TMXMAP = "mappa1.tmx";
    public static final String IN_GAME_DECK = "ingamedeck.png";
    public static final List<Card> ALL_CARDS;
    public static Map<EnemyType, ? super Enemy> ENEMIES;

    public static final Card BLANK_CARD;

    //get card by name
    public static Card getCardByName(String name) {
        for (Card c : ALL_CARDS) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

    static {
        Texture i = CardTDGame.assetManager.get(CARDSLOT);
        BLANK_CARD = new Card("blank", i, null);

        ALL_CARDS = new ArrayList<>();
//TODO register all in the asset manager and register them in the list

        ALL_CARDS.add(new Card("1", new Texture("tower.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("2", new Texture("2.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("3", new Texture("3.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("4", new Texture("4.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("5", new Texture("cardsample.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("6", new Texture("cardsample.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("7", new Texture("cardsample.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("8", new Texture("cardsample.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("9", new Texture("cardsample.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("10", new Texture("cardsample.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("11", new Texture("cardsample.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("12", new Texture("cardsample.png"), new Texture("placedtower.png")));
        ALL_CARDS.add(new Card("13", new Texture("cardsample.png"), new Texture("placedtower.png")));

    }

}
