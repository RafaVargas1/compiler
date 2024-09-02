package lang.ast;

import interpreter.visitor.Visitor;

public class IntType extends Literal {
    public IntType(){
        super("INT");
    }

    public void accept(Visitor v){ v.visit(this);}
}