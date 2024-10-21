package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import fr.uga.l3miage.pc.prisonersdilemma.models.Tour;

import java.util.List;

public interface Strategy {
     boolean play(List<Tour> history, int opponentPlayerNumber);

}
