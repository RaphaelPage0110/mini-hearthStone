package repositories;

import abstracts.CardType;
import impl.ConcreteHero;
import impl.ConcreteMinion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

public interface MinionRepository extends MongoRepository<ConcreteMinion, String> {

    ConcreteMinion findByName(String minionName);
    ArrayList<ConcreteMinion> findByType(CardType cardType);

}