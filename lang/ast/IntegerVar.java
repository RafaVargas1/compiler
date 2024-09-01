package lang.ast;

public class IntegerVar extends Expr {
    private Integer n;

    public IntegerVar(Integer n){
        this.n = n;
    }
}