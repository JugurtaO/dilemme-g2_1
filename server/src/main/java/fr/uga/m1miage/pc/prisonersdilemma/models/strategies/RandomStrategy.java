package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import java.util.Random;

public class RandomStrategy implements Strategy{
    @Override
    public boolean play(History history, PlayerRole playerRole) {
        int k= getRandomInstance().nextInt(2)+1;
        return k==1;
    }

    public Random getRandomInstance(){return new Random();}
}
