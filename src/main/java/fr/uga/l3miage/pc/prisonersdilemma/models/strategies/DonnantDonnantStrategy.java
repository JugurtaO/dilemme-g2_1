package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;

public class DonnantDonnantStrategy implements Strategy {
    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        if(history.isEmpty()){
            return true; //cooperate by default because it's the first tour of the encounter
        }
        Tour lastTour=history.getLastTour();
        return opponentPlayerNumber==1 ? lastTour.getPlayer1Decision():lastTour.getPlayer2Decision() ;
    }
}
