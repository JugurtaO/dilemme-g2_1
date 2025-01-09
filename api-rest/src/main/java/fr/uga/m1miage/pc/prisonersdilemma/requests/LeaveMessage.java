package fr.uga.m1miage.pc.prisonersdilemma.requests;

public record LeaveMessage(String messageType, String gameId, String playerName, int choosedStrategyNumber) {
}
