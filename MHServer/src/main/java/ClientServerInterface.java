package MHServer;

interface ClientServerInterface {

    void attack(idMinion, idTarget);
    void castSpell(idSpell, idTarget);
    void summon(idMinion);
    void createHand();
    void skipTurn();
    void draw();
    void surrender();
    void notify(idPlayer, idPlayer);
    void getState(List<Integer>);
    void matchmaking(idClient);
    void tryMatch(idClient);

}