package business.repositories;

import impl.ConcreteHero;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroRepository extends MongoRepository<ConcreteHero, String> {

    /**
     * Find a hero in the database using its name
     *
     * @param heroName the name of the hero (ex: Uther)
     * @return a ConcreteHero if the name matches
     */
    ConcreteHero findByHeroName(String heroName);

}
