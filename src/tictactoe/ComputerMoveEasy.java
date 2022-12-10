package tictactoe;

import java.util.Random;

public class ComputerMoveEasy implements PlayerMove {
    private final TicTacToe ticTacToe;
    private final Symbol symbol;

    public ComputerMoveEasy(TicTacToe ticTacToe, Symbol symbol) {
        this.ticTacToe = ticTacToe;
        this.symbol = symbol;
    }

    @Override
    public void move() {
        System.out.println("Making move level \"easy\"");
        var random = new Random();
        var linearCoordinates = this.ticTacToe.getEmptyPositions().get(random.nextInt(this.ticTacToe.getEmptyPositions().size()));
        this.ticTacToe.move(linearCoordinates, this.symbol);
    }
}
