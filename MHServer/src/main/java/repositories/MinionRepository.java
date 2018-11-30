package repositories;

import impl.ConcreteMinion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MinionRepository extends MongoRepository<ConcreteMinion, String> {

    public ConcreteMinion findByName(String minionName);

}