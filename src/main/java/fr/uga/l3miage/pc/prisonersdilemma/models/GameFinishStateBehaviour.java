package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;

import java.util.Random;

public class GameFinishStateBehaviour implements GameStateBehaviour {

    @Override
    public GameMessage joinGame(GameEncounter gameEncounter, String playerName) {
        return new GameMessage("game.error",gameEncounter,playerName+ "cannot join. Game is finished");
    }

    @Override
    public GameMessage leaveGame(GameEncounter gameEncounter, String playerName) {
        return new GameMessage("game.error",gameEncounter,playerName+ "cannot leave. Game is finished");

    }

    @Override
    public GameMessage makeDecision(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision) {
        return new GameMessage("game.error", gameEncounter, "cannot make decision. Game is finished");
    }
    }
