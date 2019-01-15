package business.messageModels;

import abstracts.NotTargetedEffect;
import impl.ConcreteHero;

public class MyHeroMessage {

  private int armorPoints;
  private int currentHealthPoints;
  private String heroName;
  private String imgurl;
  private String powerImgUrl;
  private boolean canUseHeroPower;
  private boolean targetedHeroPower;
  private String powerImgName;
  private String powerImgText;

  public MyHeroMessage(ConcreteHero hero) {

    this.armorPoints = hero.getArmorPoints();
    this.currentHealthPoints = hero.getCurrentHealthPoints();
    this.heroName = hero.getHeroName();
    this.imgurl = hero.getImgurl();
    this.powerImgUrl = hero.getPowerImgUrl();
    this.canUseHeroPower = hero.canUseHeroAbility();
    this.powerImgName = hero.getPowerImgName();
    this.powerImgText = hero.getPowerImgText();

    this.targetedHeroPower = !(hero.getMyEffect() instanceof NotTargetedEffect);
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

  public boolean isCanUseHeroPower() {
    return canUseHeroPower;
  }

  public void setCanUseHeroPower(boolean canUseHeroPower) {
    this.canUseHeroPower = canUseHeroPower;
  }

  public String getPowerImgUrl() {
    return powerImgUrl;
  }

  public void setPowerImgUrl(String powerImgUrl) {
    this.powerImgUrl = powerImgUrl;
  }

  public boolean isTargetedHeroPower() {
    return targetedHeroPower;
  }

  public void setTargetedHeroPower(boolean targetedHeroPower) {
    this.targetedHeroPower = targetedHeroPower;
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
}
