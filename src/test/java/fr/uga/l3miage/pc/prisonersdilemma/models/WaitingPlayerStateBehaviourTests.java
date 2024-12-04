package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitingPlayersStateBehaviourTests {

    private WaitingPlayersStateBehaviour stateBehaviour;
    private GameEncounter gameEncounter;

    @BeforeEach
    void setUp() {
        stateBehaviour = new WaitingPlayersStateBehaviour();
        gameEncounter = new GameEncounter(5,null,null); // Ensure this class is properly implemented.
    }

    @Test
    void joinGame_ShouldAddPlayer1_WhenPlayer1IsNull() {
        // given
        String playerName = "Player1";

        // when
        GameMessage result = stateBehaviour.joinGame(gameEncounter, playerName);

        // then
        assertNotNull(gameEncounter.getPlayer1());
        assertEquals(playerName, gameEncounter.getPlayer1().getName());
        assertEquals("game.join", result.messageType());
        assertEquals(playerName + " has joined the game", result.content());
    }

    @Test
    void joinGame_ShouldAddPlayer2_WhenPlayer2IsNull() {
        // given
        gameEncounter.setPlayer1(new Player("Player1", gameEncounter));
        String playerName = "Player2";

        // when
        GameMessage result = stateBehaviour.joinGame(gameEncounter, playerName);

        // then
        assertNotNull(gameEncounter.getPlayer2());
        assertEquals(playerName, gameEncounter.getPlayer2().getName());
        assertEquals("game.join", result.messageType());
        assertEquals(playerName + " has joined the game", result.content());
    }

    @Test
    void joinGame_ShouldReturnError_WhenGameIsFull() {
        // given
        gameEncounter.setPlayer1(new Player("Player1", gameEncounter));
        gameEncounter.setPlayer2(new Player("Player2", gameEncounter));
        String playerName = "Player3";

        // when
        GameMessage result = stateBehaviour.joinGame(gameEncounter, playerName);

        // then
        assertEquals("game.error", result.messageType());
        assertEquals("error cannot join a full game", result.content());
    }

    @Test
    void leaveGame_ShouldSetPlayer1ToAi_WhenPlayer1Leaves() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        gameEncounter.setPlayer1(player1);
        String playerName = "Player1";

        // when
        GameMessage result = stateBehaviour.leaveGame(gameEncounter, playerName);

        // then
        assertTrue(gameEncounter.getPlayer1().isAiMode());
        assertNotNull(gameEncounter.getPlayer1().getStrategy());
        assertEquals("game.leave", result.messageType());
        assertEquals(playerName + " has left the game", result.content());
    }

    @Test
    void leaveGame_ShouldSetPlayer2ToAi_WhenPlayer2Leaves() {
        // given
        gameEncounter.setPlayer1(new Player("Player1", gameEncounter));
        Player player2 = new Player("Player2", gameEncounter);
        gameEncounter.setPlayer2(player2);
        String playerName = "Player2";

        // when
        GameMessage result = stateBehaviour.leaveGame(gameEncounter, playerName);

        // then
        assertTrue(gameEncounter.getPlayer2().isAiMode());
        assertNotNull(gameEncounter.getPlayer2().getStrategy());
        assertEquals("game.leave", result.messageType());
        assertEquals(playerName + " has left the game", result.content());
    }

    @Test
    void leaveGame_ShouldReturnError_WhenPlayerIsNotInGame() {
        // given
        gameEncounter.setPlayer1(new Player("Player1", gameEncounter));
        gameEncounter.setPlayer2(new Player("Player2", gameEncounter));
        String playerName = "Player3";

        // when
        GameMessage result = stateBehaviour.leaveGame(gameEncounter, playerName);

        // then
        assertEquals("game.error", result.messageType());
        assertEquals(playerName + " cannot leave the game is not in it", result.content());
    }

    @Test
    void makeDecision_ShouldReturnError_WhenGameIsIncomplete() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        gameEncounter.setPlayer1(player1);

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player1, true);

        // then
        assertEquals("game.error", result.messageType());
        assertEquals("erreur : cannot make decision in a game where a player is missing", result.content());
    }
}
