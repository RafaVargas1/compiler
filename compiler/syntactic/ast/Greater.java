package lang.ast;

public abstract class Greater extends BinaryOperation {  
    public Greater(Expr a, Expr b){
        super(a, b);
    }
}