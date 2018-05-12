package oxgame.app.states;

import oxgame.app.game.Board;
import oxgame.app.game.Player;
import oxgame.app.exceptions.WrongArgumentException;

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
    GameState nextState(String input) throws WrongArgumentException;
}
