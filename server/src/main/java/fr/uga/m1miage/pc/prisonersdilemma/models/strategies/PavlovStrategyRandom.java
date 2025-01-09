package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import java.util.Random;

public class PavlovStrategyRandom implements Strategy{
    Random random = new Random();
    @Override
    public boolean play(History history, PlayerRole playerRole) {

        if (random.nextInt(10)== 1){
            return random.nextBoolean();
        }
        PavlovStrategy strategy = new PavlovStrategy();
        return strategy.play(history, playerRole);
    }
}
