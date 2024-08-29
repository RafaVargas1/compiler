package lang.ast;

public abstract class Subtraction extends BinaryOperation {  
    public Subtraction(Expr a, Expr b){
        super(a, b);
    }
}