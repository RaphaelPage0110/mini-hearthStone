package repositories;

import abstracts.Card;
import abstracts.CardType;
import impl.Player;
import inter.Effect;
import inter.Target;

import java.util.ArrayList;

public class MyCardMessage {

    protected String id;
    /**
     * Indicates the points of damage that this card can inflict.
     */
    protected int damagePoints;

    /**
     * The name of the card
     */
    protected String name;

    protected String imgurl;

    /**
     * Indicates the mana cost of this card.
     */
    protected int requiredMana;

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

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MyCardMessage(Card card) {
        id = card.getId();
        type = card.getType().toString();
        requiredMana = card.getRequiredMana();
        damagePoints = card.getDamagePoints();
        name = card.getName();
        imgurl = card.getImgurl();

    }
}
