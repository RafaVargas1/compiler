package lang.ast;

public abstract class Equal extends BinaryOperation {  
    public Equal(Expr a, Expr b){
        super(a, b);
    }
}