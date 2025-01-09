package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.enums.PlayerRole;
import fr.uga.m1miage.pc.prisonersdilemma.models.History;
import fr.uga.m1miage.pc.prisonersdilemma.models.Tour;

import java.util.ArrayDeque;
import java.util.Deque;

public class GradualStrategy implements Strategy {
    private final Deque<Boolean> playLeft = new ArrayDeque<>();

    @Override
    public boolean play(History history, PlayerRole playerRole) {
        return history.isEmpty() || isPlayLeft(history, playerRole);

    }
    public boolean isPlayLeft(History history, PlayerRole playerRole) {
        if (!playLeft.isEmpty()) {
            return playLeft.pop();
        }
        Tour lastTour = history.getLastTour();
        boolean opponentDecision = lastTour.getPlayerDecision(playerRole.opponent());
        if (opponentDecision) {
            return true;
        } else {
            int betrayByOpponentCount=history.getBetrayToursOf(playerRole.opponent()    ).size();
            playLeft.push(true);
            playLeft.push(true);
            for (int i = 0; i < betrayByOpponentCount - 1; i++) {
                playLeft.push(false);
            }
            return false;
        }

    }

}
