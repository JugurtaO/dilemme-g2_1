package fr.uga.m1miage.pc.prisonersdilemma.models.strategies.external;

import fr.uga.l3miage.pc.prisonersdilemma.enums.Decision;
import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;

import java.util.List;

public class ExternalMapper {
    private ExternalMapper() {}
    public static List<Decision>toDecisionList(History history, PlayerRole role){
       return history.getAllTours().stream().map(tour -> Boolean.TRUE.equals(tour.getPlayerDecision(role))? Decision.COOPERER:Decision.TRAHIR).toList();
    }

    public static boolean toBoolean(Decision decision){
        return decision == Decision.COOPERER;
    }
}
