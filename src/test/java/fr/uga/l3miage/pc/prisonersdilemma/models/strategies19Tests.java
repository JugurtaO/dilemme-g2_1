package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static org.mockito.Mockito.*;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class strategies19Tests {

    private final DonnantDonnantRandomStrategy donnantDonnantRandomStrategy=new DonnantDonnantRandomStrategy();
    @Mock
    private DonnantDonnantStrategy donnantDonnantStrategy=mock(DonnantDonnantStrategy.class);

    @Mock
    private Random random=mock(Random.class);


    @Test
     void playEmptyHistoryOK(){
        List<Tour> history= new ArrayList<>();
        boolean expectedResponse=donnantDonnantRandomStrategy.play(history,1);
        assertTrue(expectedResponse);
    }

    @Test
    void testPlayRandomStrategyOK() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,false));

        when(random.nextInt(4)).thenReturn(1);
        when(random.nextInt(2)).thenReturn(1);
        boolean result = donnantDonnantRandomStrategy.play(history, 1);

        assertTrue(result);

    }

//    @Test
//    void testPlayRandomStrategyDefect2() {
//        List<Tour> history = new ArrayList<>();
//        history.add(new Tour(1,true,false));
//
//        when(random.nextInt(4)).thenReturn(2);
//        when(random.nextInt(2)).thenReturn(2);
//
//        boolean result = donnantDonnantRandomStrategy.play(history, 1);
//        assertFalse(result);
//
//    }

}
