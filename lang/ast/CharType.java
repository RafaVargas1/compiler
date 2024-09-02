package lang.ast;

import interpreter.visitor.Visitor;

public class CharType extends Literal {
    public CharType(){
        super("CHAR");
    }

    public void accept(Visitor v){ v.visit(this);}
}