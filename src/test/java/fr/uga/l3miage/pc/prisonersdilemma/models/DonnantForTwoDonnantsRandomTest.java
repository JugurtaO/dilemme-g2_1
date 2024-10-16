package fr.uga.l3miage.pc.prisonersdilemma.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DonnantForTwoDonnantsRandomTest {
    private  final DonnantForTwoDonnantsRandomStrategy donnantForTwoDonnantsRandom=spy(new DonnantForTwoDonnantsRandomStrategy());
    private Random randomMock=mock(Random.class);


    @Test
    public void  cooperateIfEmptyHistoryOK(){
        List<Tour> history=new ArrayList();

        boolean result= donnantForTwoDonnantsRandom.play(history,1);
        assertTrue(result);

    }
    @Test
    public void  cooperateIfHistorySizeUnder2OK(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));

        boolean result= donnantForTwoDonnantsRandom.play(history,1);
        assertTrue(result);

    }
    @Test
    public void playAsPlayer1OK(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,true,true));
        history.add(new Tour(3,false,false));
        history.add(new Tour(4,true,false));
        boolean result= donnantForTwoDonnantsRandom.play(history,2);

        assertFalse(result);

    }

    @Test
    public void playAsPlayer2OK(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,false,true));
        history.add(new Tour(3,true,false));
        history.add(new Tour(4,true,true));
        boolean result= donnantForTwoDonnantsRandom.play(history,1);

        assertTrue(result);

    }
    @Test
    public void playRandomlyFalse(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,false,true));
        history.add(new Tour(3,true,false));
        history.add(new Tour(4,false,true));

        doReturn(randomMock).when(donnantForTwoDonnantsRandom).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(1);
        boolean result= donnantForTwoDonnantsRandom.play(history,2);
        assertFalse(result);

    }
    @Test
    public void playRandomlyTrue(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,false,true));
        history.add(new Tour(3,true,false));
        history.add(new Tour(4,false,true));

        doReturn(randomMock).when(donnantForTwoDonnantsRandom).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(4);
        boolean result= donnantForTwoDonnantsRandom.play(history,2);
        assertTrue(result);

    }

}
