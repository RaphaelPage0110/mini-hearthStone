package impl;

import abstracts.CardType;
import abstracts.Hero;
import impl.behaviour.generic.notTargetedEffect.ModifyArmor;
import impl.behaviour.generic.notTargetedEffect.Summon;
import impl.behaviour.generic.targetedEffect.DamageTarget;

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

    public boolean canUseHeroAbility() {
        if(myPlayer.getMyMana()>=2){
            return canUseHeroAbility;
        }else{
            return false;
        }
    }

    public void setCanUseHeroAbility(boolean canUseHeroAbility) {
        this.canUseHeroAbility = canUseHeroAbility;
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

    public void generateEffect(Map<String,String> abilityKeyWord){
        //the abilities of the heroes are stored using a Map in the database in the form <key:value> where key is the
        //ability keyword and value is its modifier
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

    public Map<String,String> getAbilityKeyWord() {
        return abilityKeyWord;
    }

    public void setAbilityKeyWord(Map<String,String> abilityKeyWord) {
        this.abilityKeyWord = abilityKeyWord;
    }

    @Override
    public void setMaxHealthPoints(int healthPoints) {

    }

    /**
     * TODO : write this method
     *
     */
    @Override
    public void activateEffect() {  this.getMyEffect().effect();

    }

    @Override
    public void addDamagePoints(int bonusDamage) {

    }

    @Override
    public void addMaxHealthPoints(int bonusHealthPoints) {
        this.maxHealthPoints += bonusHealthPoints;
        this.currentHealthPoints += bonusHealthPoints;
    }

    @Override
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }
}
