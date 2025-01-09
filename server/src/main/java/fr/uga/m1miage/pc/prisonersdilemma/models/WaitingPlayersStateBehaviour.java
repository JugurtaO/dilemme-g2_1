package fr.uga.m1miage.pc.prisonersdilemma.models;

import fr.uga.m1miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.m1miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.m1miage.pc.prisonersdilemma.models.strategies.StrategyFactory;

import java.util.Objects;
import java.util.Random;

public class WaitingPlayersStateBehaviour implements GameStateBehaviour {
    public static final String GAME_JOIN = "game.joined";
    public static final String GAME_ERROR = "game.error";
    public static final String GAME_LEFT = "game.left";
    Random random=new Random();

    @Override
    public GameMessage joinGame(GameEncounter gameEncounter, String playerName) {
        if(gameEncounter.getPlayer1()==null){
            gameEncounter.setPlayer1(new Player(playerName,gameEncounter));
            gameEncounter.setGameState(GameState.WAITING_FOR_PLAYER);
            return new GameMessage(GAME_JOIN,gameEncounter,playerName+" has joined the game");
        }
        if(gameEncounter.getPlayer2()==null){
            gameEncounter.setPlayer2(new Player(playerName,gameEncounter));
            gameEncounter.setGameState(GameState.GAME_IN_PROGRESS);
            gameEncounter.setGameStateBehaviour(new GameInProgressStateBehaviour());
            return new GameMessage(GAME_JOIN,gameEncounter,playerName+" has joined the game");
        }

        return new GameMessage(GAME_ERROR,gameEncounter,"error cannot join a full game");

    }

    @Override
    public GameMessage makeDecision(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision) {
        return new GameMessage(GAME_ERROR,gameEncounter,"erreur : cannot make decision in a game where a player is missing");
    }

    @Override
    public GameMessage leaveGame(GameEncounter gameEncounter, String playerName,int choosedStrategyNumber) {
        if(Objects.equals(gameEncounter.getPlayer1Name(), playerName)){
            gameEncounter.getPlayer1().setAiMode(true);
            gameEncounter.getPlayer1().setStrategy(StrategyFactory.getStrategyInstance(choosedStrategyNumber));
            return new GameMessage(GAME_LEFT,gameEncounter,playerName+" has left the game");
        }
        if(Objects.equals(gameEncounter.getPlayer2Name(), playerName)){
            gameEncounter.getPlayer2().setAiMode(true);
            gameEncounter.getPlayer2().setStrategy(StrategyFactory.getStrategyInstance(choosedStrategyNumber));
            return new GameMessage(GAME_LEFT,gameEncounter,playerName+" has left the game");
        }
        return new GameMessage(GAME_ERROR,gameEncounter,playerName+" cannot leave the game is not in it");

    }
}
