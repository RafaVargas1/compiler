package lang.ast;

public abstract class Multiplication extends BinaryOperation {  
    public Multiplication(Expr a, Expr b){
        super(a, b);
    }
}