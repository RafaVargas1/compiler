package lang.ast;

import interpreter.visitor.Visitor;

public class IntegerVar extends Expr {
    private Integer n;

    public IntegerVar(Integer n){
        this.n = n;
    }

    public Integer getValue(){ return n;}

    public void accept(Visitor v){ v.visit(this);}
}