package oxgame.app;

public class PreEndState implements GameState {

    private RoundBuffer playerBuffer;
    private Player winningPlayer;
    private ScoreBoard scoreBoard;

    public PreEndState(Player winningPlayer, RoundBuffer playerBuffer) {
        this.playerBuffer = playerBuffer;
        this.winningPlayer = winningPlayer;
        System.out.println(winningPlayer + " wins the game!");
    }

    @Override
    public void showState() {

    }

    @Override
    public GameState nextState(String input) {
        return null;
    }

    @Override
    public Player showPlayer() {
        return null;
    }

    @Override
    public Board getBoard() {
        return null;
    }
}
