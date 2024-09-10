//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class FloatVar extends Expr {
    private Float n;

    public FloatVar(Float n){
        this.n = n;
    }

    public Float getValue(){ return n;}

    public void accept(Visitor v){ v.visit(this);}
}