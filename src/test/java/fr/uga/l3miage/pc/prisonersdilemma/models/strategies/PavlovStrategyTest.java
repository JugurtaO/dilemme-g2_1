package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PavlovStrategyTest {
    private final Strategy strategy = StrategyFactory.getStrategyInstance(13);

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

    @Test
    void playWhenPreviousScoreIs3() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, true);
        history.addTour(false, true);
        history.addTour(true, true);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }

    @Test
    void playWhenPreviousScoreIs5() {
        // given
        PlayerRole playerRole = PlayerRole.J1;
        History history = new History();
        history.addTour(false, false);
        history.addTour(true, true);
        history.addTour(true, false);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertFalse(decision);
    }

    @Test
    void playWhenPreviousScoreIs0() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, true);
        history.addTour(false, false);
        history.addTour(true, false);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertFalse(decision);
    }

    @Test
    void playWhenPreviousScoreIs1() {
        // given
        PlayerRole playerRole = PlayerRole.J1;
        History history = new History();
        history.addTour(false, true);
        history.addTour(true, false);
        history.addTour(false, false);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }

}
