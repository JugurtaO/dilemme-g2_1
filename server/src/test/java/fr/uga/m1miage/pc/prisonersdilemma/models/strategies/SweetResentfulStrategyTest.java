package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SweetResentfulStrategyTest {
    private final Strategy strategy = StrategyFactory.getStrategyInstance(18);

    @Test
    void playWhenOpponentNeverBetrayAsPlayer1() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, true);
        history.addTour(true, true);
        history.addTour(true, true);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }

    @Test
    void playWhenOpponentNeverBetrayAsPlayer2() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, true);
        history.addTour(true, true);
        history.addTour(true, true);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }

    @Test
    void playWhenOpponentBetray() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, true);
        history.addTour(true, true);
        history.addTour(false, true);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertFalse(decision);
        history.addTour(false, false);
        decision = strategy.play(history, playerRole);
        assertFalse(decision);
        history.addTour(true, false);
        decision = strategy.play(history, playerRole);
        assertFalse(decision);
        history.addTour(false, false);
        decision = strategy.play(history, playerRole);
        assertFalse(decision);
        history.addTour(false, false);
        decision = strategy.play(history, playerRole);
        assertTrue(decision);
        history.addTour(false, false);
        decision = strategy.play(history, playerRole);
        assertTrue(decision);
    }

    @Test
    void playFirstTurn() {
        // given
        PlayerRole playerRole = PlayerRole.J1;
        History history = new History();
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }
}
