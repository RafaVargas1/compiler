package lang.ast;

import interpreter.visitor.Visitor;

public class Addition extends BinaryOperation {  
    public Addition(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}