package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;

public class AdaptativStrategy implements Strategy{
    @Override
    public boolean play(History history, int opponentPlayerNumber) {

        if(history.size() <5){
            return true;
        }
        if(history.size()<10){
            return false;
        }
        return history.getAverageCooperateScore(opponentPlayerNumber==1?2:1)> history.getAverageBetrayScore(opponentPlayerNumber==1?2:1);
    }
}
