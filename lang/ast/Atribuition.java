package lang.ast;

import interpreter.visitor.Visitor;

public class Atribuition extends Node {
    private Node id;
    private Expr e;

    public Atribuition(Node id, Expr e){
        this.id = id;
        this.e = e;
    }

    public Node getId() { return id; }
    public Expr getExpr() { return e; }

    public void accept(Visitor v){ v.visit(this);}
}
