package it.simone.davide.cardtd.classes;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final int nunOfCards;
    private final List<Card> cards;

    public Deck(int nunOfCards) {
        this.nunOfCards = nunOfCards;
        cards = new ArrayList<>();
    }

    public boolean canAddCard() {
        return (cards.size() < nunOfCards);

    }

    public int getNunOfCards() {
        return nunOfCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {

        if (canAddCard())
            cards.add(card);

    }

    //write a method that add more cards to the deck
    public void addCards(List<Card> cards) {

        for (Card i : cards) {
            addCard(i);
        }

    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeCard(int index) {
        if (index < cards.size() && index >= 0)
            cards.remove(index);
    }

    public boolean isDeckValid() {
        return cards.size() == nunOfCards;
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "nunOfCards=" + nunOfCards +
                ", cards=" + cards +
                '}';
    }

    public void swapElem(Card card1, Card card2) {
        Card tmp = card1.clone();

        card1.changeCard(card2);
        card2.changeCard(tmp);

    }

    public void fixDeck() {

        for (int i = 1; i < cards.size(); i++) {
            if (!cards.get(i).getName().equals("blank") && cards.get(i - 1).getName().equals("blank")) {
                swapElem(cards.get(i), cards.get(i - 1));

            }

        }

    }

}
