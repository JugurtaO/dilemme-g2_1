package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tour {
    private final int tourNumber;
    private  Boolean player1Decision;
    private  Boolean player2Decision;

    public Tour(int tourNumber, Boolean decision1, Boolean decision2){
        this.tourNumber = tourNumber;
        this.player1Decision = decision1;
        this.player2Decision = decision2;
    }

    public Boolean getPlayerDecision(PlayerRole playerRole){
        return playerRole==PlayerRole.J1?player1Decision:player2Decision;
    }
    public int getPlayerScore(PlayerRole playerRole) {
        boolean playerDecision=getPlayerDecision(playerRole);
        boolean opponentDecision=getPlayerDecision(playerRole.opponent());
        if (playerDecision && opponentDecision) {
            return 0;
        }
        if (!playerDecision && opponentDecision) {
            return 5;
        }
        if (playerDecision) {
            return 3;
        }
        return 1;

    }
}
