package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.StrategyFactory;

import java.util.Objects;
import java.util.Random;

public class WaitingPlayersStateBehaviour implements GameStateBehaviour {
    Random random=new Random();

    @Override
    public GameMessage joinGame(GameEncounter gameEncounter, String playerName) {
        if(gameEncounter.getPlayer1()==null){
            gameEncounter.setPlayer1(new Player(playerName,gameEncounter));
            return new GameMessage("game.join",gameEncounter,playerName+" has joined the game");
        }
        if(gameEncounter.getPlayer2()==null){
            gameEncounter.setPlayer2(new Player(playerName,gameEncounter));
            return new GameMessage("game.join",gameEncounter,playerName+" has joined the game");
        }
        return new GameMessage("game.error",gameEncounter,"error cannot join a full game");

    }

    @Override
    public GameMessage leaveGame(GameEncounter gameEncounter, String playerName) {
        if(Objects.equals(gameEncounter.getPlayer1().getName(), playerName)){
            gameEncounter.getPlayer1().setAiMode(true);
            gameEncounter.getPlayer1().setStrategy(StrategyFactory.getStrategyInstance(this.random.nextInt(18)));
            return new GameMessage("game.leave",gameEncounter,playerName+" has left the game");
        }
        if(Objects.equals(gameEncounter.getPlayer2().getName(), playerName)){
            gameEncounter.getPlayer2().setAiMode(true);
            gameEncounter.getPlayer2().setStrategy(StrategyFactory.getStrategyInstance(this.random.nextInt(18)));
            return new GameMessage("game.leave",gameEncounter,playerName+" has left the game");
        }
        return new GameMessage("game.error",gameEncounter,playerName+" cannot leave the game is not in it");

    }

    @Override
    public GameMessage makeDecision(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision) {
        return new GameMessage("game.error",gameEncounter,"erreur : cannot make decision in a game where a player is missing");
    }
}
