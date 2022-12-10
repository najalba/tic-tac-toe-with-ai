package tictactoe;

import java.util.Random;

public class ComputerMoveMedium implements PlayerMove {
    private final TicTacToe ticTacToe;
    private final Symbol symbol;

    public ComputerMoveMedium(TicTacToe ticTacToe, Symbol symbol) {
        this.ticTacToe = ticTacToe;
        this.symbol = symbol;
    }

    @Override
    public void move() {
        System.out.println("Making move level \"medium\"");
        int linearCoordinates;
        var winCoordinates = this.ticTacToe.winningLinearCoordinates(this.symbol);
        if (winCoordinates > 0) {
            linearCoordinates = winCoordinates;
        } else {
            var notLooseCoordinates = this.ticTacToe.winningLinearCoordinates(this.oppositeSymbol(this.symbol));
            if (notLooseCoordinates > 0) {
                linearCoordinates = notLooseCoordinates;
            } else {
                var random = new Random();
                linearCoordinates = this.ticTacToe.getEmptyPositions().get(random.nextInt(this.ticTacToe.getEmptyPositions().size()));
            }
        }
        this.ticTacToe.move(linearCoordinates, this.symbol);
    }
}
