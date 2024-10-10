package fr.uga.l3miage.pc.prisonersdilemma;
import fr.uga.l3miage.pc.prisonersdilemma.models.GameEncounter;
import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;


public class Main {
    public static void main(String[] args) {
        int nbTours= Utils.askPlayer1ForNbTours();

        String name1= Utils.askPlayerForName(1);
        Player player1=new Player(name1,null);
        String name2= Utils.askPlayerForName(2);
        Player player2=new Player(name2,null);

        GameEncounter gameEncounter=new GameEncounter(nbTours,player1,player2);
        player1.setGameEncounter(gameEncounter);
        player2.setGameEncounter(gameEncounter);

        gameEncounter.start();

    }
}