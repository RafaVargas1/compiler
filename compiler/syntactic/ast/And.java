package lang.ast;

public abstract class And extends BinaryOperation {  
    public And(Expr a, Expr b){
        super(a, b);
    }
}