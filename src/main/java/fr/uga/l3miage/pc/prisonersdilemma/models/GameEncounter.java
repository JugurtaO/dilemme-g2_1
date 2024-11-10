package fr.uga.l3miage.pc.prisonersdilemma.models;
import fr.uga.l3miage.pc.prisonersdilemma.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Setter
@Getter
public  class GameEncounter {
    private int gameId;
    private int nbTours;
    private Player player1;
    private Player player2;
    private GameState gameState;
    private String winner;

    private History history;



    public GameEncounter(int n,Player p1,Player p2){
        this.nbTours=n;
        this.player1=p1;
        this.player2=p2;
        this.history=new History();

    }



    public History getHistory(){
        return history;
    }

    public String getPlayer1Name(){
        return player1.getName();
    }

    public void start(){

        for (int i = 1; i <=nbTours ; i++) {
            System.out.println("******** TOUR "+i+" *********");
           if(!player1.getAiMode()){
               Utils.playerLeaveGameHandler(player1);
           }
            boolean player1Decision=player1.makeDecision();
            System.out.println("# Le joueur 1 a jou�, c'est votre tour"+player2.getName()+" ------");
            if(!player2.getAiMode()){
                Utils.playerLeaveGameHandler(player2);
            }
            boolean player2Decision=player2.makeDecision();
           System.out.println("# Le joueur 2 a jou�, c'est votre tour"+player1.getName()+"------");
            history.addTour(player1Decision,player2Decision);
            Utils.calculateScores(player1,player1Decision,player2,player2Decision);
            Utils.displayTourNumberAndScores(i,player1,player2);
        }

    }

    public boolean isGameOver(){
        return winner!=null;
    }


}
