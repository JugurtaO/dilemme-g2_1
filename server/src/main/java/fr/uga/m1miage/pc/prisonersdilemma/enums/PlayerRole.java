package fr.uga.m1miage.pc.prisonersdilemma.enums;

public enum PlayerRole {
    J1(),
    J2();

    public PlayerRole opponent() {
        return this == J1 ? J2 : J1;
    }
}
