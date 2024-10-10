package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public  class GameEncounter {
    private int nbTours;
    private Player player1;
    private Player player2;
    private String winner;

    private List<Tour> history;


    public GameEncounter(int n,Player p1,Player p2){
        this.nbTours=n;
        this.player1=p1;
        this.player2=p2;
        this.history=new ArrayList<>();


    }

    public String getWinner(){
        return this.winner;
    }
    public List<Tour> getHistory(){
        return history;
    }
    public boolean isFinished(){
        return this.winner!=null;
    }

    public String getPlayer1Name(){
        return player1.getName();
    }

    public void start(){

        for (int i = 1; i <=nbTours ; i++) {
            System.out.println("******** TOUR "+i+" *********");

           if(!player1.getAIMode()){
               Utils.playerLeaveGameHandler(player1);
           }
            boolean player1Decision=player1.makeDecision();
            System.out.println("# Le joueur 1 a joué, c'est votre tour "+player2.getName()+" ------");

            if(!player2.getAIMode()){
                Utils.playerLeaveGameHandler(player2);
            }
            boolean player2Decision=player2.makeDecision();
            System.out.println("# Le joueur 2 a joué, c'est votre tour "+player1.getName()+" ------");


            Tour tour= new Tour(i,player1Decision,player2Decision);
            history.add(tour);
            Utils.calculateScores(player1,player1Decision,player2,player2Decision);
            Utils.displayTourNumberAndScores(i,player1,player2);
        }

    }



}
