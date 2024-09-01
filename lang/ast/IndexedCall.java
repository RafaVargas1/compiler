package lang.ast;

public class IndexedCall extends Expr {
    private String id;
    private Expr[] params;
    private Expr index;

    public IndexedCall(String id, Expr[] params, Expr index){
        this.id = id;
        this.params = params;
        this.index = index;
    }
}