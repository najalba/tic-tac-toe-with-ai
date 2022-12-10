package tictactoe;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String[] command;
        do {
            do {
                System.out.print("Input command:");
                command = scanner.nextLine().split(" ");
            } while (!validCommand(command));
            if ("start".equals(command[0])) {
                var ticTacToe = new TicTacToe();
                PlayerMove[] players = new PlayerMove[2];
                players[0] = switch (command[1]) {
                    case "easy" -> new ComputerMoveEasy(ticTacToe, Symbol.X);
                    case "medium" -> new ComputerMoveMedium(ticTacToe, Symbol.X);
                    case "hard" -> new ComputerMoveHard(ticTacToe, Symbol.X);
                    case "user" -> new HumanMove(scanner, ticTacToe, Symbol.X);
                    default -> throw new IllegalArgumentException("no such command");
                };
                players[1] = switch (command[2]) {
                    case "easy" -> new ComputerMoveEasy(ticTacToe, Symbol.O);
                    case "medium" -> new ComputerMoveMedium(ticTacToe, Symbol.O);
                    case "hard" -> new ComputerMoveHard(ticTacToe, Symbol.O);
                    case "user" -> new HumanMove(scanner, ticTacToe, Symbol.O);
                    default -> throw new IllegalArgumentException("no such command");
                };
                ticTacToe.setPlayers(players);
                ticTacToe.play();
            }
        } while (!"exit".equals(command[0]));
    }

    private static boolean validCommand(String[] command) {
        var startCommands = List.of("easy", "medium", "hard", "user");
        if (command.length != 1 && command.length != 3) {
            System.out.println("Bad parameters!");
            return false;
        }
        if (command.length == 1 && !"exit".equals(command[0])) {
            System.out.println("Bad parameters!");
            return false;
        }
        if (command.length == 3 && (!command[0].equals("start") || !startCommands.contains(command[1]) || !startCommands.contains(command[2]))) {
            System.out.println("Bad parameters!");
            return false;
        }
        return true;
    }
}
