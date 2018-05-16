package ox.app.game;

import ox.app.languages.InstructionDriver;
import ox.app.utility.RoundBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ScoreBoard {
    private final Map<Player, Integer> playerPointsMap;
    private final RoundBuffer playerBuffer;
    private final InstructionDriver instructionDriver;
    private static final String EMPTY_LINE = "";

    public ScoreBoard(RoundBuffer playerBuffer, InstructionDriver instructionDriver) {
        this.playerBuffer = playerBuffer;
        this.playerPointsMap = new HashMap<>();
        this.instructionDriver = instructionDriver;
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
        playerPointsMap.forEach((player, points) -> output.accept(player + instructionDriver.pointsMessage() + points));
        output.accept(EMPTY_LINE);
    }

    public void showTheWinner(Consumer<String> output) {
        Player theWinner;
        if ((theWinner = getTheWinner()) != null) {
            output.accept(instructionDriver.theWinnerIsMessage() + theWinner);
        } else {
            output.accept(instructionDriver.drawMessage());
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
