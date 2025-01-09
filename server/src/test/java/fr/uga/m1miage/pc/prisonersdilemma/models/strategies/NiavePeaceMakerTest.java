package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class NiavePeaceMakerTest {

    private final NaivePeaceMakerStrategy naivePeaceMakerStrategy = spy(new NaivePeaceMakerStrategy());
    private Random randomMock = mock(Random.class);
    private DonnantDonnantStrategy donnantDonnantStrategyMock = mock(DonnantDonnantStrategy.class);

    @Test
    void cooperateAsPlayer1WithSameDecisionAsPlayer2() {
        History history = new History();
        doReturn(randomMock).when(naivePeaceMakerStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(naivePeaceMakerStrategy).getDonnantDonnantInstance();

        when(randomMock.nextInt(5)).thenReturn(1);
        when(donnantDonnantStrategyMock.play(any(), any())).thenReturn(true);

        boolean result = naivePeaceMakerStrategy.play(history, PlayerRole.J2);

        assertTrue(result);
    }

    @Test
    void betrayAsPlayer1WithSameDecisionAsPlayer2() {
        History history = new History();
        doReturn(randomMock).when(naivePeaceMakerStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(naivePeaceMakerStrategy).getDonnantDonnantInstance();
        when(randomMock.nextInt(5)).thenReturn(1);
        when(donnantDonnantStrategyMock.play(any(), any())).thenReturn(false);
        boolean result = naivePeaceMakerStrategy.play(history, PlayerRole.J2);
        assertFalse(result);
    }

    @Test
    void cooperateSometimesAsPlayer1() {
        History history = new History();
        doReturn(randomMock).when(naivePeaceMakerStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(4);
        boolean result = naivePeaceMakerStrategy.play(history, PlayerRole.J2);
        assertTrue(result);
    }

}
