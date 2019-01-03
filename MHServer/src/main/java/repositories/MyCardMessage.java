package repositories;

import abstracts.Card;
import abstracts.CardType;
import impl.Player;
import inter.Effect;
import inter.Target;

import java.util.ArrayList;

public class MyCardMessage {

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    protected String imgurl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Indicates the type of this card that can be COMMON, PALADIN, MAGE, or WARRIOR.
     */
    protected String type;

    public int getRequiredMana() {
        return requiredMana;
    }

    public void setRequiredMana(int requiredMana) {
        this.requiredMana = requiredMana;
    }

    /**
     * Indicates the mana cost of this card.
     */
    protected int requiredMana;

    public int getDamagePoints() {
        return damagePoints;
    }

    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Indicates the points of damage that this card can inflict.
     */
    protected int damagePoints;

    /**
     * The name of the card
     */
    protected String name;

    public MyCardMessage(Card card) {
        type = card.getType().toString();
        requiredMana = card.getRequiredMana();
        damagePoints = card.getDamagePoints();
        name = card.getName();
        imgurl = card.getImgurl();
    }
}
