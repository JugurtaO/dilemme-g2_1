package fr.uga.m1miage.pc.prisonersdilemma.models.strategies.external;

import fr.uga.l3miage.pc.prisonersdilemma.enums.Decision;
import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExternalMapperTest {

    @Test
    void testToDecisionListWithEmptyHistory() {
        // Arrange
        History history = new History();

        // Act
        List<Decision> result = ExternalMapper.toDecisionList(history, PlayerRole.J1);

        // Assert
        assertTrue(result.isEmpty(), "The decision list should be empty for an empty history.");
    }

    @Test
    void testToDecisionListWithMultipleTours() {
        // Arrange
        History history = new History();
        PlayerRole role = PlayerRole.J1;

        // Create  tours
        history.addTour(true,false);
        history.addTour(false,true);
        history.addTour(true,true);
        // Act
        List<Decision> result = ExternalMapper.toDecisionList(history, role);

        // Assert
        assertEquals(3, result.size(), "The decision list should have the same size as the number of tours.");
        assertEquals(Decision.COOPERER, result.get(0), "First decision should be COOPERER.");
        assertEquals(Decision.TRAHIR, result.get(1), "Second decision should be TRAHIR.");
        assertEquals(Decision.COOPERER, result.get(2), "Third decision should be COOPERER.");
    }

    @Test
    void testToDecisionListWithMultipleToursWithJ2() {
        // Arrange
        History history = new History();
        PlayerRole role = PlayerRole.J2;

        // Create  tours
        history.addTour(true,false);
        history.addTour(false,false);
        history.addTour(true,true);
        // Act
        List<Decision> result = ExternalMapper.toDecisionList(history, role);

        // Assert
        assertEquals(3, result.size(), "The decision list should have the same size as the number of tours.");
        assertEquals(Decision.TRAHIR, result.get(0), "First decision should be COOPERER.");
        assertEquals(Decision.TRAHIR, result.get(1), "Second decision should be TRAHIR.");
        assertEquals(Decision.COOPERER, result.get(2), "Third decision should be COOPERER.");
    }

    @Test
    void testToBooleanWithCooperer() {
        // Act
        boolean result = ExternalMapper.toBoolean(Decision.COOPERER);

        // Assert
        assertTrue(result, "The boolean value should be true for Decision.COOPERER.");
    }

    @Test
    void testToBooleanWithTrahir() {
        // Act
        boolean result = ExternalMapper.toBoolean(Decision.TRAHIR);

        // Assert
        assertFalse( result, "The boolean value should be false for Decision.TRAHIR.");
    }
}
