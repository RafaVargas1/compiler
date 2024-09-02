package lang.ast;

import interpreter.visitor.Visitor;

public class Multiplication extends BinaryOperation {  
    public Multiplication(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}