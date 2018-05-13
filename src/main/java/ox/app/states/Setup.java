package ox.app.states;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.game.Symbol;
import ox.app.utility.RoundBuffer;
import ox.app.utility.SetupChooser;
import ox.app.validators.BoardValidator;
import ox.app.validators.WinningStrokeValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Setup {
    private GameState currentState;
    private final Supplier<String> input;
    private final Consumer<String> output;
    private static final String HELLO_MESSAGE = "Game has started.\nPass number of field to place your symbol.\nTo resign pass resign as a command.\n";
    private static final String STARTING_MESSAGE = "Welcome in OX Game!\nUse default command to start with 3x3 Board and generic user names," +
            "\nor pass custom to specify names of the players, their symbols,\nand set board to play.";
    private static final int INITIAL_ROUND_COUNTER = 1;
    private static final int DEFAULT_WINNING_STROKE = 3;
    private static final int DEFAULT_BOARD_WIDTH = 3;
    private static final int DEFAULT_BOARD_HEIGHT = 3;

    public Setup(Supplier<String> input, Consumer<String> output) {
        this.input = input;
        this.output = output;
    }

    public void initializeAGame() throws WrongArgumentException {
        startupInstructions();
        if (SetupChooser.check(input,output)) {
            customSetup();
        } else {
            defaultSetup();
        }
        afterSettingsInstructions();
        applicationLoop();
    }

    private void applicationLoop() {
        while (true) {
            if (currentState.getClass().equals(EndState.class))
                return;
            this.currentState.showState();
            startTurn();
        }
    }

    private void startTurn() {
        try {
            this.currentState = currentState.nextState(input.get());
        } catch (WrongArgumentException e) {
            output.accept(e.getMessage());
            startTurn();
        }
    }

    private void customSetup() throws WrongArgumentException{
        RoundBuffer playerBuffer = playerInitializer();
        Board board = boardInitializer();
        int winningStroke = WinningStrokeValidator.validate(board,input,output);
        ScoreBoard scoreBoard = new ScoreBoard(playerBuffer,output);
        currentState = new RunState(playerBuffer,output,board,scoreBoard,INITIAL_ROUND_COUNTER,winningStroke);
    }
    private RoundBuffer playerInitializer() throws WrongArgumentException{
        RoundBuffer playerBuffer = new RoundBuffer();
        Player firstPlayer = Player.playerCreator(input,output);
        Player secondPlayer = Player.playerCreator(input,output,firstPlayer);
        playerBuffer.addPlayers(firstPlayer,secondPlayer);
        return playerBuffer;
    }
    private Board boardInitializer() {
        int width = BoardValidator.validateWidth(input,output);
        int height = BoardValidator.validateHeight(input,output);
        return Board.newBoard(width,height);
    }

    private void defaultSetup() throws WrongArgumentException{
        RoundBuffer playerBuffer = defaultPlayers();
        Board board = defaultBoard();
        ScoreBoard scoreBoard = new ScoreBoard(playerBuffer,output);
        currentState = new RunState(playerBuffer,output,board,scoreBoard,INITIAL_ROUND_COUNTER,DEFAULT_WINNING_STROKE);
    }
    private RoundBuffer defaultPlayers() throws WrongArgumentException{
        RoundBuffer playerBuffer = new RoundBuffer();
        Player firstPlayer = new Player("Cheesecake",Symbol.X);
        Player secondPlayer = new Player("Blueberry",Symbol.O);
        playerBuffer.addPlayers(firstPlayer,secondPlayer);
        return playerBuffer;
    }
    private Board defaultBoard() {
        return Board.newBoard(DEFAULT_BOARD_WIDTH,DEFAULT_BOARD_HEIGHT);
    }

    private void afterSettingsInstructions() {
        output.accept(HELLO_MESSAGE);
    }
    private void startupInstructions() {
        output.accept(STARTING_MESSAGE);
    }

}
