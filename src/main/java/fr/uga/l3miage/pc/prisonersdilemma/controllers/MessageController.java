package fr.uga.l3miage.pc.prisonersdilemma.controllers;


import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.JoinMessage;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class MessageController {

    public static final String TOPIC_GAME = "/topic/game.";
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final GameService gameService = new GameService();

    @MessageMapping("/game.join")
    @SendTo("/topic/game.state")
    public GameMessage joinGame(@Payload JoinMessage message, SimpMessageHeaderAccessor headerAccessor) {
        GameEncounter game = gameService.joinGame(message.getPlayerName());
        GameMessage gameMessage = new GameMessage(game);

        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("gameId", game.getGameId());
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("playerName", message.getPlayerName());


        gameMessage.setContent(message.getPlayerName()+" a rejoint la partie.");

            gameMessage.setMessageType("game.joined");
            return gameMessage;

    }



}