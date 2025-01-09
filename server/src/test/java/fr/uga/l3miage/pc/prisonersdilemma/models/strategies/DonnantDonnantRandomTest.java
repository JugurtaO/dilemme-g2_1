package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DonnantDonnantRandomTest {

    private final DonnantDonnantRandomStrategy donnantDonnantRandomStrategy = spy(new DonnantDonnantRandomStrategy());

    private DonnantDonnantStrategy donnantDonnantStrategyMock = mock(DonnantDonnantStrategy.class);
    private Random randomMock = mock(Random.class);

    @Test
    void playEmptyHistoryOK() {
        History history = new History();
        boolean expectedResponse = donnantDonnantRandomStrategy.play(history, PlayerRole.J1);
        assertTrue(expectedResponse);
    }

    @Test
    void testPlayRandomStrategyOK() {
        History history = new History();
        history.addTour(true, false);

        when(randomMock.nextInt(4)).thenReturn(1);
        when(randomMock.nextInt(2)).thenReturn(0);
        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();
        boolean result = donnantDonnantRandomStrategy.play(history, PlayerRole.J1);

        assertTrue(result);

    }

    @Test
    void testPlayRandomStrategyDefect2() {
        History history = new History();
        history.addTour(true, false);

        when(randomMock.nextInt(4)).thenReturn(1); // Correspond à 2 si vous ajoutez 1
        when(randomMock.nextInt(2)).thenReturn(1); // Correspond à 1 si vous ajoutez 1

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();

        boolean result = donnantDonnantRandomStrategy.play(history, PlayerRole.J1);
        assertFalse(result);

    }

    @Test
    void testPlayRandomStrategyOK2() {
        History history = new History();
        history.addTour(true, false);

        when(randomMock.nextInt(4)).thenReturn(1); // Correspond à 2 si vous ajoutez 1
        when(randomMock.nextInt(2)).thenReturn(0); // Correspond à 1 si vous ajoutez 1

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();

        boolean result = donnantDonnantRandomStrategy.play(history, PlayerRole.J1);
        assertTrue(result);

    }

    @Test
    void testPlayRandomStrategyInvokingDonnantDonnantTrue() {
        History history = new History();
        history.addTour(true, false);

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(donnantDonnantRandomStrategy).getDonnantDonnantStrategy();
        when(randomMock.nextInt(4)).thenReturn(3);
        when(donnantDonnantStrategyMock.play(any(), any())).thenReturn(true);
        boolean result = donnantDonnantRandomStrategy.play(history, PlayerRole.J1);
        assertTrue(result);

    }

    @Test
    void testPlayRandomStrategyInvokingDonnantDonnantFalse() {
        History history = new History();
        history.addTour(true, false);

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(donnantDonnantRandomStrategy).getDonnantDonnantStrategy();
        when(randomMock.nextInt(4)).thenReturn(3);
        when(donnantDonnantStrategyMock.play(any(), any())).thenReturn(false);
        boolean result = donnantDonnantRandomStrategy.play(history, PlayerRole.J1);
        assertFalse(result);

    }

    @Test
    void getRandomInstanceAndDonnantDonnantStrategyFalse() {
        History history = new History();
        history.addTour(true, false);

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(donnantDonnantRandomStrategy).getDonnantDonnantStrategy();
        when(randomMock.nextInt(4)).thenReturn(3);
        when(donnantDonnantStrategyMock.play(any(), any())).thenReturn(false);
        boolean result = donnantDonnantRandomStrategy.play(history, PlayerRole.J1);
        assertFalse(result);
    }

    @Test
    void getRandomInstanceAndDonnantDonnantTrue() {
        History history = new History();
        history.addTour(true, false);

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(donnantDonnantRandomStrategy).getDonnantDonnantStrategy();
        when(randomMock.nextInt(4)).thenReturn(3);
        when(donnantDonnantStrategyMock.play(any(), any())).thenReturn(true);
        boolean result = donnantDonnantRandomStrategy.play(history, PlayerRole.J1);
        assertTrue(result);
    }

}
