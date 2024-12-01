package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TruePeaceMakerTest {

    private final TruePeaceMakerStrategy truePeaceMakerStrategy = spy(new TruePeaceMakerStrategy());
    private Random randomMock = mock(Random.class);

    @Test
    void cooperateWhenHistorySizeLessThan2() {
        History history = new History();
        history.addTour(true, false);
        boolean result = truePeaceMakerStrategy.play(history, PlayerRole.J2);
        assertTrue(result);

    }

    @Test
    void cooperate1() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, true);
        history.addTour(true, true);
        boolean result = truePeaceMakerStrategy.play(history, PlayerRole.J1);

        assertTrue(result);

    }

    @Test
    void cooperate2() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, false);
        history.addTour(true, false);

        doReturn(randomMock).when(truePeaceMakerStrategy).getRandomInstance();
        when(randomMock.nextInt(3)).thenReturn(2);
        boolean result = truePeaceMakerStrategy.play(history, PlayerRole.J2);

        assertTrue(result);

    }

    @Test
    void betray() {
        History history = new History();
        history.addTour(true, false);
        history.addTour(false, false);
        history.addTour(true, false);

        doReturn(randomMock).when(truePeaceMakerStrategy).getRandomInstance();
        when(randomMock.nextInt(3)).thenReturn(1);
        boolean result = truePeaceMakerStrategy.play(history, PlayerRole.J2);

        assertFalse(result);

    }

}
