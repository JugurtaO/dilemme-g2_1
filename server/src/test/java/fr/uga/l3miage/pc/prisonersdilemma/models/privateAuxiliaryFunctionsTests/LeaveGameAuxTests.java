package fr.uga.l3miage.pc.prisonersdilemma.models.privateAuxiliaryFunctionsTests;

import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameInProgressStateBehaviour;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaveGameAuxTests {
    private GameInProgressStateBehaviour gameInProgressStateBehaviour;

    @BeforeEach
    void setup(){
        this.gameInProgressStateBehaviour=new GameInProgressStateBehaviour();
    }

    @Test
    void advanceToNextTourOK(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        this.gameInProgressStateBehaviour.advanceToNextTour(gameEncounter);
        assertEquals( 1,gameEncounter.getCurrentTourNumber());
    }
    @Test
    void endGameOK(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        this.gameInProgressStateBehaviour.endGame(gameEncounter);
        assertEquals(GameState.GAME_FINISHED,gameEncounter.getGameState());
    }

    @Test
    void determineWinnerEgality(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        Player p1=new Player("Jug",gameEncounter);
        Player p2=new Player("Tom",gameEncounter);
        p1.setScore(2);
        p2.setScore(2);
        gameEncounter.setPlayer1(p1);
        gameEncounter.setPlayer2(p2);
        gameInProgressStateBehaviour.determineWinner(gameEncounter);
        assertNull(gameEncounter.getWinner());

    }
    @Test
    void plyer1Win(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        Player p1=new Player("Jug",gameEncounter);
        Player p2=new Player("Tom",gameEncounter);
        p1.setScore(3);
        p2.setScore(2);
        gameEncounter.setPlayer1(p1);
        gameEncounter.setPlayer2(p2);
        gameInProgressStateBehaviour.determineWinner(gameEncounter);
        assertEquals(p1.getName(),gameEncounter.getWinner());
    }
    @Test
    void plyer2Win(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        Player p1=new Player("Jug",gameEncounter);
        Player p2=new Player("Tom",gameEncounter);
        p1.setScore(3);
        p2.setScore(7);
        gameEncounter.setPlayer1(p1);
        gameEncounter.setPlayer2(p2);
        gameInProgressStateBehaviour.determineWinner(gameEncounter);
        assertEquals(p2.getName(),gameEncounter.getWinner());
    }

    @Test
    void isGameFinishedWithNewTour(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        Player p1=new Player("Jug",gameEncounter);
        gameEncounter.setPlayer1(p1);
        boolean response=gameInProgressStateBehaviour.isGameFinished(gameEncounter,true);
        assertFalse(response);

    }
    @Test
    void isGameFinishedWithExistingTour(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        Player p1=new Player("Jug",gameEncounter);
        gameEncounter.setPlayer1(p1);
        boolean response=gameInProgressStateBehaviour.isGameFinished(gameEncounter,false);
        assertFalse(response);
    }
    @Test
    void isGameFinishedWithExistingTour2(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        Player p1=new Player("Jug",gameEncounter);
        gameEncounter.setPlayer1(p1);
        gameEncounter.setCurrentTourNumber(5);
        boolean response=gameInProgressStateBehaviour.isGameFinished(gameEncounter,false);
        assertTrue(response);

    }
}
