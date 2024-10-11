package fr.uga.l3miage.pc.prisonersdilemma.models;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ResentfulStrategyTest {
    private final Strategy strategy=new ResentfulStrategy();
    @Test
    public void playWhenOpponentNeverBetrayAsPlayer1() {
        //given
        int opponentPlayerNumber = 2;
        Tour tour1= new Tour(1,true,true);
        Tour tour2= new Tour(2,false,true);
        Tour tour3= new Tour(3,true,true);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }
    @Test
    public void playWhenOpponentNeverBetrayAsPlayer2() {
        //given
        int opponentPlayerNumber = 1;
        Tour tour1= new Tour(1,true,false);
        Tour tour2= new Tour(2,true,false);
        Tour tour3= new Tour(3,true,false);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }
    @Test
    public void playWhenOpponentBetrayAsPlayer1() {
        //given
        int opponentPlayerNumber = 2;
        Tour tour1= new Tour(1,true,true);
        Tour tour2= new Tour(2,false,false);
        Tour tour3= new Tour(3,true,true);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }
    @Test
    public void playWhenOpponentBetrayAsPlayer2() {
        //given
        int opponentPlayerNumber = 1;
        Tour tour1= new Tour(1,false,true);
        Tour tour2= new Tour(2,true,false);
        Tour tour3= new Tour(3,true,true);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }
    @Test
    public void playFirstTurn() {
        //given
        int opponentPlayerNumber = 1;
        List<Tour> history = new ArrayList<>();
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }
}
