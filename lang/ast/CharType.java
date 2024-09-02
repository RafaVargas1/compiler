//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public class CharType extends Literal {
    public CharType(){
        super("CHAR");
    }

    public void accept(Visitor v){ v.visit(this);}
}