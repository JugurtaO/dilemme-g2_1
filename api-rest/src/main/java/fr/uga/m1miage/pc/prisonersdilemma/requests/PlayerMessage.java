package fr.uga.m1miage.pc.prisonersdilemma.requests;

public record PlayerMessage(
     String messageType,
     String gameId,
     String playerName,
     boolean decision

){}
