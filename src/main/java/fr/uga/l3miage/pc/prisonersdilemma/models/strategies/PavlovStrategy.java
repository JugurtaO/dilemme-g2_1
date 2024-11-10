package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;

public class PavlovStrategy implements Strategy {
    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        if (history.isEmpty()) {
            return true;
        }
        Tour lastTour = history.getLastTour();
        boolean lastChoice = opponentPlayerNumber == 1 ? lastTour.getPlayer2Decision() : lastTour.getPlayer1Decision();
        if (lastTour.getPlayerScore(opponentPlayerNumber) == 0 || lastTour.getPlayerScore(opponentPlayerNumber) == 3) {
            return lastChoice;
        }
        return !lastChoice;
    }
}
