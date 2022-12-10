package tictactoe;

public interface PlayerMove {
    void move();

    default Symbol oppositeSymbol(Symbol symbol) {
        return Symbol.X.equals(symbol) ? Symbol.O : Symbol.O.equals(symbol) ? Symbol.X : Symbol.EMPTY;
    }
}
