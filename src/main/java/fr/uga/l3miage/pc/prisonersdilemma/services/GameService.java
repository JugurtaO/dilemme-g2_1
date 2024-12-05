package fr.uga.l3miage.pc.prisonersdilemma.services;


import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.models.WaitingPlayersStateBehaviour;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Setter
@Getter
public class GameService {


    private final Map<String, GameEncounter> games;


    public GameService() {
        games = new ConcurrentHashMap<>();
    }

    public synchronized GameMessage joinGame(String playerName) {
        for (GameEncounter game : games.values()) {
            if (game.getPlayer1() != null && game.getPlayer2() == null) {
                return game.joinGame(game,playerName);
            }
        }

        GameEncounter game = new GameEncounter(5, null, null);
        game.setGameStateBehaviour(new WaitingPlayersStateBehaviour());
        game.setCurrentTourNumber(1);
        games.put(game.getGameId(), game);
        return game.joinGame(game,playerName);
    }

    public synchronized GameMessage makeDecision(GameEncounter currentGame, Player currentPlayer, boolean playerDecision) {
        return currentGame.getGameStateBehaviour().makeDecision(currentGame, currentPlayer, playerDecision);
    }

//    public synchronized GameMessage leaveGame(String playerName){
//        GameEncounter game = getGameByPlayer(playerName);
//        if (game!= null) {
//            if (playerName.equals(game.getPlayer1().getName())) {
//                if (game.getPlayer2() != null) {
//                    game.setPlayer1(game.getPlayer2());
//                    game.setPlayer2(null);
//                    game.setGameState(GameState.WAITING_FOR_PLAYER);
//                    game.setBoard(new String[3][3]);
//                    waitingPlayers.put(game.getPlayer1(), game.getGameId());
//                } else {
//                    games.remove(gameId);
//                    return null;
//                }
//            } else if (playerName.equals(game.getPlayer2().getName())) {
//                game.setPlayer2(null);
//                game.setGameState(GameState.WAITING_FOR_PLAYER);
//                game.setBoard(new String[3][3]);
//                waitingPlayers.put(game.getPlayer1(), game.getGameId());
//            }
//            return game;
//        }
//        return null;
//    }
    public GameEncounter getGame(String gameId) {
        return games.get(gameId);
    }


    public GameEncounter getGameByPlayer(String playerName) {
        return games.values().stream().filter(game -> game.getPlayer1().getName().equals(playerName) || (game.getPlayer2() != null && game.getPlayer2().getName().equals(playerName))).findFirst().orElse(null);
    }


    public GameEncounter removeGame(String gameId) {
        return games.remove(gameId);
    }

}


