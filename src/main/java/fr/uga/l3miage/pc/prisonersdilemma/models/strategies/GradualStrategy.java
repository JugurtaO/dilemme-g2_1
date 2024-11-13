package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.ArrayDeque;
import java.util.Deque;

public class GradualStrategy implements Strategy {
    private final Deque<Boolean> playLeft = new ArrayDeque<>();

    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        return history.isEmpty() || isPlayLeft(history, opponentPlayerNumber);

    }
    public boolean isPlayLeft(History history, int opponentPlayerNumber) {
        if (!playLeft.isEmpty()) {
            return playLeft.pop();
        }
        Tour lastTour = history.getLastTour();
        boolean opponentDecision = opponentPlayerNumber == 1 ? lastTour.getPlayer1Decision()
                : lastTour.getPlayer2Decision();
        if (opponentDecision) {
            return true;
        } else {
            int betrayByOpponentCount=history.getBetrayToursOf(opponentPlayerNumber).size();
            playLeft.push(true);
            playLeft.push(true);
            for (int i = 0; i < betrayByOpponentCount - 1; i++) {
                playLeft.push(false);
            }
            return false;
        }

    }

}
