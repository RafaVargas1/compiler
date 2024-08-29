package lang.ast;

public abstract class Instance extends Node {
    private String type;
    private Expr e;

    public Instance(String type, Expr e){
        this.type = type;
        this.e = e;
    }
}
