package ox.app.game;

import ox.app.utility.RoundBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ScoreBoard {
    private final Map<Player,Integer> playerPointsMap;
    private final RoundBuffer playerBuffer;
    private static final String EMPTY_LINE ="";
    private static final String DRAW_MESSAGE = "It's a draw!";
    private static final String THE_WINNER_MESSAGE = "The winner is ";

    public ScoreBoard(RoundBuffer playerBuffer) {
        this.playerBuffer = playerBuffer;
        this.playerPointsMap = new HashMap<>();
    }

    public void addPoint(Player player, Integer points) {
        Integer pointsForPlayerBefore;
        if ((pointsForPlayerBefore = playerPointsMap.get(player)) != null) {
            points +=pointsForPlayerBefore;
        }
        playerPointsMap.put(player,points);
    }
    public void showScoreBoard(Consumer<String> output) {
        output.accept(EMPTY_LINE);
        playerPointsMap.forEach((player,points) -> output.accept(player + " points: " + points));
        output.accept(EMPTY_LINE);
    }
    public void showTheWinner(Consumer<String> output) {
        Player theWinner;
        if ((theWinner = getTheWinner() ) != null) {
            output.accept(THE_WINNER_MESSAGE + theWinner);
        } else {
            output.accept(DRAW_MESSAGE);
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
