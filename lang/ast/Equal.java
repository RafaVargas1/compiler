package lang.ast;

import interpreter.visitor.Visitor;

public class Equal extends BinaryOperation {  
    public Equal(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}