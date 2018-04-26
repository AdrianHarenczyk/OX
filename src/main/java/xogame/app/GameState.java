package xogame.app;

public interface GameState {
    /**
     * Shows the state of current game run. For example prints
     * board to console, or informs about victory.
     */
    void showState();
    /**
     * @return next state of the game, for example
     * could return new turn, for opposite player.
     */
    GameState nextState(String input);

    Player showPlayer();
}
