package repositories.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import repositories.Hello;
import repositories.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class WebController {

    private static final Logger LOGGER = Logger.getLogger(WebController.class.getName());
    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
            .create(SimpMessageType.MESSAGE);

    private Collection<String> waitingUsers = new HashSet<>();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Hello greeting(User user) throws Exception {
        LOGGER.log(Level.WARNING, "Received user: "+user.getName());
        return new Hello("Hello, "+user.getName());
    }


    @MessageMapping("/connect")
    @SendTo("user/queue/reply")
    public Object connectToGame(@Header("simpSessionId") String sessionId) {
        LOGGER.info("Received a connection from a user");
        waitingUsers.add(sessionId);
        System.out.println(sessionId);
        simpMessagingTemplate.convertAndSend("/queue/reply-user"+sessionId, new Hello("En attente d'un autre joueur..."));
        if (waitingUsers.size() == 2) {
            startGame();
            waitingUsers.clear();
        }


        return null;
    }

    private void startGame() {
        LOGGER.log(Level.INFO, "Starting game");
        String player1 = (String) waitingUsers.toArray()[0];
        String player2 = (String) waitingUsers.toArray()[1];

        simpMessagingTemplate.convertAndSend("/queue/reply-user"+player1, new Hello("Vous êtes le joueur 1"));
        simpMessagingTemplate.convertAndSend("/queue/reply-user"+player2, new Hello("Vous êtes le joueur 2"));


        LOGGER.log(Level.INFO, "Messages sent");
    }

}
