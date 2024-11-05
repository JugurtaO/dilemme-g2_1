package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import org.junit.jupiter.api.Test;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.Strategy;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.DonnantDonnantSuspiciousStrategy;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DonnantDonnantSuspiciousStrategyTest {
    private final Strategy strategy = new DonnantDonnantSuspiciousStrategy();

    @Test
    public void playAsPlayer1() {
        // given
        int opponentPlayerNumber = 2;
        Tour tour1 = new Tour(1, true, false);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        // when
        boolean decision = strategy.play(history, opponentPlayerNumber);
        // then
        assertFalse(decision);
    }

    @Test
    public void playAsPlayer2() {
        // given
        int opponentPlayerNumber = 1;
        Tour tour1 = new Tour(1, true, false);
        Tour tour2 = new Tour(2, true, false);

        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);

        // when
        boolean decision = strategy.play(history, opponentPlayerNumber);
        // then
        assertTrue(decision);
    }

    @Test
    public void playFirstTurn() {
        // given
        int opponentPlayerNumber = 2;
        List<Tour> history = new ArrayList<>();
        // when
        boolean decision = strategy.play(history, opponentPlayerNumber);
        // then
        assertFalse(decision);
    }
}
