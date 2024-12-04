package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameInProgressStateBehaviourTests {

    private GameInProgressStateBehaviour stateBehaviour;
    private GameEncounter gameEncounter;

    @BeforeEach
    void setUp() {
        stateBehaviour = new GameInProgressStateBehaviour();
        gameEncounter = new GameEncounter(5,null,null);
    }

    @Test
    void joinGame_ShouldReturnError_WhenGameIsInProgress() {
        // given
        String playerName = "Player1";

        // when
        GameMessage result = stateBehaviour.joinGame(gameEncounter, playerName);

        // then
        assertEquals("game.error", result.messageType());
        assertEquals(playerName + "cannot join. Game is in progress", result.content());
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
    void makeDecision_ShouldReturnError_WhenPlayersAreMissing() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        gameEncounter.setPlayer1(player1); // Player2 is missing

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player1, true);

        // then
        assertEquals("game.error", result.messageType());
        assertEquals("Game is in progress without all players", result.content());
    }

    @Test
    void makeDecision_ShouldAddPlayer1Decision_WhenNoCurrentTourExists() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        Player player2 = new Player("Player2", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setCurrentTourNumber(1);

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player1, true);

        // then
        assertEquals("game.decision", result.messageType());
        assertEquals("Player1 a joué !", result.content());
        assertNotNull(gameEncounter.getHistory().getLastTour());
        assertTrue(gameEncounter.getHistory().getLastTour().getPlayer1Decision());
    }

    @Test
    void makeDecision_ShouldAddPlayer2Decision_WhenPlayer1HasAlreadyPlayed() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        Player player2 = new Player("Player2", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setCurrentTourNumber(1);

        // Player1 makes the first decision
        stateBehaviour.makeDecision(gameEncounter, player1, true);

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player2, false);

        // then
        assertEquals("game.decision", result.messageType());
        assertEquals("Player2 a joué !", result.content());
        assertNotNull(gameEncounter.getHistory().getLastTour());
        assertFalse(gameEncounter.getHistory().getLastTour().getPlayer2Decision());
    }

    @Test
    void makeDecision_ShouldReturnError_WhenPlayerTriesToPlayTwiceInSameTour() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setCurrentTourNumber(1);

        // Player1 makes the first decision
        stateBehaviour.makeDecision(gameEncounter, player1, true);

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player1, true);

        // then
        assertEquals("game.error", result.messageType());
        assertTrue(result.content().contains("Vous avez déjà joué"));
    }

    @Test
    void makeDecision_ShouldEndGame_WhenLastTourIsCompleted() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        Player player2 = new Player("Player2", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setNbTours(1); // One-round game
        gameEncounter.setCurrentTourNumber(1);

        // Player1 and Player2 make decisions
        stateBehaviour.makeDecision(gameEncounter, player1, true);
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player2, false);

        // then
        assertEquals("game.decision", result.messageType());
        assertEquals("Player2 a joué !", result.content());
        assertEquals(fr.uga.l3miage.pc.prisonersdilemma.enums.GameState.GAME_FINISHED, gameEncounter.getGameState());
        assertNotNull(gameEncounter.getWinner());
    }
}
