package lang.ast;

public abstract class Smaller extends BinaryOperation {  
    public Smaller(Expr a, Expr b){
        super(a, b);
    }
}