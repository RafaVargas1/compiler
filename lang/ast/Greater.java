package lang.ast;

import interpreter.visitor.Visitor;

public class Greater extends BinaryOperation {  
    public Greater(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}