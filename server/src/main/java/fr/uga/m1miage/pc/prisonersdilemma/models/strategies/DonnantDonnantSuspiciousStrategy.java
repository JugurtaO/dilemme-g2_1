package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import fr.uga.m1miage.pc.prisonersdilemma.models.Tour;

public class DonnantDonnantSuspiciousStrategy implements Strategy{
    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return !history.isEmpty() && getLastTourDecision(history, playerRole);

    }

    private boolean getLastTourDecision(History history, PlayerRole playerRole){
        Tour lastTour=history.getLastTour();
        return lastTour.getPlayerDecision(playerRole.opponent());
    }
}
