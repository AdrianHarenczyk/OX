package ox.app.states;

import ox.app.exceptions.WrongArgumentException;
import ox.app.game.Board;
import ox.app.game.Player;
import ox.app.game.ScoreBoard;
import ox.app.game.Symbol;
import ox.app.io.InputOutput;
import ox.app.languages.Messenger;
import ox.app.utility.PlayerBuffer;
import ox.app.utility.SetupChooser;
import ox.app.validators.BoardValidator;
import ox.app.validators.WinningStrokeValidator;

public class Setup {
    private final InputOutput inputOutput;
    private final Messenger messenger;

    private static final int INITIAL_ROUND_COUNTER = 1;
    private static final int DEFAULT_WINNING_STROKE = 3;
    private static final int DEFAULT_BOARD_WIDTH = 3;
    private static final int DEFAULT_BOARD_HEIGHT = 3;

    private GameState currentState;

    public Setup(InputOutput inputOutput, Messenger messenger) {
        this.inputOutput = inputOutput;
        this.messenger = messenger;
    }

    public void initializeAGame() throws WrongArgumentException {
        startupInstructions();
        if (SetupChooser.check(inputOutput, messenger)) {
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
            this.currentState = currentState.nextState(inputOutput.input());
        } catch (WrongArgumentException e) {
            inputOutput.message(e.getMessage());
            startTurn();
        }
    }

    private void customSetup() throws WrongArgumentException {
        PlayerBuffer playerBuffer = playerInitializer();
        Board board = boardInitializer();
        int winningStroke = WinningStrokeValidator.validate(board, inputOutput, messenger);
        ScoreBoard scoreBoard = new ScoreBoard(playerBuffer, messenger);
        currentState = new RunState(inputOutput, messenger, playerBuffer, board,
                scoreBoard, INITIAL_ROUND_COUNTER, winningStroke);
    }

    private PlayerBuffer playerInitializer() throws WrongArgumentException {
        PlayerBuffer playerBuffer = new PlayerBuffer();
        Player firstPlayer = Player.playerCreator(inputOutput, messenger);
        Player secondPlayer = Player.playerCreator(inputOutput, firstPlayer, messenger);
        playerBuffer.addPlayers(firstPlayer, secondPlayer);
        return playerBuffer;
    }

    private Board boardInitializer() {
        int width = BoardValidator.validateWidth(inputOutput, messenger);
        int height = BoardValidator.validateHeight(inputOutput, messenger);
        return Board.newBoard(width, height);
    }

    private void defaultSetup() throws WrongArgumentException {
        PlayerBuffer playerBuffer = defaultPlayers();
        Board board = defaultBoard();
        ScoreBoard scoreBoard = new ScoreBoard(playerBuffer, messenger);
        currentState = new RunState(inputOutput, messenger, playerBuffer, board,
                scoreBoard, INITIAL_ROUND_COUNTER, DEFAULT_WINNING_STROKE);
    }

    private PlayerBuffer defaultPlayers() throws WrongArgumentException {
        PlayerBuffer playerBuffer = new PlayerBuffer();
        Player firstPlayer = new Player(messenger.defaultFirstPlayerName(), Symbol.X);
        Player secondPlayer = new Player(messenger.defaultSecondPlayerName(), Symbol.O);
        playerBuffer.addPlayers(firstPlayer, secondPlayer);
        return playerBuffer;
    }

    private Board defaultBoard() {
        return Board.newBoard(DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
    }

    private void startupInstructions() {
        inputOutput.message(messenger.welcomeMessage());
    }

    private void afterSettingsInstructions() {
        inputOutput.message(messenger.startRoundMessage());
    }

}
