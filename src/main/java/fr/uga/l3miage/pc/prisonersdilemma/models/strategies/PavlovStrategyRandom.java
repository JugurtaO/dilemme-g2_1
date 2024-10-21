package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;
import java.util.Random;

public class PavlovStrategyRandom implements Strategy{
    @Override
    public boolean play(List<Tour> history, int opponentPlayerNumber) {
        Random random = new Random();
        if (random.nextInt(10)== 1){
            return random.nextBoolean();
        }
        PavlovStrategy strategy = new PavlovStrategy();
        return strategy.play(history, opponentPlayerNumber);
    }
}
