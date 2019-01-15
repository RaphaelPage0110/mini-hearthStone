package business.messageModels;

import abstracts.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyHandMessage {

    private ArrayList<MyCardMessage> myHand = new ArrayList<>();

    public MyHandMessage(@NotNull ArrayList<Card> implHand) {
        for (Card card : implHand) {
            MyCardMessage cardMessage = new MyCardMessage(card);
            myHand.add(cardMessage);
        }
    }

    public ArrayList<MyCardMessage> getMyHand() {
        return myHand;
    }

    public void setMyHand(ArrayList<MyCardMessage> myHand) {
        this.myHand = myHand;
    }
}
