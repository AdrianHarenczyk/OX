package oxgame.app.utility;

import oxgame.app.exceptions.WrongArgumentException;
import oxgame.app.game.Player;

public class RoundBuffer {
    private Player[] players;

    public RoundBuffer() {
        players = new Player[2];
    }

    public void addPlayer(Player player) throws WrongArgumentException {
        if (players[0] == null) {
            players[0] = player;
        } else if (players[1] == null) {
            players[1] = player;
        } else throw new WrongArgumentException("You cannot add more than two players.");
    }

    public Player takePlayer() {
        return players[0];
    }
    public void swapPlayers() {
        Player firstPlayer = players[0];
        Player secondPlayer = players[1];

        players[0] = secondPlayer;
        players[1] = firstPlayer;
    }
}
