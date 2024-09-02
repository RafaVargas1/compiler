//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public abstract class UnaryOperation extends Expr {  
    private Expr n;

    public UnaryOperation(Expr value){
        this.n = value;
    }
      
    public void setN(Expr value){ this.n = value; }

    public Expr getN(){ return this.n; }
}