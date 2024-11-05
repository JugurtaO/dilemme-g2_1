package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;

public class ResentfulStrategy implements   Strategy{
    @Override
    public boolean play(List<Tour> history, int opponentPlayerNumber) {
       return history.stream().noneMatch(tour -> opponentPlayerNumber==1? !tour.getPlayer1Decision() : !tour.getPlayer2Decision());
    }
}
