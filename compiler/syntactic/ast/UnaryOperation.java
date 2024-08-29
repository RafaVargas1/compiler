package lang.ast;

public abstract class UnaryOperation extends Expr {  
    private Expr n;

    public UnaryOperation(Expr value){
        this.n = value;
    }
      
    public void setN(Expr value){ n = value; }

    public Expr getN(){ return n; }
}