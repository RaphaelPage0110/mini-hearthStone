package business.messageModels;

import impl.Player;

public class ManaMessage {

    public int manaMax;
    public int currentMana;

    public ManaMessage(Player player){
        manaMax = player.getMyManaMax();
        currentMana = player.getMyMana();
    }
}
