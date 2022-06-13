package it.simone.davide.cardtd.classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import it.simone.davide.cardtd.GameObjects;
import it.simone.davide.cardtd.enums.BuildType;

/**
 * Manage the cards of the game
 *
 * @see Image
 */
public class Card extends Image implements Cloneable {

    /**
     * The name of the card
     */
    private String name;
    /**
     * The texture of the card
     */
    private Texture cardTexture;
    /**
     * the cost of the card
     */
    private int cost;

    /**
     * If the card is selected or not
     */
    private boolean isSelected = false;

    /**
     * The structure associated to the card
     */
    private Build build;

    /**
     * The structure type associated to the card
     */
    private BuildType buildType;

    /**
     * the default color of the card
     */
    private Color defColor;

    /**
     * Create a new card
     *
     * @param name        the name of the card
     * @param cardTexture the texture of the card
     * @param buildType   the type of the structure
     * @param cost        the cost of the card
     */
    public Card(String name, Texture cardTexture, BuildType buildType, int cost) {
        super(cardTexture);
        this.name = name;
        this.cardTexture = cardTexture;
        this.cost = cost;
        this.buildType = buildType;
        setSize(100, 150);
        build = GameObjects.BUILDINGS.get(buildType);
        Color c = getColor();
        defColor = new Color(c.r, c.r, c.b, c.a);

    }

    /**
     * Sets the name of the card
     *
     * @param name of the card
     */
    public void setName(String name) {

        this.name = name;

    }

    /**
     * Returns the structure type associated to the card
     *
     * @return the structure type associated to the card
     */
    public Build getBuild() {
        return build;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColor(Color color) {
        super.setColor(color);
    }

    /**
     * Replacing the card with another one
     *
     * @param card the card to be replaced
     */
    public void changeCard(Card card) {

        this.name = card.name;
        this.cardTexture = card.cardTexture;
        this.cost = card.cost;
        this.buildType = card.buildType;
        this.build = card.build;

        setDrawable(card.getDrawable());

    }

    /**
     * Sets if the card is selected or not
     *
     * @param selected if the card is selected
     */
    public void setSelected(boolean selected) {

        if (selected) {
            getColor().a = 0.5f;
            isSelected = true;

        } else {
            getColor().a = 1f;
            isSelected = false;
        }

    }

    /**
     * Returns if the card is selected or not
     *
     * @return if the card is selected or not
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Card clone() {

        return new Card(name, cardTexture, buildType, cost);
    }

    /**
     * Gets the hovered card
     *
     * @return the hovered card
     */
    public Card getHoverCard() {
        Card x = new Card(name, cardTexture, buildType, cost);

        x.getColor().a = 0.5f;

        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + cardTexture.hashCode();
        result = 31 * result + getCost();
        result = 31 * result + (isSelected() ? 1 : 0);
        result = 31 * result + buildType.hashCode();
        return result;
    }

    /**
     * Returns the cost of the card
     *
     * @return the cost of the card
     */
    public int getCost() {
        return cost;
    }

    /**
     * Checks if then buy the card or not
     *
     * @param balance the balance of the player
     * @return if then buy the card or not
     */
    public boolean isCanBuy(int balance) {

        boolean t = balance >= cost;

        setCanBuy(t);
        return t;
    }

    /**
     * if then buy the card or not
     *
     * @param canBuy if you can buy the card
     */
    public void setCanBuy(boolean canBuy) {

        float s = 0.5f;
        if (canBuy) {

            setColor(defColor.r + s, defColor.g + s, defColor.b + s, getColor().a);

        } else {
            setColor(defColor.r - s, defColor.g - s, defColor.b - s, getColor().a);

        }

    }
}
