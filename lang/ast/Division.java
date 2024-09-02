package lang.ast;

import interpreter.visitor.Visitor;

public class Division extends BinaryOperation {  
    public Division(Expr a, Expr b){
        super(a, b);
    }

    public void accept(Visitor v){ v.visit(this);}
}