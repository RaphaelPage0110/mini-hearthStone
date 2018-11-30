package repositories;

import impl.ConcreteSpell;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SpellRepository extends MongoRepository<ConcreteSpell, String> {
    public ConcreteSpell findByName(String spellName);
}