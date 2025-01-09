package fr.uga.m1miage.pc.prisonersdilemma.endpoints;

import fr.uga.m1miage.pc.prisonersdilemma.requests.JoinMessage;
import fr.uga.m1miage.pc.prisonersdilemma.requests.LeaveMessage;
import fr.uga.m1miage.pc.prisonersdilemma.requests.PlayerMessage;
import fr.uga.m1miage.pc.prisonersdilemma.responses.GameMessage;
import io.micrometer.common.lang.NonNull;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public interface GameEndpoints {
    @MessageMapping("/game.join")
    @SendTo("/topic/game.state")
    GameMessage joinGame(@Payload JoinMessage message, SimpMessageHeaderAccessor headerAccessor);


    @MessageMapping("/game.decision")
    void makePlayerDecision(@Payload @NonNull PlayerMessage message);

    @MessageMapping("/game.leave")
     void leaveGame(@Payload LeaveMessage message);

    @EventListener
     void handleWebSocketDisconnectListener(SessionDisconnectEvent event);

}
