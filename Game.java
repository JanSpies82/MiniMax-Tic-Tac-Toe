import java.util.Scanner;

import org.fusesource.jansi.Ansi;

public class Game {
    public Board board;
    public Player player1;
    public Player player2;
    public Boolean XTurn;
    public Boolean gameOver;
    public Integer winner;

    public Game(Player p1, Player p2) {
        board = new Board();
        player1 = p1;
        player2 = p2;
        XTurn = true;
        gameOver = false;
        winner = 0;
    }

    public void play() {
        XTurn = selectStartingPlayer();
        board.XTurn = XTurn;
        player1.setStarter(XTurn);
        player2.setStarter(XTurn);
        while (!gameOver) {
            // System.out.print("\033[H\033[2J");
            board.printBoard((XTurn ? player1 : player2).symbol);
            if (XTurn) {
                player1.makeMove(board);
            } else {
                player2.makeMove(board);
            }
            XTurn = !XTurn;
            gameOver = board.getWinner() != -1;
        }
        endingMessage(board.getWinner());
    }

    public void endingMessage(int winner) {
        System.out.print("\033[H\033[2J");
        board.printBoard((XTurn ? player1 : player2).symbol);
        System.out.println("Game over!");
        if (winner == 0) {
            System.out.println("It's a draw!");
        } else if (winner == 1) {
            System.out.println("Player " + player1.name + " wins!");
        } else {
            System.out.println("Player " + player2.name + " wins!");
        }
        System.out.println("Press Enter to continue");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public Boolean selectStartingPlayer() {
            Scanner sc = ScannerSingleton.getInstance();
            System.out.println("Player " + player1.name + " is X.");
            System.out.println("Who should start? (X/O)");
            System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).toString());
            String input = sc.nextLine();
            System.out.print(Ansi.ansi().reset().toString());
            while (!input.equals("X") && !input.equals("O")) {
                System.out.println("Invalid input. Please enter X or O.");
                System.out.print(Ansi.ansi().fg(Ansi.Color.YELLOW).toString());
                input = sc.nextLine();
                System.out.print(Ansi.ansi().reset().toString());
            }
            return input.equals("X");
        }
    }

