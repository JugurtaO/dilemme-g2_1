package fr.uga.l3miage.pc.prisonersdilemma.dto;

public record PlayerMessage (
     String messagetype,
     String gameId,
     String playerName,
     Boolean decision

){}
