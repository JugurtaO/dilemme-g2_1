package fr.uga.l3miage.pc.prisonersdilemma;

public enum GameState {
    WAITING_FOR_PLAYER("Waiting for player."),
    PLAYER1_WON("Player 1 won."),
    PLAYER2_WON("Player 2 won."),
    TIE("Tie.");

    String description;

    GameState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
