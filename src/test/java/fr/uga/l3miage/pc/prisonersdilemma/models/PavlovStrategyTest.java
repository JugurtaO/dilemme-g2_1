package fr.uga.l3miage.pc.prisonersdilemma.models;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PavlovStrategyTest {
    private final Strategy strategy=new PavlovStrategy();

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

    @Test
    public void playWhenPreviousScoreIs3() {
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
    public void playWhenPreviousScoreIs5() {
        //given
        int opponentPlayerNumber = 1;
        Tour tour1= new Tour(1,false,false);
        Tour tour2= new Tour(2,true,true);
        Tour tour3= new Tour(3,true,false);
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
    public void playWhenPreviousScoreIs0() {
        //given
        int opponentPlayerNumber = 2;
        Tour tour1= new Tour(1,true,true);
        Tour tour2= new Tour(2,false,false);
        Tour tour3= new Tour(3,true,false);
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
    public void playWhenPreviousScoreIs1() {
        //given
        int opponentPlayerNumber = 1;
        Tour tour1= new Tour(1,false,true);
        Tour tour2= new Tour(2,true,false);
        Tour tour3= new Tour(3,false,false);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }

}
