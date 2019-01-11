package business.repositories;

import impl.ConcreteHero;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroRepository extends MongoRepository<ConcreteHero, String> {

    ConcreteHero findByHeroName(String heroName);

}
