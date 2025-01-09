package fr.uga.m1miage.pc.prisonersdilemma.models;

import fr.uga.m1miage.pc.prisonersdilemma.dto.GameMessage;

public interface GameStateBehaviour {

     GameMessage joinGame(GameEncounter gameEncounter, String playerName);

     GameMessage leaveGame(GameEncounter gameEncounter, String playerName,int choosedStrategyNumber);
     GameMessage makeDecision(GameEncounter gameEncounter,Player currentPlayer, boolean playerDecision);
}
