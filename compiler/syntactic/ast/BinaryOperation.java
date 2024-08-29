package lang.ast;

public abstract class BinaryOperation extends Expr {  
    private Expr a;
    private Expr b;

    public BinaryOperation(Expr a, Expr b){
        this.a = a;
        this.b = b;
    }
      
    public void setA(Expr n){ a = n; }
    public void setB(Expr n){ r = n; }

    public Expr getA(){ return a; }
    public Expr getB(){ return b; }
}