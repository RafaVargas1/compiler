//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public class IntegerVar extends Expr {
    private Integer n;

    public IntegerVar(Integer n){
        this.n = n;
    }

    public Integer getValue(){ return n;}

    public void accept(Visitor v){ v.visit(this);}
}