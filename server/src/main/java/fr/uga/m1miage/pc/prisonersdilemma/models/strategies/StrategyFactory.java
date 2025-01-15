package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;


import fr.uga.m1miage.pc.prisonersdilemma.models.strategies.external.*;

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
            case 10 -> new AlwaysBetrayStrategy();
            case 11 -> new AlwaysCooperateStrategy();
            case 12 -> new ResentfulStrategy();
            case 13 -> new PavlovStrategy();
            case 14 -> new PavlovStrategyRandom();
            case 15 -> new AdaptativStrategy();
            case 16 -> new GradualStrategy();
            case 17 -> new DonnantDonnantSuspiciousStrategy();
            case 18 -> new SweetResentfulStrategy();
            case 19 -> new ExternalDonnantDonnantStrategy();
            case 20 -> new ExternalDonnantDonnantRandomStrategy();
            case 21 -> new ExternalDonnantForTwoDonnantsRandomStrategy();
            case 22 -> new ExternalDonnantForTwoDonnantsStrategy();
            case 23 -> new ExternalNaiveSounderStrategy();
            case 24 -> new ExternalRepentantSounderStrategy();
            case 25 -> new ExternalNaivePeaceMakerStrategy();
            case 26 -> new ExternalTruePeacemakerStrategy();
            case 27 -> new ExternalRandomStrategy();
            case 28 -> new ExternalAlwaysBetrayStrategy();
            case 29 -> new ExternalAlwaysCooperateStrategy();
            case 30 -> new ExternalResentfulStrategy();
            case 31 -> new ExternalPavlovStrategy();
            case 32 -> new ExternalPavlovRandomStrategy();
            case 33 -> new ExtenalAdaptativStrategy();
            default -> new RandomStrategy();
        };
    }
}
