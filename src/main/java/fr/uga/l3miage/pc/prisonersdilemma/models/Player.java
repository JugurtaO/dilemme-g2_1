package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.Strategy;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.StrategyFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

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
    public boolean makeDecision() {
        if (!aiMode) {
            //Player makes decision
            System.out.println ("make decision (True: to cooperate | False: to betray)"+ name);
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter decision : ");
            String playerDecision = scanner.nextLine();
            while (!playerDecision.toLowerCase().equals("true") && !playerDecision.toLowerCase().equals("false")) {
                System.out.println("Enter a valide decision true/false : ");
                playerDecision = scanner.nextLine();
            }

            return Boolean.parseBoolean(playerDecision);

        } else {
            // AI makes decision
            int opponentPlayerNumber = gameEncounter.getPlayer1Name().equals(name) ? 2 : 1;
            return strategy.play(gameEncounter.getHistory(), opponentPlayerNumber);
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        this.score += points;
    }

    public boolean getAiMode() {
        return aiMode;
    }

   @Override
 public void leaveEncounter() {
        aiMode = true;
        strategy = StrategyFactory.getStrategyInstance(19);
    }


}
