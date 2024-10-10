package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;
import java.util.Random;

public class NaivePeaceMakerStrategy extends Strategy{
    private final DonnantDonnantStrategy donnantDonnantStrategy;
    NaivePeaceMakerStrategy() {
        this.donnantDonnantStrategy=new DonnantDonnantStrategy();
    }
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        int k=new Random().nextInt(5)+1;
        if(k==2 || k==3){
            return donnantDonnantStrategy.play(history,opponentPlayerNumber);
        }else{
            return true;
        }

    }
}
