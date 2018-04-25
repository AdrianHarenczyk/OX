import java.util.Scanner;

class Setup {
    private Player player;
    private Board board;
    private GameState currentState;
    private UserInput userInput;

    void initializeAGame() {
        player = new Player("user", Player.Symbol.X);
        currentState = new InitialState();
        userInput = new UserInput(new Scanner(System.in)::nextLine);
        applicationLoop();
    }

    private void applicationLoop() {
        while (true) {
            startTurn();
        }
    }

    private void startTurn() {
        this.currentState.showState();
        this.currentState = currentState.nextState(userInput.getData());
    }

}
