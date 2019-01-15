package impl;

import abstracts.CardType;
import abstracts.Effect;
import abstracts.NotTargetedEffect;
import impl.behaviour.generic.notTargetedEffect.ModifyArmor;
import impl.behaviour.generic.notTargetedEffect.Summon;
import impl.behaviour.generic.targetedEffect.DamageTarget;
import inter.Target;
import org.springframework.data.annotation.Id;

import java.util.Map;

import static abstracts.Consts.*;

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
    private Map<String, String> abilityKeyWord;
    private String powerImgName;
    private String powerImgText;
    private String powerImgUrl;
    private boolean canUseHeroAbility;

    public ConcreteHero() {
    }

    public ConcreteHero(
            CardType heroType,
            int maxHealthPoints,
            int armorPoints,
            Map<String, String> abilityKeyWord,
            String heroName,
            String imgurl) {
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

    public void setId(String id) {
        this.id = id;
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
     *
     * @param heroName the name of the hero
     */
    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Effect getMyEffect() {
        return myEffect;
    }

    public void setMyEffect(Effect myEffect) {
        this.myEffect = myEffect;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public void setMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public String getPowerImgUrl() {
        return powerImgUrl;
    }

    public void setPowerImgUrl(String powerImgUrl) {
        this.powerImgUrl = powerImgUrl;
    }

    public String getPowerImgName() {
        return powerImgName;
    }

    public void setPowerImgName(String powerImgName) {
        this.powerImgName = powerImgName;
    }

    public String getPowerImgText() {
        return powerImgText;
    }

    public void setPowerImgText(String powerImgText) {
        this.powerImgText = powerImgText;
    }

    public boolean isCanUseHeroAbility() {
        return canUseHeroAbility;
    }

    public void setCanUseHeroAbility(boolean canUseHeroAbility) {
        this.canUseHeroAbility = canUseHeroAbility;
    }

    /**
     * allows to generate the effect of a hero the abilities of the heroes are stored using a Map in
     * the database in the form <key:value> where key is the ability keyword and value is its modifier
     *
     * @param abilityKeyWord a map containing the keywords of the abilities
     */
    public void generateEffect(Map<String, String> abilityKeyWord) {

        for (Map.Entry<String, String> entry : abilityKeyWord.entrySet()) {

            switch (entry.getKey()) {
                case DAMAGE_TARGET_ABILITY:
                    DamageTarget abilityDamage = new DamageTarget(Integer.parseInt(entry.getValue()));
                    this.setMyEffect(abilityDamage);
                    break;
                case MODIFY_ARMOR_ABILITY:
                    ModifyArmor abilityArmor = new ModifyArmor(this, Integer.parseInt(entry.getValue()));
                    this.setMyEffect(abilityArmor);
                    break;
                case SUMMON_ABILITY:
                    Summon abilitySummon = new Summon(entry.getValue());
                    this.setMyEffect(abilitySummon);
                    break;
                default:
                    break;
            }
        }
    }

    public Map<String, String> getAbilityKeyWord() {
        return abilityKeyWord;
    }

    public void setAbilityKeyWord(Map<String, String> abilityKeyWord) {
        this.abilityKeyWord = abilityKeyWord;
    }

    /**
     * Allows a hero to activate its hero power
     *
     * @param target the target on which the spell is cast.
     */
    public void activateEffect(Target target) {
        if (canUseHeroAbility()) {
            if (this.getMyEffect() instanceof NotTargetedEffect) {
                this.getMyEffect().effect();
            } else {
                this.getMyEffect().effect(target);
            }
            setCanUseHeroAbility(false);
        }
    }

    @Override
    public void addDamagePoints(int bonusDamage) {
    }

    /**
     * add the value in parameter to the maxHealthPoints of the hero and to the currentHealthPoints of
     * the hero
     *
     * @param bonusHealthPoints the health points that should be added to the heros life
     */
    @Override
    public void addMaxHealthPoints(int bonusHealthPoints) {
        this.maxHealthPoints += bonusHealthPoints;
        this.currentHealthPoints += bonusHealthPoints;
    }

    @Override
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }

    @Override
    public void setMaxHealthPoints(int healthPoints) {
        this.maxHealthPoints = healthPoints;
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

            if (this.isDead()) this.dies();

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
            long sum = (long) this.currentHealthPoints + (long) healingPoints;
            int hp;
            if (sum >= Integer.MAX_VALUE) {
                hp = Math.min(Integer.MAX_VALUE, this.maxHealthPoints);
            } else {
                hp = (int) Math.min(sum, (long) this.maxHealthPoints);
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

    /**
     * return a boolean stating if the hero ability can be used
     *
     * @return true if the ability can be used
     */
    public boolean canUseHeroAbility() {
        if (myPlayer.getMyMana() >= HERO_POWER_COST) {
            return canUseHeroAbility;
        } else {
            return false;
        }
    }
}
