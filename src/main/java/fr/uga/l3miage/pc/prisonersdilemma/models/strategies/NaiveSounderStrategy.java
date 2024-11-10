package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import java.util.Random;

public class NaiveSounderStrategy implements   Strategy{
    public Random getRandomInstance(){return new Random();}
    public DonnantDonnantStrategy getDonnantDonnantStrategyInstance(){return new DonnantDonnantStrategy();}
    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        //play randomly
        int k= getRandomInstance().nextInt(8)+1;
        if(k==1 || k==4 || k==8){
            return getDonnantDonnantStrategyInstance().play(history,opponentPlayerNumber);
        }else {
            return false;
        }

    }
}
