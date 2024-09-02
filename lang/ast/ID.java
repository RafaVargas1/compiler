package lang.ast;

import interpreter.visitor.Visitor;

public class ID extends Expr {
    private String i;

    public ID(String i){
        this.i = i;
    }

    public void accept(Visitor v){ v.visit(this);}
}
