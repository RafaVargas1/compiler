package lang.ast;

import interpreter.visitor.Visitor;

public class Different extends BinaryOperation {  
    public Different(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}