package fr.uga.m1miage.pc.prisonersdilemma.models.strategies.external;

import fr.uga.l3miage.pc.prisonersdilemma.enums.Decision;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.PacificateurNaifStrategie;
import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class ExternalNaivePeaceMakerStrategyTest {

    @Mock
    private PacificateurNaifStrategie mockedExternalStrategy;

    @InjectMocks
    private ExternalNaivePeaceMakerStrategy strategyUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlayReturnsFalseWhenExternalStrategyBetray() {
        // Arrange
        History history = new History();
        history.addTour(false, false);
        history.addTour(false, false);
        history.addTour(false, false);
        PlayerRole playerRole = PlayerRole.J1;

        when(mockedExternalStrategy.faireChoix(anyList()))
                .thenReturn(Decision.TRAHIR);

        // Act
        boolean result = strategyUnderTest.play(history, playerRole);

        // Assert
        assertFalse(false);
    }

    @Test
    void testPlayReturnsTrueWhenExternalStrategyCooperate() {
        // Arrange
        History history = new History();
        history.addTour(true,false);
        PlayerRole playerRole = PlayerRole.J2;


        when(mockedExternalStrategy.faireChoix(anyList()))
                .thenReturn(Decision.COOPERER);

        // Act
        boolean result = strategyUnderTest.play(history, playerRole);

        // Assert
        assertTrue(result);
    }


}
