package business.repositories;

import abstracts.CardType;
import impl.ConcreteSpell;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface SpellRepository extends MongoRepository<ConcreteSpell, String> {

  /**
   * find a spell in the database using its type (ex: CardType.MAGE)
   *
   * @param cardType the type of the spell
   * @return an ArrayList of ConcreteSpell whose type matches
   */
  ArrayList<ConcreteSpell> findByType(CardType cardType);
}
