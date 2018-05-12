package ox.app.game;

import ox.app.utility.RoundBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ScoreBoard {
    private final Map<Player,Integer> playerPointsMap;
    private final RoundBuffer playerBuffer;
    private final Consumer<String> output;

    public ScoreBoard(RoundBuffer playerBuffer, Consumer<String> output) {
        this.playerBuffer = playerBuffer;
        this.output = output;
        this.playerPointsMap = new HashMap<>();
    }

    public void addPoint(Player player, Integer points) {
        Integer pointsForPlayerBefore;
        if ((pointsForPlayerBefore = playerPointsMap.get(player)) != null) {
            points +=pointsForPlayerBefore;
        }
        playerPointsMap.put(player,points);
    }
    public void showScoreBoard() {
        System.out.println();
        playerPointsMap.forEach((player,points) -> System.out.println(player + " points: " + points));
        System.out.println();
    }
    public void showTheWinner() {
        Player theWinner;
        if ((theWinner = getTheWinner() ) != null) {
            output.accept("The winner is "+ theWinner);
        } else {
            output.accept("It's a draw!");
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
