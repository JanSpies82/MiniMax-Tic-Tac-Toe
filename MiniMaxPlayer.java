import java.util.Hashtable;

public class MiniMaxPlayer extends Player {
    private Hashtable<String, Node> nodeTable;
    private Node root;
    public MiniMaxPlayer(String name, String symbol) {
        super(name, symbol);
    }
    
    @Override
    public void makeMove(Board board) {
        Node node = nodeTable.get(board.toString());
        if (node == null){
            System.out.println(name + " is perplexed...");
            nodeTable.clear();
            root = new Node(board.toString(), symbol);
            root.setMaximiser(true);
            nodeTable.put(root.getState(), root);
            nodeTable.putAll(root.generateChildren());
            root.setAllVals();
            node = nodeTable.get(board.toString());
        }
        int bestVal = Integer.MIN_VALUE;
        double bestProb = 0.0;
        Node bestNode = null;
        for (Node child : node.children) {
            if (child.getValue() > bestVal) {
                bestVal = child.getValue();
                bestProb = child.probSuccess;
                bestNode = child;
            } else if (child.getValue() == bestVal && child.probSuccess > bestProb) {
                bestProb = child.probSuccess;
                bestNode = child;
            }
        }
        board.makeMove(bestNode.getState());
    }

    @Override
    public void setStarter(boolean Xstarts) {
        if (Xstarts){
            root = new Node("X         ",symbol);
            if (symbol.equals("X"))
                root.setMaximiser(true);
             else 
                root.setMaximiser(false);
        } else {
            root = new Node("O         ", symbol);
            if (symbol.equals("O"))
                root.setMaximiser(true);
             else 
                root.setMaximiser(false);
        }
        nodeTable = new Hashtable<String, Node>();
        nodeTable.put(root.getState(), root);
        System.out.println(name + " is thinking...");
        nodeTable.putAll(root.generateChildren());
        root.setAllVals();
        root.setAllProbSuccess();
    }
    
    
}
