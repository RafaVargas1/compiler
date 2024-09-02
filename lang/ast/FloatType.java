//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public class FloatType extends Literal {
    public FloatType(){
        super("FLOAT");
    }

    public void accept(Visitor v){ v.visit(this);}
}