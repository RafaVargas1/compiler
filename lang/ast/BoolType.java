package lang.ast;

import interpreter.visitor.Visitor;

public class BoolType extends Literal {
    public BoolType(){
        super("BOOL");
    }

    public void accept(Visitor v){ v.visit(this);}
}