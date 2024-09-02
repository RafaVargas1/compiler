//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public abstract class Absolute extends Expr {
    private String value;

    public Absolute(String value){
        this.value = value;
    }

    public void accept(Visitor v){ v.visit(this);}
}