package lang.ast;

import interpreter.visitor.Visitor;

public class True extends Absolute {
    public True(){
        super("true");
    }

    public void accept(Visitor v){ v.visit(this);}
}
