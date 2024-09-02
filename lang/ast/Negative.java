package lang.ast;

import interpreter.visitor.Visitor;

public class Negative extends UnaryOperation {  
    public Negative(Expr n){
        super(n);
    }

    public void accept(Visitor v){ v.visit(this);}

}