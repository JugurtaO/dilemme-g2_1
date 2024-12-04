package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;

public class DonnantForTwoDonnantsStrategy implements Strategy {

    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return (history.isEmpty() || history.size() < 2)  || getDonnantDecision(history, playerRole);



    }

    private boolean getDonnantDecision(History history, PlayerRole playerRole) {
        List<Tour> lastTwoTours = history.getLastTours(2);
        if(lastTwoTours.get(0).getPlayerDecision(playerRole.opponent()).equals(lastTwoTours.get(1).getPlayerDecision(playerRole.opponent()))){
            return lastTwoTours.get(1).getPlayerDecision(playerRole.opponent());
        }
        else return !lastTwoTours.get(1).getPlayerDecision(playerRole.opponent());
    }


}
