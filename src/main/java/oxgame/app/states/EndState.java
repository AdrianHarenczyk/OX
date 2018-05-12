package oxgame.app.states;

import oxgame.app.game.Player;
import oxgame.app.game.ScoreBoard;
import oxgame.app.utility.RoundBuffer;

public class EndState implements GameState {
    private final ScoreBoard scoreBoard;
    private final RoundBuffer players;

    EndState(ScoreBoard scoreBoard, RoundBuffer players) {
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
        return null;
    }
}
