package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;

public class GameFinishStateBehaviour implements GameStateBehaviour {

    public static final String GAME_ERROR = "game.error";

    @Override
    public GameMessage joinGame(GameEncounter gameEncounter, String playerName) {
        return new GameMessage(GAME_ERROR,gameEncounter,playerName+ "cannot join. Game is finished");
    }

    @Override
    public GameMessage leaveGame(GameEncounter gameEncounter, String playerName,int choosedStrategyNumber) {
        return new GameMessage(GAME_ERROR,gameEncounter,playerName+ "cannot leave. Game is finished");

    }

    @Override
    public GameMessage makeDecision(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision) {
        return new GameMessage(GAME_ERROR, gameEncounter, "cannot make decision. Game is finished");
    }
    }
