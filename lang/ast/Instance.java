package lang.ast;

public class Instance extends Expr {
    private Node type;
    private Expr e;

    public Instance(Node type, Expr e){
        this.type = type;
        this.e = e;
    }
}
