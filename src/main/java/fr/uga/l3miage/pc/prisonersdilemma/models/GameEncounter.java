package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public  class GameEncounter {
    private String gameId;
    private int nbTours;
    private Player player1;
    private Player player2;
    private GameState gameState;
    private String winner;
    private History history;



    public GameEncounter(int n,Player p1,Player p2){
        this.gameId = UUID.randomUUID().toString();
        this.nbTours=n;
        this.player1=p1;
        this.player2=p2;
        this.history=new History();

    }


    public String getPlayer1Name(){
        return player1.getName();
    }

    public boolean isGameOver(){
        return winner!=null;
    }


}
