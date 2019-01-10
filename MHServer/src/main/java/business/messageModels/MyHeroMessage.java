package business.messageModels;

import impl.ConcreteHero;
import inter.NotTargetedEffect;

public class MyHeroMessage {

    public int armorPoints;
    public int currentHealthPoints;
    public String heroName;
    public String imgurl;
    public String powerImgUrl;
    public boolean canUseHeroPower;
    public boolean targetedHeroPower;
    public String powerImgName;
    public String powerImgText;

    public MyHeroMessage(ConcreteHero hero) {

        this.armorPoints = hero.getArmorPoints();
        this.currentHealthPoints = hero.getCurrentHealthPoints();
        this.heroName = hero.getHeroName();
        this.imgurl = hero.getImgurl();
        this.powerImgUrl= hero.getPowerImgUrl();
        this.canUseHeroPower = hero.canUseHeroAbility();
        this.powerImgName = hero.getPowerImgName();
        this.powerImgText = hero.getPowerImgText();

        if(hero.getMyEffect() instanceof NotTargetedEffect){
            this.targetedHeroPower = false;
        } else {
            this.targetedHeroPower = true;
        }

    }
    public int getArmorPoints() {
        return armorPoints;
    }

    public void setArmorPoints(int armorPoints) {
        this.armorPoints = armorPoints;
    }

    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
