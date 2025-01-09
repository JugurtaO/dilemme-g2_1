package fr.uga.m1miage.pc.prisonersdilemma.models;



public interface PlayerInterface {

    boolean makeDecision(boolean decision);

    int getScore();

    void leaveEncounter();



}
