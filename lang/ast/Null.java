package lang.ast;

import interpreter.visitor.Visitor;

public class Null extends Absolute {
    public Null(){
        super("null");
    }

    public void accept(Visitor v){ v.visit(this);}
}
