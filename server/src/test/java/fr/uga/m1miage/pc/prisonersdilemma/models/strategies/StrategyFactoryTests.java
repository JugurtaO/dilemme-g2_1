package fr.uga.m1miage.pc.prisonersdilemma.models.strategies;

import fr.uga.m1miage.pc.prisonersdilemma.models.strategies.external.*;
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
     @Test
     void getInstance15() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(15) instanceof AdaptativStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance16() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(16) instanceof GradualStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance17() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(17) instanceof DonnantDonnantSuspiciousStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance18() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(18) instanceof SweetResentfulStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance19() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(19) instanceof ExternalDonnantDonnantStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance20() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(20) instanceof ExternalDonnantDonnantRandomStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance21() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(21) instanceof ExternalDonnantForTwoDonnantsRandomStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance22() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(22) instanceof ExternalDonnantForTwoDonnantsStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance23() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(23) instanceof ExternalNaiveSounderStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance24() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(24) instanceof ExternalRepentantSounderStrategy;
         assertTrue(isRightInstance);
     }

     @Test
     void getInstance25() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(25) instanceof ExternalNaivePeaceMakerStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance26() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(26) instanceof ExternalTruePeacemakerStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance27() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(27) instanceof ExternalRandomStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance28() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(28) instanceof ExternalAlwaysBetrayStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance29() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(29) instanceof ExternalAlwaysCooperateStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance30() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(30) instanceof ExternalResentfulStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance31() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(31) instanceof ExternalPavlovStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance32() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(32) instanceof ExternalPavlovRandomStrategy;
         assertTrue(isRightInstance);
     }
     @Test
     void getInstance33() {
         boolean isRightInstance=StrategyFactory.getStrategyInstance(33) instanceof ExtenalAdaptativStrategy;
         assertTrue(isRightInstance);
     }
}
