package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.RepentantSounderStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RepentantSounderTest {


    private final RepentantSounderStrategy repentantSounderStrategy = spy(new RepentantSounderStrategy());
    private Random randomMock=mock(Random.class);

    @Test
    public void cooperateWhenEmptyHistory() {
        List<Tour> history = new ArrayList<>();
        boolean result = repentantSounderStrategy.play(history, 2);
        assertTrue(result);
    }


    @Test
    public void cooperateAsPlayer1WhenOpponentReactsToPreviousTestByBetraying(){
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,true));
        history.add(new Tour(2,false,true));
        history.add(new Tour(3,true,false));
        repentantSounderStrategy.setThereAnyTestToCheck(true);
        boolean result=repentantSounderStrategy.play(history,2);

        assertTrue(result);
    }

    @Test
    public void playRandomlyAsPlayer1WhenOpponentReactsToPreviousTestByCooperating1(){
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,true));
        history.add(new Tour(2,false,true));
        history.add(new Tour(3,true,true));
        repentantSounderStrategy.setThereAnyTestToCheck(true);

        doReturn(randomMock).when(repentantSounderStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(4);
        boolean result=repentantSounderStrategy.play(history,2);

        assertTrue(result);
    }
    @Test
    public void playRandomlyAsPlayer1WhenOpponentReactsToPreviousTestByCooperating2(){
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,true));
        history.add(new Tour(2,false,true));
        history.add(new Tour(3,true,true));
        repentantSounderStrategy.setThereAnyTestToCheck(true);

        doReturn(randomMock).when(repentantSounderStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(1);
        boolean result=repentantSounderStrategy.play(history,2);

        assertFalse(result);
    }

    @Test
    public void betrayAsPlayer1WhenOpponentBetraysWithNoTest(){
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,true));
        history.add(new Tour(2,false,false));
        repentantSounderStrategy.setThereAnyTestToCheck(false);
        boolean result=repentantSounderStrategy.play(history,2);

        assertFalse(result);
    }

    @Test
    public void playRandomlyAsPlayer1WhenOpponentCooperatesWithNoTest1(){
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,true));
        history.add(new Tour(2,false,true));
        repentantSounderStrategy.setThereAnyTestToCheck(false);
        doReturn(randomMock).when(repentantSounderStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(3);

        boolean result=repentantSounderStrategy.play(history,2);
        assertTrue(result);
    }
    @Test
    public void playRandomlyAsPlayer1WhenOpponentCooperatesWithNoTest2(){
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1,true,true));
        history.add(new Tour(2,false,false));
        repentantSounderStrategy.setThereAnyTestToCheck(false);
        doReturn(randomMock).when(repentantSounderStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(1);

        boolean result=repentantSounderStrategy.play(history,2);

        assertFalse(result);
    }

}

