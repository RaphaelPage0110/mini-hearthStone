package business.messageModels;

import impl.Player;

public class ManaMessage {

    private int manaMax;
    private int currentMana;
    private int aura;

    public ManaMessage(Player player){
        manaMax = player.getMyManaMax();
        currentMana = player.getMyMana();
        aura = player.getMyDamageAura();
    }


    public int getManaMax() {
        return manaMax;
    }

    public void setManaMax(int manaMax) {
        this.manaMax = manaMax;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getAura() {
        return aura;
    }

    public void setAura(int aura) {
        this.aura = aura;
    }
}
