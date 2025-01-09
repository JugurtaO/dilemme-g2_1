package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DonnantDonnantStrategyTest {
    private final Strategy strategy = StrategyFactory.getStrategyInstance(1);

    @Test
    void playAsPlayer1() {
        // given
        PlayerRole playerRole = PlayerRole.J1;
        History history = new History();
        history.addTour(true, false);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertFalse(decision);
    }

    @Test
    void playAsPlayer2() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, false);
        history.addTour(true, false);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }

    @Test
    void playFirstTurn() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }
}
