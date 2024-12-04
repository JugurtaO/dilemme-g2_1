package fr.uga.l3miage.pc.prisonersdilemma.models.services;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.models.WaitingPlayersStateBehaviour;
import fr.uga.l3miage.pc.prisonersdilemma.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


 class GameServiceTests {
    private GameService gameService;

    @BeforeEach
    void resetServiceGameEncounters(){
        this.gameService=new GameService();
    }

    @Test
     void joinNewGameOK(){
        GameMessage response= gameService.joinGame("Jugurta");
        assertThat(response.gameState()).isEqualTo(GameState.WAITING_FOR_PLAYER);
        assertThat(response.playerName1()).isEqualTo("Jugurta");
    }
    @Test
    void joinExistingGameOK() {
        Player p1=new Player("Samuel",null);
        GameEncounter gameEncounter=new GameEncounter(5,p1,null);
        gameEncounter.setGameState(GameState.WAITING_FOR_PLAYER);
        gameEncounter.setGameStateBehaviour(new WaitingPlayersStateBehaviour());
        p1.setGameEncounter(gameEncounter);
        this.gameService.getGames().put(gameEncounter.getGameId(), gameEncounter);
        GameMessage response= gameService.joinGame("Tom");
        assertThat(response.gameState()).isEqualTo(GameState.GAME_IN_PROGRESS);
        assertThat(response.playerName2()).isEqualTo("Tom");
    }

    @Test void getGameByIdOK(){
        Player p1=new Player("Samuel",null);
        GameEncounter gameEncounter1=new GameEncounter(5,p1,null);
        gameEncounter1.setGameState(GameState.WAITING_FOR_PLAYER);
        p1.setGameEncounter(gameEncounter1);
        this.gameService.getGames().put(gameEncounter1.getGameId(), gameEncounter1);

        GameEncounter response= this.gameService.getGame(gameEncounter1.getGameId());
        assertThat(response.getGameState()).isEqualTo(GameState.WAITING_FOR_PLAYER);
        assertThat(response.getPlayer1().getName()).isEqualTo("Samuel");
    }

    @Test void getGameByIdNotFound(){
        GameEncounter response= this.gameService.getGame(UUID.randomUUID().toString());
        assertThat(response).isNull();
    }
    @Test
    void getGameByPlayerOK(){
        Player p1=new Player("Samuel",null);
        GameEncounter gameEncounter1=new GameEncounter(5,p1,null);
        gameEncounter1.setGameState(GameState.WAITING_FOR_PLAYER);
        p1.setGameEncounter(gameEncounter1);
        this.gameService.getGames().put(gameEncounter1.getGameId(), gameEncounter1);

        GameEncounter response= this.gameService.getGameByPlayer(p1.getName());
        assertThat(response.getGameId()).isEqualTo(gameEncounter1.getGameId());
        assertThat(response.getGameState()).isEqualTo(GameState.WAITING_FOR_PLAYER);
        assertThat(response.getPlayer1().getName()).isEqualTo("Samuel");
    }
    @Test
    void getGameByPlayerNotFound(){

        GameEncounter response= this.gameService.getGameByPlayer("Julien");
        assertThat(response).isNull();
    }

    @Test
    void removeGameOK(){
        Player p1=new Player("Samuel",null);
        GameEncounter gameEncounter1=new GameEncounter(5,p1,null);
        gameEncounter1.setGameState(GameState.WAITING_FOR_PLAYER);
        p1.setGameEncounter(gameEncounter1);
        this.gameService.getGames().put(gameEncounter1.getGameId(), gameEncounter1);

        GameEncounter response= this.gameService.removeGame(gameEncounter1.getGameId());
        assertThat(response.getGameId()).isEqualTo(gameEncounter1.getGameId());
    }
    @Test
    void removeGameNotFound(){
        GameEncounter response= this.gameService.removeGame(UUID.randomUUID().toString());
        assertThat(response).isNull();
    }
}
