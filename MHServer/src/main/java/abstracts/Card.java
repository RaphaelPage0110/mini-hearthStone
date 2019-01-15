package abstracts;

import impl.Player;
import inter.Target;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Abstract representation of Minions and Spells.
 *
 * @author Raphaël Pagé & Henri Bouvet & Alexandre Melo & Glenn Plouhinec
 * @version 0.1
 */
public abstract class Card {

  /**
   * The ID of this type of Card.
   */
  @Id
  protected String id;

  /**
   * Reference the list of actions or behaviors of a this Card.
   */
  protected Map<String, String> abilityKeyWord;
  /**
   * A short description of this Card.
   */
  protected String text;
  /**
   * Indicates the type of this card that can be COMMON, PALADIN, MAGE, or WARRIOR.
   */
  protected CardType type;
  /**
   * Indicates the player who have this card.
   */
  protected Player player;
  /**
   * Indicates the mana cost of this card.
   */
  protected int requiredMana;
  /**
   * The name of the card
   */
  protected String name;
  /**
   * Reference the list of actions or behaviors of this card.
   */
  protected ArrayList<Effect> myEffects;
  /**
   * Allows a Minion or a Spell to give a target a bonus of damage, armor, health, or mana.
   */
  protected int bonus;
  /**
   * The URL of the Card image
   */
  protected String imgurl;
  /**
   * the unique identifier of this card
   */
  private String uniqueID;

  /**
   * Constructor of Card. Initializes most of the properties of this class.
   *
   * @param type           the type of the Card.
   * @param requiredMana   the mana cost of using the Card.
   * @param abilityKeyWord the list of actions or behaviors of a the Card.
   * @param name           the name of the Card.
   * @param imgurl         the URL of the Card image.
   * @param text           the description of the Card.
   */
  public Card(
          CardType type,
          int requiredMana,
          Map<String, String> abilityKeyWord,
          String name,
          String imgurl,
          String text) {
    uniqueID = UUID.randomUUID().toString();
    this.abilityKeyWord = abilityKeyWord;
    this.text = text;
    this.type = type;
    this.requiredMana = requiredMana;
    this.name = name;
    this.imgurl = imgurl;
    this.myEffects = new ArrayList<>();
  }

  /**
   * Default empty constructor of Card.
   */
  public Card() {
    uniqueID = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, String> getAbilityKeyWord() {
    return abilityKeyWord;
  }

  public void setAbilityKeyWord(Map<String, String> abilityKeyWord) {
    this.abilityKeyWord = abilityKeyWord;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CardType getType() {
    return this.type;
  }

  public void setType(CardType type) {
    this.type = type;
  }

  public Player getPlayer() {
    return this.player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public int getRequiredMana() {
    return this.requiredMana;
  }

  public void setRequiredMana(int requiredMana) {
    this.requiredMana = requiredMana;
  }

  public ArrayList<Effect> getMyEffects() {
    return this.myEffects;
  }

  public void setMyEffects(ArrayList<Effect> newActions) {
    this.myEffects = newActions;
  }

  protected void addEffect(Effect newEffect) {
    myEffects.add(newEffect);
  }

  public int getBonus() {
    return this.bonus;
  }

  protected void setBonus(int bonus) {
    this.bonus = bonus;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getImgurl() {
    return imgurl;
  }

  public void setImgurl(String imgurl) {
    this.imgurl = imgurl;
  }

  public String getUniqueID() {
    return uniqueID;
  }

  public void setUniqueID() {
    uniqueID = UUID.randomUUID().toString();
  }

  /**
   * Affects the effect that this card wil have, on one or more targets.
   *
   * @param myTarget the affected target.
   */
  public void effect(Target myTarget) {
    for (Effect action : myEffects) {
      action.effect(myTarget);
    }
  }
}
