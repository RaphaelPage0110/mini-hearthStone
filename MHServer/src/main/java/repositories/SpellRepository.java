package repositories;

import abstracts.CardType;
import impl.ConcreteMinion;
import impl.ConcreteSpell;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;


public interface SpellRepository extends MongoRepository<ConcreteSpell, String> {
    public ConcreteSpell findByName(String spellName);
    ArrayList<ConcreteSpell> findByType(CardType cardType);
}