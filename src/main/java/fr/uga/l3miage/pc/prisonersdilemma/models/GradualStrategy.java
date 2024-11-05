package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


public class GradualStrategy extends  Strategy{
    private final Deque<Boolean> playLeft=new ArrayDeque<>();
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        if(history.isEmpty()){
            return true;
        }
        if(!playLeft.isEmpty()){
            return playLeft.pop();
        }
       Tour lastTour = history.get(history.size()-1);
        boolean opponentDecision =opponentPlayerNumber==1?lastTour.getPlayer1Decision():lastTour.getPlayer2Decision();
        if(opponentDecision){
            return true;
        }else {
            int betrayByOpponentCount= (int) history.stream().filter(tour ->opponentPlayerNumber==1?!tour.getPlayer1Decision():!tour.getPlayer2Decision()).count();
            playLeft.push(true);
            playLeft.push(true);
            for(int i=0; i<betrayByOpponentCount-1; i++){
                playLeft.push(false);
            }
            return false;
        }

    }
}


