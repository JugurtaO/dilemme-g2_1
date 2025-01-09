package fr.uga.m1miage.pc.prisonersdilemma.enums;

import lombok.Getter;

@Getter
public enum GameState {
    WAITING_FOR_PLAYER("Waiting for player."),
    PLAYER1_WON("Player 1 won."),
    PLAYER2_WON("Player 2 won."),
    GAME_IN_PROGRESS("Game in progress."),
    GAME_FINISHED("Game finished."),
    TIE("Tie.");

    final String description;

    GameState(String description) {
        this.description = description;
    }

}
