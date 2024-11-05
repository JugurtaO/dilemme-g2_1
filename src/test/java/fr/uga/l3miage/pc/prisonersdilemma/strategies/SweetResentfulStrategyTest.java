package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.Strategy;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.SweetResentfulStrategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SweetResentfulStrategyTest {
    private final Strategy strategy = new SweetResentfulStrategy();

    @Test
    public void playWhenOpponentNeverBetrayAsPlayer1() {
        // given
        int opponentPlayerNumber = 2;
        Tour tour1 = new Tour(1, true, true);
        Tour tour2 = new Tour(2, true, true);
        Tour tour3 = new Tour(3, true, true);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        // when
        boolean decision = strategy.play(history, opponentPlayerNumber);
        // then
        assertTrue(decision);
    }

    @Test
    public void playWhenOpponentNeverBetrayAsPlayer2() {
        // given
        int opponentPlayerNumber = 2;
        Tour tour1 = new Tour(1, true, true);
        Tour tour2 = new Tour(2, true, true);
        Tour tour3 = new Tour(3, true, true);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        // when
        boolean decision = strategy.play(history, opponentPlayerNumber);
        // then
        assertTrue(decision);
    }

    @Test
    public void playWhenOpponentBetray() {
        // given
        int opponentPlayerNumber = 1;
        Tour tour1 = new Tour(1, true, true);
        Tour tour2 = new Tour(2, true, true);
        Tour tour3 = new Tour(3, false, true);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        // when
        boolean decision = strategy.play(history, opponentPlayerNumber);
        // then
        assertFalse(decision);
        Tour tour4 = new Tour(4, false, false);
        history.add(tour4);
        decision = strategy.play(history, opponentPlayerNumber);
        assertFalse(decision);
        Tour tour5 = new Tour(5, true, false);
        history.add(tour5);
        decision = strategy.play(history, opponentPlayerNumber);
        assertFalse(decision);

        Tour tour6 = new Tour(6, false, false);
        history.add(tour6);
        decision = strategy.play(history, opponentPlayerNumber);
        assertFalse(decision);

        Tour tour7 = new Tour(7, false, false);
        history.add(tour7);
        decision = strategy.play(history, opponentPlayerNumber);
        assertTrue(decision);
        Tour tour8 = new Tour(8, false, false);
        history.add(tour8);
        decision = strategy.play(history, opponentPlayerNumber);
        assertTrue(decision);
    }

    @Test
    public void playFirstTurn() {
        // given
        int opponentPlayerNumber = 1;
        List<Tour> history = new ArrayList<>();
        // when
        boolean decision = strategy.play(history, opponentPlayerNumber);
        // then
        assertTrue(decision);
    }
}
