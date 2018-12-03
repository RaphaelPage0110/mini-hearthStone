package repositories;
import abstracts.CardType;
import impl.ConcreteHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private HeroRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        /*
        repository.deleteAll();

        // save a couple of heroes
        Map<String, String> abilityMage = new HashMap<String, String>();
        abilityMage.put("DamageTarget","1");
        repository.save(new ConcreteHero("mage", 30, 0, abilityMage));

        Map<String, String> abilityWarrior = new HashMap<String, String>();
        abilityWarrior.put("ModifyArmor","2");
        repository.save(new ConcreteHero("warrior", 30, 0, abilityWarrior));

        Map<String, String> abilityPaladin = new HashMap<String, String>();
        abilityPaladin.put("Summon","serviteurMainArgent");
        repository.save(new ConcreteHero("paladin", 30, 0, abilityPaladin));

        // fetch all heroes
        */
        ConcreteHero heroJaina = repository.findByHeroName("Jaina");
        System.out.println(heroJaina);
        CardType type = CardType.MAGE;
        ConcreteHero heroMage = repository.findByHeroType(type);
        System.out.println(heroMage);
        System.out.println("Heroes found with findAll():");
        System.out.println("-------------------------------");
        for (ConcreteHero hero : repository.findAll()) {
            System.out.println(hero);
        }






    }

}