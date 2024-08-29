package lang.ast;

public abstract class Division extends BinaryOperation {  
    public Division(Expr a, Expr b){
        super(a, b);
    }
}