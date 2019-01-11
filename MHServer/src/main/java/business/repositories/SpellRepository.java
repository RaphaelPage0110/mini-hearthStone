package business.repositories;

import abstracts.CardType;
import impl.ConcreteSpell;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;


public interface SpellRepository extends MongoRepository<ConcreteSpell, String> {
    ArrayList<ConcreteSpell> findByType(CardType cardType);
}
