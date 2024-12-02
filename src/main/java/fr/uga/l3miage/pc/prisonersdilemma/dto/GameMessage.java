package fr.uga.l3miage.pc.prisonersdilemma.dto;

import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;

public record GameMessage(
        String messageType,
        String gameId,
        String playerName1,
        String playerName2,
        String winner,
        String content,
        Boolean decision,
        GameState gameState,
        String sender,
        int nbTours,
        int currentTourNumber
) {

}
