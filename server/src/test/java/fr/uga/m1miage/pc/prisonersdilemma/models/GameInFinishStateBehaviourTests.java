package fr.uga.m1miage.pc.prisonersdilemma.models;

import fr.uga.m1miage.pc.prisonersdilemma.dto.GameMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameFinishStateBehaviourTests {

    private GameFinishStateBehaviour stateBehaviour;
    private GameEncounter gameEncounter;

    @BeforeEach
    void setUp() {
        stateBehaviour = new GameFinishStateBehaviour();
        gameEncounter = new GameEncounter(5,null,null); // Ensure GameEncounter is implemented.
    }

    @Test
    void joinGame_ShouldReturnError_WhenGameIsFinished() {
        // given
        String playerName = "Player1";

        // when
        GameMessage result = stateBehaviour.joinGame(gameEncounter, playerName);

        // then
        assertEquals("game.error", result.messageType());
        assertEquals(playerName + "cannot join. Game is finished", result.content());
    }

    @Test
    void leaveGame_ShouldReturnError_WhenGameIsFinished() {
        // given
        String playerName = "Player1";

        // when
        GameMessage result = stateBehaviour.leaveGame(gameEncounter, playerName,1);

        // then
        assertEquals("game.error", result.messageType());
        assertEquals(playerName + "cannot leave. Game is finished", result.content());
    }

    @Test
    void makeDecision_ShouldReturnError_WhenGameIsFinished() {
        // given
        Player player1 = new Player("Player1", gameEncounter);

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player1, true);

        // then
        assertEquals("game.error", result.messageType());
        assertEquals("cannot make decision. Game is finished", result.content());
    }
}
