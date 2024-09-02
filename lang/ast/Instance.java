package lang.ast;

import interpreter.visitor.Visitor;

public class Instance extends Expr {
    private Node type;
    private Expr e;

    public Instance(Node type, Expr e){
        this.type = type;
        this.e = e;
    }

    public Node getId(){ return type; };
    public Expr getSize(){ return e; }

    public void accept(Visitor v){ v.visit(this);}
}
