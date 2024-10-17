package fr.uga.l3miage.pc.prisonersdilemma.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RandomStrategyTest {

    private final RandomStrategy randomStrategy=spy(new RandomStrategy());
    private Random randomMock=mock(Random.class);

    @Test
    public void cooperate(){
        List<Tour> history = new ArrayList<>();
        doReturn(randomMock).when(randomStrategy).getRandomInstance();
        when(randomMock.nextInt(2)).thenReturn(0);

        boolean result=randomStrategy.play(history,2);
        assertTrue(result);
    }

    @Test
    public void betray(){
        List<Tour> history = new ArrayList<>();
        doReturn(randomMock).when(randomStrategy).getRandomInstance();
        when(randomMock.nextInt(2)).thenReturn(1);

        boolean result=randomStrategy.play(history,2);
        assertFalse(result);
    }
}
