package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdaptativeStrategyTest {
    private final Strategy strategy = StrategyFactory.getStrategyInstance(15);

    @Test
    void playTurn5() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, false);
        history.addTour(true, true);
        history.addTour(true, false);
        history.addTour(true, false);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }

    @Test
    void playTurn6() {
        // given
        PlayerRole playerRole = PlayerRole.J1;
        History history = new History();
        history.addTour(true, true);
        history.addTour(true, false);
        history.addTour(true, false);
        history.addTour(true, false);
        history.addTour(true, true);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertFalse(decision);
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

    @Test
    void playTurn10() {
        // given
        PlayerRole playerRole = PlayerRole.J1;
        History history = new History();
        history.addTour(true, true);
        history.addTour(true, false);
        history.addTour(true, false);
        history.addTour(true, false);
        history.addTour(true, true);
        history.addTour(false, false);
        history.addTour(false, true);
        history.addTour(false, false);
        history.addTour(false, false);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertFalse(decision);
    }

    @Test
    void playTurn11WithBetrayAverageScoreHigher() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, true);
        history.addTour(true, false);
        history.addTour(true, false);
        history.addTour(true, false);
        history.addTour(true, true);
        history.addTour(false, false);
        history.addTour(false, true);
        history.addTour(false, true);
        history.addTour(false, false);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertFalse(decision);
    }

    @Test
    void playTurn11WithCooperateAverageScoreHigher() {
        // given
        PlayerRole playerRole = PlayerRole.J2;
        History history = new History();
        history.addTour(true, true); // 3
        history.addTour(true, true); // 6
        history.addTour(true, true);// 9
        history.addTour(true, false); // 9
        history.addTour(true, true); // 12
        history.addTour(false, false); // 0
        history.addTour(false, false);
        history.addTour(false, false);
        history.addTour(false, false);// 0
        history.addTour(false, true);
        // when
        boolean decision = strategy.play(history, playerRole);
        // then
        assertTrue(decision);
    }

}
