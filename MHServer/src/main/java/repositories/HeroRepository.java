package repositories;

import impl.ConcreteHero;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroRepository extends MongoRepository<ConcreteHero, String> {

    public ConcreteHero findByHeroName(String heroName);

}