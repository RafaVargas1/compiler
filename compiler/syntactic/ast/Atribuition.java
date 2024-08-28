package lang.ast;

public abstract class Atribuition extends Node {
    private Node id;
    private Expr e;

    public Atribuition(Node id, Expr e){
        this.id = id;
        this.e = e;
    }
}
