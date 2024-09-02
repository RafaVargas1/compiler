//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public class False extends Absolute {
    public False(){
        super("false");
    }

    public void accept(Visitor v){ v.visit(this);}
}
