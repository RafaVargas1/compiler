package lang.ast;

import interpreter.visitor.Visitor;

public class Print extends Node {
    private Expr e;
    
    public Print(Expr e){
         this.e = e;
    }    
    
    public Expr getExpr(){ return e;}

    public void accept(Visitor v){ v.visit(this);}
}