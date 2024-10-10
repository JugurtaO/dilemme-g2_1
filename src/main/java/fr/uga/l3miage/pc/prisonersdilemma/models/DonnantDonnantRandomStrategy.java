package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;
import java.util.Random;

public class DonnantDonnantRandomStrategy extends  Strategy{

    private DonnantDonnantStrategy donnantDonnantStrategy;
    DonnantDonnantRandomStrategy(){
        this.donnantDonnantStrategy=new DonnantDonnantStrategy();
    }
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        if(history.isEmpty()){
            return true; //cooperate by default because it's the first tour of the encounter
        }
        int k= new Random().nextInt(4)+1;
        if(k!=2){
            return donnantDonnantStrategy.play(history,opponentPlayerNumber);
        }
        k=new Random().nextInt(2)+1;
        return k == 1;
    }
}
