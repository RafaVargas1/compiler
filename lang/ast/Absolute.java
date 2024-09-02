package lang.ast;

import interpreter.visitor.Visitor;

public abstract class Absolute extends Expr {
    private String value;

    public Absolute(String value){
        this.value = value;
    }

    public void accept(Visitor v){ v.visit(this);}
}