package abstracts;

import impl.Player;
import inter.Effect;
import inter.Target;
import org.springframework.data.annotation.Id;


/**
 * Abstract representation of a Hero.
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public abstract class Hero implements Target {

    /**
     * The ID of this Hero.
     */
    @Id
    protected String id;

    /**
     * The type of the hero (mage, warrior or paladin)
     */
    protected CardType heroType;

    /**
     *  The name of the hero
     */
    protected String heroName;

    /**
     * Indicates the maximum number of health points that this Hero has.
     */
    protected int maxHealthPoints;

    /**
     * Indicates the current number of health points that this Hero has.
     */
    protected int currentHealthPoints;

    /**
     * Indicates the number of armor points that this Hero has.
     */
    protected int armorPoints;

    /**
     * Reference the list of actions or behaviors of this card.
     */
    protected Effect myEffect;

    protected Player myPlayer;

    /**
     * Returns value of id
     * @return id the id of the hero
     */
    public String getId() {
        return id;
    }

    /**
     * Returns value of currentHealthPoints
     * @return healthPoints the current health points of the hero
     */
    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    /**
     * Sets new value of currentHealthPoints
     * @param hp the current health points of the hero
     */
    public void setCurrentHealthPoints(int hp) {
        this.currentHealthPoints = hp;
    }

    /**
     * Returns value of armorPoints
     * @return armorPoints the current armor points of the hero
     */
    public int getArmorPoints() {
        return armorPoints;
    }

    /**
     * Sets new value of armorPoints
     * @param armor the armor points of the hero
     */
    public void setArmorPoints(int armor) {
        this.armorPoints = armor;
    }

    /**
     * Returns value of heroName
     * @return heroName the name of the hero
     */
    public String getHeroName() {
        return heroName;
    }

    /**
     * Sets new value of heroName
     * @param heroName the name of the hero
     */
    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    /**
     * Returns the special ability of a hero
     * @return myEffect the ability of a hero
     */
    public Effect getMyEffect() {
        return myEffect;
    }

    /**
     * Sets the new value of myEffects
     * @param myEffect the special ability of a hero
     */
    public void setMyEffect(Effect myEffect) {
        this.myEffect = myEffect;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }


    /**
     * {@inheritDoc}
     */
    public int takeDamage(int damageTaken) {

        if (damageTaken < this.armorPoints) {
            this.armorPoints -= damageTaken;
        } else {
            this.currentHealthPoints = this.currentHealthPoints + this.armorPoints - damageTaken;
            this.armorPoints = 0;
        }

        if (this.isDead()) {

            this.dies();

        }

        return damageTaken;
    }

    /**
     * Allows to give a few health points back to a living hero.
     * @param healingPoints the number of health points to be returned.
     */
    public void heal(int healingPoints) {
        this.currentHealthPoints = Math.min(maxHealthPoints,this.currentHealthPoints + healingPoints);
    }

    public void addArmor(int armor) {
        this.armorPoints += armor;
    }

    /**
     * check if the hero is dead
     * @return true if the hero is dead
     */
    public boolean isDead() {

        return currentHealthPoints <= 0;

    }

    /**
     * what happens when the hero dies
     */
    public void dies() {

        myPlayer.lost();


    }

    public CardType getHeroType() {
        return heroType;
    }

    public void setHeroType(CardType heroType) {
        this.heroType = heroType;
    }

    @Override
    public String toString() {
        return String.format(
                "Hero[id=%s, heroName='%s', maxHealthPoints='%s', armorPoints='%s']",
                id, heroName, maxHealthPoints, armorPoints);
    }

    public abstract void setMaxHealthPoints(int healthPoints);

    public void heroPower() {
        myEffect.effect();
    }

    /**
     *  Allows a hero to activate it's effect
     */
    public abstract void activateEffect(Target target);

    public void heroPower(Target target) {
        myEffect.effect(target);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    protected String imgurl;
}
