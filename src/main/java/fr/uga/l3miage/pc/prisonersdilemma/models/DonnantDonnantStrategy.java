package fr.uga.l3miage.pc.prisonersdilemma.models;


import java.util.List;

public class DonnantDonnantStrategy extends Strategy{
    @Override
     boolean play(List<Tour> history, int opponentPlayerNumber) {
        if(history.isEmpty()){
            return true; //cooperate by default because it's the first tour of the encounter
        }
        Tour lastTour=history.get(history.size()-1);
        return opponentPlayerNumber==1 ? lastTour.getPlayer1Decision():lastTour.getPlayer2Decision() ;
    }
}
