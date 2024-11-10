package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

 class RepentantSounderTest {


    private final RepentantSounderStrategy repentantSounderStrategy = spy(new RepentantSounderStrategy());
    private Random randomMock=mock(Random.class);

    @Test
     void cooperateWhenEmptyHistory() {
        History history=new History();
        boolean result = repentantSounderStrategy.play(history, 2);
        assertTrue(result);
    }


    @Test
     void cooperateAsPlayer1WhenOpponentReactsToPreviousTestByBetraying(){
        History history=new History();
        history.addTour(true,true);
        history.addTour(false,true);
        history.addTour(true,false);
        repentantSounderStrategy.setThereAnyTestToCheck(true);
        boolean result=repentantSounderStrategy.play(history,2);

        assertTrue(result);
    }

    @Test
     void playRandomlyAsPlayer1WhenOpponentReactsToPreviousTestByCooperating1(){
        History history=new History();
        history.addTour(true,true);
        history.addTour(false,true);
        history.addTour(true,true);
        repentantSounderStrategy.setThereAnyTestToCheck(true);

        doReturn(randomMock).when(repentantSounderStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(4);
        boolean result=repentantSounderStrategy.play(history,2);

        assertTrue(result);
    }
    @Test
     void playRandomlyAsPlayer1WhenOpponentReactsToPreviousTestByCooperating2(){
        History history=new History();
        history.addTour(true,true);
        history.addTour(false,true);
        history.addTour(true,true);
        repentantSounderStrategy.setThereAnyTestToCheck(true);

        doReturn(randomMock).when(repentantSounderStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(1);
        boolean result=repentantSounderStrategy.play(history,2);

        assertFalse(result);
    }

    @Test
     void betrayAsPlayer1WhenOpponentBetraysWithNoTest(){
        History history=new History();
        history.addTour(true,true);
        history.addTour(false,false);

        repentantSounderStrategy.setThereAnyTestToCheck(false);
        boolean result=repentantSounderStrategy.play(history,2);

        assertFalse(result);
    }

    @Test
     void playRandomlyAsPlayer1WhenOpponentCooperatesWithNoTest1(){
        History history=new History();
        history.addTour(true,true);
        history.addTour(false,true);
        repentantSounderStrategy.setThereAnyTestToCheck(false);
        doReturn(randomMock).when(repentantSounderStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(3);

        boolean result=repentantSounderStrategy.play(history,2);
        assertTrue(result);
    }
    @Test
     void playRandomlyAsPlayer1WhenOpponentCooperatesWithNoTest2(){
        History history=new History();
        history.addTour(true,true);
        history.addTour(false,false);
        repentantSounderStrategy.setThereAnyTestToCheck(false);
        doReturn(randomMock).when(repentantSounderStrategy).getRandomInstance();
        when(randomMock.nextInt(5)).thenReturn(1);

        boolean result=repentantSounderStrategy.play(history,2);

        assertFalse(result);
    }

}

