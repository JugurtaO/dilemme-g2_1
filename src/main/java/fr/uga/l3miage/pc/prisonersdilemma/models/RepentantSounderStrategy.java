package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;
import java.util.Random;

public class RepentantSounderStrategy extends Strategy{
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        if(history.isEmpty()){
            return false; //betray by default
        }
        //play sometimes randomly
        int k= new Random().nextInt(5)+1;
        if(k!=2){
            Tour lastTour=history.get(history.size()-1);
            boolean opponentDecision=opponentPlayerNumber==1?lastTour.getPlayer1Decision():lastTour.getPlayer2Decision();
            if(!opponentDecision){
                return true;
            }
            return true;
        }else  {
            return false;
        }



    }
}
