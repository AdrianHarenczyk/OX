package ox.app.states;

import ox.app.game.ScoreBoard;

public class SummaryState implements GameState {
    private final ScoreBoard scoreBoard;

    SummaryState(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    @Override
    public void showState() {
        scoreBoard.showTheWinner();

    }

    @Override
    public GameState nextState(String input) {
        return new EndState();
    }
}
