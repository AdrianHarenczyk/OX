package ox.app.states;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Coordinate;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.languages.InstructionDriver;
import ox.app.utility.BoardDrawer;
import ox.app.utility.ResignCheck;
import ox.app.utility.RoundBuffer;
import ox.app.utility.VictoryChecker;
import ox.app.validators.CoordinateValidator;

import java.util.function.Consumer;

class RunState implements GameState {

    private final RoundBuffer playerBuffer;
    private final Consumer<String> output;
    private final Consumer<String> boardOutput;
    private final Board board;
    private Player player;
    private int currentBoardSize;
    private final ScoreBoard scoreBoard;
    private final int roundCounter;
    private final int winningStroke;
    private final InstructionDriver instructionDriver;
    private static final int ENDING_ROUND = 3;

    public RunState(RoundBuffer playerBuffer, Consumer<String> output, Board board,
                    ScoreBoard scoreBoard, int roundCounter, int winningStroke,
                    Consumer<String> boardOutput, InstructionDriver instructionDriver) {
        this.playerBuffer = playerBuffer;
        this.output = output;
        this.board = board;
        this.currentBoardSize = board.size();
        this.scoreBoard = scoreBoard;
        this.roundCounter = roundCounter;
        this.winningStroke = winningStroke;
        this.boardOutput = boardOutput;
        this.instructionDriver = instructionDriver;
    }


    @Override
    public void showState() {
        player = playerBuffer.takePlayer();
        output.accept(player.toString());
        BoardDrawer.showBoard(board,boardOutput);
    }

    @Override
    public GameState nextState(String input) throws WrongArgumentException {
        PreEndState resignState;
        if ((resignState = checkResign(input))!=null) {
            return resignState;
        }
        int validCoordinates = validateCoordinateAndPlaceSymbol(input);
        if (checkIfDrawOrWin(validCoordinates)) {
            BoardDrawer.showBoard(board,boardOutput);
            return new PreEndState(playerBuffer,output,board,
                    scoreBoard, roundCounter,currentBoardSize,
                    winningStroke,boardOutput, instructionDriver);
        }
        playerBuffer.swapPlayers();
        return this;
    }
    private boolean checkIfDrawOrWin(int validCoordinates) {
        return VictoryChecker.check(Coordinate.apply(validCoordinates),board,winningStroke)
                || currentBoardSize == 0;
    }
    private int validateCoordinateAndPlaceSymbol(String input) throws WrongArgumentException{
        int validCoordinates = CoordinateValidator.validate(input,board.size(),board);
        board.placeSymbol(Coordinate.apply(validCoordinates),player.showSymbol());
        currentBoardSize--;
        return validCoordinates;
    }
    private PreEndState checkResign(String input) {
        if (ResignCheck.checkAll(input)) {
            playerBuffer.swapPlayers();
            return new PreEndState(playerBuffer, output, board,
                    scoreBoard, ENDING_ROUND, currentBoardSize,
                    winningStroke, boardOutput, instructionDriver);
        } else if (ResignCheck.check(input)) {
            playerBuffer.swapPlayers();
            return new PreEndState(playerBuffer, output, board,
                    scoreBoard, roundCounter, currentBoardSize,
                    winningStroke, boardOutput, instructionDriver);
        } else return null;
    }
}
