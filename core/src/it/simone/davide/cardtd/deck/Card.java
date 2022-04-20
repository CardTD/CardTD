package it.simone.davide.cardtd.deck;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Card extends Image implements Cloneable {

    private String name;
    private Texture texture;
    private int cost;
    private boolean isSelected = false;

    public Card(String name, Texture texture, int cost) {
        super(texture);
        this.name = name;
        this.texture = texture;
        this.cost = cost;
    }

    public void changeCard(Card card) {

        this.name = card.name;
        this.texture = card.texture;
        this.cost = card.cost;

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
        return new Card(name, texture, cost);
    }

    public Card getHoverCard() {
        Card x = new Card(name, texture, cost);

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
                ", texture=" + texture +
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
        return texture.toString().equals(card.texture.toString());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + texture.hashCode();
        result = 31 * result + cost;
        return result;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getCost() {
        return cost;
    }
}
