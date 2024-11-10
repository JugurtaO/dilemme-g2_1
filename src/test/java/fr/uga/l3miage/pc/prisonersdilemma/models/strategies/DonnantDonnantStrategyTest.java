package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;


import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


 class DonnantDonnantStrategyTest {
    private final Strategy strategy=new DonnantDonnantStrategy();
    @Test
     void playAsPlayer1() {
        //given
        int opponentPlayerNumber = 2;
        History history=new History();
        history.addTour(true,false);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }
    @Test
     void playAsPlayer2() {
        //given
        int opponentPlayerNumber = 1;
        History history=new History();
        history.addTour(true,false);
        history.addTour(true,false);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
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
}
