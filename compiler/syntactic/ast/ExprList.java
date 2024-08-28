package lang.ast;

public abstract class ExprList extends Expr {
    private Expr e;
    private Expr l;

    public ExprList(Expr e, Expr l){
        this.e = e;
        this.l = l;
    }

    public ExprList(Expr e){
        this.e = e;
        this.l = null;
    }
}
