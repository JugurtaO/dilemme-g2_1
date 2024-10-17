package fr.uga.l3miage.pc.prisonersdilemma.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class NiavePeaceMakerTest {

    private final NaivePeaceMakerStrategy naivePeaceMakerStrategy=spy(new NaivePeaceMakerStrategy());
    private Random randomMock=mock(Random.class);
    private DonnantDonnantStrategy donnantDonnantStrategyMock=mock(DonnantDonnantStrategy.class);

    @Test
    public void cooperateAsPlayer1WithSameDecisionAsPlayer2(){
        List<Tour> history = new ArrayList<>();

        doReturn(randomMock).when(naivePeaceMakerStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(naivePeaceMakerStrategy).getDonnantDonnantInstance();

        when(randomMock.nextInt(5)).thenReturn(1);
        when(donnantDonnantStrategyMock.play(anyList(),anyInt())).thenReturn(true);

        boolean result=naivePeaceMakerStrategy.play(history,2);

        assertTrue(result);
    }
    @Test
    public void betrayAsPlayer1WithSameDecisionAsPlayer2(){
        List<Tour> history = new ArrayList<>();

        doReturn(randomMock).when(naivePeaceMakerStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(naivePeaceMakerStrategy).getDonnantDonnantInstance();

        when(randomMock.nextInt(5)).thenReturn(1);
        when(donnantDonnantStrategyMock.play(anyList(),anyInt())).thenReturn(false);

        boolean result=naivePeaceMakerStrategy.play(history,2);
        assertFalse(result);
    }


    @Test
    public void cooperateSometimesAsPlayer1(){
        List<Tour> history = new ArrayList<>();

        doReturn(randomMock).when(naivePeaceMakerStrategy).getRandomInstance();

        when(randomMock.nextInt(5)).thenReturn(4);

        boolean result=naivePeaceMakerStrategy.play(history,2);

        assertTrue(result);
    }

}
