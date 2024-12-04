package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;

public interface GameStateBehaviour {

    public GameMessage joinGame(GameEncounter gameEncounter, String playerName);

    public GameMessage leaveGame(GameEncounter gameEncounter, String playerName);
    public GameMessage makeDecision(GameEncounter gameEncounter,Player currentPlayer, boolean playerDecision);
}
