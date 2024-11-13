package fr.uga.l3miage.pc.prisonersdilemma.models;

public class Tour {
    private final int tourNumber;
    private final boolean player1Decision;
    private final boolean player2Decision;

    public Tour(int tourNumber, boolean decision1, boolean decision2){
        this.tourNumber = tourNumber;
        this.player1Decision = decision1;
        this.player2Decision = decision2;
    }

public int getTourNumber() {return this.tourNumber;}
    public boolean getPlayer1Decision() {
        return player1Decision;
    }
    public boolean getPlayer2Decision() {
        return player2Decision;
    }
    public int getPlayerScore(int idPlayer) {

        if (player1Decision && !player2Decision) {
            return idPlayer == 2 ? 5 : 0;
        }
        if (!player1Decision && player2Decision) {
            return idPlayer == 1 ? 5 : 0;
        }
        if (player1Decision) {
            return 3;
        }
        return 1;

    }
}
