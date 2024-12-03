package fr.uga.l3miage.pc.prisonersdilemma.dto;

import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

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

}
