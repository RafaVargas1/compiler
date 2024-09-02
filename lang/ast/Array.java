package lang.ast;

import interpreter.visitor.Visitor;

public class Array extends Expr {
    private Node id;
    private Expr index;
    
    public Array(Node id, Expr index){
        this.id = id;
        this.index = index;
    }

    public void accept(Visitor v){ v.visit(this);}
}