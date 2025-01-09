package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import fr.uga.m1miage.pc.prisonersdilemma.models.Tour;


public class PavlovStrategy implements Strategy {
    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return history.isEmpty() || getDecisionAccordingToLastTour(history, playerRole);

    }
    private boolean getDecisionAccordingToLastTour(History history, PlayerRole playerRole) {
        Tour lastTour = history.getLastTour();
        boolean lastChoice = lastTour.getPlayerDecision(playerRole);
        if (lastTour.getPlayerScore(playerRole.opponent()) == 0 || lastTour.getPlayerScore(playerRole.opponent()) == 3) {
            return lastChoice;
        }
        return !lastChoice;
    }
}
