package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;


public class AlwaysCooperateStrategy implements   Strategy{
    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return true;
    }


}
