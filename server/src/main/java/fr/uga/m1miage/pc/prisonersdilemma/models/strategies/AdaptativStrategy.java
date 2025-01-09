package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;

public class AdaptativStrategy implements Strategy{
    @Override
    public boolean play(History history, PlayerRole playerRole) {

        if(history.size() <5){
            return true;
        }
        if(history.size()<10){
            return false;
        }
        return history.getAverageCooperateScore(playerRole)> history.getAverageBetrayScore(playerRole.opponent());
    }
}
