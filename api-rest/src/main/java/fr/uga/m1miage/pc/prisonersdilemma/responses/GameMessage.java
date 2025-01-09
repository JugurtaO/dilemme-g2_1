package fr.uga.m1miage.pc.prisonersdilemma.responses;

public record GameMessage(
        String messageType,
        String gameId,
        String playerName1,
        String playerName2,
        String winner,
        String content,
        int nbTours,
        int currentTourNumber,
        int score1,
        int score2
) {

}
