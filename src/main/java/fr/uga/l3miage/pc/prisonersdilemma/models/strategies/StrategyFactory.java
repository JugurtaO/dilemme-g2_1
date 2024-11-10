package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;


public class StrategyFactory {

    public static Strategy getStrategyInstance(int n) {
        return switch (n) {
            case 1 -> new DonnantDonnantStrategy();
            case 2 -> new DonnantDonnantRandomStrategy();
            case 3 -> new DonnantForTwoDonnantsStrategy();
            case 4 -> new DonnantForTwoDonnantsRandomStrategy();
            case 5 -> new NaiveSounderStrategy();
            case 6 -> new RepentantSounderStrategy();
            case 7 -> new NaivePeaceMakerStrategy();
            case 8 -> new TruePeaceMakerStrategy();
            case 9 -> new RandomStrategy();
            default -> new RandomStrategy();
        };
    }
}
