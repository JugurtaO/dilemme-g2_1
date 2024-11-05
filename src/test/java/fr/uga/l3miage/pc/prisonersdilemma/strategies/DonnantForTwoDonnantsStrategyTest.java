package fr.uga.l3miage.pc.prisonersdilemma.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.DonnantForTwoDonnantsStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.Strategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DonnantForTwoDonnantsStrategyTest {
    private  final Strategy donnantForTwoDonnants=new DonnantForTwoDonnantsStrategy();


    @Test
    public void  cooperateIfEmptyHistoryOK(){
        List<Tour> history=new ArrayList();

        boolean result= donnantForTwoDonnants.play(history,1);
        assertTrue(result);

    }

    @Test
    public void playAsPlayer1O(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,true,true));
        history.add(new Tour(3,false,false));
        history.add(new Tour(4,true,false));
        boolean result= donnantForTwoDonnants.play(history,2);

        assertFalse(result);

    }

    @Test
    public void playAsPlayer1OK2(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,true,true));
        history.add(new Tour(3,false,false));
        history.add(new Tour(4,true,true));
        boolean result= donnantForTwoDonnants.play(history,2);

        assertTrue(result);
    }
    @Test
    public void playAsPlayer2OK1(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,false,true));
        history.add(new Tour(3,true,false));
        history.add(new Tour(4,true,true));
        boolean result= donnantForTwoDonnants.play(history,2);

        assertTrue(result);

    }
    @Test
    public void playAsPlayer2OK2(){
        List<Tour> history=new ArrayList();
        history.add(new Tour(1,true,false));
        history.add(new Tour(2,false,true));
        history.add(new Tour(3,true,false));
        history.add(new Tour(4,false,true));
        boolean result= donnantForTwoDonnants.play(history,2);

        assertTrue(result);

    }
}
