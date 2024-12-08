package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;

public interface GameStateBehaviour {

     GameMessage joinGame(GameEncounter gameEncounter, String playerName);

     GameMessage leaveGame(GameEncounter gameEncounter, String playerName,int choosedStrategyNumber);
     GameMessage makeDecision(GameEncounter gameEncounter,Player currentPlayer, boolean playerDecision);
}
