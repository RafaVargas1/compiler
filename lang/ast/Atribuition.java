package lang.ast;

public class Atribuition extends Node {
    private Node id;
    private Expr e;

    public Atribuition(Node id, Expr e){
        this.id = id;
        this.e = e;
    }

    public Node getId() { return id; }
    public Expr getExpr() { return e; }
}
