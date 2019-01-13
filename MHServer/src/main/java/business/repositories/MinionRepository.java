package business.repositories;

import abstracts.CardType;
import impl.ConcreteMinion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface MinionRepository extends MongoRepository<ConcreteMinion, String> {

    /**
     * find a minion in the database using its name
     *
     * @param minionName the name of the minion (ex: Sanglier brocheroc)
     * @return a ConcreteMinion if the name matches
     */
    ConcreteMinion findByName(String minionName);

    /**
     * find a minion in the database using its type (ex: CardType.COMMON)
     * @param cardType the type of the minion
     * @return an ArrayList of the ConcreteMinion whose type matches
     */
    ArrayList<ConcreteMinion> findByType(CardType cardType);

}
