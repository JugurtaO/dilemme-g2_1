package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;

public class ResentfulStrategy extends  Strategy{
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
       return history.stream().noneMatch(tour -> opponentPlayerNumber==1? !tour.getPlayer1Decision() : !tour.getPlayer2Decision());
    }
}
