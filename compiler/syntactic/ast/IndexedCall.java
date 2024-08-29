package lang.ast;

public abstract class IndexedCall extends Node {
    private String id;
    private Node params;
    private Expr index;

    public IndexedCall(String id, Node params, Expr index){
        this.id = id;
        this.params = params;
        this.index = index;
    }
}
