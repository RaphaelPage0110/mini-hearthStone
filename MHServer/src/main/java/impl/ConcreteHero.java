package impl;

import abstracts.CardType;
import impl.behaviour.generic.notTargetedEffect.ModifyArmor;
import impl.behaviour.generic.notTargetedEffect.Summon;
import impl.behaviour.generic.targetedEffect.DamageTarget;
import inter.Effect;
import inter.NotTargetedEffect;
import inter.Target;
import org.springframework.data.annotation.Id;

import java.util.Map;

public class ConcreteHero implements Target {

    /**
     * The ID of this ConcreteHero.
     */
    @Id
    private String id;

    /**
     * the url of the heros picture
     */
    private String imgurl;

    /**
     * The type of the hero (mage, warrior or paladin)
     */
    private CardType heroType;

    /**
     * The name of the hero
     */
    private String heroName;

    /**
     * Indicates the maximum number of health points that this Hero has.
     */
    private int maxHealthPoints;

    /**
     * Indicates the current number of health points that this Hero has.
     */
    private int currentHealthPoints;

    /**
     * Indicates the number of armor points that this Hero has.
     */
    private int armorPoints;

    /**
     * Reference the list of actions or behaviors of this card.
     */
    private Effect myEffect;

    private Player myPlayer;
    private Map<String,String> abilityKeyWord;
    private String powerImgName;
    private String powerImgText;
    private String powerImgUrl;
    private boolean canUseHeroAbility;

    public ConcreteHero() {}


    public ConcreteHero(CardType heroType, int maxHealthPoints, int armorPoints, Map<String,String> abilityKeyWord, String heroName, String imgurl) {
        super();

        this.heroType = heroType;
        this.armorPoints = armorPoints;
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
        this.abilityKeyWord = abilityKeyWord;
        this.heroName = heroName;
        this.imgurl = imgurl;
        this.canUseHeroAbility = true;

        generateEffect(abilityKeyWord);

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


    public CardType getHeroType() {
        return heroType;
    }

    public void setHeroType(CardType heroType) {
        this.heroType = heroType;
    }

    /**
     * Returns value of id
     *
     * @return id the id of the hero
     */
    public String getId() {
        return id;
    }

    /**
     * Returns value of currentHealthPoints
     *
     * @return healthPoints the current health points of the hero
     */
    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    /**
     * Sets new value of currentHealthPoints
     *
     * @param hp the current health points of the hero
     */
    public void setCurrentHealthPoints(int hp) {
        this.currentHealthPoints = hp;
    }

    /**
     * Returns value of armorPoints
     *
     * @return armorPoints the current armor points of the hero
     */
    public int getArmorPoints() {
        return armorPoints;
    }

    /**
     * Sets new value of armorPoints
     *
     * @param armor the armor points of the hero
     */
    public void setArmorPoints(int armor) {
        this.armorPoints = armor;
    }

    /**
     * Returns value of heroName
     *
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
     *
     * @return myEffect the ability of a hero
     */
    public Effect getMyEffect() {
        return myEffect;
    }

    /**
     * Sets the new value of myEffects
     *
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
     * set canUseHeroAbility to true if the hero can use is ability (false otherwise
     * @param canUseHeroAbility
     */
    public void setCanUseHeroAbility(boolean canUseHeroAbility) {
        this.canUseHeroAbility = canUseHeroAbility;
    }

    /**
     * get the URL image of the hero power
     * @return
     */
    public String getPowerImgUrl() {
        return powerImgUrl;
    }

    public void setPowerImgUrl(String powerImgUrl) {
        this.powerImgUrl = powerImgUrl;
    }

    /**
     * get the image name of the hero power
     * @return
     */
    public String getPowerImgName() {
        return powerImgName;
    }

    public void setPowerImgName(String powerImgName) {
        this.powerImgName = powerImgName;
    }

    /**
     * get the text of the hero power
     * @return
     */
    public String getPowerImgText() {
        return powerImgText;
    }

    public void setPowerImgText(String powerImgText) {
        this.powerImgText = powerImgText;
    }

    public boolean isCanUseHeroAbility() {
        return canUseHeroAbility;
    }

    /**
     * allows to generate the effect of a hero
     * the abilities of the heroes are stored using a Map in the database in the form <key:value> where key is the
     * ability keyword and value is its modifier
     * @param abilityKeyWord
     */
    public void generateEffect(Map<String,String> abilityKeyWord){

        for (Map.Entry<String, String> entry : abilityKeyWord.entrySet()) {

            switch(entry.getKey()) {

                case "damageTarget":
                    DamageTarget abilityDamage = new DamageTarget(Integer.parseInt(entry.getValue()));
                    this.setMyEffect(abilityDamage);
                    break;
                case "modifyArmor" :
                    ModifyArmor abilityArmor = new ModifyArmor(this, Integer.parseInt(entry.getValue()));
                    this.setMyEffect(abilityArmor);
                    break;
                case "summon":
                    Summon abilitySummon = new Summon(entry.getValue());
                    this.setMyEffect(abilitySummon);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * get the abilityKeyWord stored for a hero using a Map in the form <key:value> where key is the
     * ability keyword and value is its modifier
     * @return
     */
    public Map<String,String> getAbilityKeyWord() {
        return abilityKeyWord;
    }

    public void setAbilityKeyWord(Map<String,String> abilityKeyWord) {
        this.abilityKeyWord = abilityKeyWord;
    }

    /**
     *
     * @param healthPoints
     */
    @Override
    public void setMaxHealthPoints(int healthPoints) {
        this.maxHealthPoints = healthPoints;
    }

    /**
     * Allows a hero to activate its hero power
     */
    public void activateEffect(Target target) {
        if (this.getMyEffect() instanceof NotTargetedEffect ){
            this.getMyEffect().effect();
        }
        else {
            this.getMyEffect().effect(target);
        }

    }

    @Override
    public void addDamagePoints(int bonusDamage) {}

    /**
     * add the value in parameter to the maxHealthPoints of the hero
     * and to the currentHealthPoints of the hero
     * @param bonusHealthPoints
     */
    @Override
    public void addMaxHealthPoints(int bonusHealthPoints) {
        this.maxHealthPoints += bonusHealthPoints;
        this.currentHealthPoints += bonusHealthPoints;
    }

    /**
     * get the max health point of the hero
     * @return
     */
    @Override
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }

    /**
     * {@inheritDoc}
     */
    public int takeDamage(int damageTaken) {
        if (damageTaken >= 0) {
            if (damageTaken < this.armorPoints) {
                this.armorPoints -= damageTaken;
                damageTaken = 0;
            } else {
                this.currentHealthPoints = this.currentHealthPoints + this.armorPoints - damageTaken;
                damageTaken = damageTaken - this.armorPoints;
                this.armorPoints = 0;
            }

            if (this.isDead())
                this.dies();

            return damageTaken;
        } else {
            return 0;
        }
    }

    /**
     * Allows to give a few health points back to a living hero.
     *
     * @param healingPoints the number of health points to be returned.
     */
    @Override
    public void heal(int healingPoints) {
        if (healingPoints >= 0) {
            long sum = (long)this.currentHealthPoints + (long)healingPoints;
            int hp;
            if (sum >= Integer.MAX_VALUE) {
                hp = Math.min(Integer.MAX_VALUE, this.maxHealthPoints);
            } else {
                hp  = (int)Math.min(sum, (long) this.maxHealthPoints);
            }
            this.currentHealthPoints = hp;
        }
    }

    public void addArmor(int armor) {
        this.armorPoints += armor;
    }

    /**
     * check if the hero is dead
     *
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

    public void heroPower() {
        myEffect.effect();
    }

    public void heroPower(Target target) {
        myEffect.effect(target);
    }

    /**
     * return a boolean which said if the hero ability can be used
     *
     * @return
     */
    public boolean canUseHeroAbility() {
        if (myPlayer.getMyMana() >= 2) {
            return canUseHeroAbility;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Hero[id=%s, heroName='%s', maxHealthPoints='%s', armorPoints='%s']",
                id, heroName, maxHealthPoints, armorPoints);
    }

}
