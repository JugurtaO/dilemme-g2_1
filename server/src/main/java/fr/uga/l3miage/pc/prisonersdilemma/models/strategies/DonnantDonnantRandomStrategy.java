package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import java.util.Random;

public class DonnantDonnantRandomStrategy implements Strategy {
    
    public Random getRandomInstance() {
        return new Random();
    }
    public DonnantDonnantStrategy getDonnantDonnantStrategy(){
        return new DonnantDonnantStrategy();
    }
    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return history.isEmpty() || getRandomInstanceAndDonnantDonnantStrategy(history, playerRole);

    }

    private boolean getRandomInstanceAndDonnantDonnantStrategy(History history, PlayerRole playerRole){
        int k= getRandomInstance().nextInt(4)+1;
        if(k!=2){
            return getDonnantDonnantStrategy().play(history,playerRole);
        }
        k=getRandomInstance().nextInt(2)+1;
        return k == 1;
    }
}
