package ox.app.states;

import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.utility.RoundBuffer;

public class SummaryState implements GameState {
    private final ScoreBoard scoreBoard;
    private final RoundBuffer players;

    SummaryState(ScoreBoard scoreBoard, RoundBuffer players) {
        this.scoreBoard = scoreBoard;
        this.players = players;
    }

    @Override
    public void showState() {
        Player firstPlayer = players.takePlayer();
        players.swapPlayers();
        Player secondPlayer = players.takePlayer();
        int firstPlayerScore = scoreBoard.getPointOfPlayer(firstPlayer);
        int secondPlayerScore = scoreBoard.getPointOfPlayer(secondPlayer);

    }

    @Override
    public GameState nextState(String input) {
        return new EndState();
    }
}
