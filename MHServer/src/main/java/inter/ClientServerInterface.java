package inter;

import identifiers.*;

import java.util.List;

public interface ClientServerInterface {

    void attack(IdMinion minion, IdTarget target);
    void castSpell(IdSpell spell, IdTarget target);
    void summon(IdMinion minion);
    void createHand();
    void skipTurn();
    void draw();
    void surrender();
    void notifyPlayers(IdPlayer player1, IdPlayer player2);
    void getState(List<Integer> updatesList);
    void matchmaking(IdPlayer client);
    void tryMatch(IdPlayer client);

}