package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.DonnantForTwoDonnantsRandomStrategy;
import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.Strategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PlayerTests {

    private  final Strategy strategyMock=mock(DonnantForTwoDonnantsRandomStrategy.class);
    @Test void makeDecionTrue(){
    Player player=new Player("Julien",null);
        GameEncounter gameEncounter=new GameEncounter(5,player,null);
        player.setGameEncounter(gameEncounter);
        boolean response=player.makeDecision(true);
        assertTrue(response);
    }
    @Test void makeDecionFalse(){
        Player player=new Player("Julien",null);
        GameEncounter gameEncounter=new GameEncounter(5,player,null);
        player.setGameEncounter(gameEncounter);
        boolean response=player.makeDecision(false);
        assertFalse(response);
    }
    @Test void makeDecionAsAiFalse(){
        Player player=new Player("Julien",null);
        player.setAiMode(true);
        player.setStrategy(strategyMock);
        GameEncounter gameEncounter=new GameEncounter(5,player,null);
        player.setGameEncounter(gameEncounter);
        when(strategyMock.play(new History(),1)).thenReturn(false);
        boolean response=player.makeDecision(true);
        assertFalse(response);
    }

    @Test
    void leaveEncounter(){
        Player player=new Player("Julien",null);
        GameEncounter gameEncounter=new GameEncounter(5,player,null);
        player.setGameEncounter(gameEncounter);
        player.leaveEncounter();
        assertTrue(player.isAiMode());
    }

}
