package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;

public class AlwaysCooperateStrategy extends  Strategy{
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        return true;
    }


}
