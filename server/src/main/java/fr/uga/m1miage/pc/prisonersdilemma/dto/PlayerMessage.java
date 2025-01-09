package fr.uga.m1miage.pc.prisonersdilemma.dto;

public record PlayerMessage (
     String messageType,
     String gameId,
     String playerName,
     boolean decision

){}
