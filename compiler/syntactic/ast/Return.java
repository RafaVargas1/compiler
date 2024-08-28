package lang.ast;

public abstract class Return extends Node {
    private Expr e;
    private Node n;
    
    public Return(Expr e, Node n){
         this.e = e;
         this.n = n;
    } 
    
    public Return(Expr e){
        this.e = e;
        this.n = null;
    }
}