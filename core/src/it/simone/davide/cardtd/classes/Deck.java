package it.simone.davide.cardtd.classes;

import java.util.*;

/**
 * This class manage the deck in the game
 */
public class Deck {

    /**
     * The number of cards that will go into the deck
     */
    private final int nunOfCards;
    /**
     * The list of all available cards
     */
    private final List<Card> cards;

    /**
     * Creates new deck
     *
     * @param nunOfCards the number of cards in the deck
     */
    public Deck(int nunOfCards) {
        this.nunOfCards = nunOfCards;
        cards = new ArrayList<>();
    }

    /**
     * Check if is possible to add one card to the deck
     *
     * @return if is possible to add one card to the deck
     */
    public boolean canAddCard() {
        return (cards.size() < nunOfCards);

    }

    /**
     * Gets the list of all available cards
     *
     * @return the list of all available cards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Adds card to deck
     *
     * @param card the current card that will be inserted in the deck
     */
    public void addCard(Card card) {

        if (canAddCard())
            cards.add(card);

    }

    /**
     * Return card by id
     *
     * @param i the id of the card
     * @return card by id
     */
    public Card getCard(int i) {
        return cards.get(i);
    }

    /**
     * Returns a string containing the number of cards in the deck and all the card of the deck
     *
     * @return
     */
    @Override
    public String toString() {
        return "Deck{" +
                "nunOfCards=" + nunOfCards +
                ", cards=" + cards +
                '}';
    }

    /**
     * Swaps cards
     *
     * @param card1 First card to be swapped with other
     * @param card2 Second card to be swapped with other
     */
    public void swapElem(Card card1, Card card2) {
        Card tmp = card1.clone();

        card1.changeCard(card2);
        card2.changeCard(tmp);

    }

    /**
     *  Arrange the deck so that at the beginning we are all the cards and after the blank cards
     */
    public void fixDeck() {

        for (int i = 1; i < cards.size(); i++) {
            if (!cards.get(i).getName().equals("blank") && cards.get(i - 1).getName().equals("blank")) {
                swapElem(cards.get(i), cards.get(i - 1));

            }

        }

    }

    /**
     * Return the queue with the cards to use in the game
     *
     * @return the queue with the cards to use in the game
     */
    public Queue getRealDeck() {
        Queue<Card> c;
        List<Card> l = new LinkedList<>();

        for (Card s : cards) {
            if (!s.getName().equals("blank")) {

                l.add(s.clone());
            }

        }
        Collections.shuffle(l);
        c = new LinkedList<>(l);

        return c;
    }

}
