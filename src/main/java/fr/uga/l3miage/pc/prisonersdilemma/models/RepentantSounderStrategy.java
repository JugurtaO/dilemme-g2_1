package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.List;
import java.util.Random;

public class RepentantSounderStrategy extends Strategy {

    private boolean isThereAnyTestToCheck = false;

    @Override
    boolean play(List<Tour> history, int opponentPlayerNumber) {
        if (history.isEmpty()) {
            return true;
        }
        Tour lastTour = history.get(history.size() - 1);
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
