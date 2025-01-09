package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;


public class StrategyFactory {
    private StrategyFactory() {}
    public static Strategy getStrategyInstance(int n) {
        return switch (n) {
            case 1 -> new DonnantDonnantStrategy();
            case 2 -> new DonnantDonnantRandomStrategy();
            case 3 -> new DonnantForTwoDonnantsRandomStrategy();
            case 4 -> new DonnantForTwoDonnantsStrategy();
            case 5 -> new NaiveSounderStrategy();
            case 6 -> new RepentantSounderStrategy();
            case 7 -> new NaivePeaceMakerStrategy();
            case 8 -> new TruePeaceMakerStrategy();
            case 9 -> new RandomStrategy();
            case 10 -> new AlwaysBetrayStrategy();
            case 11 -> new AlwaysCooperateStrategy();
            case 12 -> new ResentfulStrategy();
            case 13 -> new PavlovStrategy();
            case 14 -> new PavlovStrategyRandom();
            case 15 -> new AdaptativStrategy();
            case 16 -> new GradualStrategy();
            case 17 -> new DonnantDonnantSuspiciousStrategy();
            case 18 -> new SweetResentfulStrategy();
            default -> new RandomStrategy();
        };
    }
}
