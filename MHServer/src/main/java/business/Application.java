package business;

import abstracts.*;
import business.messageModels.*;
import business.repositories.HeroRepository;
import business.repositories.MinionRepository;
import business.repositories.SpellRepository;
import impl.*;
import impl.behaviour.generic.notTargetedEffect.DrawCard;
import impl.behaviour.generic.notTargetedEffect.Summon;
import impl.behaviour.generic.targetedEffect.TransformInto;
import inter.Target;
import org.apache.commons.lang3.time.StopWatch;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static abstracts.Consts.LIMIT_MANA_MAX;

@SpringBootApplication
public class Application {

  @Autowired
  public HeroRepository heroRepository;
  @Autowired
  public MinionRepository minionRepository;
  @Autowired
  public SpellRepository spellRepository;
  private Game game;
  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  /**
   * allows to create a new game of mini-hearthstone
   */
  public void createGame(Player player1, Player player2) {

    this.game = new Game(player1, player2);
    instanciatePlayers(game);

    while (!game.isGameOver()) {

      // the two players play their turn
      playRound(game);
      game.incrementTurn();
    }

    simpMessagingTemplate.convertAndSend(
            "/queue/reply_gameOver-user" + game.getWinner().getSessionId(), "Vous avez gagné!!");
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_gameOver-user" + game.getLoser().getSessionId(), "Vous avez perdu.");
  }

  private void instanciatePlayers(@NotNull Game game) {

    Player player1 = game.getPlayer1();
    Player player2 = game.getPlayer2();

    player1.setMyGame(game);
    player2.setMyGame(game);

    player1.setOpponent(player2);
    player2.setOpponent(player1);

    String sessionPlayer1 = player1.getSessionId();
    String sessionPlayer2 = player2.getSessionId();

    simpMessagingTemplate.convertAndSend(
            "/queue/reply_gameFound-user" + sessionPlayer1, new HelloMessage("Vous êtes le joueur 1"));
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_gameFound-user" + sessionPlayer2, new HelloMessage("Vous êtes le joueur 2"));

    // the first player draws 3 cards
    for (int i = 0; i < 3; i++) {
      draw(player1);
    }
    sendHand(player1);

    // the second player draws 4 cards
    for (int i = 0; i < 4; i++) {
      draw(player2);
    }
    sendHand(player2);

    // we send to the players their hero and mana
    ArrayList<Player> playerList = new ArrayList<>();
    playerList.add(player1);
    playerList.add(player2);

    for (Player player : playerList) {

      sendPlayerHeroMessage(player);
      sendOpponentPlayerHeroMessage(player);
      sendManaMessage(player);
    }
  }

  /**
   * manage the players turn
   */
  private void playRound(@NotNull Game game) {

    Player firstToPlay = game.getPlayer1();
    Player secondToPlay = game.getPlayer2();

    ArrayList<Player> playerList = new ArrayList<>();
    playerList.add(firstToPlay);
    playerList.add(secondToPlay);

    for (Player player : playerList) {
      if (!game.isGameOver()) {

        // the players minions can attack again
        for (ConcreteMinion minion : player.getMyMinions()) {
          minion.setCanAttack(true);
        }
        sendMinionsInPlay(player);
        action(player, game);
      }
    }
  }

  /**
   * send the opponent player's hero status to the a player
   *
   * @param player the player
   */
  public void sendOpponentPlayerHeroMessage(Player player) {
    MyHeroMessage myHeroMessage = new MyHeroMessage(player.getOpponent().getMyHero());
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_hisHero-user" + player.getSessionId(), myHeroMessage);
  }

  /**
   * send his hero status to a player
   *
   * @param player the player
   */
  public void sendPlayerHeroMessage(Player player) {
    MyHeroMessage myHeroMessage = new MyHeroMessage(player.getMyHero());
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_myHero-user" + player.getSessionId(), myHeroMessage);
  }

  /**
   * Send the mana of the player to himself and his opponent
   *
   * @param player the player
   */
  public void sendManaMessage(Player player) {
    // we send their mana to the user and his opponent
    ManaMessage manaMessage = new ManaMessage(player);
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_myMana-user" + player.getSessionId(), manaMessage);
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_hisMana-user" + player.getOpponent().getSessionId(), manaMessage);
  }

  public void sendHand(Player player) {
    ArrayList<Card> playerHand = player.getMyHand();

    ArrayList<MyCardMessage> myHandMessage = new ArrayList<>();

    for (Card card : playerHand) {
      MyCardMessage cardMessage = new MyCardMessage(card);
      myHandMessage.add(cardMessage);
    }
    HisHandMessage hisHandMessage = new HisHandMessage(playerHand.size());
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_myHand-user" + player.getSessionId(), myHandMessage);
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_hisHand-user" + player.getOpponent().getSessionId(), hisHandMessage);
  }

  /**
   * allows a player to play its turn
   *
   * @param activePlayer the player whose turn it is to play
   */
  @SuppressWarnings("PMD")
  private void action(@NotNull Player activePlayer, @NotNull Game game) {
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_yourTurn-user" + activePlayer.getSessionId(),
            "<h4><b>C'est à vous!</b></h4>");
    activePlayer.setPlayOrder(activePlayer.getPlayOrder() + 1);
    game.setPassTurn(false);
    // we increase the mana of each player during the first 10 turns
    if (game.getTurn() <= LIMIT_MANA_MAX) {
      activePlayer.addManaMax(1);
    }
    // we refill the players mana
    activePlayer.setMyMana(activePlayer.getMyManaMax());

    // we allow the players to use their heros ability
    activePlayer.getMyHero().setCanUseHeroAbility(true);
    sendOpponentPlayerHeroMessage(activePlayer.getOpponent());
    sendPlayerHeroMessage(activePlayer);

    // we send their mana to the user and his opponent
    sendManaMessage(activePlayer);

    draw(activePlayer);
    sendHand(activePlayer);

    // a player has 2 minutes to play
    StopWatch watch = new StopWatch();
    watch.start();
    boolean oneMinuteWarning = false;
    boolean thirtySecWarning = false;
    boolean tenSecWarning = false;
    while (!game.isPassTurn()) {

      if (watch.getTime(TimeUnit.MINUTES) == 1 && !oneMinuteWarning) {

        simpMessagingTemplate.convertAndSend(
                "/queue/reply_yourTurn-user" + activePlayer.getSessionId(),
                "<h4><b>Plus qu'une minute!</b></h4>");
        oneMinuteWarning = true;
      }

      if (watch.getTime(TimeUnit.SECONDS) == 90 && !thirtySecWarning) {

        simpMessagingTemplate.convertAndSend(
                "/queue/reply_yourTurn-user" + activePlayer.getSessionId(),
                "<h4><b>Plus que 30 secondes!</b></h4>");
        thirtySecWarning = true;
      }

      if (watch.getTime(TimeUnit.SECONDS) == 110 && !tenSecWarning) {

        simpMessagingTemplate.convertAndSend(
                "/queue/reply_yourTurn-user" + activePlayer.getSessionId(),
                "<h4><b>Plus que 10 secondes!</b></h4>");
        tenSecWarning = true;
      }

      if (watch.getTime(TimeUnit.MINUTES) == 2) {
        simpMessagingTemplate.convertAndSend(
                "/queue/reply_yourTurn-user" + activePlayer.getSessionId(),
                "<h4><b>Temps écoulé!</b></h4>");
        game.setPassTurn(true);
      }
    }
    watch.stop();
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_passedTurn-user" + activePlayer.getSessionId(), "Passed turn");
  }

  public void showPossibleTargetsForMinion() {
    Player waitingPlayer = game.getWaitingPlayer();
    ArrayList<ConcreteMinion> targetMinions = waitingPlayer.findTauntMinions();

    if (targetMinions.size() == 0) {
      targetMinions = waitingPlayer.getMyMinions();
    }

    ArrayList<MyCardMessage> myMinionsMessage = new ArrayList<>();

    for (ConcreteMinion minion : targetMinions) {
      MyCardMessage cardMessage = new MyCardMessage(minion);
      myMinionsMessage.add(cardMessage);
    }

    simpMessagingTemplate.convertAndSend(
            "/queue/reply_targetsMinions-user" + waitingPlayer.getOpponent().getSessionId(),
            myMinionsMessage);
  }

  /**
   * allows to attack a minion
   *
   * @param attackerID the uniqueId of the minion that attacks
   * @param targetID   the uniqueId of the minion targeted
   */
  public void attackMinion(String attackerID, String targetID) {

    Player activePlayer = game.getActivePlayer();
    Player waitingPlayer = game.getWaitingPlayer();

    ConcreteMinion minionThatAttacks = (ConcreteMinion) activePlayer.findCardById(attackerID);
    ConcreteMinion minionToAttack = (ConcreteMinion) waitingPlayer.findCardById(targetID);

    minionThatAttacks.attack(minionToAttack);
    sendBothPlayersMinion();

    if (minionThatAttacks.isHasLifesteal()) {
      // we are sending the active player hero status to both players in case the minion that
      // attacked had lifeSteal
      sendPlayerHeroMessage(activePlayer);
      sendOpponentPlayerHeroMessage(waitingPlayer);
    }

    if (minionToAttack.isHasLifesteal()) {
      // we are sending the waiting players status to both players if the minion targeted has
      // lifesteal
      sendPlayerHeroMessage(waitingPlayer);
      sendOpponentPlayerHeroMessage(activePlayer);
    }
  }

  /**
   * allows to attack a hero
   *
   * @param attackerID : id of the attacker
   */
  public void attackHero(String attackerID) {

    Player activePlayer = game.getActivePlayer();
    Player waitingPlayer = activePlayer.getOpponent();
    ConcreteMinion minionThatAttacks = (ConcreteMinion) activePlayer.findCardById(attackerID);

    // the minion attack the waiting player's hero
    minionThatAttacks.attack(waitingPlayer.getMyHero());

    // the status of the attacked hero is sent to both players
    sendOpponentPlayerHeroMessage(activePlayer);
    sendPlayerHeroMessage(waitingPlayer);

    if (minionThatAttacks.isHasLifesteal()) {
      // the status of the active player's hero is sent to both players if the minion attacking has
      // lifesteal
      sendOpponentPlayerHeroMessage(waitingPlayer);
      sendPlayerHeroMessage(activePlayer);
    }

    // the status of the active player's minions is sent to both players
    sendMinionsInPlay(activePlayer);
  }

  /**
   * Allows a player to play a spell card
   *
   * @param spellToPlayID the uniqueID of the spell
   * @param idPlayer      the sessionId of the player
   * @param idTarget      the uniqueID of the minion targeted
   */
  public void playSpellCard(String spellToPlayID, String idTarget, String idPlayer) {

    Player player = game.getPlayerByID(idPlayer);

    // only the active player can play a spell card
    if (player == game.getActivePlayer()) {

      ConcreteSpell spellToPlay = (ConcreteSpell) player.findCardById(spellToPlayID);
      player.changeMana(-spellToPlay.getRequiredMana());

      player.removeCardFromHand(spellToPlay);

      for (Effect effect : spellToPlay.getMyEffects()) {

        // if the card effect is to summon a minion, then we have to treat it separately
        if (effect instanceof Summon) {

          // some spells can summon multiple minions of the same type at the same time
          int numberOfMinionsToSummon = ((Summon) effect).getNumberSummoned();

          for (int i = 0; i < numberOfMinionsToSummon; i++) {

            // we fetch the minions details from the database using it's name
            String minionKeyword = ((Summon) effect).getMyMinionKeyword();
            ConcreteMinion minionToSummon = minionRepository.findByName(minionKeyword);
            minionToSummon.setUniqueID();
            minionToSummon.setPlayer(player);

            minionToSummon.generateEffect();

            // we add the newly created minion to the game
            player.addMinion(minionToSummon);
          }

        }

        // the transform effect is somewhat similar to the summon effect, except that we don't
        // summon a new minion,
        // we just update the targeted minions parameters
        else if (effect instanceof TransformInto) {

          ConcreteMinion minionBeingTransformed =
                  (ConcreteMinion) player.getOpponent().findCardById(idTarget);
          if (minionBeingTransformed == null) {
            minionBeingTransformed = (ConcreteMinion) player.findCardById(idTarget);
          }
          String minionKeyword = ((TransformInto) effect).getMyMinionKeyword();
          ConcreteMinion minionModel = minionRepository.findByName(minionKeyword);

          minionBeingTransformed.setHasTaunt(false);
          minionBeingTransformed.setHasLifesteal(false);
          minionBeingTransformed.setCanAttack(false);
          minionBeingTransformed.setName(minionModel.getName());
          minionBeingTransformed.setRequiredMana(minionModel.getRequiredMana());
          minionBeingTransformed.setDamagePoints(minionModel.getDamagePoints());
          minionBeingTransformed.setMaxHealthPoints(minionModel.getMaxHealthPoints());
          minionBeingTransformed.setCurrentHealthPoints(minionModel.getCurrentHealthPoints());
          minionBeingTransformed.setType(minionModel.getType());
          minionBeingTransformed.setText(minionModel.getText());
          minionBeingTransformed.setImgurl(minionModel.getImgurl());
          minionBeingTransformed.setAbilityKeyWord(minionModel.getAbilityKeyWord());
          minionBeingTransformed.setDeathRattleKeyWords(minionModel.getDeathRattleKeyWords());
          minionBeingTransformed.generateEffect();

        } else if (effect instanceof DrawCard) {

          for (int i = 0; i < ((DrawCard) effect).getNumberDraw(); i++) {

            this.draw(player);
          }

        } else if (effect instanceof NotTargetedEffect) {

          effect.effect();

        } else if (effect instanceof TargetedEffect) {

          ConcreteMinion target = (ConcreteMinion) player.getOpponent().findCardById(idTarget);
          if (target == null) {
            target = (ConcreteMinion) player.findCardById(idTarget);
          }
          effect.effect(target);
        }
      }

      sendManaMessage(player);

      sendHand(player);
      sendMinionsInPlay(player);
      sendMinionsInPlay(player.getOpponent());

      sendPlayerHeroMessage(player);
      sendOpponentPlayerHeroMessage(player);
      sendPlayerHeroMessage(player.getOpponent());
      sendOpponentPlayerHeroMessage(player.getOpponent());
    }
  }

  /**
   * allows a player to play a minion card
   *
   * @param minionToPlayID the uniqueID of the minion
   * @param playerID       the sessionId of the player
   */
  public void playMinionCard(String minionToPlayID, String playerID) {

    Player player = game.getPlayerByID(playerID);

    // only the active player can play a minion card
    if (player == game.getActivePlayer()) {
      ConcreteMinion minionToPlay = (ConcreteMinion) player.findCardById(minionToPlayID);

      player.changeMana(-minionToPlay.getRequiredMana());
      player.removeCardFromHand(minionToPlay);
      player.addMinion(minionToPlay);

      // we apply all the card's effect
      for (Effect effect : minionToPlay.getMyEffects()) {
        if (effect instanceof NotTargetedEffect) {
          effect.effect();
        }
      }

      sendManaMessage(player);

      sendHand(player);
      sendMinionsInPlay(player);
    }
  }

  /**
   * allows a user to use a hero power that doesnt require a target
   *
   * @param sessionId the sessionId of the player
   */
  public void useHeroPower(String sessionId) {
    Player activePlayer = game.getPlayerByID(sessionId);
    // Player activePlayer = game.getActivePlayer();
    // Player waitingPlayer = game.getWaitingPlayer();
    ConcreteHero hero = activePlayer.getMyHero();

    if (hero.canUseHeroAbility()) {

      Effect heroPower = hero.getMyEffect();

      if (heroPower instanceof Summon) {

        // some heroPowers could summon multiple minions of the same type at the same time
        int numberOfMinionsToSummon = ((Summon) heroPower).getNumberSummoned();

        for (int i = 0; i < numberOfMinionsToSummon; i++) {

          // we fetch the minion to summon in the database
          String minionKeyword = ((Summon) hero.getMyEffect()).getMyMinionKeyword();
          ConcreteMinion minionToSummon = minionRepository.findByName(minionKeyword);
          minionToSummon.setUniqueID();
          minionToSummon.generateEffect();
          minionToSummon.setPlayer(activePlayer);

          // we add the minion to the game
          activePlayer.addMinion(minionToSummon);

          // we apply its effects
          for (Effect effect : minionToSummon.getMyEffects()) {
            effect.effect();
          }
        }
        sendBothPlayersMinion();
      } else {

        activePlayer.getMyHero().activateEffect(null);
      }

      afterHeroPowerUsed(activePlayer, hero);
    }
  }

  /**
   * allows a user to use a hero power that requires a target
   *
   * @param sessionId the sessionId of the player
   * @param targetID  the uniqueId of the minion targeted
   */
  public void useHeroPowerOnTarget(String sessionId, String targetID) {
    Player activePlayer = game.getPlayerByID(sessionId);
    ConcreteHero hero = activePlayer.getMyHero();
    Target target;

    if (hero.canUseHeroAbility()) {

      if (targetID.equals("hero")) {
        target = activePlayer.getOpponent().getMyHero();
      } else {
        target = (ConcreteMinion) activePlayer.getOpponent().findCardById(targetID);
      }

      hero.activateEffect(target);

      afterHeroPowerUsed(activePlayer, hero);

      if (target instanceof ConcreteHero) {
        sendOpponentPlayerHeroMessage(activePlayer);
        sendPlayerHeroMessage(activePlayer.getOpponent());
      } else {
        sendMinionsInPlay(activePlayer.getOpponent());
      }
    }
  }

  /**
   * sets what happens after a hero uses its hero ability
   *
   * @param player the sessionId of the player
   * @param hero   the hero of the player
   */
  private void afterHeroPowerUsed(@NotNull Player player, @NotNull ConcreteHero hero) {

    // a hero can only use its hero ability once
    hero.setCanUseHeroAbility(false);
    // a hero ability cost 2 mana
    player.changeMana(-2);

    sendPlayerHeroMessage(player);
    sendOpponentPlayerHeroMessage(player.getOpponent());
    sendManaMessage(player);
  }

  /**
   * allows a player to draw a card
   *
   * @param activePlayer the player
   */
  private void draw(Player activePlayer) {

    ArrayList<Card> playerStock = activePlayer.getMyStock();

    // we first have to check if we should refill the stock
    if (playerStock.size() < 2) {

      this.refillStock(activePlayer);
    }

    // we add the first card that's in the players stock to its hand
    Card cardDrawn = playerStock.get(0);
    activePlayer.addCardToHand(cardDrawn);
    activePlayer.removeFromStock(cardDrawn);
  }

  /**
   * refills the player stock until it's at ten cards again
   *
   * @param activePlayer the player
   */
  private void refillStock(Player activePlayer) {

    CardType cardType;
    CardType typeCommon = CardType.COMMON;

    switch (activePlayer.getMyHero().getHeroType()) {
      case MAGE:
        cardType = CardType.MAGE;
        break;
      case PALADIN:
        cardType = CardType.PALADIN;
        break;
      case WARRIOR:
        cardType = CardType.WARRIOR;
        break;
      default:
        cardType = null;
        break;
    }

    ArrayList<ConcreteMinion> listMinionsCommon = minionRepository.findByType(typeCommon);
    ArrayList<ConcreteMinion> listMinionsLimited = minionRepository.findByType(cardType);

    // this arraylist contains all the minions this player can have
    ArrayList<ConcreteMinion> listMinions = new ArrayList<>(listMinionsCommon);
    listMinions.addAll(listMinionsLimited);

    List<ConcreteSpell> listSpellsCommon = spellRepository.findByType(typeCommon);
    List<ConcreteSpell> listSpellsLimited = spellRepository.findByType(cardType);

    // this arraylist contains all the spells this player can have
    ArrayList<ConcreteSpell> listSpells = new ArrayList<>(listSpellsCommon);
    listSpells.addAll(listSpellsLimited);

    ConcreteSpell spellPicked;
    ConcreteMinion minionPicked;
    ConcreteSpell spellToAdd;
    ConcreteMinion minionToAdd;

    while (activePlayer.getMyStock().size() < 10) {

      // we first have to randomly decide if we have to pick a spell or a minion.
      int randomNum = ThreadLocalRandom.current().nextInt(0, 2);

      // if randomNum is 0 we will pick a spell
      if (randomNum == 0) {
        // we then randomly pick a spell from all the spells the player can pick from
        int randomNumber = ThreadLocalRandom.current().nextInt(0, listSpells.size());

        spellPicked = listSpells.get(randomNumber);
        spellToAdd = spellPicked.clone();
        spellToAdd.setPlayer(activePlayer);
        spellToAdd.setUniqueID();

        // for an unknow reason, when generating the effect of a new minion, if this effect type had
        // already been generated then it would add it to the new minion, resultating in an
        // additional Effect.
        // to fix it, I set the effect of a new minion to null. But that could be better.
        ArrayList<Effect> nullEffect = new ArrayList<>();
        spellToAdd.setMyEffects(nullEffect);

        spellToAdd.generateSpellEffect(spellToAdd.getAbilityKeyWord());
        activePlayer.addCardToStock(spellToAdd);
      }
      if (randomNum == 1) {

        int randomNumber = ThreadLocalRandom.current().nextInt(0, listMinions.size());
        minionPicked = listMinions.get(randomNumber);

        minionToAdd = minionPicked.clone();

        minionToAdd.generateEffect();

        minionToAdd.setPlayer(activePlayer);
        minionToAdd.setUniqueID();

        activePlayer.addCardToStock(minionToAdd);
      }
    }
  }

  /**
   * send the minions of one player in play to both of the players
   *
   * @param player the player
   */
  public void sendMinionsInPlay(Player player) {

    ArrayList<ConcreteMinion> playerMinions = player.getMyMinions();

    ArrayList<MyCardMessage> myMinionsMessage = new ArrayList<>();

    for (ConcreteMinion minion : playerMinions) {
      MyCardMessage cardMessage = new MyCardMessage(minion);
      myMinionsMessage.add(cardMessage);
    }
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_playMinion-user" + player.getSessionId(), myMinionsMessage);
    simpMessagingTemplate.convertAndSend(
            "/queue/reply_playedMinion-user" + player.getOpponent().getSessionId(), myMinionsMessage);
  }

  /**
   * send the minions of both players in play to both of the players
   */
  public void sendBothPlayersMinion() {
    sendMinionsInPlay(game.getActivePlayer());
    sendMinionsInPlay(game.getWaitingPlayer());
  }

  public Application setMinionRepository(MinionRepository minionRepository) {
    this.minionRepository = minionRepository;
    return this;
  }

  public Application setSpellRepository(SpellRepository spellRepository) {
    this.spellRepository = spellRepository;
    return this;
  }

  public Application setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
    return this;
  }

  public void gameOver(String loserID) {

    Player loser = game.getPlayerByID(loserID);
    game.setLoser(loser);
    game.setWinner(loser.getOpponent());
    game.setGameOver(true);
    game.setPassTurn(true);
  }

  public void passTurn(String sessionId) {
    Player passingTurn = game.getPlayerByID(sessionId);

    // only the active player can pass its turn
    if (passingTurn == game.getActivePlayer()) {
      game.setPassTurn(true);
    }
  }
}
