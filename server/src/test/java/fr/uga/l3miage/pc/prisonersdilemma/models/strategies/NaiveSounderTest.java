package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class NaiveSounderTest {
    private final NaiveSounderStrategy naiveSounder = spy(new NaiveSounderStrategy());
    private Random randomMock = mock(Random.class);
    private final DonnantDonnantStrategy donnantDonnantStrategyMock = mock(DonnantDonnantStrategy.class);

    @Test
    void betrayBySkippingCallToDonnantDonnantStrategy() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, true);
        when(randomMock.nextInt(8)).thenReturn(3);
        boolean result = naiveSounder.play(history, PlayerRole.J2);
        assertFalse(result);
    }

    @Test
    void betrayByCallingDonnantDonnantStrategy() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, true);

        doReturn(donnantDonnantStrategyMock).when(naiveSounder).getDonnantDonnantStrategyInstance();
        when(donnantDonnantStrategyMock.play(any(), any())).thenReturn(false);
        when(randomMock.nextInt(8)).thenReturn(3);
        boolean result = naiveSounder.play(history, PlayerRole.J1);
        assertFalse(result);
    }

    @Test
    void cooperateByCallingDonnantDonnantStrategy() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(true, true);

        doReturn(donnantDonnantStrategyMock).when(naiveSounder).getDonnantDonnantStrategyInstance();
        doReturn(randomMock).when(naiveSounder).getRandomInstance();
        when(donnantDonnantStrategyMock.play(any(), any())).thenReturn(true);
        when(randomMock.nextInt(8)).thenReturn(7);

        boolean result = naiveSounder.play(history, PlayerRole.J1);
        assertTrue(result);
    }

}
