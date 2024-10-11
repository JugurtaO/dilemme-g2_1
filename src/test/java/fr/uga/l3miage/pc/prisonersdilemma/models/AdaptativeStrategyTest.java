package fr.uga.l3miage.pc.prisonersdilemma.models;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class AdaptativeStrategyTest {
    private final Strategy strategy=new AdaptativStrategy();
    @Test
    public void playTurn5() {
        //given
        int opponentPlayerNumber = 2;
        Tour tour1= new Tour(1,true,false);
        Tour tour2= new Tour(2,true,true);
        Tour tour3= new Tour(3,true,false);
        Tour tour4= new Tour(4,true,false);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        history.add(tour4);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }
    @Test
    public void playTurn6() {
        //given
        int opponentPlayerNumber = 1;
        Tour tour1= new Tour(1,true,true);
        Tour tour2= new Tour(2,true,false);
        Tour tour3= new Tour(3,true,false);
        Tour tour4= new Tour(4,true,false);
        Tour tour5= new Tour(5,true,true);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        history.add(tour4);
        history.add(tour5);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }
    @Test
    public void playFirstTurn() {
        //given
        int opponentPlayerNumber = 2;
        List<Tour> history = new ArrayList<>();
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }
    @Test
    public void playTurn10() {
        //given
        int opponentPlayerNumber = 1;
        Tour tour1= new Tour(1,true,true);
        Tour tour2= new Tour(2,true,false);
        Tour tour3= new Tour(3,true,false);
        Tour tour4= new Tour(4,true,false);
        Tour tour5= new Tour(5,true,true);
        Tour tour6= new Tour(6,false,false);
        Tour tour7= new Tour(7,false,true);
        Tour tour8= new Tour(8,false,false);
        Tour tour9= new Tour(9,false,false);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        history.add(tour4);
        history.add(tour5);
        history.add(tour6);
        history.add(tour7);
        history.add(tour8);
        history.add(tour9);

        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }

    @Test
    public void playTurn11WithBetrayAverageScoreHigher() {
        //given
        int opponentPlayerNumber = 2;
        Tour tour1= new Tour(1,true,true);
        Tour tour2= new Tour(2,true,false);
        Tour tour3= new Tour(3,true,false);
        Tour tour4= new Tour(4,true,false);
        Tour tour5= new Tour(5,true,true);
        Tour tour6= new Tour(6,false,false);
        Tour tour7= new Tour(7,false,true);
        Tour tour8= new Tour(8,false,false);
        Tour tour9= new Tour(9,false,true);
        Tour tour10= new Tour(10,false,false);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        history.add(tour4);
        history.add(tour5);
        history.add(tour6);
        history.add(tour7);
        history.add(tour8);
        history.add(tour9);
        history.add(tour10);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertFalse(decision);
    }

    @Test
    public void playTurn11WithCooperateAverageScoreHigher() {
        //given
        int opponentPlayerNumber = 2;
        Tour tour1= new Tour(1,true,true);
        Tour tour2= new Tour(2,true,true);
        Tour tour3= new Tour(3,true,true);
        Tour tour4= new Tour(4,true,false);
        Tour tour5= new Tour(5,true,true);
        Tour tour6= new Tour(6,false,false);
        Tour tour7= new Tour(7,false,false);
        Tour tour8= new Tour(8,false,false);
        Tour tour9= new Tour(9,false,false);
        Tour tour10= new Tour(10,false,false);
        List<Tour> history = new ArrayList<>();
        history.add(tour1);
        history.add(tour2);
        history.add(tour3);
        history.add(tour4);
        history.add(tour5);
        history.add(tour6);
        history.add(tour7);
        history.add(tour8);
        history.add(tour9);
        history.add(tour10);
        //when
        boolean decision=strategy.play(history,opponentPlayerNumber);
        //then
        assertTrue(decision);
    }

}
