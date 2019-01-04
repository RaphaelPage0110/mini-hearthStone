package business.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Controller;
import business.messageModels.Hello;
import business.messageModels.User;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class WebController {

    private static final Logger LOGGER = Logger.getLogger(WebController.class.getName());
    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
            .create(SimpMessageType.MESSAGE);

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Hello greeting(User user) throws Exception {
        LOGGER.log(Level.WARNING, "Received user: "+user.getName());
        return new Hello("Hello, "+user.getName());
    }
}
