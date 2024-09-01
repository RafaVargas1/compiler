package lang.ast;

public class IndexedCall extends Expr {
    private String id;
    private Node params;
    private Expr index;

    public IndexedCall(String id, Node params, Expr index){
        this.id = id;
        this.params = params;
        this.index = index;
    }
}
