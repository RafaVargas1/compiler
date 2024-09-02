package lang.ast;

import interpreter.visitor.Visitor;

public class FloatVar extends Expr {
    private Float n;

    public FloatVar(Float n){
        this.n = n;
    }

    public Float getValue(){ return n;}

    public void accept(Visitor v){ v.visit(this);}
}