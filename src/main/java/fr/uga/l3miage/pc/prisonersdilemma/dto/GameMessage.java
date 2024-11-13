package fr.uga.l3miage.pc.prisonersdilemma.dto;

import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameMessage {
    private String messageType;
    private String gameId;
    private String playerName1;
    private String playerName2;
    private String winner;
    private String content;
    private Boolean decision;
    private GameState gameState;
    private String sender;

    public GameMessage (){}
    public GameMessage(GameEncounter game) {
        this.gameId = game.getGameId();
        this.playerName1 = game.getPlayer1()!=null ? game.getPlayer1().getName():"";
        this.playerName2 = game.getPlayer2()!=null ?game.getPlayer2().getName():"";
        this.winner = game.getWinner();
        this.gameState = game.getGameState();
    }


}
