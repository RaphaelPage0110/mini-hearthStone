package impl;

import abstracts.CardType;
import abstracts.Hero;
import impl.behaviour.generic.notTargetedEffect.ModifyArmor;
import impl.behaviour.generic.notTargetedEffect.Summon;
import impl.behaviour.generic.targetedEffect.DamageTarget;
import inter.NotTargetedEffect;
import inter.Target;

import java.util.Map;

public class ConcreteHero extends Hero {

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

    /**
     * return a boolean which said if the hero ability can be used
     * @return
     */
    public boolean canUseHeroAbility() {
        if(myPlayer.getMyMana()>=2){
            return canUseHeroAbility;
        }else{
            return false;
        }
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
                    DamageTarget abilityDamage = new DamageTarget(this, Integer.parseInt(entry.getValue()));
                    this.setMyEffect(abilityDamage);
                    break;
                case "modifyArmor" :
                    ModifyArmor abilityArmor = new ModifyArmor(this, Integer.parseInt(entry.getValue()));
                    this.setMyEffect(abilityArmor);
                    break;
                case "summon" :
                    Summon abilitySummon = new Summon(this, entry.getValue());
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

    }

    /**
     * Allows a hero to activate its hero power
     */
    @Override
    public void activateEffect(Target target) {
        if (this.getMyEffect() instanceof NotTargetedEffect ){
            this.getMyEffect().effect();
        }
        else {
            this.getMyEffect().effect(target);
        }

    }

    @Override
    public void addDamagePoints(int bonusDamage) {

    }

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
}
