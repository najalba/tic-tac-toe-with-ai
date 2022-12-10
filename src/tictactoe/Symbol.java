package tictactoe;

public enum Symbol {
    X("X", "X"), O("O", "O"), EMPTY("_", " ");
    private final String in;
    private final String out;

    Symbol(String in, String out) {
        this.in = in;
        this.out = out;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public static Symbol byIn(String in) {
        return switch (in) {
            case "X" -> X;
            case "O" -> O;
            case "_" -> EMPTY;
            default -> throw new IllegalArgumentException("No such in %s".formatted(in));
        };
    }
}
