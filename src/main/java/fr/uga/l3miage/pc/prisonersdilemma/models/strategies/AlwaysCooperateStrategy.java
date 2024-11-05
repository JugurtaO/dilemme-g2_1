package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;

public class AlwaysCooperateStrategy implements   Strategy{
    @Override
    public boolean play(List<Tour> history, int opponentPlayerNumber) {
        return true;
    }


}
