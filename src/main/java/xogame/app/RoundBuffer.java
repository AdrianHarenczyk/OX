package xogame.app;

public class RoundBuffer {
    private Player[] players;

    public RoundBuffer() {
        players = new Player[2];
    }

    public void addPlayer(Player player) throws UnsupportedOperationException{
        if (players[0] == null) {
            players[0] = player;
        } else if (players[1] == null) {
            players[1] = player;
        } else throw new UnsupportedOperationException("You cannot add more than two players.");
    }

    public Player takePlayer() {
        Player temporaryPlayer = players[1];
        Player resultPlayer = players[0];
        players[0] = temporaryPlayer;
        players[1] = resultPlayer;
        return resultPlayer;
    }
}
