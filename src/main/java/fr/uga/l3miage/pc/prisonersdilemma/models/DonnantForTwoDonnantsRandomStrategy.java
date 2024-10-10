package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;

import java.util.List;
import java.util.Random;

public class DonnantForTwoDonnantsRandomStrategy extends Strategy {


    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        if(history.size()<2){
            return true; //cooperate by default
        }

        Tour[] lastTwoTours= Utils.getLastTwoTurns(history);
        if(opponentPlayerNumber==1 && lastTwoTours[1].getPlayer1Decision()==lastTwoTours[0].getPlayer1Decision()){
            return lastTwoTours[1].getPlayer1Decision();
        }
        if(opponentPlayerNumber==2 && lastTwoTours[1].getPlayer2Decision()==lastTwoTours[0].getPlayer2Decision()){
            return lastTwoTours[1].getPlayer2Decision();
        }
        int k=new Random().nextInt(5)+1;
        return k==1 || k==5;
    }
}
