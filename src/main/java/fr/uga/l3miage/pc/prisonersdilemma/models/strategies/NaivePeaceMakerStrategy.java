package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import java.util.Random;

public class NaivePeaceMakerStrategy implements Strategy{
    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        int k=getRandomInstance().nextInt(5)+1;
        if(k==2 || k==3){
            return getDonnantDonnantInstance().play(history,opponentPlayerNumber);
        }else{
            return true;
        }
    }

    public DonnantDonnantStrategy getDonnantDonnantInstance(){
        return new DonnantDonnantStrategy();
    }
    public Random getRandomInstance(){return new Random();}
}
