package lang.ast;

import interpreter.visitor.Visitor;

public class Mod extends BinaryOperation {  
    public Mod(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}
