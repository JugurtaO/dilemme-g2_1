package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;


public class PavlovStrategy implements Strategy {
    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        return history.isEmpty() || getDecisionAccordingToLastTour(history, opponentPlayerNumber);

    }
    private boolean getDecisionAccordingToLastTour(History history, int opponentPlayerNumber) {
        Tour lastTour = history.getLastTour();
        boolean lastChoice = opponentPlayerNumber == 1 ? lastTour.getPlayer2Decision() : lastTour.getPlayer1Decision();
        if (lastTour.getPlayerScore(opponentPlayerNumber) == 0 || lastTour.getPlayerScore(opponentPlayerNumber) == 3) {
            return lastChoice;
        }
        return !lastChoice;
    }
}
