package business;


import business.controller.GameController;
import business.repositories.HeroRepository;
import business.repositories.MinionRepository;
import business.repositories.SpellRepository;
import impl.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static abstracts.CardType.COMMON;
import static abstracts.CardType.WARRIOR;
import static org.mockito.Mockito.*;

public class GameTest {

    @Mock
    GameController gameController;
    @Mock
    HeroRepository heroRepository;
    @Mock
    MinionRepository minionRepository;
    @Mock
    SpellRepository spellRepository;
    @Mock
    SimpMessagingTemplate simpMessagingTemplate;
    String idPlayer1, idPlayer2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Player player1 = new Player();
        ConcreteHero garrosh;
        ConcreteSpell shieldBlock;
        idPlayer1 = "sessionId1";
        idPlayer2 = "sessionId2";
//        Map<String, String> map;
        EntitiesFactory entitiesFactory = new EntitiesFactory();



        doNothing().when(simpMessagingTemplate).convertAndSend(any(String.class),any(Object.class));

//        map = new HashMap<>();
//        map.put("modifyArmor","2");
//        garrosh = new ConcreteHero(WARRIOR,30,0,map,"Garrosh", "img");
        garrosh = entitiesFactory.createHero("Garrosh");
        player1.setMyHero(garrosh); garrosh.setMyPlayer(player1);

//        map.clear();
        when(minionRepository.findByType(COMMON)).thenReturn(new ArrayList<>(Collections.singleton(entitiesFactory.createMinion("Sanglier Brocheroc")/*new ConcreteMinion(COMMON, 1, 1, 1, map, map, "Sanglier Brocheroc", "img", "")*/)));
        when(minionRepository.findByType(WARRIOR)).thenReturn(new ArrayList<ConcreteMinion>());

//        map.put("modifyArmor","5");
//        map.put("drawCard","1");
//        shieldBlock = new ConcreteSpell(WARRIOR,3,0,0,map,"Maîtrise du blocage","img","ajoute 5 points d'armure et pioche 1 carte", player1);
        shieldBlock = entitiesFactory.createSpell("Maîtrise du blocage");
        player1.addCardToStock(shieldBlock); shieldBlock.setPlayer(player1);

        when(spellRepository.findByType(COMMON)).thenReturn(new ArrayList<ConcreteSpell>());
        when(spellRepository.findByType(WARRIOR)).thenReturn(new ArrayList<>(Collections.singleton(shieldBlock)));

        when(heroRepository.findByHeroName(any(String.class))).thenReturn(garrosh);
        when(gameController.makeGC()).thenReturn(
                new GameController().setHeroRepository(heroRepository).setApplication(new Application().setMinionRepository(minionRepository).setSpellRepository(spellRepository).setSimpMessagingTemplate(simpMessagingTemplate)).setMessagingTemplate(simpMessagingTemplate)
        );


    }

    @Test
    public void queueingTest() {
        gameController = gameController.makeGC();
        gameController.connectToGame(idPlayer1, "Garrosh");
        assert gameController.getWaitingUsers().get(0).getMyHero().getHeroName().equals("Garrosh");

        gameController.connectToGame(idPlayer2, "Garrosh");
        //assert gameController.getApplication().getGame().getPlayer1().getMyHero().getHeroName().equals("Garrosh");
    }
}
