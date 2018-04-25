class Player {
    private String name;
    private Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    enum Symbol {
        X,
        O;
    }

    @Override
    public String toString() {
        return name + " with symbol " + symbol;
    }
}
