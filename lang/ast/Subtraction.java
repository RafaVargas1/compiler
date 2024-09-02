package lang.ast;

import interpreter.visitor.Visitor;

public class Subtraction extends BinaryOperation {  
    public Subtraction(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}