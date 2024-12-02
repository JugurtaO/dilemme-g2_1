package fr.uga.l3miage.pc.prisonersdilemma.controllers;


import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.JoinMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.PlayerMessage;
import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
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
                null,
                game.getGameState(),
                null,
                game.getNbTours(),
                0
                );

    }

    /**
     * Handles a request from a client to make a decision.
     * If the Decision is valid, the game state is updated and sent to all subscribers of the game's topic.
     * If the Game is over, a message is sent indicating the result of the game.
     *
     * @param message the message from the client containing the player's name, game ID, and decision
     */
    @MessageMapping("/game.decision")
    public void makeMove(@Payload PlayerMessage message) {
        String gameId = message.gameId();
        GameEncounter game = gameService.getGame(gameId);
        String player = message.playerName();
        boolean decision = message.decision();
        if (game.isGameOver()) {
            GameMessage errorMessage = new GameMessage("game.error",gameId,gameId,null,null,"Game not found or is already over.",null,game.getGameState(),null,game.getNbTours(),0);
            this.messagingTemplate.convertAndSend("/topic/game." + gameId, errorMessage);
            return;
        }

        if (game.getGameState().equals(GameState.WAITING_FOR_PLAYER)) {
            GameMessage errorMessage = new GameMessage("game.error",gameId,gameId,null,null,"Game is waiting for another player to join.",null,game.getGameState(),null,game.getNbTours(),0);
            this.messagingTemplate.convertAndSend("/topic/game." + gameId, errorMessage);

        }

        //Penser à vérifier si un joueur a déjà joué et qu'il essaie rejouer tandis  que l'autre n'a pas encore joué
        // de l'informer et ne pas jouer jusqu'à ce que l'autre joue.

        if (game.getTurn().equals(player)) {
            game.makeMove(player, move);

            TicTacToeMessage gameStateMessage = new TicTacToeMessage(game);
            gameStateMessage.setType("game.move");
            this.messagingTemplate.convertAndSend("/topic/game." + gameId, gameStateMessage);

            if (game.isGameOver()) {
                TicTacToeMessage gameOverMessage = gameToMessage(game);
                gameOverMessage.setType("game.gameOver");
                this.messagingTemplate.convertAndSend("/topic/game." + gameId, gameOverMessage);
                ticTacToeManager.removeGame(gameId);
            }
        }
    }



}