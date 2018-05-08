package oxgame.app.game;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {
    Map<Player,Integer> playerPointsMap;

    public ScoreBoard() {
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
}
