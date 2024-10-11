package fr.uga.l3miage.pc.prisonersdilemma.utils;

import fr.uga.l3miage.pc.prisonersdilemma.models.Player;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());
    private Utils(){}




    public static void displayStrategiesMenu() {
        System.out.println("Please choose one STRATEGY : ");
        System.out.println("1. Donnant donnant");
        System.out.println("2. Donnant donnant / random");
        System.out.println("3. Donnant for two donnants / random");
        System.out.println("4. Donnant for two donnants");
        System.out.println("5. Naive Sounder");
        System.out.println("6. Repentant Sounder");
        System.out.println("7. Naive peacemaker");
        System.out.println("8. True peacemaker");
        System.out.println("9. Random");
        // System.out.println("10. Always betray");
        // System.out.println("11. Always cooperate");
        // System.out.println("12. Resentful");
        // System.out.println("13. Pavlov");
        // System.out.println("14. Pavlov / random");
        // System.out.println("15. Adaptive");
        // System.out.println("16. Gradual");
        // System.out.println("17. Suspicious donnant donnant");
        // System.out.println("18. Sweat Resentful");
    }

    public static int chooseStrategy(String name) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        System.out.println("Player " + name + " , choose strategy number : ");
        int strategyNumber = scanner.nextInt();
        while (strategyNumber < 1 || strategyNumber > 18) {
            System.out.println("Enter a valid strategy number [1 - 18 ] : ");
            strategyNumber = scanner.nextInt();
        }

        return strategyNumber;
    }

    public static Tour[] getLastTwoTurns(List<Tour> history) {
        Tour[] lastTwo = new Tour[2];
        Iterator<Tour> iterator = history.iterator();
        Tour previousTour = null;
        Tour lastTour = null;

        while (iterator.hasNext()) {
            previousTour = lastTour;
            lastTour = iterator.next();
        }

        lastTwo[0] = previousTour;
        lastTwo[1] = lastTour;
        return lastTwo;
    }

    public  static String askPlayerForName(int playerNum){
        Scanner scanner=new Scanner(System.in);
        LOGGER.info(String.format("Joueur %d, saisissez votre pseudo !",playerNum));
        LOGGER.info(" Pseudo : ");
        String name=scanner.nextLine();
        return name;

    }

    public static int askPlayer1ForNbTours() {
        Scanner scanner1 = new Scanner(System.in);
        LOGGER.info("Joueur 1, veuillez saisir le nombre de tours à jouer !");
        LOGGER.info("Nombre de tours : ");
        int nbTours = scanner1.nextInt();
        while (nbTours < 0) {
            LOGGER.info("Le nombre de tours doit être positif : ");
            nbTours = scanner1.nextInt();
        }

        return nbTours;
    }

    public static void calculateScores(Player p1, boolean player1Decision, Player p2, boolean player2Decision) {
        if (player1Decision && player2Decision) {
            p1.updateScore(3);
            p2.updateScore(3);
        } else if (player1Decision) {
            p2.updateScore(5);

        } else if (player2Decision) {
            p1.updateScore(5);
        } else {
            p1.updateScore(1);
            p2.updateScore(1);
        }
    }

    public static void displayTourNumberAndScores(int tourNum,Player player1,Player player2){
        LOGGER.info("***************************");
        LOGGER.info(String.format("*** TOUR %d ***",tourNum));
        LOGGER.info( String.format("%s : %d",player1.getName(),player1.getScore()));
        LOGGER.info( String.format("%s : %d",player2.getName(),player2.getScore()));
        LOGGER.info("***************************");
    }

    public static void playerLeaveGameHandler(Player player){
        Scanner scanner=new Scanner(System.in);
        LOGGER.info(String.format("%s , voullez-vous quitter ? YES/NO",player.getName()));
        LOGGER.info(" Réponse : ");
        String repsonse=scanner.nextLine();

        while(!repsonse.toLowerCase().equals("yes") && !repsonse.toLowerCase().equals("no")){
            LOGGER.info("Enter a valide response YES/NO: ");

            repsonse = scanner.nextLine();
        }

        if (repsonse.equals("yes")) {
            player.leaveEncounter();
        }

    }
}
