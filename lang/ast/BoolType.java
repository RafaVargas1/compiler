//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class BoolType extends Literal {
    public BoolType(){
        super("BOOL");
    }

    public void accept(Visitor v){ v.visit(this);}
}