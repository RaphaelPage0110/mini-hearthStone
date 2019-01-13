package business.messageModels;

import abstracts.Card;
import impl.ConcreteMinion;
import impl.ConcreteSpell;
import inter.Effect;
import inter.TargetedEffect;
import org.jetbrains.annotations.NotNull;

public class MyCardMessage {

    protected String id;
    /**
     * Indicates the points of damage that this card can inflict.
     */
    private int damagePoints;

    /**
     * The name of the card
     */
    private String name;

    private String imgurl;

    /**
     * Indicates the mana cost of this card.
     */
    private int requiredMana;

    /**
     * Indicates the type of this card that can be COMMON, PALADIN, MAGE, or WARRIOR.
     */
    private String type;

    private int healthPoints;

    private String cardType;

    private String text;

    private boolean taunt;

    private boolean canAttack;

    private boolean lifeSteal;

    private boolean targetSpell;


    public MyCardMessage(@NotNull Card card) {
        id = card.getUniqueID();
        type = card.getType().toString();
        requiredMana = card.getRequiredMana();
        name = card.getName();
        imgurl = card.getImgurl();
        text = card.getText();
        if (card instanceof ConcreteMinion) {
            cardType = "minion";
            healthPoints = ((ConcreteMinion) card).getCurrentHealthPoints();
            damagePoints = card.getDamagePoints() + card.getPlayer().getMyDamageAura();
            taunt = ((ConcreteMinion) card).isHasTaunt();
            canAttack = ((ConcreteMinion) card).isCanAttack();
            lifeSteal = ((ConcreteMinion) card).isHasLifesteal();
        }
        if (card instanceof ConcreteSpell) {
            cardType = "spell";
            targetSpell = false;
            for (Effect effect : card.getMyEffects()) {
                if (effect instanceof TargetedEffect) {
                    targetSpell = true;
                }
            }
        }

    }

    public boolean isTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean isLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(boolean lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public boolean isTargetSpell() {
        return targetSpell;
    }

    public void setTargetSpell(boolean targetSpell) {
        this.targetSpell = targetSpell;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }


}
