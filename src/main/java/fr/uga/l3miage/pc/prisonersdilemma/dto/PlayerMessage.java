package fr.uga.l3miage.pc.prisonersdilemma.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlayerMessage {
    private String messagetype;
    private int gameId;
    private String playerName;
    private String content;
}
