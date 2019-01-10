package business;

import business.controller.GameController;
import business.repositories.HeroRepository;
import impl.ConcreteHero;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashMap;
import java.util.Map;

import static abstracts.CardType.WARRIOR;
import static org.mockito.Mockito.*;

public class GameTest {

    @Mock
    GameController gameController;
    @Mock
    HeroRepository heroRepository;
    @Mock
    SimpMessagingTemplate simpMessagingTemplate;
    String idPlayer1, idPlayer2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        idPlayer1 = "sessionId1";
        idPlayer2 = "sessionId2";
        Map<String, String> map;

        map = new HashMap<>();
        map.put("modifyArmor","2");
        when(heroRepository.findByHeroName("Garrosh")).thenReturn(new ConcreteHero(WARRIOR,30,0,map,"Garrosh", "img"));
        when(gameController.makeGC()).thenReturn(
                new GameController().setHeroRepository(heroRepository).setApplication(new Application()).setMessagingTemplate(simpMessagingTemplate)
        );
        doNothing().when(simpMessagingTemplate).convertAndSend(any(String.class),any(Object.class));
    }

    @Test
    public void queueingTest() {
        gameController = gameController.makeGC();
        gameController.connectToGame(idPlayer1, "Garrosh");
        assert gameController.getWaitingUsers().get(0).getMyHero().getHeroName().equals("Garrosh");

        //gameController.connectToGame(idPlayer2, "Garrosh");
        //assert gameController.getApplication().getGame().getPlayer1().getMyHero().getHeroName().equals("Garrosh");
    }
}
