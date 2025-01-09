package fr.uga.m1miage.pc.prisonersdilemma.dto;

import fr.uga.m1miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.m1miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.m1miage.pc.prisonersdilemma.models.Tour;

import java.util.List;
public record GameMessage(
        String messageType,
        String gameId,
        String playerName1,
        String playerName2,
        String winner,
        String content,
        GameState gameState,
        int nbTours,
        int currentTourNumber,
        List<Tour> history,
        int score1,
        int score2
) {
public GameMessage(String messageType,GameEncounter game,String content){
    this(messageType,game.getGameId(),game.getPlayer1Name(),game.getPlayer2Name(),game.getWinner(),content,game.getGameState(),game.getNbTours(),game.getCurrentTourNumber(),game.getHistory().getAllTours(),game.getPlayer1Score(),game.getPlayer2Score());
}
}
