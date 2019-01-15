package mocks;

import abstracts.Card;
import impl.ConcreteSpell;
import impl.EntitiesFactory;
import impl.Player;
import impl.behaviour.generic.notTargetedEffect.DrawCard;

import static abstracts.ConstsUtils.*;

public class DrawCardMock extends DrawCard {

  private ConcreteSpell spell;

  private int numberCards;

  public DrawCardMock(ConcreteSpell concreteSpell, int numberCards) {
    this.numberCards = numberCards;
    this.spell = concreteSpell;
  }

  @Override
  public void effect() {

    EntitiesFactory entitiesFactory = new EntitiesFactory();
    Card newCard;

    for (int i = 0; i < numberCards; i++) {

      int random = (int) Math.floor(Math.random() * 14);

      switch (random) {
        case 1:
          newCard = entitiesFactory.createMinion(SANGLIER_BROCHEROC);
          break;
        case 2:
          newCard = entitiesFactory.createMinion(SOLDAT_DU_COMTE_DE_L_OR);
          break;
        case 3:
          newCard = entitiesFactory.createMinion(CHEVAUCHEUR_DE_LOUP);
          break;
        case 4:
          newCard = entitiesFactory.createMinion(CHEF_DE_RAID);
          break;
        case 5:
          newCard = entitiesFactory.createMinion(YETI_NOROIT);
          break;
        case 6:
          newCard = entitiesFactory.createMinion(CHAMPION_FRISSELAME);
          break;
        case 7:
          newCard = entitiesFactory.createMinion(AVOCAT_COMMIS_D_OFFICE);
          break;
        case 8:
          newCard = entitiesFactory.createSpell(IMAGE_MIROIR);
          break;
        case 9:
          newCard = entitiesFactory.createSpell(EXPLOSION_DES_ARCANES);
          break;
        case 10:
          newCard = entitiesFactory.createSpell(METAMORPHOSE);
          break;
        case 11:
          newCard = entitiesFactory.createSpell(BENEDICTION_DE_PUISSANCE);
          break;
        case 12:
          newCard = entitiesFactory.createSpell(CONSECRATION);
          break;
        case 13:
          newCard = entitiesFactory.createSpell(TOURBILLON);
          break;
        default:
          newCard = entitiesFactory.createSpell(MAITRISE_DU_BLOCAGE);
          break;
      }
      spell.getPlayer().addCardToHand(newCard);
    }
  }
}
