package tictactoe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class ComputerMoveHard implements PlayerMove {
    private final TicTacToe ticTacToe;
    private final Symbol symbol;
    private final State winState;
    private final State opponentWinState;

    public ComputerMoveHard(TicTacToe ticTacToe, Symbol symbol) {
        this.ticTacToe = ticTacToe;
        this.symbol = symbol;
        this.winState = Symbol.X.equals(symbol) ? State.X_WINS : State.O_WINS;
        this.opponentWinState = State.X_WINS.equals(winState) ? State.O_WINS : State.X_WINS;
    }

    @Override
    public void move() {
        System.out.println("Making move level \"hard\"");
        var bestMove = this.minimaxMoveIndex(this.ticTacToe, this.symbol);
        this.ticTacToe.move(bestMove.index(), this.symbol);
    }

    private Move minimaxMoveIndex(TicTacToe ticTacToe, Symbol symbol) {
        var emptyPositions = ticTacToe.getEmptyPositions();

        var state = ticTacToe.state();
        if (this.winState.equals(state)) {
            return new Move(-1, 10);
        } else if (this.opponentWinState.equals(state)) {
            return new Move(-1, -10);
        } else if (State.DRAW.equals(state)) {
            return new Move(-1, 0);
        }

        var moves = new ArrayList<Move>();
        for (int index : emptyPositions) {
            var newBoard = new TicTacToe(ticTacToe);
            newBoard.move(index, symbol);
            var result = minimaxMoveIndex(newBoard, this.oppositeSymbol(symbol));
            moves.add(new Move(index, result.score()));
        }
        var byScoreComparator = Comparator.comparing(Move::score);
        //return Symbol.X.equals(symbol) ? moves.stream().max(byScoreComparator).orElseThrow(NoSuchElementException::new) : moves.stream().min(byScoreComparator).orElseThrow(NoSuchElementException::new);
        return this.symbol.equals(symbol) ? moves.stream().max(byScoreComparator).orElseThrow(NoSuchElementException::new) : moves.stream().min(byScoreComparator).orElseThrow(NoSuchElementException::new);
    }
}

record Move(int index, int score) {
}
