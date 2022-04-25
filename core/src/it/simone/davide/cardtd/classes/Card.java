package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import it.simone.davide.cardtd.CardTDGame;
import it.simone.davide.cardtd.StaticVariables;

public class Card extends Image implements Cloneable {

    private String name;
    private Texture cardTexture, placedTexture;
    private int cost;
    private boolean isSelected = false;
    private Build build;

    public Card(String name, Texture cardtexture, Texture placedTexture) {
        super(cardtexture);
        this.name = name;
        this.cardTexture = cardtexture;
        this.cost = cost;
        this.placedTexture = placedTexture;
        setSize(100, 150);
        build = new Build(CardTDGame.assetManager.<Texture>get(StaticVariables.TOWER), new Texture("bullet.png"), 200, 1f, 1, 0,0);

    }

    public void setName(String name) {

        this.name = name;

    }

    public Build getBuild() {
        return build;
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

        if (getCost() != card.getCost()) return false;
        if (isSelected() != card.isSelected()) return false;
        if (!getName().equals(card.getName())) return false;
        if (!getCardTexture().equals(card.getCardTexture())) return false;
        return getPlacedTexture().equals(card.getPlacedTexture());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getCardTexture().hashCode();
        result = 31 * result + getPlacedTexture().hashCode();
        result = 31 * result + getCost();
        result = 31 * result + (isSelected() ? 1 : 0);
        return result;
    }

    public Texture getCardTexture() {
        return cardTexture;
    }

    public int getCost() {
        return cost;
    }
}
