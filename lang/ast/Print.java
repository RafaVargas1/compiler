package lang.ast;

public class Print extends Node {
    private Expr e;
    
    public Print(Expr e){
         this.e = e;
    }    
}