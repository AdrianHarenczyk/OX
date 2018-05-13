package ox.app.states;

import ox.app.game.ScoreBoard;

public class SummaryState implements GameState {
    private final ScoreBoard scoreBoard;
    private static final String ENDING_MESSAGE = "\nTo exit application, press enter.";

    SummaryState(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    @Override
    public void showState() {
        scoreBoard.showTheWinner();
        printEndingCommand();
    }

    @Override
    public GameState nextState(String input) {
        return new EndState();
    }

    private void printEndingCommand() {
        System.out.println(ENDING_MESSAGE);
    }
}
