    package fr.uga.l3miage.pc.prisonersdilemma.models;

    import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
    import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
    import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.StrategyFactory;

    import java.util.Objects;
    import java.util.Random;

    public class GameInProgressStateBehaviour implements GameStateBehaviour {
        Random random=new Random();

        @Override
        public GameMessage joinGame(GameEncounter gameEncounter, String playerName) {
            return new GameMessage("game.error",gameEncounter,playerName+ "cannot join. Game is in progress");
        }

        @Override
        public GameMessage leaveGame(GameEncounter gameEncounter, String playerName) {
            if(Objects.equals(gameEncounter.getPlayer1Name(), playerName)){
                gameEncounter.getPlayer1().setAiMode(true);
                gameEncounter.getPlayer1().setStrategy(StrategyFactory.getStrategyInstance(this.random.nextInt(18)));
                return new GameMessage("game.leave",gameEncounter,playerName+" has left the game");
            }
            if(Objects.equals(gameEncounter.getPlayer2Name(), playerName)){
                gameEncounter.getPlayer2().setAiMode(true);
                gameEncounter.getPlayer2().setStrategy(StrategyFactory.getStrategyInstance(this.random.nextInt(18)));
                return new GameMessage("game.leave",gameEncounter,playerName+" has left the game");
            }
            return new GameMessage("game.error",gameEncounter,playerName+" cannot leave the game is not in it");

        }

        @Override
        public GameMessage makeDecision(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision) {
            if(gameEncounter.getPlayer1()==null || gameEncounter.getPlayer2()==null){
                return new GameMessage("game.error",gameEncounter,"Game is in progress without all players");
            }
            Tour currentTour = gameEncounter.getTourByTourNumber(gameEncounter.getCurrentTourNumber());
            GameMessage gameMessage;
            if (currentTour == null) { //on crée le tour
                if (currentPlayer.getRole() == PlayerRole.J1) {
                    gameEncounter.getHistory().addTour(playerDecision, null);
                    gameMessage = new GameMessage("game.decision", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " a joué !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), null, gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());
                } else {
                    gameEncounter.getHistory().addTour(null, playerDecision);
                    gameMessage = new GameMessage("game.decision", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " a joué !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), null, gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());
                }

            } else { // le tour existe c'est à dire quelqu'un a déjà joué -> vérifier sile joueur rente de jouer si c'est le cas ne rien fair eou l'informer
                if (currentPlayer.getRole() == PlayerRole.J1 && currentTour.getPlayer1Decision() != null || currentPlayer.getRole() == PlayerRole.J2 && currentTour.getPlayer2Decision() != null) { //vérifier que le le joueur qui tente de jouer n'a pas encore joué, si c'est le cas on fait rien
                    gameMessage = new GameMessage("game.error", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " : Vous avez déjà joué, veuillez patienter que votre adversaire joue !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), gameEncounter.getHistory().getAllTours(), gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());
                } else {
                    if (currentPlayer.getRole() == PlayerRole.J1) {
                        currentTour.setPlayer1Decision(playerDecision);
                        gameMessage = new GameMessage("game.decision", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " a joué !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), gameEncounter.getHistory().getAllTours(), gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());

                    } else {
                        currentTour.setPlayer2Decision(playerDecision);
                        gameMessage = new GameMessage("game.decision", gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " a joué !", gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), gameEncounter.getHistory().getAllTours(), gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());

                    }
                    //Calculer le score des 2 joueurs car ils ont joué tous les 2
                    int p1Score = gameEncounter.getPlayer1().getScore();
                    int p2Score = gameEncounter.getPlayer2().getScore();
                    gameEncounter.getPlayer1().setScore(p1Score + currentTour.getPlayerScore(PlayerRole.J1));
                    gameEncounter.getPlayer2().setScore(p2Score + currentTour.getPlayerScore(PlayerRole.J2));

                    //Dernier tour (fin de la partie) -> désigner le gagnant
                    if (gameEncounter.getCurrentTourNumber() == gameEncounter.getNbTours()) {
                        int p1FinalScore = gameEncounter.getPlayer1().getScore();
                        int p2FinalScore = gameEncounter.getPlayer2().getScore();
                        if (p1FinalScore == p2FinalScore) { //Égalité !
                            gameEncounter.setWinner(null);
                        } else {
                            gameEncounter.setWinner(p1FinalScore > p2FinalScore ? gameEncounter.getPlayer1Name() : gameEncounter.getPlayer2Name());
                        }
                        gameEncounter.setGameState(fr.uga.l3miage.pc.prisonersdilemma.enums.GameState.GAME_FINISHED);
                    } else {
                        //Passer au  tour suivant.
                        gameEncounter.setCurrentTourNumber(gameEncounter.getCurrentTourNumber() + 1);
                    }


                }

            }
            return gameMessage;
        }
    }
