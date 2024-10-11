package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;

public class PavlovStrategy extends Strategy{
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        Tour lastTour=history.get(history.size()-1);
        boolean lastChoice=opponentPlayerNumber==1?lastTour.getPlayer2Decision():lastTour.getPlayer1Decision();
        if(lastTour.getPlayerScore(opponentPlayerNumber)==0||lastTour.getPlayerScore(opponentPlayerNumber)==3){
            return lastChoice;
        }
        return !lastChoice;
    }
}
