package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;
import java.util.Random;

public class RandomStrategy extends Strategy{
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        int k= getRandomInstance().nextInt(2)+1;
        return k==1;
    }

    public Random getRandomInstance(){return new Random();}
}
