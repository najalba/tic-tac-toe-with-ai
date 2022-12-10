package tictactoe;

import java.util.Scanner;

public class HumanMove implements PlayerMove {
    private final Scanner scanner;
    private final TicTacToe ticTacToe;
    private final Symbol symbol;

    public HumanMove(Scanner scanner, TicTacToe ticTacToe, Symbol symbol) {
        this.scanner = scanner;
        this.ticTacToe = ticTacToe;
        this.symbol = symbol;
    }

    @Override
    public void move() {
        String rawCoordinates;
        do {
            System.out.print("Enter the coordinates: ");
            rawCoordinates = this.scanner.nextLine();
        } while (this.ticTacToe.invalidCoordinates(rawCoordinates));
        var linearCoordinates = this.ticTacToe.toLinearCoordinates(rawCoordinates);
        this.ticTacToe.move(linearCoordinates, this.symbol);
    }
}
