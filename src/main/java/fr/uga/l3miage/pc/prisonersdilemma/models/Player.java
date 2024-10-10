package fr.uga.l3miage.pc.prisonersdilemma.models;


import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;

import java.util.Scanner;

import static fr.uga.l3miage.pc.prisonersdilemma.utils.Utils.chooseStrategy;

public class Player  implements  PlayerInterface{
    private final String name;
    private int score=0;
    private Strategy strategy;
    private boolean AIMode=false;
    private  GameEncounter gameEncounter;

    public Player(String name, GameEncounter gameEncounter){
        this.name=name;
        this.gameEncounter=gameEncounter;
        this.strategy=null;

    }
public void setGameEncounter(GameEncounter gameEncounter){this.gameEncounter=gameEncounter;}
    @Override
    public boolean makeDecision() {
        if(!AIMode){
            //Player makes decision
            System.out.println(name +" :  make decision (True: to cooperate | False: to betray");
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter decision : ");
            String playerDecision = scanner.nextLine();
            while(!playerDecision.toLowerCase().equals("true") && !playerDecision.toLowerCase().equals("false")){
                System.out.print("Enter a valide decision true/false : ");
                playerDecision = scanner.nextLine();
            }

            return Boolean.parseBoolean(playerDecision);

        }else {
            // AI makes decision
            int opponentPlayerNumber= gameEncounter.getPlayer1Name()==name ? 2:1;
            return strategy.play(gameEncounter.getHistory(), opponentPlayerNumber);
        }
    }

    @Override
    public int getScore() {
        return score;
    }
    public void updateScore(int points){
        this.score+=points;
    }
    public String getName(){
        return name;
    }

public boolean getAIMode(){return AIMode;}

    @Override
    public void leaveEncounter() {
        AIMode=true;
        Utils.displayStrategiesMenu();
        int strategyNumber=chooseStrategy(name);

        switch (strategyNumber){
            case 1:
                strategy = new DonnantDonnantStrategy();
                break;
            case 2:
                strategy = new DonnantDonnantRandomStrategy();
                break;
            case 3:
                strategy= new DonnantForTwoDonnants();
                break;
            case 4:
                strategy = new DonnantForTwoDonnantsRandomStrategy();
                break;
            case 5:
                strategy= new NaiveSounderStrategy();
                break;
            case 6:
                strategy= new RepentantSounderStrategy();
                break;
            case 7:
                strategy= new NaivePeaceMakerStrategy();
                break;
            case 8:
                strategy= new TruePeaceMakerStrategy();
                break;
            case 9:
                strategy= new RandomStrategy();
                break;
            default:
                break;

        }

    }
}
