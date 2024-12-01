package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DonnantForTwoDonnantsStrategyRandomTest {
    private final DonnantForTwoDonnantsRandomStrategy donnantForTwoDonnantsRandom = spy(
            new DonnantForTwoDonnantsRandomStrategy());
    private Random randomMock = mock(Random.class);

    @Test
    void cooperateIfEmptyHistoryOK() {
        History history = new History();
        boolean result = donnantForTwoDonnantsRandom.play(history, PlayerRole.J1);
        assertTrue(result);

    }

    @Test
    void cooperateIfHistorySizeUnder2OK() {
        History history = new History();
        history.addTour(true, false);

        boolean result = donnantForTwoDonnantsRandom.play(history, PlayerRole.J1);
        assertTrue(result);

    }

    @Test
    void playAsPlayer1OK() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(true, true);
        history.addTour(false, false);
        history.addTour(true, false);
        doReturn(randomMock).when(donnantForTwoDonnantsRandom).getRandomInstance();
        when(randomMock.nextInt(4)).thenReturn(4);
        boolean result = donnantForTwoDonnantsRandom.play(history, PlayerRole.J1);

        assertFalse(result);

    }

    @Test
    void playAsPlayer2OK() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, false);
        history.addTour(true, true);
        doReturn(randomMock).when(donnantForTwoDonnantsRandom).getRandomInstance();
        when(randomMock.nextInt(4)).thenReturn(4);
        boolean result = donnantForTwoDonnantsRandom.play(history, PlayerRole.J2);
        assertTrue(result);
    }

    @Test
    void playRandomlyFalse() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, false);
        history.addTour(false, true);

        doReturn(randomMock).when(donnantForTwoDonnantsRandom).getRandomInstance();
        when(randomMock.nextInt(4)).thenReturn(1);
        when(randomMock.nextInt(2)).thenReturn(1);
        boolean result = donnantForTwoDonnantsRandom.play(history, PlayerRole.J2);
        assertFalse(result);

    }

    @Test
    void playRandomlyTrue() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, false);
        history.addTour(false, true);
        doReturn(randomMock).when(donnantForTwoDonnantsRandom).getRandomInstance();
        when(randomMock.nextInt(4)).thenReturn(4);
        boolean result = donnantForTwoDonnantsRandom.play(history, PlayerRole.J2);
        assertTrue(result);

    }

}
