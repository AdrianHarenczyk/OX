package ox.app.game;

import ox.app.io.InputOutput;
import ox.app.languages.Messenger;
import ox.app.utility.PlayerBuffer;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {
    private static final String EMPTY_LINE = "";
    private final Map<Player, Integer> playerPointsMap;
    private final PlayerBuffer playerBuffer;
    private final Messenger messenger;

    public ScoreBoard(PlayerBuffer playerBuffer, Messenger messenger) {
        this.playerBuffer = playerBuffer;
        this.playerPointsMap = new HashMap<>();
        this.messenger = messenger;
    }

    public void addPoint(Player player, Integer points) {
        Integer pointsForPlayerBefore;
        if ((pointsForPlayerBefore = playerPointsMap.get(player)) != null) {
            points += pointsForPlayerBefore;
        }
        playerPointsMap.put(player, points);
    }

    public void showScoreBoard(InputOutput inputOutput) {
        inputOutput.message(EMPTY_LINE);
        playerPointsMap.forEach((player, points) -> inputOutput.message(player + messenger.pointsMessage() + points));
        inputOutput.message(EMPTY_LINE);
    }

    public void showTheWinner(InputOutput inputOutput) {
        Player theWinner;
        if ((theWinner = getTheWinner()) != null) {
            inputOutput.message(messenger.theWinnerIsMessage() + theWinner);
        } else {
            inputOutput.message(messenger.drawMessage());
        }
    }

    private Player getTheWinner() {
        Player[] players = playerBuffer.getPlayers();
        int playerOnePoints = playerPointsMap.get(players[0]);
        int playerTwoPoints = playerPointsMap.get(players[1]);
        if (playerOnePoints > playerTwoPoints) {
            return players[0];
        } else if (playerTwoPoints > playerOnePoints) {
            return players[1];
        } else return null;
    }
}
