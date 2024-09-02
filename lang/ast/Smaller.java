package lang.ast;

import interpreter.visitor.Visitor;

public class Smaller extends BinaryOperation {  
    public Smaller(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}