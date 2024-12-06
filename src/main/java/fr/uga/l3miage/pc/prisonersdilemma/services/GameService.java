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

    public synchronized GameMessage leaveGame(String playerName){
        GameEncounter game = getGameByPlayer(playerName);

        GameMessage gameMessage=game.leaveGame(game,playerName);
        if((game.getPlayer1().isAiMode() && (game.getPlayer2()==null||game.getPlayer2().isAiMode()))){
                removeGame(game.getGameId());
        }

        return gameMessage;

    }
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


