package fr.uga.l3miage.pc.prisonersdilemma.manager;


import fr.uga.l3miage.pc.prisonersdilemma.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manager class for the dilemaPrisonners games.
 * Handles adding and removing players from games, and storing and retrieving the current games.
 *
 * @author Jugurta
 */
public class GameManager {

    /**
     * Map of active dilema games, with the game ID as the key.
     */
    private final Map<Integer, GameEncounter> games;

    /**
     * Map of players waiting to join a dilemaPrisonners game, with the player's name as the key.
     */
    protected final Map<String, Integer> waitingPlayers;

    /**
     * Constructs a new TicTacToeManager.
     */
    public GameManager() {
        games = new ConcurrentHashMap<>();
        waitingPlayers = new ConcurrentHashMap<>();
    }

    /**
     * Attempts to add a player to an existing dilema game, or creates a new game if no open games are available.
     *
     * @param playerName the name of the player
     * @return the dilema game the player was added to
     */
    public synchronized GameEncounter joinGame(String playerName) {

        //REVOIR cette condition et la simplifier (Peut être l'enlever )
        if (games.values().stream()
                .anyMatch(game -> game.getPlayer1().getName().equals(playerName) ||
                                (game.getPlayer2() != null && game.getPlayer2().getName().equals(playerName)))
        )
        {
            return games.values()
                    .stream()
                    .filter(game -> game.getPlayer1().getName().equals(playerName) || game.getPlayer2().getName().equals(playerName)).findFirst().get();
        }

        for (GameEncounter game : games.values()) {
            if (game.getPlayer1() != null && game.getPlayer2() == null) {
                Player player=new Player(playerName,game);
                game.setPlayer2(player);
                game.setGameState(GameState.GAME_IN_PROGRESS);
                return game;
            }
        }

        Player player=new Player(playerName,null);
        GameEncounter game = new GameEncounter(5, player,null);
        player.setGameEncounter(game);
        games.put(game.getGameId(), game);
        waitingPlayers.put(playerName, game.getGameId());
        return game;
    }

    /**
     * Removes a player from their Tic-Tac-Toe game. If the player was the only player in the game,
     * the game is removed.
     *
     * @param playerName the name of the player
     */
    public synchronized GameEncounter leaveGame(String playerName) {
        Integer gameId = getGameByPlayer(playerName) != null ? getGameByPlayer(playerName).getGameId() : null;
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

    /**
     * Returns the Tic-Tac-Toe game with the given game ID.
     *
     * @param gameId the ID of the game
     * @return the Tic-Tac-Toe game with the given game ID, or null if no such game exists
     */
    public GameEncounter getGame(int gameId) {
        return games.get(gameId);
    }

    /**
     * Returns the Tic-Tac-Toe game the given player is in.
     *
     * @param playerName the name of the player
     * @return the Tic-Tac-Toe game the given player is in, or null if the player is not in a game
     */
    public GameEncounter getGameByPlayer(String playerName) {
        return games.values().stream().filter(game -> game.getPlayer1().getName().equals(playerName) || (game.getPlayer2() != null &&
                game.getPlayer2().getName().equals(playerName))).findFirst().orElse(null);
    }

    /**
     * Removes the Tic-Tac-Toe game with the given game ID.
     *
     * @param gameId the ID of the game to remove
     */
    public void removeGame(int gameId) {
        games.remove(gameId);
    }
}


