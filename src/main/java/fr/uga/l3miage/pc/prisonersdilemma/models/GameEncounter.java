package fr.uga.l3miage.pc.prisonersdilemma.models;

import fr.uga.l3miage.pc.prisonersdilemma.models.states.GameEncounterStateInterface;
import fr.uga.l3miage.pc.prisonersdilemma.models.states.WaitingState;
import fr.uga.l3miage.pc.prisonersdilemma.utils.Utils;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public  class GameEncounter {
    private static final Logger LOGGER = Logger.getLogger(GameEncounter.class.getName());

    private int nbTours;
    private Player player1;
    private Player player2;
    private GameEncounterStateInterface state;
    private WebSocketSession player1Session;
    private WebSocketSession player2Session;
    private WebSocketSession currentSession;

    private List<Tour> history;



    public GameEncounter(int n,Player p1,Player p2){
        this.nbTours=n;
        this.player1=p1;
        this.player2=p2;
        this.history=new ArrayList<>();
        this.state = new WaitingState();
    }

    public void setPlayer1Session(WebSocketSession session) {
        this.player1Session = session;
    }

    public void setPlayer2Session(WebSocketSession session) {
        this.player2Session = session;
    }

    public WebSocketSession getPlayer1Session() {
        return player1Session;
    }

    public WebSocketSession getPlayer2Session() {
        return player2Session;
    }

    public void setCurrentSession(WebSocketSession session) {
        this.currentSession = session;
    }

    public WebSocketSession getCurrentSession() {
        return currentSession;
    }

    public void setState(GameEncounterStateInterface state) {
        this.state = state;
    }

    public void joinGame() throws IOException {
        state.joinGame(this);
    }

    public void startGame() throws IOException {
        state.startGame(this);
    }

    public void endGame() throws IOException {
        state.endGame(this);
    }

    public List<Tour> getHistory(){
        return history;
    }

    public String getPlayer1Name(){
        return player1.getName();
    }

    public void start(){

        for (int i = 1; i <=nbTours ; i++) {
           LOGGER.info(String.format("******** TOUR %d *********",i));

           if(!player1.getAiMode()){
               Utils.playerLeaveGameHandler(player1);
           }
            boolean player1Decision=player1.makeDecision();
           LOGGER.info(String.format("# Le joueur 1 a jou�, c'est votre tour %s ------",player2.getName()));

            if(!player2.getAiMode()){
                Utils.playerLeaveGameHandler(player2);
            }
            boolean player2Decision=player2.makeDecision();
           LOGGER.info(String.format("# Le joueur 2 a jou�, c'est votre tour %s------",player1.getName()));


            Tour tour= new Tour(i,player1Decision,player2Decision);
            history.add(tour);
            Utils.calculateScores(player1,player1Decision,player2,player2Decision);
            Utils.displayTourNumberAndScores(i,player1,player2);
        }

    }



}
