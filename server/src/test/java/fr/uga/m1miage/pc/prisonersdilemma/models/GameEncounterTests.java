package fr.uga.m1miage.pc.prisonersdilemma.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class GameEncounterTests {
    @Test
    void gameOverFalse(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        assertFalse(gameEncounter.isGameOver());
    }
    @Test
    void gameOverTrue(){
        GameEncounter gameEncounter=new GameEncounter(5,null,null);
        gameEncounter.setWinner("Julien");
        assertTrue(gameEncounter.isGameOver());
    }
}
