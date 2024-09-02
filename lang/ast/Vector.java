package lang.ast;

import interpreter.visitor.Visitor;

public class Vector extends Literal {
    private Node type;

    public Vector(Node type){
        super("VECTOR");
        this.type = type;
    }

    public void accept(Visitor v){ v.visit(this);}
}