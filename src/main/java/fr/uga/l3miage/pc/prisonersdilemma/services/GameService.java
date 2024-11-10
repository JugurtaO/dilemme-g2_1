package fr.uga.l3miage.pc.prisonersdilemma.services;


import fr.uga.l3miage.pc.prisonersdilemma.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    public synchronized GameEncounter leaveGame(String playerName) {
        String gameId = getGameByPlayer(playerName) != null ? getGameByPlayer(playerName).getGameId() : null;
        if (gameId != null) {
            waitingPlayers.remove(playerName);
            GameEncounter game = games.get(gameId);
            if (playerName.equals(game.getPlayer1().getName())) {
                if (game.getPlayer2() != null) {
                    game.setPlayer1(game.getPlayer2());
                    game.setPlayer2(null);
                    game.setGameState(GameState.WAITING_FOR_PLAYER);
                    waitingPlayers.put(game.getPlayer1Name(), game.getGameId());
                } else {
                    games.remove(gameId);
                    return null;
                }
            } else if (playerName.equals(game.getPlayer2().getName())) {
                game.setPlayer2(null);
                game.setGameState(GameState.WAITING_FOR_PLAYER);
                waitingPlayers.put(game.getPlayer1().getName(), game.getGameId());
            }
            return game;
        }
        return null;
    }


    public GameEncounter getGame(String gameId) {
        return games.get(gameId);
    }


    public GameEncounter getGameByPlayer(String playerName) {
        return games.values().stream().filter(game -> game.getPlayer1().getName().equals(playerName) || (game.getPlayer2() != null &&
                game.getPlayer2().getName().equals(playerName))).findFirst().orElse(null);
    }


    public void removeGame(String gameId) {
        games.remove(gameId);
    }

}


