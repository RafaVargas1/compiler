//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public abstract class BinaryOperation extends Expr {  
    private Expr a;
    private Expr b;

    public BinaryOperation(Expr a, Expr b){
        this.a = a;
        this.b = b;
    }
      
    public void setA(Expr n){ this.a = n; }
    public void setB(Expr n){ this.b = n; }

    public Expr getA(){ return this.a; }
    public Expr getB(){ return this.b; }
}