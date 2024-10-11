package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;

public class AlwaysBetrayStrategy extends  Strategy{
    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        return false;
    }


}
