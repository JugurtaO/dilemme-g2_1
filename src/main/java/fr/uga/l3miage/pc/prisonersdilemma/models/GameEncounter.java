package fr.uga.l3miage.pc.prisonersdilemma.models;
import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public  class GameEncounter {
    private static final Logger LOGGER = Logger.getLogger(GameEncounter.class.getName());

    private int nbTours;
    private Player player1;
    private Player player2;


    private List<Tour> history;



    public GameEncounter(int n,Player p1,Player p2){
        this.nbTours=n;
        this.player1=p1;
        this.player2=p2;
        this.history=new ArrayList<>();

    }



    public List<Tour> getHistory(){
        return history;
    }

    public String getPlayer1Name(){
        return player1.getName();
    }

    public void start(){

        for (int i = 1; i <=nbTours ; i++) {
           LOGGER.info(String.format("******** TOUR %d *********",i));

           if(!player1.getAiMode()){
               Utils.playerLeaveGameHandler(player1);
           }
            boolean player1Decision=player1.makeDecision();
           LOGGER.info(String.format("# Le joueur 1 a jou�, c'est votre tour %s ------",player2.getName()));

            if(!player2.getAiMode()){
                Utils.playerLeaveGameHandler(player2);
            }
            boolean player2Decision=player2.makeDecision();
           LOGGER.info(String.format("# Le joueur 2 a jou�, c'est votre tour %s------",player1.getName()));


            Tour tour= new Tour(i,player1Decision,player2Decision);
            history.add(tour);
            Utils.calculateScores(player1,player1Decision,player2,player2Decision);
            Utils.displayTourNumberAndScores(i,player1,player2);
        }

    }



}
