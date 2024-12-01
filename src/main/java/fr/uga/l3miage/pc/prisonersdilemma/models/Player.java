package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.Strategy;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.StrategyFactory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player implements PlayerInterface {
    private final String name;
    private int score = 0;
    private Strategy strategy;
    private boolean aiMode = false;
    private GameEncounter gameEncounter;

    public Player(String name, GameEncounter gameEncounter) {
        this.name = name;
        this.gameEncounter = gameEncounter;
        this.strategy = null;

    }

    @Override
    public boolean makeDecision(boolean decision) {
        if (!aiMode) {
            return decision;
        } else {
            PlayerRole playerRole = gameEncounter.getPlayer1Name().equals(name) ? PlayerRole.J1 : PlayerRole.J2;
            return strategy.play(gameEncounter.getHistory(), playerRole);
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
 public void leaveEncounter() {
        aiMode = true;
        strategy = StrategyFactory.getStrategyInstance(19);
    }


}
