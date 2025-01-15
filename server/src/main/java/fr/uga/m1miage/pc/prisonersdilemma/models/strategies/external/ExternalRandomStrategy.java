package fr.uga.m1miage.pc.prisonersdilemma.models.strategies.external;

import fr.uga.l3miage.pc.prisonersdilemma.models.Strategie;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.AleatoireStrategie;
import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import fr.uga.m1miage.pc.prisonersdilemma.models.strategies.Strategy;

public class ExternalRandomStrategy implements Strategy {
    Strategie externalStrategy = new AleatoireStrategie();
    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return ExternalMapper.toBoolean(externalStrategy.faireChoix(ExternalMapper.toDecisionList(history,playerRole.opponent())));
    }
}
