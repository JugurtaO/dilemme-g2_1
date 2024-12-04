package fr.uga.l3miage.pc.prisonersdilemma.controllers;


import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.JoinMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.PlayerMessage;
import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.services.GameService;
import lombok.NonNull;
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

    public static final String TOPIC_GAME1 = "/topic/game.";
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final GameService gameService = new GameService();
    @MessageMapping("/game.join")
    @SendTo("/topic/game.state")
    public GameMessage joinGame(@Payload JoinMessage message, SimpMessageHeaderAccessor headerAccessor) {
        GameEncounter game = gameService.joinGame(message.playerName());

        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("gameId", game.getGameId());
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("playerName", message.playerName());
        return new GameMessage (
                "game.joined",
                game.getGameId(),
                game.getPlayer1Name(),
                game.getPlayer2()!=null ?  game.getPlayer2().getName():null,
                null,
                message.playerName()+" a rejoint la partie.",
                game.getGameState(),
                game.getNbTours(),
                game.getCurrentTourNumber(),
                game.getHistory().getAllTours(),
                game.getPlayer1()!=null ? game.getPlayer1().getScore():0,
                game.getPlayer2()!=null ? game.getPlayer2().getScore():0
                );

    }


    @MessageMapping("/game.decision")
    public void makePlayerDecision(@Payload @NonNull PlayerMessage message) {
        String gameId = message.gameId();
        boolean decision = message.decision();
        GameEncounter game = gameService.getGame(gameId);
        Player player = message.playerName().equals(game.getPlayer1Name()) ? game.getPlayer1() : game.getPlayer2();

        if (game.isGameOver()) {
            GameMessage errorMessage = new GameMessage("game.error",gameId,gameId,null,null,"Game not found or is already over.",game.getGameState(),game.getNbTours(),game.getCurrentTourNumber(),game.getHistory().getAllTours(),game.getPlayer1().getScore(),game.getPlayer2().getScore());
            this.messagingTemplate.convertAndSend(TOPIC_GAME1 + gameId, errorMessage);
            return;
        }
        if (game.getGameState().equals(GameState.WAITING_FOR_PLAYER)) {
            GameMessage errorMessage = new GameMessage("game.error",gameId,gameId,null,null,"Game is waiting for another player to join.",game.getGameState(),game.getNbTours(),game.getCurrentTourNumber(),game.getHistory().getAllTours(),game.getPlayer1().getScore(),game.getPlayer2().getScore());
            this.messagingTemplate.convertAndSend(TOPIC_GAME1 + gameId, errorMessage);
            return;

        }

        GameMessage gameMessage= gameService.makeDecision(game,player,player.makeDecision(decision));
        this.messagingTemplate.convertAndSend(TOPIC_GAME1 + gameId, gameMessage);

        if (game.isGameOver()) {
            GameMessage gameMessage2=new GameMessage("game.gameOver",game.getGameId(),game.getPlayer1Name(),game.getPlayer2Name(),game.getWinner(),"La partie est terminée !",game.getGameState(),game.getNbTours(),game.getCurrentTourNumber(),game.getHistory().getAllTours(),game.getPlayer1().getScore(),game.getPlayer2().getScore());
                this.messagingTemplate.convertAndSend(TOPIC_GAME1 + gameId, gameMessage2);
                gameService.removeGame(gameId);


        }


}}