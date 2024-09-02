package lang.ast;

import interpreter.visitor.Visitor;

public class And extends BinaryOperation {  
    public And(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}