package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    public boolean playerHasBetray(PlayerRole playerRole){
        return tours.stream().noneMatch(tour -> !tour.getPlayerDecision(playerRole));
    }
    public List<Tour> getCooperateToursOf(PlayerRole playerRole){
        return tours.stream().filter(tour -> tour.getPlayerDecision(playerRole)).toList();
    }

    public List<Tour> getBetrayToursOf(PlayerRole playerRole){
        return tours.stream().filter(tour->!tour.getPlayerDecision(playerRole)).toList();
    }

    public double getAverageBetrayScore(PlayerRole playerRole) {
        List<Tour> betrayTurns = this.getBetrayToursOf(playerRole);
        int sum = betrayTurns.stream()
                .mapToInt(tour -> tour.getPlayerScore(playerRole))
                .sum(); // Somme des scores
        double average = 0;
        if (!betrayTurns.isEmpty()) {
            average = (double) sum / betrayTurns.size();
        }
        return average;
    }

    public double getAverageCooperateScore(PlayerRole playerRole) {
        List<Tour> cooperateTurns = this.getCooperateToursOf(playerRole);
        int sum = cooperateTurns.stream()
                .mapToInt(tour -> tour.getPlayerScore(playerRole))
                .sum(); // Somme des scores
        double average = 0;
        if (!cooperateTurns.isEmpty()) {
            average = (double) sum / cooperateTurns.size();
        }
        return average;
    }


}
