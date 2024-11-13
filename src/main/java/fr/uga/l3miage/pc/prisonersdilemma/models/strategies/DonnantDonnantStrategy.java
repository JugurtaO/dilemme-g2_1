package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

public class DonnantDonnantStrategy implements Strategy {
    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        return history.isEmpty() || getLastTourDecision(history, opponentPlayerNumber);

    }
    private boolean getLastTourDecision(History history, int opponentPlayerNumber) {
        Tour lastTour=history.getLastTour();
        return opponentPlayerNumber==1 ? lastTour.getPlayer1Decision():lastTour.getPlayer2Decision() ;
    }
}
