package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.History;
import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.Random;

public class RepentantSounderStrategy implements Strategy {

    private boolean isThereAnyTestToCheck = false;

    @Override
    public boolean play(History history, int opponentPlayerNumber) {
        return history.isEmpty() || getRepentantDecision(history, opponentPlayerNumber);


    }
    private boolean getRepentantDecision(History history, int opponentPlayerNumber) {
        Tour lastTour = history.getLastTour();
        boolean opponentDecision = opponentPlayerNumber == 1 ? lastTour.getPlayer1Decision() : lastTour.getPlayer2Decision();

        if (isThereAnyTestToCheck) {
            isThereAnyTestToCheck = false;
            if (!opponentDecision) {
                return true;
            }
        }
        if (!opponentDecision)
            return false;

        //play sometimes randomly
        int k = getRandomInstance().nextInt(5) + 1;
        if (k != 2) {
            return true;
        } else {
            isThereAnyTestToCheck = true;
            return false;
        }

    }

    public Random getRandomInstance() {
        return new Random();
    }

    public void setThereAnyTestToCheck(boolean value) {
        isThereAnyTestToCheck = value;
    }

}
