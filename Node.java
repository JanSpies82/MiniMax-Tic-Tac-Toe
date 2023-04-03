import java.util.ArrayList;
import java.util.Hashtable;

import org.fusesource.jansi.Ansi;

public class Node {
    public String state;
    public Integer value;
    public double probSuccess;
    public Boolean maximiser;
    public ArrayList<Node> children;
    private String symbol;
    private String startSymbol;
    public int nodeID = 0;
    private static int nodeCount = 0;

    public Node(String state, String symbol) {
        this.state = state;
        this.symbol = symbol;
        this.startSymbol = state.substring(0, 1);
        this.children = new ArrayList<Node>();
        this.probSuccess = 0.0;
        this.nodeID = nodeCount;
        nodeCount++;
    }

    public Hashtable<String, Node> generateChildren() {
        Hashtable<String, Node> returnTable = new Hashtable<String, Node>();
        String childStartSymbol = (this.startSymbol.equals("X")) ? "O" : "X";
        for (int i = 1; i < 10; i++) {
            if (state.charAt(i) == ' ') {
                String newState = childStartSymbol + state.substring(1, i) + startSymbol + state.substring(i + 1);
                Node child = new Node(newState, symbol);
                child.setMaximiser(!maximiser);
                returnTable.put(newState, child);
                children.add(child);
            }
        }

        for (Node child : children) {
            returnTable.putAll(child.generateChildren());
        }
        return returnTable;
    }

    public Integer setAllVals() {
        if (evaluate() != -2 || children.size() == 0 )
            return this.value;

        if (maximiser) {
            this.value = Integer.MIN_VALUE;
            for (Node child : children) {
                this.value = Math.max(value, child.setAllVals());
            }
        } else {
            this.value = Integer.MAX_VALUE;
            for (Node child : children) {
                this.value = Math.min(value, child.setAllVals());
            }
        }
        return this.value;
    }

    public void setAllProbSuccess() {
        if (value == null){
            value = evaluate();
        }
        if (children.size() == 0) {
            if (value == 1) {
                probSuccess = 1.0;
            } else if (value == 0) {
                probSuccess = 0.5;
            } else {
                probSuccess = 0.0;
            }
        } else {
            for (Node child : children) {
                child.setAllProbSuccess();
                if (child.probSuccess == 0.0 && !maximiser) {
                    probSuccess = 0.0;
                    return;
                } else if (child.probSuccess == 1.0 && maximiser) {
                    probSuccess = 1.0;
                    return;
                }
                probSuccess += child.probSuccess;
            }
            probSuccess /= getNumDescendants();
        }
    }

    public Integer evaluate() {
        Board b = new Board(state);
        int winner = b.getWinner();
        if (winner == 0) {
            this.value = 0;
        } else if (winner == 1) {
            if (symbol.equals("X")) {
                this.value = 1;
            } else {
                this.value = -1;
            }
        } else if (winner == 2) {
            if (symbol.equals("X")) {
                this.value = -1;
            } else {
                this.value = 1;
            }
        } else if (winner == -1){
            this.value = 0;
            return -2;
        } 
        else  {
            this.value = 0;
        }
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setMaximiser(Boolean maximiser) {
        this.maximiser = maximiser;
    }

    public String getState() {
        return state;
    }

    public int getValue() {
        return value;
    }

    public Boolean getMaximiser() {
        return maximiser;
    }

    public int getNumDescendants() {
        int numDescendants = 0;
        for (Node child : children) {
            numDescendants += child.getNumDescendants();
        }
        return numDescendants + 1;
    }

    public void printTree() {
        print();
        for (Node child : children) {
            child.printTree();
        }
    }

    public void print() {
        System.out.println("-------------------------");
        System.out.println("State: " + state);
        System.out.println("Value: " + value);
        System.out.println("Prob Success: " + probSuccess);
        System.out.println("Maximiser: " + maximiser);
        System.out.println("Num Children: " + children.size());
        System.out.println("-------------------------");
    }

}
