package fr.uga.l3miage.pc.prisonersdilemma.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinMessage {
    private String type;
    private String gameId;
    private String playerName;
    private String content;
}
