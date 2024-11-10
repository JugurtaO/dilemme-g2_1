package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class DonnantForTwoDonnantsStrategyTest {
    private  final Strategy donnantForTwoDonnants=StrategyFactory.getStrategyInstance(4);


    @Test
    void  cooperateIfEmptyHistoryOK(){
        History history=new History();

        boolean result= donnantForTwoDonnants.play(history,1);
        assertTrue(result);

    }

    @Test
    void playAsPlayer1O(){
        History history=new History();
        history.addTour(true,false);
        history.addTour(true,true);
        history.addTour(false,false);
        history.addTour(true,false);
        boolean result= donnantForTwoDonnants.play(history,2);

        assertFalse(result);

    }

    @Test
    void playAsPlayer1OK2(){
        History history=new History();
        history.addTour(true,false);
        history.addTour(true,true);
        history.addTour(false,false);
        history.addTour(true,true);
        boolean result= donnantForTwoDonnants.play(history,2);

        assertTrue(result);
    }
    @Test
     void playAsPlayer2OK1(){
        History history=new History();
        history.addTour(true,false);
        history.addTour(false,true);
        history.addTour(true,false);
        history.addTour(true,true);
        boolean result= donnantForTwoDonnants.play(history,2);
        assertTrue(result);

    }
    @Test
     void playAsPlayer2OK2(){
        History history=new History();
        history.addTour(true,false);
        history.addTour(false,true);
        history.addTour(true,false);
        history.addTour(false,true);
        boolean result= donnantForTwoDonnants.play(history,2);
        assertTrue(result);

    }
}
