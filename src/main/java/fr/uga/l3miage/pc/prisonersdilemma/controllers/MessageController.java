package fr.uga.l3miage.pc.prisonersdilemma.controllers;


import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.JoinMessage;
import fr.uga.l3miage.pc.prisonersdilemma.dto.PlayerMessage;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.services.GameService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    public static final String TOPIC_GAME1 = "/topic/game.";
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final GameService gameService = new GameService();
    @MessageMapping("/game.join")
    @SendTo("/topic/game.state")
    public synchronized GameMessage joinGame(@Payload JoinMessage message) {
        return gameService.joinGame(message.playerName());
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

//    @MessageMapping("/game.leave")
//    public void leaveGame(@Payload LeaveMessage message) {
//        GameMessage gameMessage = gameService.leaveGame(message.playerName());
//        if (gameMessage.gameId() != null) {
//            messagingTemplate.convertAndSend("/topic/game." + gameMessage.gameId(), gameMessage);
//        }
//    }

//    @EventListener
//    public  void SessionDisconnectEvent(SessionDisconnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String gameId = headerAccessor.getSessionAttributes().get("gameId").toString();
//        String player = headerAccessor.getSessionAttributes().get("player").toString();
//        TicTacToe game = ticTacToeManager.getGame(gameId);
//        if (game != null) {
//            if (game.getPlayer1().equals(player)) {
//                game.setPlayer1(null);
//                if (game.getPlayer2() != null) {
//                    game.setGameState(GameState.PLAYER2_WON);
//                    game.setWinner(game.getPlayer2());
//                } else {
//                    ticTacToeManager.removeGame(gameId);
//                }
//            } else if (game.getPlayer2() != null && game.getPlayer2().equals(player)) {
//                game.setPlayer2(null);
//                if (game.getPlayer1() != null) {
//                    game.setGameState(GameState.PLAYER1_WON);
//                    game.setWinner(game.getPlayer1());
//                } else {
//                    ticTacToeManager.removeGame(gameId);
//                }
//            }
//            TicTacToeMessage gameMessage = gameToMessage(game);
//            gameMessage.setType("game.gameOver");
//            messagingTemplate.convertAndSend("/topic/game." + gameId, gameMessage);
//            ticTacToeManager.removeGame(gameId);
//        }
//    }


}

