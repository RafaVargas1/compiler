package lang.ast;

import interpreter.visitor.Visitor;

public class Return extends Node {
    private Expr[] e;
    
    public Return(Expr[] e){
         this.e = e;
    } 

    public void accept(Visitor v){ v.visit(this);}
}