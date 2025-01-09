package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryTest {
    private History history;

    @BeforeEach
    void initializeHistory() {
        history = new History();
    }

    @Test
    void testAddTour() {
        history.addTour(true, true);
        history.addTour(false, true);
        history.addTour(false, false);

        List<Tour> expected = new ArrayList<>();
        expected.add(new Tour(1,true,true));
        expected.add(new Tour(2,false,true));
        expected.add(new Tour(3,false,false));
        assertEquals(3, history.size(), "Size should be 1 after adding one tour");
        assertThat(history.getAllTours()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testIsEmpty() {
        assertTrue(history.isEmpty(), "History should initially be empty");
        history.addTour(true, false);
        assertFalse(history.isEmpty(), "History should not be empty after adding a tour");
    }

    @Test
    void testSize() {
        assertEquals(0, history.size(), "Initial size should be 0");
        history.addTour(true, false);
        assertEquals(1, history.size(), "Size should be 1 after adding one tour");
        history.addTour(false, true);
        assertEquals(2, history.size(), "Size should be 2 after adding two tours");
    }

    @Test
    void testGetAllTours() {
        history.addTour(true, false);
        history.addTour(false, true);
        List<Tour> expected = new ArrayList<>();
        expected.add(new Tour(1,true,false));
        expected.add(new Tour(2,false,true));
        List<Tour> tours = history.getAllTours();
        assertEquals(2, tours.size(), "There should be 2 tours in the history");
        assertThat(tours).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testGetLastTour() {
        history.addTour(true, false);
        history.addTour(false, true);
        Tour lastTour = history.getLastTour();
        assertFalse(lastTour.getPlayerDecision(PlayerRole.J1), "Last tour should have player 1 betraying");
        assertTrue(lastTour.getPlayerDecision(PlayerRole.J2), "Last tour should have player 2 cooperating");
    }

    @Test
    void testGetLastTours() {
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, true);
        List<Tour> lastTwoTours = history.getLastTours(2);
        assertEquals(2, lastTwoTours.size(), "Should return last 2 tours");
        assertFalse(lastTwoTours.get(0).getPlayerDecision(PlayerRole.J1), "Second to last tour should have player 1 cooperating");
        assertTrue(lastTwoTours.get(0).getPlayerDecision(PlayerRole.J2), "Second to last tour should have player 2 betray");
        assertTrue(lastTwoTours.get(1).getPlayerDecision(PlayerRole.J1), "Last tour should have player 1 cooperating");
        assertTrue(lastTwoTours.get(1).getPlayerDecision(PlayerRole.J2), "Last tour should have player 2 cooperating");
    }
    @Test
    void testGetLastToursWithGreaterThanCurrent() {
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, true);
        List<Tour> lastTwoTours = history.getLastTours(50);
        assertTrue(lastTwoTours.isEmpty());
    }
    @Test
    void testGetLastToursWithLessThanZero() {
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, true);
        List<Tour> lastTwoTours = history.getLastTours(-6);
        assertTrue(lastTwoTours.isEmpty());
    }

    @Test
    void testPlayerHasBetray() {
        history.addTour(true, true);
        history.addTour(true, false);
        assertFalse(history.playerHasBetray(PlayerRole.J2), "Player 2 has betrayed in the second tour");
        assertTrue(history.playerHasBetray(PlayerRole.J1), "Player 1 has not betrayed in any tour");
    }

    @Test
    void testGetCooperateToursOf() {
        history.addTour(true, false);
        history.addTour(false, false);
        history.addTour(true, true);
        List<Tour> player1CooperateTours = history.getCooperateToursOf(PlayerRole.J1);
        List<Tour> player2CooperateTours = history.getCooperateToursOf(PlayerRole.J2);
        assertEquals(2, player1CooperateTours.size(), "Player 1 should have cooperated in 2 tours");
        assertEquals(1, player2CooperateTours.size(), "Player 2 should have cooperated in 1 tours");
    }

    @Test
    void testGetBetrayToursOf() {
        history.addTour(true, true);
        history.addTour(true, false);
        history.addTour(true, false);
        List<Tour> player1BetrayTours = history.getBetrayToursOf(PlayerRole.J1);
        List<Tour> player2BetrayTours = history.getBetrayToursOf(PlayerRole.J2);
        assertEquals(2, player2BetrayTours.size(), "Player 2 should have betrayed in 2 tours");
        assertEquals(0, player1BetrayTours.size(), "Player 1 should have betrayed in 0 tours");
    }

    @Test
    void testGetAverageBetrayScore() {
        assertEquals(0,history.getAverageBetrayScore(PlayerRole.J1),"Player 1's average betray score should be 0 for empty list");
        assertEquals(0,history.getAverageBetrayScore(PlayerRole.J2),"Player 2's average betray score should be 0 for empty list");
        history.addTour(true, false);
        history.addTour(false, false);
        history.addTour(true, true);
        history.addTour(false, true);
        history.addTour(true, false);
        history.addTour(false, false);
        double player1AverageBetrayScore = history.getAverageBetrayScore(PlayerRole.J1);
        double player2AverageBetrayScore = history.getAverageBetrayScore(PlayerRole.J2);
        assertEquals((double) 7/3, player1AverageBetrayScore, "Player 1's average betray score should be 7/3");
        assertEquals(3, player2AverageBetrayScore, "Player 2's average betray score should be 3");
    }

    @Test
    void testGetAverageCooperateScore() {
        assertEquals(0,history.getAverageCooperateScore(PlayerRole.J1),"Player 1's average cooperate score should be 0 for empty list");
        assertEquals(0,history.getAverageCooperateScore(PlayerRole.J2),"Player 2's average cooperate score should be 0 for empty list");
        history.addTour(true, false);
        history.addTour(false, false);
        history.addTour(true, true);
        history.addTour(false, true);
        history.addTour(true, false);
        history.addTour(false, false);
        double player1AverageCooperateScore = history.getAverageCooperateScore(PlayerRole.J1);
        double player2AverageCooperateScore = history.getAverageCooperateScore(PlayerRole.J2);
        assertEquals(2, player1AverageCooperateScore, "Player 1's average cooperate score should be 1");
        assertEquals(1.5, player2AverageCooperateScore, "Player 2's average cooperate score should be 1.5");
    }

    @Test
    void testGetTourByTourNumberNull(){
        assertNull(history.getTourByTourNumber(2),"Tour list is empty ");
    }

    @Test
    void testGetTourByTourNumberFound(){
        history.addTour(true, false);
        Tour result=history.getTourByTourNumber(1);
        assertNotNull(result,"Tour list is empty ");
        assertEquals(1,result.getTourNumber());
    }
    @Test
    void testGetTourByTourNumberNotFound(){
        history.addTour(true, false);
        Tour result=history.getTourByTourNumber(2);
        assertNull(result,"Tour list is empty ");
    }
}
