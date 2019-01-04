package business.messageModels;

import impl.ConcreteHero;

public class MyHeroMessage {

    private int armorPoints;
    private int currentHealthPoints;
    private String heroName;
    private String imgurl;

    public MyHeroMessage(ConcreteHero hero) {

        this.armorPoints = hero.getArmorPoints();
        this.currentHealthPoints = hero.getCurrentHealthPoints();
        this.heroName = hero.getHeroName();
        this.imgurl = hero.getImgurl();

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
