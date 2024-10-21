package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;
import java.util.Stack;

public class GradualStrategy implements   Strategy{
    private final Stack<Boolean> playLeft=new Stack<>();
    @Override
    public boolean play(List<Tour> history, int opponentPlayerNumber) {
        if(!playLeft.isEmpty()){
            return playLeft.pop();
        }
       Tour lastTour = history.get(history.size()-1);
        boolean opponentDecision =opponentPlayerNumber==1?lastTour.getPlayer1Decision():lastTour.getPlayer2Decision();
        if(opponentDecision){
            return true;
        }else {
            int betrayByOpponentCount= (int) history.stream().filter(tour ->opponentPlayerNumber==1?!lastTour.getPlayer1Decision():!lastTour.getPlayer2Decision()).count();
            playLeft.push(true);
            playLeft.push(true);
            for(int i=0; i<betrayByOpponentCount-1; i++){
                playLeft.push(false);
            }
            return false;
        }

    }
}


