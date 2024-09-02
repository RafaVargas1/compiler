package lang.ast;

import interpreter.visitor.Visitor;

public class Iterate extends Node {
    private Expr t;
    private Node c;
    
    public Iterate(Expr t, Node c){
         this.t = t;
         this.c = c;
    }    

    public Expr getTeste(){ return t;}
    public Node getBody(){ return c;}

    public void accept(Visitor v){ v.visit(this);}
}