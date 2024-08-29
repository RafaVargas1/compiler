package lang.ast;

public abstract class Iterate extends Node {
    private Expr t;
    private Node c;
    
    public Iterate(Expr t, Node c){
         this.t = t;
         this.c = c;
    }    
}