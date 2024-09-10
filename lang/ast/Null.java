//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Null extends Absolute {
    public Null(){
        super("null");
    }

    public void accept(Visitor v){ v.visit(this);}
}
