package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;

public class DonnantForTwoDonnantsStrategy implements Strategy {

    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        if (history.isEmpty() || history.size() < 2) {
            return true;
        }
        List<Tour> lastTwoTours = history.getLastTours(2);
        if (opponentPlayerNumber == 1 &&
                lastTwoTours.get(1).getPlayer1Decision() == lastTwoTours.get(0).getPlayer1Decision()) {
            return lastTwoTours.get(1).getPlayer1Decision();
        }
        if (opponentPlayerNumber == 2 &&
                lastTwoTours.get(1).getPlayer2Decision() == lastTwoTours.get(0).getPlayer2Decision()) {
            return lastTwoTours.get(1).getPlayer2Decision();
        }
        return true;
    }


}
