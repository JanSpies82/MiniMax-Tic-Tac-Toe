import java.util.ArrayList;

import org.fusesource.jansi.Ansi;

public class Board {
    public ArrayList<String> board;
    public Boolean XTurn;

    public Board() {
        board = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            board.add(" ");
        }
        XTurn = true;
    }

    public Board(Board b) {
        board = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            board.add(b.board.get(i));
        }
        XTurn = b.XTurn;
    }

    public Board(String board) {
        this.board = new ArrayList<String>();
        for (int i = 1; i < 10; i++) {
            this.board.add(board.substring(i, i + 1));
        }
        XTurn = board.substring(0, 1).equals("X");

    }

    public void makeMove(int move) {
        if (XTurn) {
            board.set(move, "X");
        } else {
            board.set(move, "O");
        }
        XTurn = !XTurn;
    }

    public void makeMove(String newState){
        for (int i = 1; i < 10; i++) {
            if (newState.charAt(i) != board.get(i - 1).charAt(0)) {
                if (!isValidMove(i - 1)) {
                    throw new IllegalArgumentException("Invalid move");
                }
            }
        }
        
        board = new ArrayList<String>();
        for (int i = 1; i < 10; i++) {
            board.add(newState.substring(i, i + 1));
        }
        XTurn = newState.substring(0, 1).equals("X");
    }

    public void undoMove(int move) {
        board.set(move, " ");
        XTurn = !XTurn;
    }

    public void setTurn(Boolean turn) {
        XTurn = turn;
    }

    public Boolean isFull() {
        for (int i = 0; i < 9; i++) {
            if (board.get(i).equals(" ")) {
                return false;
            }
        }
        return true;
    }

    // Returns 0 if draw, 1 if X wins, 2 if O wins,
    public Integer getWinner() {
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board.get(i).equals(board.get(i + 3)) && board.get(i).equals(board.get(i + 6))
                    && !board.get(i).equals(" ")) {
                // System.out.println("Column win");
                if (board.get(i).equals("X"))
                    return 1;
                else
                    return 2;
            }
        }

        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board.get(i).equals(board.get(i + 1)) && board.get(i).equals(board.get(i + 2))
                    && !board.get(i).equals(" ")) {
                // System.out.println("Row win");
                if (board.get(i).equals("X"))
                    return 1;
                else
                    return 2;
            }
        }

        // Check diagonals
        if (board.get(0).equals(board.get(4)) && board.get(0).equals(board.get(8)) && !board.get(0).equals(" ")) {
            // System.out.println("Diagonal 1 win");
            if (board.get(0).equals("X"))
                return 1;
            else
                return 2;

        }
        if (board.get(2).equals(board.get(4)) && board.get(2).equals(board.get(6)) && !board.get(2).equals(" ")) {
            // System.out.println("Diagonal 2 win");
            if (board.get(2).equals("X"))
                return 1;
            else
                return 2;
        }

        if (isFull()) {
            // System.out.println("Draw");
            return 0;
        }
        return -1;

    }

    public Boolean isValidMove(int move) {
        return board.get(move).equals(" ");
    }

    public ArrayList<Integer> getMoves() {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            if (board.get(i).equals(" ")) {
                moves.add(i);
            }
        }
        return moves;
    }

    public String toString() {
        String s = "";
        if (XTurn)
            s += "X";
        else
            s += "O";

        for (int i = 0; i < 9; i++) {
            s += board.get(i);
        }
        return s;
    }

    public void printBoard(String symbol) {
        System.out.println(" " + ((board.get(0).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(0) + Ansi.ansi().reset().toString() + " | "
                + ((board.get(1).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(1) + Ansi.ansi().reset().toString() + " | "
                + ((board.get(2).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(2) + Ansi.ansi().reset().toString() + " ");
        System.out.println("---+---+---");
        System.out.println(" " + ((board.get(3).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(3) + Ansi.ansi().reset().toString() + " | "
                + ((board.get(4).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(4) + Ansi.ansi().reset().toString() + " | "
                + ((board.get(5).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(5) + Ansi.ansi().reset().toString() + " ");
        System.out.println("---+---+---");
        System.out.println(" " + ((board.get(6).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(6) + Ansi.ansi().reset().toString() + " | "
                + ((board.get(7).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(7) + Ansi.ansi().reset().toString() + " | "
                + ((board.get(8).equals(symbol)) ? Ansi.ansi().fg(Ansi.Color.YELLOW).toString() : "") + board.get(8) + Ansi.ansi().reset().toString() + " ");
    }
}
