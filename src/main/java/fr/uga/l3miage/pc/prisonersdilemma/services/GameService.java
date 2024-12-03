package fr.uga.l3miage.pc.prisonersdilemma.services;


import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Setter
@Getter
public class GameService {


    private final Map<String, GameEncounter> games;

    protected final Map<String, String> waitingPlayers;

    public GameService() {
        games = new ConcurrentHashMap<>();
        waitingPlayers = new ConcurrentHashMap<>();
    }

    public synchronized GameEncounter joinGame(String playerName) {
        for (GameEncounter game : games.values()) {
            if (game.getPlayer1() != null && game.getPlayer2() == null) {
                Player player = new Player(playerName, game);
                player.setRole(PlayerRole.J2);
                game.setPlayer2(player);
                game.setGameState(GameState.GAME_IN_PROGRESS);
                return game;
            }
        }

        Player player = new Player(playerName, null);
        GameEncounter game = new GameEncounter(5, player, null);
        player.setGameEncounter(game);
        player.setRole(PlayerRole.J1);
        game.setGameState(GameState.WAITING_FOR_PLAYER);
        game.setCurrentTourNumber(1);
        games.put(game.getGameId(), game);
        //waitingPlayers.put(playerName, game.getGameId());
        return game;
    }

    public synchronized GameMessage makeDecision(GameEncounter currentGame, Player currentPlayer, boolean playerDecision) {
        Tour currentTour = currentGame.getTourByTourNumber(currentGame.getCurrentTourNumber());
        GameMessage gameMessage;
        if (currentTour == null) { //on crée le tour
            if (currentPlayer.getRole() == PlayerRole.J1) {
                currentGame.getHistory().addTour(playerDecision, null);
                gameMessage = new GameMessage("game.decision", currentGame.getGameId(), currentGame.getPlayer1Name(), currentGame.getPlayer2Name(), currentGame.getWinner(), currentPlayer.getName() + " a joué !", currentGame.getGameState(), currentGame.getNbTours(), currentGame.getCurrentTourNumber(),null,currentGame.getPlayer1().getScore(),currentGame.getPlayer2().getScore());
            } else {
                currentGame.getHistory().addTour(null, playerDecision);
                gameMessage = new GameMessage("game.decision", currentGame.getGameId(), currentGame.getPlayer1Name(), currentGame.getPlayer2Name(), currentGame.getWinner(), currentPlayer.getName() + " a joué !", currentGame.getGameState(), currentGame.getNbTours(), currentGame.getCurrentTourNumber(),null,currentGame.getPlayer1().getScore(),currentGame.getPlayer2().getScore());
            }

        } else { // le tour existe c'est à dire quelqu'un a déjà joué -> vérifier sile joueur rente de jouer si c'est le cas ne rien fair eou l'informer
            if (currentPlayer.getRole() == PlayerRole.J1 && currentTour.getPlayer1Decision() != null || currentPlayer.getRole() == PlayerRole.J2 && currentTour.getPlayer2Decision() != null) { //vérifier que le le joueur qui tente de jouer n'a pas encore joué, si c'est le cas on fait rien
                gameMessage = new GameMessage("game.error", currentGame.getGameId(), currentGame.getPlayer1Name(), currentGame.getPlayer2Name(), currentGame.getWinner(), currentPlayer.getName() + " : Vous avez déjà joué, veuillez patienter que votre adversaire joue !", currentGame.getGameState(), currentGame.getNbTours(), currentGame.getCurrentTourNumber(),currentGame.getHistory().getAllTours(),currentGame.getPlayer1().getScore(),currentGame.getPlayer2().getScore());
            } else {
                if (currentPlayer.getRole() == PlayerRole.J1) {
                    currentTour.setPlayer1Decision(playerDecision);
                    gameMessage = new GameMessage("game.decision", currentGame.getGameId(), currentGame.getPlayer1Name(), currentGame.getPlayer2Name(), currentGame.getWinner(), currentPlayer.getName() + " a joué !", currentGame.getGameState(), currentGame.getNbTours(), currentGame.getCurrentTourNumber(),currentGame.getHistory().getAllTours(),currentGame.getPlayer1().getScore(),currentGame.getPlayer2().getScore());

                } else {
                    currentTour.setPlayer2Decision(playerDecision);
                    gameMessage = new GameMessage("game.decision", currentGame.getGameId(), currentGame.getPlayer1Name(), currentGame.getPlayer2Name(), currentGame.getWinner(), currentPlayer.getName() + " a joué !", currentGame.getGameState(), currentGame.getNbTours(), currentGame.getCurrentTourNumber(),currentGame.getHistory().getAllTours(),currentGame.getPlayer1().getScore(),currentGame.getPlayer2().getScore());

                }
                //Calculer le score des 2 joueurs car ils ont joué tous les 2
                int p1Score = currentGame.getPlayer1().getScore();
                int p2Score = currentGame.getPlayer2().getScore();
                currentGame.getPlayer1().setScore(p1Score + currentTour.getPlayerScore(PlayerRole.J1));
                currentGame.getPlayer2().setScore(p2Score + currentTour.getPlayerScore(PlayerRole.J2));

                //Dernier tour (fin de la partie) -> désigner le gagnant
                if (currentGame.getCurrentTourNumber() == currentGame.getNbTours()) {
                    int p1FinalScore = currentGame.getPlayer1().getScore();
                    int p2FinalScore = currentGame.getPlayer2().getScore();
                    if (p1FinalScore == p2FinalScore) { //Égalité !
                        currentGame.setWinner(null);
                    } else {
                        currentGame.setWinner(p1FinalScore > p2FinalScore ? currentGame.getPlayer1Name() : currentGame.getPlayer2Name());
                    }
                    currentGame.setGameState(GameState.GAME_FINISHED);
                }else {
                    //Passer au  tour suivant.
                    currentGame.setCurrentTourNumber(currentGame.getCurrentTourNumber() + 1);
                }


            }


        }


        return gameMessage;
    }


    public GameEncounter getGame(String gameId) {
        return games.get(gameId);
    }


    public GameEncounter getGameByPlayer(String playerName) {
        return games.values().stream().filter(game -> game.getPlayer1().getName().equals(playerName) || (game.getPlayer2() != null && game.getPlayer2().getName().equals(playerName))).findFirst().orElse(null);
    }


    public GameEncounter removeGame(String gameId) {
        return games.remove(gameId);
    }

}


