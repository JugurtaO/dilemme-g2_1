package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;

public abstract class Strategy {

        private String name;
        abstract boolean play(List<Tour> history,int opponentPlayerNumber);

}
