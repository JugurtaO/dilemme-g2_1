package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.ArrayDeque;
import java.util.Deque;

public class SweetResentfulStrategy implements Strategy {
    private final Deque<Boolean> playLeft = new ArrayDeque<>();

    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        if (history.isEmpty()) {
            return true;
        }
        if (!playLeft.isEmpty()) {
            return playLeft.pop();
        }
        Tour lastTour = history.getLastTour();
        boolean opponentDecision = opponentPlayerNumber == 1 ? lastTour.getPlayer1Decision()
                : lastTour.getPlayer2Decision();
        if (opponentDecision) {
            return true;
        } else {
            playLeft.push(true);
            playLeft.push(true);
            playLeft.push(false);
            playLeft.push(false);
            playLeft.push(false);
            return false;
        }

    }
}
