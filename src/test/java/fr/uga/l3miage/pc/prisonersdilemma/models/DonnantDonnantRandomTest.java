package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static org.mockito.Mockito.*;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class DonnantDonnantRandomTest {

    private final DonnantDonnantRandomStrategy donnantDonnantRandomStrategy=spy(new DonnantDonnantRandomStrategy());

    private DonnantDonnantStrategy donnantDonnantStrategyMock=mock(DonnantDonnantStrategy.class);
    private Random randomMock=mock(Random.class);



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

        when(randomMock.nextInt(4)).thenReturn(0);
        when(randomMock.nextInt(2)).thenReturn(0);
        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();
        boolean result = donnantDonnantRandomStrategy.play(history, 1);

        assertTrue(result);

    }

    @Test
    void testPlayRandomStrategyDefect2() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,false));

        when(randomMock.nextInt(4)).thenReturn(1);  // Correspond à 2 si vous ajoutez 1
        when(randomMock.nextInt(2)).thenReturn(1);  // Correspond à 1 si vous ajoutez 1

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();

        boolean result = donnantDonnantRandomStrategy.play(history, 1);
        assertFalse(result);

    }

    @Test
    void testPlayRandomStrategyOK2() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,false));

        when(randomMock.nextInt(4)).thenReturn(1);  // Correspond à 2 si vous ajoutez 1
        when(randomMock.nextInt(2)).thenReturn(0);  // Correspond à 1 si vous ajoutez 1

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();

        boolean result = donnantDonnantRandomStrategy.play(history, 1);
        assertTrue(result);

    }

    @Test
    void testPlayRandomStrategyInvokingDonnantDonnantTrue() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,false));

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(donnantDonnantRandomStrategy).getDonnantDonnantStrategy();
        when(randomMock.nextInt(4)).thenReturn(3);
        when(donnantDonnantStrategyMock.play(anyList(),anyInt())).thenReturn(true);
        boolean result = donnantDonnantRandomStrategy.play(history, 1);
        assertTrue(result);

    }

    @Test
    void testPlayRandomStrategyInvokingDonnantDonnantFalse() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,false));

        doReturn(randomMock).when(donnantDonnantRandomStrategy).getRandomInstance();
        doReturn(donnantDonnantStrategyMock).when(donnantDonnantRandomStrategy).getDonnantDonnantStrategy();
        when(randomMock.nextInt(4)).thenReturn(3);
        when(donnantDonnantStrategyMock.play(anyList(),anyInt())).thenReturn(false);
        boolean result = donnantDonnantRandomStrategy.play(history, 1);
        assertFalse(result);

    }

}
