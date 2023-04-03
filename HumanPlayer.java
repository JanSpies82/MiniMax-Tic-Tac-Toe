import java.util.Scanner;
import org.fusesource.jansi.Ansi;

public class HumanPlayer extends Player {

    public HumanPlayer(String symbol) {
        super("", symbol);
        System.out.println("Please enter your name player " + symbol + ":");
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).toString() + "");
        Scanner sc = ScannerSingleton.getInstance();
        String name = sc.nextLine();
        System.out.println(Ansi.ansi().reset().toString());
        setName(name);
    }

    @Override
    public void makeMove(Board board) {
        System.out.println("Please enter a move Player " + name + " (1-9):");
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).toString());
        Scanner sc = ScannerSingleton.getInstance();
        String input = sc.nextLine();
        System.out.print(Ansi.ansi().reset().toString());
        while (!input.matches("[1-9]") ||
                !board.isValidMove(Integer.parseInt(input) - 1)) {
            System.out.println("Invalid input. Please enter a number 1-9 that is not already taken.");
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).toString());
            input = sc.nextLine();
            System.out.print(Ansi.ansi().reset().toString());
        }
        board.makeMove(Integer.parseInt(input) - 1);
    }

    @Override
    public void setStarter(boolean starter) {
    }

}
