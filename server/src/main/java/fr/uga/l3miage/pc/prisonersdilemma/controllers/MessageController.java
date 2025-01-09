package fr.uga.l3miage.pc.prisonersdilemma.controllers;


import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.JoinMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.LeaveMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.PlayerMessage;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.services.GameService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class MessageController {

    public static final String TOPIC_GAME1 = "/topic/game.";
    public static final String PLAYER_NAME_KEY = "playerName";
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final GameService gameService = new GameService();
    @MessageMapping("/game.join")
    @SendTo("/topic/game.state")
    public synchronized GameMessage joinGame(@Payload JoinMessage message, SimpMessageHeaderAccessor headerAccessor) {
        GameMessage gameMessage=gameService.joinGame(message.playerName());
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        if (sessionAttributes == null) {
            sessionAttributes = new ConcurrentHashMap<>();
            headerAccessor.setSessionAttributes(sessionAttributes);
        }
        sessionAttributes.put(PLAYER_NAME_KEY, message.playerName());
        return gameMessage;
    }

    @MessageMapping("/game.decision")
    public synchronized  void makePlayerDecision(@Payload @NonNull PlayerMessage message) {
        String gameId = message.gameId();
        boolean decision = message.decision();
        GameEncounter game = gameService.getGame(gameId);
        Player player = message.playerName().equals(game.getPlayer1Name()) ? game.getPlayer1() : game.getPlayer2();

        GameMessage gameMessage= gameService.makeDecision(game,player,player.makeDecision(decision));
        this.messagingTemplate.convertAndSend(TOPIC_GAME1 + gameId, gameMessage);

        if (game.isGameOver()) {
            GameMessage gameMessage2=new GameMessage("game.gameOver",game.getGameId(),game.getPlayer1Name(),game.getPlayer2Name(),game.getWinner(),"La partie est terminée !",game.getGameState(),game.getNbTours(),game.getCurrentTourNumber(),game.getHistory().getAllTours(),game.getPlayer1().getScore(),game.getPlayer2().getScore());
                this.messagingTemplate.convertAndSend(TOPIC_GAME1 + gameId, gameMessage2);
                gameService.removeGame(gameId);
        }
}

    @MessageMapping("/game.leave")
    public void leaveGame(@Payload LeaveMessage message) {
        GameMessage gameMessage = gameService.leaveGame(message.playerName(),message.choosedStrategyNumber());
        messagingTemplate.convertAndSend( TOPIC_GAME1+ gameMessage.gameId(), gameMessage);
    }

@EventListener
public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

    Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
    if (sessionAttributes != null && sessionAttributes.containsKey(PLAYER_NAME_KEY)) {
        String playerName = (String) sessionAttributes.get(PLAYER_NAME_KEY);
        if(playerName!=null){
            GameEncounter game=gameService.getGameByPlayer(playerName);
            if(game!=null){
                GameMessage gameMessage = gameService.leaveGame(playerName, 1);
                messagingTemplate.convertAndSend(TOPIC_GAME1 + gameMessage.gameId(), gameMessage);
            }

        }
    }

}
}

