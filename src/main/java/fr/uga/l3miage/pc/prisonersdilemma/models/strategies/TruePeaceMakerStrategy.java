package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;


import java.util.List;
import java.util.Random;

public class TruePeaceMakerStrategy implements Strategy{

    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return (history.isEmpty() || history.size() < 2) || getTruePeaceMakerDecision(history, playerRole);


    }

    private  boolean getTruePeaceMakerDecision(History history, PlayerRole playerRole) {
        List<Tour> lastTwoTours = history.getLastTours(2);
        if(lastTwoTours.get(1).getPlayerDecision(playerRole.opponent())&&lastTwoTours.get(0).getPlayerDecision(playerRole.opponent())){
            return true;
        }
        else {
            int k = getRandomInstance().nextInt(3) + 1;
            return k == 3;
        }}
    public Random getRandomInstance(){return new Random();}
}
