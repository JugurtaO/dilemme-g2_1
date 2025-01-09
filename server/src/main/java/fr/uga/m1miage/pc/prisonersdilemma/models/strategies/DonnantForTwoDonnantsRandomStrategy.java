package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import java.util.Random;

public class DonnantForTwoDonnantsRandomStrategy implements Strategy {
    public Random getRandomInstance() {
        return new Random();
    }
    public DonnantForTwoDonnantsStrategy getDonnantForTwoDonnantStrategy(){
        return new DonnantForTwoDonnantsStrategy();
    }
    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return history.isEmpty()|| history.size()<2 || getRandomInstanceAndDonnantForTwoDonnantStrategy(history, playerRole);

    }

    private boolean getRandomInstanceAndDonnantForTwoDonnantStrategy(History history, PlayerRole playerRole){
        int k= getRandomInstance().nextInt(4)+1;
        if(k!=2){
            return getDonnantForTwoDonnantStrategy().play(history,playerRole);
        }
        k=getRandomInstance().nextInt(2)+1;
        return k == 1;
    }
}
