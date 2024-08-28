package lang.ast;

public abstract class Addition extends BinaryOperation {  
    public Addition(Expr a, Expr b){
        super(a, b);
    }
}