package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

public class StrategyFactory {

    public static Strategy getStrategyInstance(int n) {
        switch (n) {
            case 1:
                return new DonnantDonnantStrategy();

            case 2:
                return new DonnantDonnantRandomStrategy();

            case 3:
                return new DonnantForTwoDonnantsStrategy();
            case 4:
                return new DonnantForTwoDonnantsRandomStrategy();
            case 5:
                return new NaiveSounderStrategy();
            case 6:
                return new RepentantSounderStrategy();
            case 7:
                return new NaivePeaceMakerStrategy();
            case 8:
                return new TruePeaceMakerStrategy();
            case 9:
                return new RandomStrategy();
            default:
                return new RandomStrategy();
        }
    }
}
