package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;

public class AlwaysBetrayStrategy implements   Strategy{
    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        return false;
    }


}
