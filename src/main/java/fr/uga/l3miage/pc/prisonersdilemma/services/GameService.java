package fr.uga.l3miage.pc.prisonersdilemma.services;


import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Setter
@Getter
public class GameService {


    private final Map<String, GameEncounter> games;

    protected final Map<String, String> waitingPlayers;

    public GameService() {
        games = new ConcurrentHashMap<>();
        waitingPlayers = new ConcurrentHashMap<>();
    }

    public synchronized GameEncounter joinGame(String playerName) {
        for (GameEncounter game : games.values()) {
            if (game.getPlayer1() != null && game.getPlayer2() == null) {
                Player player = new Player(playerName, game);
                game.setPlayer2(player);
                game.setGameState(GameState.GAME_IN_PROGRESS);
                return game;
            }
        }

        Player player = new Player(playerName, null);
        GameEncounter game = new GameEncounter(5, player, null);
        player.setGameEncounter(game);
        game.setGameState(GameState.WAITING_FOR_PLAYER);
        games.put(game.getGameId(), game);
        waitingPlayers.put(playerName, game.getGameId());
        return game;
    }

//    public synchronized Tour makeDecision(String gameId,String playerName,boolean playerDecision){
//        GameEncounter game = getGame(gameId);
//        if (game.isGameOver()) {
//            GameMessage errorMessage = new GameMessage("game.error",gameId,gameId,null,null,"Game not found or is already over.",null,game.getGameState(),null,game.getNbTours(),0);
//            this.messagingTemplate.convertAndSend("/topic/game." + gameId, errorMessage);
//            return;
//        }
//
//        if (game.getGameState().equals(GameState.WAITING_FOR_PLAYER)) {
//            GameMessage errorMessage = new GameMessage("game.error",gameId,gameId,null,null,"Game is waiting for another player to join.",null,game.getGameState(),null,game.getNbTours(),0);
//            this.messagingTemplate.convertAndSend("/topic/game." + gameId, errorMessage);
//            return;
//        }
//
//
//        if (game.getTurn().equals(player)) {
//            game.makeMove(player, move);
//
//            TicTacToeMessage gameStateMessage = new TicTacToeMessage(game);
//            gameStateMessage.setType("game.move");
//            this.messagingTemplate.convertAndSend("/topic/game." + gameId, gameStateMessage);
//
//            if (game.isGameOver()) {
//                TicTacToeMessage gameOverMessage = gameToMessage(game);
//                gameOverMessage.setType("game.gameOver");
//                this.messagingTemplate.convertAndSend("/topic/game." + gameId, gameOverMessage);
//                ticTacToeManager.removeGame(gameId);
//            }
//        }
//    }



    public GameEncounter getGame(String gameId) {
        return games.get(gameId);
    }


    public GameEncounter getGameByPlayer(String playerName) {
        return games.values().stream().filter(game -> game.getPlayer1().getName().equals(playerName) || (game.getPlayer2() != null &&
                game.getPlayer2().getName().equals(playerName))).findFirst().orElse(null);
    }


    public GameEncounter removeGame(String gameId) {
        return games.remove(gameId);
    }

}


