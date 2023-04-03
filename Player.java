public abstract class Player {
    public String name;
    public String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract void setStarter(boolean starter);

    public void setName(String name){
        this.name = name;
    }

    public abstract void makeMove(Board board);
}
