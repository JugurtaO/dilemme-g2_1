package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;


import java.util.List;
import java.util.Random;

public class TruePeaceMakerStrategy implements Strategy{

    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        return (history.isEmpty() || history.size() < 2) || getTruePeaceMakerDecision(history, opponentPlayerNumber);


    }

    private  boolean getTruePeaceMakerDecision(History history, int opponentPlayerNumber) {List<Tour> lastTwoTours = history.getLastTours(2);
        if ((opponentPlayerNumber == 1 &&
                lastTwoTours.get(1).getPlayer1Decision() != lastTwoTours.get(0).getPlayer1Decision()) ||
                (opponentPlayerNumber == 2 &&
                        lastTwoTours.get(1).getPlayer2Decision() != lastTwoTours.get(0).getPlayer2Decision())) {
            return true; // Cooperate
        } else {
            int k = getRandomInstance().nextInt(3) + 1;
            return k == 3;
        }}
    public Random getRandomInstance(){return new Random();}
}
