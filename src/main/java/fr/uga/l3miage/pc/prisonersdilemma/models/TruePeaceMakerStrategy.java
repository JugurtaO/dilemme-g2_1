package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;

import java.util.List;
import java.util.Random;

public class TruePeaceMakerStrategy extends Strategy{
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        if(history.isEmpty() || history.size()<2){
            return false;
        }

        Tour[] lastTwoTours= Utils.getLastTwoTurns(history);
        if((opponentPlayerNumber==1 && lastTwoTours[1].getPlayer1Decision()!= lastTwoTours[0].getPlayer1Decision()) ||
                (opponentPlayerNumber==2 && lastTwoTours[1].getPlayer2Decision()!= lastTwoTours[0].getPlayer2Decision())){
            return true;
        }else {
            int k=new Random().nextInt(3)+1;
            return k==3;
        }

    }
}
