package business.controller;

import business.Application;
import business.messageModels.HelloMessage;
import business.repositories.HeroRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import impl.ConcreteHero;
import impl.Player;
import inter.ClientServerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class GameController implements ClientServerInterface {

  private static final Logger LOGGER = Logger.getLogger(GameController.class.getName());
  SimpMessageHeaderAccessor headerAccessor =
          SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);

  @Autowired
  private Application myApplication;

  private List<Player> waitingUsers = new ArrayList<>();

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @Autowired
  private HeroRepository heroRepo;

  /**
   * {@inheritDoc}
   */
  @MessageMapping("/connectGame")
  @SendTo("user/queue/reply")
  public void connectToGame(@Header("simpSessionId") String sessionId, String heroname) {
    LOGGER.info("Received a connection from a user");
    Player player = new Player();
    player.setSessionId(sessionId);
    ConcreteHero hero = heroRepo.findByHeroName(heroname);
    hero.generateEffect(hero.getAbilityKeyWord());
    player.setMyHero(hero);
    hero.setMyPlayer(player);

    waitingUsers.add(player);
    System.out.println(sessionId);

    heroname = hero.getHeroName();

    simpMessagingTemplate.convertAndSend(
            "/queue/reply-user" + sessionId,
            new HelloMessage("Vous avez choisi " + heroname + "! En attente d'un autre joueur..."));

    if (waitingUsers.size() == 2) {
      LOGGER.log(Level.INFO, "Starting game");
      Player player1 = waitingUsers.get(0);
      Player player2 = waitingUsers.get(1);
      waitingUsers.clear();
      myApplication.createGame(player1, player2);
    }
  }

  /** {@inheritDoc} */
  @MessageMapping("/disconnectGame")
  @SendTo("user/queue/reply")
  public void disconnectFromGame(@Header("simpSessionId") String sessionId) {
    LOGGER.info("Received a disconnection from a user");
    boolean done = false;
    for (Player player : waitingUsers) {
      if (player.getSessionId().equals(sessionId)) {
        waitingUsers.remove(player);
        done = true;
        break;
      }
    }
    if (done) {
      simpMessagingTemplate.convertAndSend(
              "/queue/reply-user" + sessionId, new HelloMessage("Recherche de partie annulée."));
    } else {
      if (myApplication.findPlayerById(sessionId) != null) {
        myApplication.gameOver(sessionId);
      } else {
        simpMessagingTemplate.convertAndSend(
                "/queue/reply-user" + sessionId,
                new HelloMessage("Vous ne recherchiez pas de partie."));
      }
    }
  }

  /** {@inheritDoc} */
  @MessageMapping("/passTurn")
  @SendTo("user/queue/reply_passTurn")
  public void passTurn(@Header("simpSessionId") String sessionId) {
    this.myApplication.passTurn(sessionId);
  }

  /** {@inheritDoc} */
  @MessageMapping("/gameOver")
  @SendTo("user/queue/reply_gameOver")
  public void gameOver(@Header("simpSessionId") String sessionId) {

    myApplication.gameOver(sessionId);
  }

  /** {@inheritDoc} */
  @MessageMapping("/playMinion")
  @SendTo({"user/queue/reply_playMinion", "user/queue/reply_hePlayedMinion"})
  public void playMinion(@Header("simpSessionId") String sessionId, String idMinion) {

    myApplication.playMinionCard(idMinion, sessionId);
  }

  /** {@inheritDoc} */
  @MessageMapping("/playSpell")
  @SendTo("user/queue/reply_playSpell")
  public void playSpell(@Header("simpSessionId") String sessionId, String idSpell) {

    myApplication.playSpellCard(idSpell, null, sessionId);
  }

  /** {@inheritDoc} */
  @MessageMapping("/castSpellOnThisMinion")
  public void castSpellOnThisMinion(@Header("simpSessionId") String sessionId, String message) {

    JsonElement jelement = new JsonParser().parse(message);
    JsonObject jobject = jelement.getAsJsonObject();
    JsonElement spellIDJson = jobject.get("spellID");
    JsonElement targetIDJson = jobject.get("targetID");
    String idSpell = spellIDJson.getAsString();
    String targetID = targetIDJson.getAsString();

    myApplication.playSpellCard(idSpell, targetID, sessionId);
  }

  /** {@inheritDoc} */
  @MessageMapping("/showTargetForMinion")
  @SendTo("user/queue/reply_showTargets")
  public void showPossibleTargetsForMinion(@Header("simpSessionId") String sessionId) {

    myApplication.showPossibleTargetsForMinion(sessionId);
  }

  /** {@inheritDoc} */
  @MessageMapping("/attackThisMinion")
  public void attackThisMinion(@Header("simpSessionId") String sessionId, String message) {

    JsonElement jelement = new JsonParser().parse(message);
    JsonObject jobject = jelement.getAsJsonObject();
    JsonElement attackerIDJson = jobject.get("attackerID");
    JsonElement targetIDJson = jobject.get("targetID");
    String attackerID = attackerIDJson.getAsString();
    String targetID = targetIDJson.getAsString();
    myApplication.attackMinion(sessionId, attackerID, targetID);
  }

  /** {@inheritDoc} */
  @MessageMapping("/attackHero")
  public void attackHero(@Header("simpSessionId") String sessionId, String attackerID) {

    myApplication.attackHero(sessionId, attackerID);
  }

  /** {@inheritDoc} */
  @MessageMapping("/useHeroPower")
  public void useHeroPower(@Header("simpSessionId") String sessionId) {
    myApplication.useHeroPower(sessionId);
  }

  /** {@inheritDoc} */
  @MessageMapping("/useHeroPowerOnTarget")
  public void useHeroPowerOnTarget(@Header("simpSessionId") String sessionId, String targetID) {
    myApplication.useHeroPowerOnTarget(sessionId, targetID);
  }

  public GameController setApplication(Application application) {
    this.myApplication = application;
    return this;
  }

  public GameController setHeroRepository(HeroRepository heroRepository) {
    this.heroRepo = heroRepository;
    return this;
  }

  public GameController makeGC() {
    return new GameController();
  }

  public GameController setMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
    return this;
  }

  public List<Player> getWaitingUsers() {
    return this.waitingUsers;
  }
}
