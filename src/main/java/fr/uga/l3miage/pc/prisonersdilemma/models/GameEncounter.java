package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;


@Setter
@Getter
public class GameEncounter implements GameStateBehaviour {
    private String gameId;
    private int nbTours;
    private int currentTourNumber;
    private Player player1;
    private Player player2;
    private GameState gameState;
    private GameStateBehaviour gameStateBehaviour;
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
        return player1==null?null:player1.getName();
    }
    public String getPlayer2Name(){
        return player2==null?null:player2.getName();
    }
    public int getPlayer1Score(){
        return player1==null?-1:player1.getScore();
    }
    public int getPlayer2Score(){
        return player2==null?-1:player2.getScore();
    }
    public void setPlayer1( Player player){
        this.player1=player;
        player.setRole(PlayerRole.J1);
    }
    public void setPlayer2( Player player){
        this.player2=player;
        player.setRole(PlayerRole.J2);
    }




    public boolean isGameOver(){
        return winner!=null || gameState==GameState.GAME_FINISHED;
    }
    public Tour getTourByTourNumber(int tourNumber) {
        return history.getTourByTourNumber(tourNumber);
    }


    @Override
    public GameMessage joinGame(GameEncounter gameEncounter, String playerName) {
        return this.gameStateBehaviour.joinGame(gameEncounter, playerName);
    }

    @Override
    public GameMessage leaveGame(GameEncounter gameEncounter, String playerName) {
        return this.gameStateBehaviour.leaveGame(gameEncounter, playerName);
    }

    @Override
    public GameMessage makeDecision(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision) {
        return this.gameStateBehaviour.makeDecision(gameEncounter, currentPlayer, playerDecision);
    }
}
