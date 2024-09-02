package lang.ast;

import interpreter.visitor.Visitor;

public class FloatType extends Literal {
    public FloatType(){
        super("FLOAT");
    }

    public void accept(Visitor v){ v.visit(this);}
}