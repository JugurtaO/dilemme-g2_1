package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;


import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


 class AdaptativeStrategyTest {
    private final Strategy strategy=new AdaptativStrategy();
    @Test
    void playTurn5() {
        //given
        int opponentPlayerNumber = 2;
        History history=new History();
        history.addTour(true,false);
        history.addTour(true,true);
        history.addTour(true,false);
        history.addTour(true,false);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }
    @Test
    void playTurn6() {
        //given
        int opponentPlayerNumber = 1;
        History history=new History();
        history.addTour(true,true);
        history.addTour(true,false);
        history.addTour(true,false);
        history.addTour(true,false);
        history.addTour(true,true);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }
    @Test
    void playFirstTurn() {
        //given
        int opponentPlayerNumber = 2;
        History history=new History();
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }
    @Test
    void playTurn10() {
        //given
        int opponentPlayerNumber = 1;
        History history=new History();
        history.addTour(true,true);
        history.addTour(true,false);
        history.addTour(true,false);
        history.addTour(true,false);
        history.addTour(true,true);
        history.addTour(false,false);
        history.addTour(false,true);
        history.addTour(false,false);
        history.addTour(false,false);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }

    @Test
    void playTurn11WithBetrayAverageScoreHigher() {
        //given
        int opponentPlayerNumber = 2;
        History history=new History();
        history.addTour(true,true);
        history.addTour(true,false);
        history.addTour(true,false);
        history.addTour(true,false);
        history.addTour(true,true);
        history.addTour(false,false);
        history.addTour(false,true);
        history.addTour(false,true);
        history.addTour(false,false);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }

    @Test
     void playTurn11WithCooperateAverageScoreHigher() {
        //given
        int opponentPlayerNumber = 2;
        History history=new History();
        history.addTour(true,true); // 3
        history.addTour(true,true); // 6
        history.addTour(true,true);// 9
        history.addTour(true,false); //9
        history.addTour(true,true); // 12
        history.addTour(false,false); //0
        history.addTour(false,false);
        history.addTour(false,false);
        history.addTour(false,false);//0
        history.addTour(false,true);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }

}
