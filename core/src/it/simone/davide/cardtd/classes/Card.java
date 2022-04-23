package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Card extends Image implements Cloneable {

    private String name;
    private Texture cardTexture, placedTexture;
    private int cost;
    private boolean isSelected = false;

    public Card(String name, Texture cardtexture, Texture placedTexture) {
        super(cardtexture);
        this.name = name;
        this.cardTexture = cardtexture;
        this.cost = cost;
        this.placedTexture=placedTexture;
        setSize(100, 150);
    }

    public void setName(String name) {

        this.name = name;

    }

    public Texture getPlacedTexture() {
        return placedTexture;
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
    }

    public void changeCard(Card card) {

        this.name = card.name;
        this.cardTexture = card.cardTexture;
        this.placedTexture = card.placedTexture;

        setDrawable(card.getDrawable());

    }

    public void setSelected(boolean selected) {

        if (selected) {
            getColor().a = 0.5f;
            isSelected = true;

        } else {
            getColor().a = 1f;
            isSelected = false;
        }

    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public Card clone() {
        return new Card(name, cardTexture, placedTexture);
    }

    public Card getHoverCard() {
        Card x = new Card(name, cardTexture, placedTexture);

        x.getColor().a = 0.5f;

        return x;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", texture=" + cardTexture +
                ", cost=" + cost +
                ", isSelected=" + isSelected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (cost != card.cost) return false;
        if (!getName().equals(card.getName())) return false;
        return cardTexture.toString().equals(card.cardTexture.toString());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + cardTexture.hashCode();
        result = 31 * result + cost;
        return result;
    }

    public Texture getCardTexture() {
        return cardTexture;
    }

    public int getCost() {
        return cost;
    }
}
