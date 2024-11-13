package fr.uga.l3miage.pc.prisonersdilemma.models;



public interface PlayerInterface {

    boolean makeDecision(boolean decision);

    int getScore();

    void leaveEncounter();



}
