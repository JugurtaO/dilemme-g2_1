package fr.uga.m1miage.pc.prisonersdilemma.models.strategies.external;

import fr.uga.l3miage.pc.prisonersdilemma.models.Strategie;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.PavlovStrategie;
import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import fr.uga.m1miage.pc.prisonersdilemma.models.strategies.Strategy;

public class ExternalPavlovStrategy implements Strategy {
    Strategie externalStrategy = new PavlovStrategie();
    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return ExternalMapper.toBoolean(externalStrategy.faireChoix(ExternalMapper.toDecisionList(history,playerRole.opponent())));
    }
}
