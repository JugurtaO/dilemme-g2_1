package fr.uga.l3miage.pc.prisonersdilemma.models;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<Tour> tours = new ArrayList<>();

    public  void addTour(boolean player1Decision,boolean player2Decision){
        tours.add(new Tour(tours.size()+1,player1Decision,player2Decision));
    }

    public Boolean isEmpty(){
        return tours.isEmpty();
    }

    public int size(){
        return tours.size();
    }

    public List<Tour> getAllTours() {return tours;}

    public Tour getLastTour() {return tours.get(tours.size()-1);}

    public List<Tour> getLastTours(int numberTour) {
        if (numberTour <= 0 || numberTour > tours.size()) {
            return new ArrayList<>();
        }
        List<Tour> lastTours = new ArrayList<>();
        for (int i = tours.size() - numberTour; i < tours.size(); i++) {
            lastTours.add(tours.get(i));
        }
        return lastTours;
    }
    public boolean playerHasBetray(int player){
        return tours.stream().noneMatch(tour -> player==1? !tour.getPlayer1Decision() : !tour.getPlayer2Decision());
    }
    public List<Tour> getCooperateToursOf(int player){
        return tours.stream().filter(tour -> player==1?tour.getPlayer1Decision():tour.getPlayer2Decision()).toList();
    }

    public List<Tour> getBetrayToursOf(int player){
        return tours.stream().filter(tour -> player==1? !tour.getPlayer1Decision():!tour.getPlayer2Decision()).toList();
    }

    public double getAverageBetrayScore(int player) {
        List<Tour> betrayTurns = this.getBetrayToursOf(player);
        int sum = betrayTurns.stream()
                .mapToInt(tour -> tour.getPlayerScore(player))
                .sum(); // Somme des scores
        double average = 0;
        if (!betrayTurns.isEmpty()) {
            average = (double) sum / betrayTurns.size();
        }
        return average;
    }

    public double getAverageCooperateScore(int player) {
        List<Tour> cooperateTurns = this.getCooperateToursOf(player);
        int sum = cooperateTurns.stream()
                .mapToInt(tour -> tour.getPlayerScore(player))
                .sum(); // Somme des scores
        double average = 0;
        if (!cooperateTurns.isEmpty()) {
            average = (double) sum / cooperateTurns.size();
        }
        return average;
    }


}
