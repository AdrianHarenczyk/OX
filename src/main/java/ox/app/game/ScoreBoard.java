package ox.app.game;

import ox.app.languages.Messenger;
import ox.app.utility.RoundBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ScoreBoard {
    private static final String EMPTY_LINE = "";
    private final Map<Player, Integer> playerPointsMap;
    private final RoundBuffer playerBuffer;
    private final Messenger messenger;

    public ScoreBoard(RoundBuffer playerBuffer, Messenger messenger) {
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

    public void showScoreBoard(Consumer<String> output) {
        output.accept(EMPTY_LINE);
        playerPointsMap.forEach((player, points) -> output.accept(player + messenger.pointsMessage() + points));
        output.accept(EMPTY_LINE);
    }

    public void showTheWinner(Consumer<String> output) {
        Player theWinner;
        if ((theWinner = getTheWinner()) != null) {
            output.accept(messenger.theWinnerIsMessage() + theWinner);
        } else {
            output.accept(messenger.drawMessage());
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
