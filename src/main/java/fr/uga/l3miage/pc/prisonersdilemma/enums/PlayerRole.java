package fr.uga.l3miage.pc.prisonersdilemma.enums;

public enum PlayerRole {
    J1(),
    J2();

    public PlayerRole opponent() {
        return this == J1 ? J2 : J1;
    }
}
