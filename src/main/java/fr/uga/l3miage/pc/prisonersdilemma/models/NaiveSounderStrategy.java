package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;
import java.util.Random;

public class NaiveSounderStrategy extends  Strategy{
    private DonnantDonnantStrategy donnantDonnantStrategy;
    NaiveSounderStrategy(){
        this.donnantDonnantStrategy=new DonnantDonnantStrategy();
    }
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        //play randomly
        int k= new Random().nextInt(8)+1;
        if(k==1 || k==4 || k==8){
            return donnantDonnantStrategy.play(history,opponentPlayerNumber);
        }else {
            return false;
        }

    }
}
