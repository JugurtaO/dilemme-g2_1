package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.StrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameInProgressStateBehaviourTests {
    private
    GameInProgressStateBehaviour stateBehaviour;
    private GameEncounter gameEncounter;

    @BeforeEach
    void setUp() {
        stateBehaviour = new GameInProgressStateBehaviour();
        gameEncounter = new GameEncounter(5,null,null);
    }

    @Test
    void joinGame_ShouldReturnError_WhenGameIsInProgress() {
        // given
        String playerName = "Player1";

        // when
        GameMessage result = stateBehaviour.joinGame(gameEncounter, playerName);

        // then
        assertEquals("game.error", result.messageType());

    }

    @Test
    void leaveGame_ShouldSetPlayer1ToAi_WhenPlayer1Leaves() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.getHistory().addTour(true,false);
        String playerName = "Player1";

        // when
        GameMessage result = stateBehaviour.leaveGame(gameEncounter, playerName,1);

        // then
        assertTrue(gameEncounter.getPlayer1().isAiMode());
        assertNotNull(gameEncounter.getPlayer1().getStrategy());
        assertEquals("game.left", result.messageType());
    }

    @Test
    void leaveGame_ShouldSetPlayer2ToAi_WhenPlayer2Leaves() {
        // given
        Player player2 = new Player("Player2", gameEncounter);
        gameEncounter.setPlayer2(player2);
        gameEncounter.getHistory().addTour(true,false);

        String playerName = "Player2";

        // when
        GameMessage result = stateBehaviour.leaveGame(gameEncounter, playerName,1);

        // then
        assertTrue(gameEncounter.getPlayer2().isAiMode());
        assertNotNull(gameEncounter.getPlayer2().getStrategy());
        assertEquals("game.left", result.messageType());
    }

    @Test
    void leaveGame_ShouldReturnError_WhenPlayerIsNotInGame() {
        // given
        gameEncounter.setPlayer1(new Player("Player1", gameEncounter));
        gameEncounter.setPlayer2(new Player("Player2", gameEncounter));
        String playerName = "Player3";

        // when
        GameMessage result = stateBehaviour.leaveGame(gameEncounter, playerName,1);

        // then
        assertEquals("game.error", result.messageType());

    }

    @Test
    void makeDecision_ShouldReturnError_WhenPlayersAreMissing() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        gameEncounter.setPlayer1(player1); // Player2 is missing

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player1, true);

        // then
        assertEquals("game.error", result.messageType());
    }

    @Test
    void makeDecision_ShouldAddPlayer1Decision_WhenNoCurrentTourExists() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        Player player2 = new Player("Player2", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setCurrentTourNumber(1);

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player1, true);

        // then
        assertEquals("game.decision", result.messageType());
        assertNotNull(gameEncounter.getHistory().getLastTour());
        assertTrue(gameEncounter.getHistory().getLastTour().getPlayer1Decision());
    }

    @Test
    void makeDecision_ShouldAddPlayer2Decision_WhenPlayer1HasAlreadyPlayed() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        Player player2 = new Player("Player2", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setCurrentTourNumber(1);

        // Player1 makes the first decision
        stateBehaviour.makeDecision(gameEncounter, player1, true);

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player2, false);

        // then
        assertEquals("game.decision", result.messageType());
        assertNotNull(gameEncounter.getHistory().getLastTour());
        assertFalse(gameEncounter.getHistory().getLastTour().getPlayer2Decision());
    }

    @Test
    void makeDecision_ShouldReturnError_WhenPlayerTriesToPlayTwiceInSameTour() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setCurrentTourNumber(1);

        // Player1 makes the first decision
        stateBehaviour.makeDecision(gameEncounter, player1, true);

        // when
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player1, true);

        // then
        assertEquals("game.error", result.messageType());
    }

    @Test
    void makeDecision_ShouldEndGame_WhenLastTourIsCompleted() {
        // given
        Player player1 = new Player("Player1", gameEncounter);
        Player player2 = new Player("Player2", gameEncounter);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setNbTours(1); // One-round game
        gameEncounter.setCurrentTourNumber(1);

        // Player1 and Player2 make decisions
        stateBehaviour.makeDecision(gameEncounter, player1, true);
        GameMessage result = stateBehaviour.makeDecision(gameEncounter, player2, false);

        // then
        assertEquals("game.decision", result.messageType());
        assertEquals(fr.uga.l3miage.pc.prisonersdilemma.enums.GameState.GAME_FINISHED, gameEncounter.getGameState());
        assertNotNull(gameEncounter.getWinner());
    }

    @Test
    void handleNewTourJ1_With_Player2_Not_AI(){
        Player player1 = new Player("Player1", gameEncounter);
        player1.setRole(PlayerRole.J1);
        Player player2 = new Player("Player2", gameEncounter);
        player2.setRole(PlayerRole.J2);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setCurrentTourNumber(1);
        GameMessage expectedMessage = new GameMessage("game.decision", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), player1.getName() + " " + "a joué !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), null, gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());

        GameMessage result=stateBehaviour.handleNewTour(gameEncounter,player1,true);
        assertEquals(expectedMessage.gameId(),result.gameId());
        assertNull(result.history());
    }
    @Test
    void handleNewTourJ1_With_Player2_as_AI(){
        Player player1 = new Player("Player1", gameEncounter);
        player1.setRole(PlayerRole.J1);
        Player player2 = new Player("Player2", gameEncounter);
        player2.setRole(PlayerRole.J2);
        player2.setAiMode(true);
        player2.setStrategy(StrategyFactory.getStrategyInstance(16));
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setCurrentTourNumber(1);
        GameMessage expectedMessage = new GameMessage("game.decision", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), player1.getName() + " " + "a joué !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), gameEncounter.getHistory().getAllTours(), gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());

        GameMessage result=stateBehaviour.handleNewTour(gameEncounter,player1,true);
        assertEquals(expectedMessage.gameId(),result.gameId());
        assertNotNull(result.history());
        assertEquals(2,gameEncounter.getCurrentTourNumber());
    }
    @Test
    void handleNewTourJ2_With_Player1_Not_AI(){
        Player player1 = new Player("Player1", gameEncounter);
        player1.setRole(PlayerRole.J1);
        Player player2 = new Player("Player2", gameEncounter);
        player2.setRole(PlayerRole.J2);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setCurrentTourNumber(1);
        GameMessage expectedMessage = new GameMessage("game.decision", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), player1.getName() + " " + "a joué !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), null, gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());

        GameMessage result=stateBehaviour.handleNewTour(gameEncounter,player2,true);
        assertEquals(expectedMessage.gameId(),result.gameId());
        assertNull(result.history());
    }
    @Test
    void handleNewTourJ2_With_Player1_as_AI(){
        Player player1 = new Player("Player1", gameEncounter);
        player1.setRole(PlayerRole.J1);
        player1.setAiMode(true);
        player1.setStrategy(StrategyFactory.getStrategyInstance(16));
        Player player2 = new Player("Player2", gameEncounter);
        player2.setRole(PlayerRole.J2);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        gameEncounter.setCurrentTourNumber(1);
        GameMessage expectedMessage = new GameMessage("game.decision", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), player1.getName() + " " + "a joué !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), gameEncounter.getHistory().getAllTours(), gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());

        GameMessage result=stateBehaviour.handleNewTour(gameEncounter,player2,true);
        assertEquals(expectedMessage.gameId(),result.gameId());
        assertNotNull(result.history());
        assertEquals(2,gameEncounter.getCurrentTourNumber());
    }

    @Test
    void handleExistingTourJ1_as_Not_AI(){
        gameEncounter.getHistory().addTour(true,null);
        gameEncounter.setCurrentTourNumber(1);
        Tour currentTour=gameEncounter.getHistory().getLastTour();
        Player player1 = new Player("Player1", gameEncounter);
        player1.setRole(PlayerRole.J1);
        Player player2 = new Player("Player2", gameEncounter);
        player2.setRole(PlayerRole.J2);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        GameMessage expectedResponse = new GameMessage("game.error", gameEncounter, player1.getName() + " : Vous avez déjà joué, veuillez patienter que votre adversaire joue !");
        GameMessage result=stateBehaviour.handleExistingTour(gameEncounter,player1,true,currentTour);

        assertEquals(expectedResponse.gameId(),result.gameId());
        assertEquals(expectedResponse.messageType(),result.messageType());
    }


    @Test
    void handleExistingTourJ2_as_Not_AI(){
        gameEncounter.getHistory().addTour(false,true);
        gameEncounter.setCurrentTourNumber(1);
        Tour currentTour=gameEncounter.getHistory().getLastTour();
        Player player1 = new Player("Player1", gameEncounter);
        player1.setRole(PlayerRole.J1);
        Player player2 = new Player("Player2", gameEncounter);
        player2.setRole(PlayerRole.J2);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        GameMessage expectedResponse = new GameMessage("game.error", gameEncounter, player2.getName() + " : Vous avez déjà joué, veuillez patienter que votre adversaire joue !");
        GameMessage result=stateBehaviour.handleExistingTour(gameEncounter,player2,true,currentTour);

        assertEquals(expectedResponse.gameId(),result.gameId());
        assertEquals(expectedResponse.messageType(),result.messageType());
    }
    @Test
    void handleExistingTourJ1_as_AI(){
        gameEncounter.getHistory().addTour(true,false);
        gameEncounter.setCurrentTourNumber(1);
        Player player1 = new Player("Player1", gameEncounter);
        player1.setRole(PlayerRole.J1);
        player1.setAiMode(true);
        player1.setStrategy(StrategyFactory.getStrategyInstance(16));
        Player player2 = new Player("Player2", gameEncounter);
        player2.setRole(PlayerRole.J2);
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        Tour currentTour=gameEncounter.getHistory().getLastTour();
        GameMessage expectedResponse =new GameMessage("game.decision", gameEncounter, player1.getName() + "  a joué !" );
        GameMessage result=stateBehaviour.handleExistingTour(gameEncounter,player1,true,currentTour);

        assertEquals(expectedResponse.gameId(),result.gameId());
        assertEquals(expectedResponse.messageType(),result.messageType());

    }
    @Test
    void handleExistingTourJ2_as_AI(){
        gameEncounter.getHistory().addTour(true,false);
        gameEncounter.setCurrentTourNumber(1);
        Player player1 = new Player("Player1", gameEncounter);
        player1.setRole(PlayerRole.J1);
        Player player2 = new Player("Player2", gameEncounter);
        player2.setRole(PlayerRole.J2);
        player2.setAiMode(true);
        player2.setStrategy(StrategyFactory.getStrategyInstance(16));
        gameEncounter.setPlayer1(player1);
        gameEncounter.setPlayer2(player2);
        Tour currentTour=gameEncounter.getHistory().getLastTour();
        GameMessage expectedResponse =new GameMessage("game.decision", gameEncounter, player2.getName() + "  a joué !" );
        GameMessage result=stateBehaviour.handleExistingTour(gameEncounter,player2,true,currentTour);

        assertEquals(expectedResponse.gameId(),result.gameId());
        assertEquals(expectedResponse.messageType(),result.messageType());

    }
}
