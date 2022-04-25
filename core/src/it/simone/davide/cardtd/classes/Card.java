package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.enums.BuildType;

public class Card extends Image implements Cloneable {

    private String name;
    private Texture cardTexture;
    private int cost;
    private boolean isSelected = false;
    private Build build;
    private BuildType buildType;

    public Card(String name, Texture cardtexture, BuildType buildType) {
        super(cardtexture);
        this.name = name;
        this.cardTexture = cardtexture;
        this.cost = cost;
        this.buildType = buildType;
        setSize(100, 150);
        build = GameObjects.BUILDINGS.get(buildType);

    }

    public void setName(String name) {

        this.name = name;

    }

    public Build getBuild() {
        return build;
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
    }

    public void changeCard(Card card) {

        this.name = card.name;
        this.cardTexture = card.cardTexture;
        this.cost = card.cost;
        this.buildType = card.buildType;
        this.build = card.build;

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

        return new Card(name, cardTexture, buildType);
    }

    public Card getHoverCard() {
        Card x = new Card(name, cardTexture, buildType);

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
        if (!cardTexture.equals(card.cardTexture)) return false;
        return buildType == card.buildType;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + cardTexture.hashCode();
        result = 31 * result + getCost();
        result = 31 * result + (isSelected() ? 1 : 0);
        result = 31 * result + buildType.hashCode();
        return result;
    }

    public int getCost() {
        return cost;
    }
}
