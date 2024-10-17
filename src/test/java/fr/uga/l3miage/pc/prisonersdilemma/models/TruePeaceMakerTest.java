package fr.uga.l3miage.pc.prisonersdilemma.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TruePeaceMakerTest {

    private final TruePeaceMakerStrategy truePeaceMakerStrategy = spy(new TruePeaceMakerStrategy());
    private Random randomMock = mock(Random.class);


    @Test
    public void cooperateWhenHistorySizeLessThan2() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1, true, false));

        boolean result = truePeaceMakerStrategy.play(history, 2);
        assertTrue(result);

    }

    @Test
    public void cooperate1() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1, true, false));
        history.add(new Tour(2, false, true));
        history.add(new Tour(3, true, false));

        boolean result = truePeaceMakerStrategy.play(history, 2);

        assertTrue(result);

    }

    @Test
    public void cooperate2() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1, true, true));
        history.add(new Tour(2, false, false));
        history.add(new Tour(3, true, false));

        doReturn(randomMock).when(truePeaceMakerStrategy).getRandomInstance();
        when(randomMock.nextInt(3)).thenReturn(2);
        boolean result = truePeaceMakerStrategy.play(history, 2);

        assertTrue(result);

    }

    @Test
    public void betray() {
        List<Tour> history = new ArrayList<>();
        history.add(new Tour(1, true, true));
        history.add(new Tour(2, false, false));
        history.add(new Tour(3, true, false));

        doReturn(randomMock).when(truePeaceMakerStrategy).getRandomInstance();
        when(randomMock.nextInt(3)).thenReturn(1);
        boolean result = truePeaceMakerStrategy.play(history, 2);

        assertFalse(result);

    }

}
