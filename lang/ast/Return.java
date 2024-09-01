package lang.ast;

public class Return extends Node {
    private Expr[] e;
    
    public Return(Expr[] e){
         this.e = e;
    } 
}