package oxgame.app.game;

public enum Symbol {
        X {
            @Override
            public Symbol otherSymbol() {
                return Symbol.O;
            }
        },
        O {
            @Override
            public Symbol otherSymbol() {
                return Symbol.X;
            }
        };

        public abstract Symbol otherSymbol();


}

