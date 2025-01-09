package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RandomStrategyTest {

    private final RandomStrategy randomStrategy = spy(new RandomStrategy());
    private Random randomMock = mock(Random.class);

    @Test
    void cooperate() {
        History history = new History();
        doReturn(randomMock).when(randomStrategy).getRandomInstance();
        when(randomMock.nextInt(2)).thenReturn(0);

        boolean result = randomStrategy.play(history, PlayerRole.J2);
        assertTrue(result);
    }

    @Test
    void betray() {
        History history = new History();
        doReturn(randomMock).when(randomStrategy).getRandomInstance();
        when(randomMock.nextInt(2)).thenReturn(1);

        boolean result = randomStrategy.play(history, PlayerRole.J2);
        assertFalse(result);
    }
}
