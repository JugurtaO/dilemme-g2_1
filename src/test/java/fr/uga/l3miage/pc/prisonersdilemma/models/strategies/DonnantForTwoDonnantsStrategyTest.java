package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DonnantForTwoDonnantsStrategyTest {
    private final Strategy donnantForTwoDonnants = StrategyFactory.getStrategyInstance(4);

    @Test
    void cooperateIfEmptyHistoryOK() {
        History history = new History();

        boolean result = donnantForTwoDonnants.play(history, PlayerRole.J1);
        assertTrue(result);

    }

    @Test
    void playAsPlayer1OpponentDecision() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(true, true);
        history.addTour(false, false);
        history.addTour(true, false);
        boolean result = donnantForTwoDonnants.play(history, PlayerRole.J1);

        assertFalse(result);

    }

    @Test
    void playAsPlayer1NotOpponentDecision() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(true, true);
        history.addTour(false, false);
        history.addTour(true, true);
        boolean result = donnantForTwoDonnants.play(history, PlayerRole.J1);

        assertFalse(result);
    }

    @Test
    void playAsPlayer2OK1() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, false);
        history.addTour(true, true);
        boolean result = donnantForTwoDonnants.play(history, PlayerRole.J2);
        assertTrue(result);

    }

    @Test
    void playAsPlayer2OK2() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, false);
        history.addTour(false, true);
        boolean result = donnantForTwoDonnants.play(history, PlayerRole.J2);
        assertTrue(result);

    }
}
