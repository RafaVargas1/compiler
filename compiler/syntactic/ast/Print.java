package lang.ast;

public abstract class Print extends Node {
    private Expr e;
    
    public Print(Expr e){
         this.e = e;
    }    
}