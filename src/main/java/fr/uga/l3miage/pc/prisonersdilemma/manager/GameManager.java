package fr.uga.l3miage.pc.prisonersdilemma.manager;


import fr.uga.l3miage.pc.prisonersdilemma.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
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
    protected final Map<String, String> waitingPlayers;

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
     * @param player the name of the player
     * @return the dilema game the player was added to
     */
    public synchronized GameEncounter joinGame(String player) {
        if (games.values().stream().anyMatch(game -> game.getPlayer1().equals(player) || (game.getPlayer2() != null && game.getPlayer2().equals(player)))) {
            return games.values().stream().filter(game -> game.getPlayer1().equals(player) || game.getPlayer2().equals(player)).findFirst().get();
        }

        for (GameEncounter game : games.values()) {
            if (game.getPlayer1() != null && game.getPlayer2() == null) {
                game.setPlayer2(player);
                game.setGameState(GameState.PLAYER1_TURN);
                return game;
            }
        }

        GameEncounter game = new GameEncounter(player, null);
        games.put(game.getGameId(), game);
        waitingPlayers.put(player, game.getGameId());
        return game;
    }

    /**
     * Removes a player from their Tic-Tac-Toe game. If the player was the only player in the game,
     * the game is removed.
     *
     * @param player the name of the player
     */
    public synchronized GameEncounter leaveGame(String player) {
        String gameId = getGameByPlayer(player) != null ? getGameByPlayer(player).getGameId() : null;
        if (gameId != null) {
            waitingPlayers.remove(player);
            GameEncounter game = games.get(gameId);
            if (player.equals(game.getPlayer1())) {
                if (game.getPlayer2() != null) {
                    game.setPlayer1(game.getPlayer2());
                    game.setPlayer2(null);
                    game.setGameState(GameState.WAITING_FOR_PLAYER);
                    game.setBoard(new String[3][3]);
                    waitingPlayers.put(game.getPlayer1Name(), game.getGameId());
                } else {
                    games.remove(gameId);
                    return null;
                }
            } else if (player.equals(game.getPlayer2())) {
                game.setPlayer2(null);
                game.setGameState(GameState.WAITING_FOR_PLAYER);
                game.setBoard(new String[3][3]);
                waitingPlayers.put(game.getPlayer1(), game.getGameId());
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
        return games.values().stream().filter(game -> game.getPlayer1().equals(playerName) || (game.getPlayer2() != null &&
                game.getPlayer2().equals(playerName))).findFirst().orElse(null);
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


