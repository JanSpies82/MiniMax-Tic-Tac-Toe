import java.util.Scanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

class Main {

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        Integer choice = 0;
        while (choice != 4) {
            choice = displayMenu();
            if (choice == 1) {
                playAgainstPlayer();
            } else if (choice == 2) {
                playAgainstComputer();
            } else if (choice == 3) {
                computerBattle();
            } 
            else {
                System.out.println("Goodbye!");
            }
        }
        ScannerSingleton.getInstance().close();
    }

    public static Integer displayMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("1. Play against another player");
        System.out.println("2. Play against the computer (If you dare)");
        System.out.println("3. Watch the computer battle");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice: ");
        Scanner sc = ScannerSingleton.getInstance();
        System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).toString());
        String input = sc.nextLine();
        System.out.print(Ansi.ansi().reset().toString());
        while (!input.matches("[1-4]")) {
            System.out.println("Invalid input. Please enter a number 1-4.");
            System.out.print("Please enter your choice: ");
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).toString());
            input = sc.nextLine();
            System.out.print(Ansi.ansi().reset().toString());
        }
        return Integer.parseInt(input);
    }

    public static void playAgainstPlayer() {
        System.out.println("You have chosen to play against another player.");
        Player player1 = new HumanPlayer("X");
        Player player2 = new HumanPlayer("O");
        Game game = new Game(player1, player2);
        game.play();
    }

    public static void playAgainstComputer() {
        System.out.println("You have chosen to play against the computer.");
        Player player1 = new HumanPlayer("X");
        Player player2 = new MiniMaxPlayer("HAL 9000", "O");
        Game game = new Game(player1, player2);
        game.play();
    }

    public static void computerBattle() {
        System.out.println("You have chosen to watch the computer battle.");
        Player player1 = new MiniMaxPlayer("HAL 9000","X");
        Player player2 = new MiniMaxPlayer("HAL 9001", "O");
        Game game = new Game(player1, player2);
        game.play();
    }

}