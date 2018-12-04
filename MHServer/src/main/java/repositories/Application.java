package repositories;
import impl.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private Player player1;
    private Player player2;

    @Autowired
    private HeroRepository repository;

    @Autowired
    private MinionRepository minionRepository;

    @Autowired
    private SpellRepository spellRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Application application = new Application();

        application.instanciatePlayers();

        int turn = 1;
        while(true) {

            //we increase the mana of each player during the first 10 turns
            if (turn <= 10 ) {
                application.player1.addManaMax(1);
                application.player2.addManaMax(1);
            }

            //we refill the players mana
            application.player1.setMyMana(player1.getMyManaMax());
            application.player2.setMyMana(player2.getMyManaMax());

            //we allow the players to use their heros ability
            application.player1.setCanUseHeroAbility(true);
            application.player2.setCanUseHeroAbility(true);

            //the two players play their turn
            application.playRound();
            turn++;

        }

    }

    /**
     * manage the players turn
     */
    private void playRound() {
        int turnPlayer1 = this.player1.getPlayOrder();
        int turnPlayer2 = this.player2.getPlayOrder();
        if (turnPlayer2 > turnPlayer1) {
            action(this.player1, this.player2);
            action(this.player2, this.player1);
        } else {
            action(this.player2, this.player1);
            action(this.player1, this.player2);
        }
    }

    /**
     * TODO : Compléter cette fonction quand on en saura plus sur comment communiquer avec le client
     * allows a player to play its turn
     * @param activePlayer the player whose turn it is to play
     * @param opponent its opponent
     */
    private void action(Player activePlayer, Player opponent) {
        draw(activePlayer);
        while(true){
            //IL FAUDRA TROUVER UN MOYEN DE RECUPERER LA DECISION DU JOUEUR COTE CLIENT
            String idAction = "";
            switch (idAction) {
                case "playCard":
                    playCard(activePlayer, opponent);
                    break;
                case "attack":
                    attack(activePlayer, opponent);
                    break;
                case "useHeroAbility":
                    useHeroPower(activePlayer, opponent);
                    break;
                case "endTurn":
                    return;
                default:
                    break;
            }
        }
    }

    /**
     * allows a player to use its hero ability
     * @param activePlayer the player whose turn it is to play
     * @param opponent its opponent
     */
    private void useHeroPower(Player activePlayer, Player opponent) {

        if(activePlayer.canUseHeroAbility()) {
            activePlayer.getMyHero().activateEffect();
            activePlayer.setCanUseHeroAbility(false);
        }

    }

    /**
     * allows a player to attack a target
     * @param activePlayer
     * @param opponent
     */
    private void attack(Player activePlayer, Player opponent) {

    }

    /**
     * allows a player to play a card
     * @param activePlayer
     * @param opponent
     */
    private void playCard(Player activePlayer, Player opponent) {
    }

    /**
     * allows a player to draw a card
     * @param activePlayer
     */
    private void draw(Player activePlayer) {
    }

    /**
     * allows to create two players and links them to the game
     */
    private void instanciatePlayers() {
    }

}