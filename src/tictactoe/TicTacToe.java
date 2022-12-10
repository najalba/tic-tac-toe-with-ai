package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TicTacToe {
    private final Symbol[] model = new Symbol[9];
    private final List<Integer> emptyPositions = new ArrayList<>();
    private PlayerMove[] players;

    public TicTacToe() {
        for (int i = 0; i < model.length; i++) {
            this.model[i] = Symbol.EMPTY;
            this.emptyPositions.add(i);
        }
    }

    public TicTacToe(TicTacToe ticTacToe) {
        System.arraycopy(ticTacToe.model, 0, this.model, 0, this.model.length);
        this.emptyPositions.addAll(ticTacToe.emptyPositions);
    }

    public void setPlayers(PlayerMove[] players) {
        this.players = players;
    }

    public void play() {
        Objects.requireNonNull(this.players);
        State state;
        int playerIndex = 0;
        do {
            print();
            this.players[playerIndex].move();
            playerIndex = ++playerIndex % 2;
            state = this.state();
        } while (State.NOT_FINISHED.equals(state));
        this.print();
        switch (state) {
            case O_WINS -> System.out.println("O wins");
            case X_WINS -> System.out.println("X wins");
            case DRAW -> System.out.println("Draw");
        }
    }

    public void move(Integer linearCoordinates, Symbol symbol) {
        this.model[linearCoordinates] = symbol;
        this.emptyPositions.remove(linearCoordinates);
    }

    public boolean invalidCoordinates(String rawCoordinates) {
        if (rawCoordinates.length() != 3 || rawCoordinates.matches("[^0-9] .|. [^0-9]|.\\S.")) {
            System.out.println("You should enter numbers!");
            return true;
        }
        if (rawCoordinates.matches("[^1-3] .|. [^1-3]")) {
            System.out.println("Coordinates should be from 1 to 3!");
            return true;
        }
        if (!model[this.toLinearCoordinates(rawCoordinates)].equals(Symbol.EMPTY)) {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }
        return false;
    }

    public Integer toLinearCoordinates(String rawCoordinates) {
        var coordinates = rawCoordinates.split(" ");
        var x = Integer.parseInt(coordinates[0]) - 1;
        var y = Integer.parseInt(coordinates[1]) - 1;
        return x * 3 + y;
    }

    public List<Integer> getEmptyPositions() {
        return this.emptyPositions;
    }

    public Integer winningLinearCoordinates(Symbol symbol) {
        int winningCoordinates = -1;

        var row1 = List.of(this.model[0], this.model[1], this.model[2]);
        var index1 = this.winnerRowIndex(row1, symbol);
        if (index1 > -1) {
            return index1;
        }

        var row2 = List.of(this.model[3], this.model[4], this.model[5]);
        var index2 = this.winnerRowIndex(row2, symbol);
        if (index2 > -1) {
            return 3 + index2;
        }

        var row3 = List.of(this.model[6], this.model[7], this.model[8]);
        var index3 = this.winnerRowIndex(row3, symbol);
        if (index3 > -1) {
            return 6 + index3;
        }

        var col1 = List.of(this.model[0], this.model[3], this.model[6]);
        var index4 = this.winnerRowIndex(col1, symbol);
        if (index4 > -1) {
            return 3 * index4;
        }

        var col2 = List.of(this.model[1], this.model[4], this.model[7]);
        var index5 = this.winnerRowIndex(col2, symbol);
        if (index5 > -1) {
            return 3 * index5 + 1;
        }

        var col3 = List.of(this.model[2], this.model[5], this.model[8]);
        var index6 = this.winnerRowIndex(col3, symbol);
        if (index6 > -1) {
            return 3 * index6 + 2;
        }

        var d1 = List.of(this.model[0], this.model[4], this.model[8]);
        var index7 = this.winnerRowIndex(d1, symbol);
        if (index7 > -1) {
            return 4 * index7;
        }

        var d2 = List.of(this.model[2], this.model[4], this.model[6]);
        var index8 = this.winnerRowIndex(d2, symbol);
        if (index8 > -1) {
            return 2 * index8 + 2;
        }

        return winningCoordinates;
    }

    public State state() {
        if (this.wins(Symbol.X)) {
            return State.X_WINS;
        } else if (this.wins(Symbol.O)) {
            return State.O_WINS;
        } else if (Arrays.stream(model).noneMatch(Symbol.EMPTY::equals)) {
            return State.DRAW;
        } else {
            return State.NOT_FINISHED;
        }
    }

    private void print() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.printf(" %s", model[i * 3 + j].getOut());
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    private boolean wins(Symbol symbol) {
        return model[0].equals(model[1]) && model[1].equals(model[2]) && model[2].equals(symbol) ||
                model[3].equals(model[4]) && model[4].equals(model[5]) && model[5].equals(symbol) ||
                model[6].equals(model[7]) && model[7].equals(model[8]) && model[8].equals(symbol) ||
                model[0].equals(model[3]) && model[3].equals(model[6]) && model[6].equals(symbol) ||
                model[1].equals(model[4]) && model[4].equals(model[7]) && model[7].equals(symbol) ||
                model[2].equals(model[5]) && model[5].equals(model[8]) && model[8].equals(symbol) ||
                model[0].equals(model[4]) && model[4].equals(model[8]) && model[8].equals(symbol) ||
                model[2].equals(model[4]) && model[4].equals(model[6]) && model[6].equals(symbol);
    }

    private int winnerRowIndex(List<Symbol> elements, Symbol symbol) {
        var symbolCount = elements.stream().filter(s -> s.equals(symbol)).count();
        var emptyCount = elements.stream().filter(Symbol.EMPTY::equals).count();
        return symbolCount == 2 && emptyCount == 1 ? elements.indexOf(Symbol.EMPTY) : -1;
    }
}
