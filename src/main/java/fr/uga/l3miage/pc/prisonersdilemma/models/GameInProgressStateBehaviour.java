    package fr.uga.l3miage.pc.prisonersdilemma.models;

    import fr.uga.l3miage.pc.prisonersdilemma.dto.GameMessage;
    import fr.uga.l3miage.pc.prisonersdilemma.enums.GameState;
    import fr.uga.l3miage.pc.prisonersdilemma.enums.PlayerRole;
    import fr.uga.l3miage.pc.prisonersdilemma.models.strategies.StrategyFactory;

    import java.util.Objects;
    import java.util.Random;

    public class GameInProgressStateBehaviour implements GameStateBehaviour {
        public static final String GAME_ERROR = "game.error";
        public static final String GAME_LEFT = "game.left";
        public static final String GAME_DECISION = "game.decision";
        public static final String A_JOUE = "a joué";
        Random random=new Random();

        @Override
        public GameMessage joinGame(GameEncounter gameEncounter, String playerName) {
            return new GameMessage(GAME_ERROR,gameEncounter,playerName+ "cannot join. Game is in progress");
        }

        @Override
        public GameMessage leaveGame(GameEncounter gameEncounter, String playerName,int choosedStrategyNumber) {
            if (isPlayer(gameEncounter.getPlayer1Name(), playerName)) {
                handlePlayerLeave(gameEncounter, gameEncounter.getPlayer1(), gameEncounter.getPlayer2());
                return new GameMessage(GAME_LEFT, gameEncounter, playerName + " has left the game");
            }

            if (isPlayer(gameEncounter.getPlayer2Name(), playerName)) {
                handlePlayerLeave(gameEncounter, gameEncounter.getPlayer2(), gameEncounter.getPlayer1());
                return new GameMessage(GAME_LEFT, gameEncounter, playerName + " has left the game");
            }

            return new GameMessage(GAME_ERROR, gameEncounter, playerName + " cannot leave the game is not in it");
        }

        private boolean isPlayer(String playerNameInGame, String playerName) {
            return Objects.equals(playerNameInGame, playerName);
        }

        private void handlePlayerLeave(GameEncounter gameEncounter, Player leavingPlayer, Player otherPlayer) {
            leavingPlayer.setAiMode(true);
            leavingPlayer.setStrategy(StrategyFactory.getStrategyInstance(this.random.nextInt(18)));

            if (!gameEncounter.getHistory().isEmpty()) {
                Tour currentTour = gameEncounter.getHistory().getLastTour();

                if (currentTour.getPlayerDecision(leavingPlayer.getRole()) == null) {
                    gameEncounter.makeDecision(
                            gameEncounter, leavingPlayer, leavingPlayer.makeDecision(true)
                    );
                }

                if (otherPlayer != null && otherPlayer.isAiMode()) {
                    gameEncounter.setGameState(GameState.GAME_FINISHED);
                    gameEncounter.setGameStateBehaviour(new GameFinishStateBehaviour());
                }
            }
        }


        @Override
        public GameMessage makeDecision(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision) {
            if (gameEncounter.getPlayer1() == null || gameEncounter.getPlayer2() == null) {
                return new GameMessage(GAME_ERROR, gameEncounter, "Game is in progress without all players");
            }

            Tour currentTour = gameEncounter.getTourByTourNumber(gameEncounter.getCurrentTourNumber());
            if (currentTour == null) {
                // Nouveau tour
                return handleNewTour(gameEncounter, currentPlayer, playerDecision);
            } else {
                // Tour existant
                return handleExistingTour(gameEncounter, currentPlayer, playerDecision, currentTour);
            }
        }

        public GameMessage handleNewTour(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision) {
            GameMessage gameMessage;
            if (currentPlayer.getRole() == PlayerRole.J1) {
                if (gameEncounter.getPlayer2().isAiMode()) {
                    gameEncounter.getHistory().addTour(playerDecision, gameEncounter.getPlayer2().makeDecision(true));
                    gameEncounter.setCurrentTourNumber(gameEncounter.getCurrentTourNumber() + 1);
                    gameMessage = new GameMessage(GAME_DECISION, gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " " + A_JOUE, gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), gameEncounter.getHistory().getAllTours(), gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());
                } else {
                    gameEncounter.getHistory().addTour(playerDecision, null);
                    gameMessage = new GameMessage(GAME_DECISION, gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " " + A_JOUE, gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), null, gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());
                }
            } else {
                if (gameEncounter.getPlayer1().isAiMode()) {
                    gameEncounter.getHistory().addTour(gameEncounter.getPlayer1().makeDecision(true), playerDecision);
                    gameEncounter.setCurrentTourNumber(gameEncounter.getCurrentTourNumber() + 1);
                    gameMessage = new GameMessage(GAME_DECISION, gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " " + A_JOUE, gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), gameEncounter.getHistory().getAllTours(), gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());
                } else {
                    gameEncounter.getHistory().addTour(null, playerDecision);
                    gameMessage = new GameMessage(GAME_DECISION, gameEncounter.getGameId(), gameEncounter.getPlayer1Name(), gameEncounter.getPlayer2Name(), gameEncounter.getWinner(), currentPlayer.getName() + " " + A_JOUE, gameEncounter.getGameState(), gameEncounter.getNbTours(), gameEncounter.getCurrentTourNumber(), null, gameEncounter.getPlayer1().getScore(), gameEncounter.getPlayer2().getScore());
                }
            }
            if ((currentPlayer.getRole() == PlayerRole.J1 && gameEncounter.getPlayer2().isAiMode()) || (currentPlayer.getRole() == PlayerRole.J2 && gameEncounter.getPlayer1().isAiMode())) {
                Tour currentCreatedTour = gameEncounter.getTourByTourNumber(gameEncounter.getCurrentTourNumber() -1);
                updateScoresAndCheckGameState(gameEncounter,currentCreatedTour,true);
            }
            return gameMessage;
        }

        public GameMessage handleExistingTour(GameEncounter gameEncounter, Player currentPlayer, boolean playerDecision, Tour currentTour) {
            GameMessage gameMessage;
            if ( !currentPlayer.isAiMode() && (currentPlayer.getRole() == PlayerRole.J1  && currentTour.getPlayer1Decision() != null || currentPlayer.getRole() == PlayerRole.J2 && currentTour.getPlayer2Decision() != null)) { //vérifier que le le joueur qui tente de jouer n'a pas encore joué, si c'est le cas on fait rien
                gameMessage= new GameMessage(GAME_ERROR, gameEncounter, currentPlayer.getName() + " : Vous avez déjà joué, veuillez patienter que votre adversaire joue !");
            } else {
                if (currentPlayer.getRole() == PlayerRole.J1) {
                    currentTour.setPlayer1Decision(playerDecision);
                } else {
                    currentTour.setPlayer2Decision(playerDecision);
                }

                gameMessage=new GameMessage(GAME_DECISION, gameEncounter, currentPlayer.getName() + " " + A_JOUE);
                updateScoresAndCheckGameState(gameEncounter,currentTour,false);


            }
            return gameMessage;
        }
        public void updateScoresAndCheckGameState(GameEncounter gameEncounter, Tour currentCreatedTour, boolean isNewTour) {
            updateScores(gameEncounter, currentCreatedTour);

            if (isGameFinished(gameEncounter, isNewTour)) {
                determineWinner(gameEncounter);
                endGame(gameEncounter);
            } else if (!isNewTour) {
                advanceToNextTour(gameEncounter);
            }
        }

        public void updateScores(GameEncounter gameEncounter, Tour currentCreatedTour) {
            int p1Score = gameEncounter.getPlayer1().getScore();
            int p2Score = gameEncounter.getPlayer2().getScore();
            gameEncounter.getPlayer1().setScore(p1Score + currentCreatedTour.getPlayerScore(PlayerRole.J1));
            gameEncounter.getPlayer2().setScore(p2Score + currentCreatedTour.getPlayerScore(PlayerRole.J2));
        }

        public boolean isGameFinished(GameEncounter gameEncounter, boolean isNewTour) {
            int currentTourNumber = gameEncounter.getCurrentTourNumber();
            return isNewTour
                    ? (currentTourNumber - 1 == gameEncounter.getNbTours())
                    : (currentTourNumber == gameEncounter.getNbTours());
        }

        public void determineWinner(GameEncounter gameEncounter) {
            int p1FinalScore = gameEncounter.getPlayer1().getScore();
            int p2FinalScore = gameEncounter.getPlayer2().getScore();

            if (p1FinalScore == p2FinalScore) {
                gameEncounter.setWinner(null); // Égalité
            } else {
                gameEncounter.setWinner(p1FinalScore > p2FinalScore ? gameEncounter.getPlayer1Name() : gameEncounter.getPlayer2Name());
            }
        }

        public void endGame(GameEncounter gameEncounter) {
            gameEncounter.setGameState(GameState.GAME_FINISHED);
            gameEncounter.setGameStateBehaviour(new GameFinishStateBehaviour());
        }

        public void advanceToNextTour(GameEncounter gameEncounter) {
            gameEncounter.setCurrentTourNumber(gameEncounter.getCurrentTourNumber() + 1);
        }



    }
