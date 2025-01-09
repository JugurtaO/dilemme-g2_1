package fr.uga.m1miage.pc.prisonersdilemma.requests;

public record JoinMessage(
     String messagetype,
     String gameId,
     String playerName,
     String content
){}
