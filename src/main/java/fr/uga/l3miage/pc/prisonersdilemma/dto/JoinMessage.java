package fr.uga.l3miage.pc.prisonersdilemma.dto;

public record JoinMessage (
     String messagetype,
     String gameId,
     String playerName,
     String content
){}
