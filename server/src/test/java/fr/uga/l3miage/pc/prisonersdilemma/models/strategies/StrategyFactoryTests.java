package fr.uga.l3miage.pc.prisonersdilemma.models.strategies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

 class StrategyFactoryTests {

    @Test
    void getInstance2() {
        boolean isRightInstance=StrategyFactory.getStrategyInstance(2) instanceof DonnantDonnantRandomStrategy;
       assertTrue(isRightInstance);
    }
    @Test
    void getInstance3() {
        boolean isRightInstance=StrategyFactory.getStrategyInstance(3) instanceof DonnantForTwoDonnantsRandomStrategy;
        assertTrue(isRightInstance);
    }
    @Test
    void getInstance5() {
        boolean isRightInstance=StrategyFactory.getStrategyInstance(5) instanceof NaiveSounderStrategy;
        assertTrue(isRightInstance);
    }
    @Test
    void getInstance6() {
        boolean isRightInstance=StrategyFactory.getStrategyInstance(6) instanceof RepentantSounderStrategy;
        assertTrue(isRightInstance);
    }
    @Test
    void getInstance7() {
        boolean isRightInstance=StrategyFactory.getStrategyInstance(7) instanceof NaivePeaceMakerStrategy;
        assertTrue(isRightInstance);
    }
    @Test
    void getInstance8() {
        boolean isRightInstance=StrategyFactory.getStrategyInstance(8) instanceof TruePeaceMakerStrategy;
        assertTrue(isRightInstance);
    }
    @Test
    void getInstance9() {
        boolean isRightInstance=StrategyFactory.getStrategyInstance(9) instanceof RandomStrategy;
        assertTrue(isRightInstance);
    }
    @Test
    void getInstance14() {
        boolean isRightInstance=StrategyFactory.getStrategyInstance(14) instanceof PavlovStrategyRandom;
        assertTrue(isRightInstance);
    }
}
