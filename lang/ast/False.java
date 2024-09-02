package lang.ast;

import interpreter.visitor.Visitor;

public class False extends Absolute {
    public False(){
        super("false");
    }

    public void accept(Visitor v){ v.visit(this);}
}
