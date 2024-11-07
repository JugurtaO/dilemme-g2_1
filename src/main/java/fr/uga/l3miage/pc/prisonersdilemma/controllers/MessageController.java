package fr.uga.l3miage.pc.prisonersdilemma.controllers;


import fr.uga.l3miage.pc.prisonersdilemma.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.dto.JoinMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.PlayerMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.services.GameService;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Controller class for handling WebSocket messages and managing the Tic-Tac-Toe games.
 *
 * @author Jugurta
 */
@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final GameService gameService = new GameService();

  //Test controller
    @MessageMapping("/send")  // Client sends to /app/send
    @SendTo("/topic/messages")  // Server broadcasts to /topic/messages
    public String handleMessage(String  message) throws Exception{
        return "Message reçu : " + message;
    }

    /**
     * Handles a request from a client to join a Tic-Tac-Toe game.
     * If a game is available and the player is successfully added to the game,
     * the current state of the game is sent to all subscribers of the game's topic.
     *
     * @param message the message from the client containing the player's name
     * @return the current state of the game, or an error message if the player was unable to join
     */
    @MessageMapping("/game.join")
    @SendTo("/topic/game.state")
    public Object joinGame(@Payload JoinMessage message, SimpMessageHeaderAccessor headerAccessor) {
        GameEncounter game = gameService.joinGame(message.getPlayerName());
        headerAccessor.getSessionAttributes().put("gameId", game.getGameId());
        headerAccessor.getSessionAttributes().put("player", message.getPlayerName());

        GameMessage gameMessage = new GameMessage(game);
        gameMessage.setMessagetype("game.joined");
        return gameMessage;
    }

    /**
     * Handles a request from a client to leave a Tic-Tac-Toe game.
     * If the player is successfully removed from the game, a message is sent to subscribers
     * of the game's topic indicating that the player has left.
     *
     * @param message the message from the client containing the player's name
     */
    @MessageMapping("/game.leave")
    public void leaveGame(@Payload PlayerMessage message) {
        GameEncounter game = gameService.leaveGame(message.getPlayerName());
        if (game != null) {
            GameMessage gameMessage = new GameMessage(game);
            gameMessage.setMessagetype("game.left");
            messagingTemplate.convertAndSend("/topic/game." + game.getGameId(), gameMessage);
        }
    }

    /**
     * Handles a request from a client to make a decision in a dilema game.
     * If the decision is valid, the game state is updated and sent to all subscribers of the game's topic.
     * If the game is over, a message is sent indicating the result of the game.
     *
     * @param message the message from the client containing the player's name, game ID,
     *     Messagetype;
     *      playerName;
     *       winner;
     *       content;
     *       decision;
     *      gameState;
     *      and sender
     */
    @MessageMapping("/game.move")
    public void makeDecision(@Payload GameMessage message) {
        String playerName= message.getSender();
        String gameId = message.getGameId();
        boolean decision = message.getDecision();
        GameEncounter game = gameService.getGame(gameId);

        if (game == null || game.isGameOver()) {
            GameMessage errorMessage = new GameMessage();
            errorMessage.setMessagetype("error");
            errorMessage.setContent("Game not found or is already over.");
            this.messagingTemplate.convertAndSend("/topic/game." + gameId, errorMessage);
            return;
        }

        if (game.getGameState().equals(GameState.WAITING_FOR_PLAYER)) {
            GameMessage errorMessage = new GameMessage();
            errorMessage.setMessagetype("error");
            errorMessage.setContent("Game is waiting for another player to join.");
            this.messagingTemplate.convertAndSend("/topic/game." + gameId, errorMessage);
            return;
        }


        /*
        * Écrire une condition pour vérifier si les deux joueurs n'ont pas déjà fait une décision
        * prendre celle du joueur en cours en compte
        * */

//        if (game.getTurn().equals(playerName)) {
//            game.makeMove(player, move);
//
//            GameMessage gameStateMessage = new GameMessage(game);
//            gameStateMessage.setType("game.move");
//            this.messagingTemplate.convertAndSend("/topic/game." + gameId, gameStateMessage);
//
//            if (game.isGameOver()) {
//                GameMessage gameOverMessage = gameToMessage(game);
//                gameOverMessage.setType("game.gameOver");
//                this.messagingTemplate.convertAndSend("/topic/game." + gameId, gameOverMessage);
//                gameService.removeGame(gameId);
//            }
//        }
    }

    @EventListener
    public void SessionDisconnectEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String gameId = headerAccessor.getSessionAttributes().get("gameId").toString();
        String player = headerAccessor.getSessionAttributes().get("player").toString();
        GameEncounter game = gameService.getGame(gameId);
        if (game != null) {
            if (game.getPlayer1().getName().equals(player)) {
                game.setPlayer1(null);
                if (game.getPlayer2() != null) {
                    game.setGameState(GameState.PLAYER2_WON);
                    game.setWinner(game.getPlayer2().getName());
                } else {
                    gameService.removeGame(gameId);
                }
            } else if (game.getPlayer2() != null && game.getPlayer2().equals(player)) {
                game.setPlayer2(null);
                if (game.getPlayer1() != null) {
                    game.setGameState(GameState.PLAYER1_WON);
                    game.setWinner(game.getPlayer1().getName());
                } else {
                    gameService.removeGame(gameId);
                }
            }
            GameMessage gameMessage = new GameMessage(game);
            gameMessage.setMessagetype("game.gameOver");
            messagingTemplate.convertAndSend("/topic/game." + gameId, gameMessage);
            gameService.removeGame(gameId);
        }
    }


}