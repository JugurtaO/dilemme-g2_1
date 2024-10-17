package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.DonnantDonnantStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.NaiveSounderStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class NaiveSounderTest {
    private  final NaiveSounderStrategy naiveSounder=spy(new NaiveSounderStrategy());
    private Random randomMock=mock(Random.class);
    private final DonnantDonnantStrategy donnantDonnantStrategyMock= mock(DonnantDonnantStrategy.class);

    @Test
    public void betrayBySkippingCallToDonnantDonnantStrategy(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,false,true));

        when(randomMock.nextInt(8)).thenReturn(2);
        boolean result= naiveSounder.play(history,1);

        assertFalse(result);
    }

    @Test
    public void betrayByCallingDonnantDonnantStrategy(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,false,true));

        doReturn(donnantDonnantStrategyMock).when(naiveSounder).getDonnantDonnantStrategyInstance();
        when(donnantDonnantStrategyMock.play(anyList(),anyInt())).thenReturn(false);
        when(randomMock.nextInt(8)).thenReturn(3);
        boolean result= naiveSounder.play(history,1);
        assertFalse(result);
    }
    @Test
    public void cooperateByCallingDonnantDonnantStrategy(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,true,true));

        doReturn(donnantDonnantStrategyMock).when(naiveSounder).getDonnantDonnantStrategyInstance();
        doReturn(randomMock).when(naiveSounder).getRandomInstance();
        when(donnantDonnantStrategyMock.play(anyList(),anyInt())).thenReturn(true);
        when(randomMock.nextInt(8)).thenReturn(7);

        boolean result= naiveSounder.play(history,1);
        assertTrue(result);
    }

}
