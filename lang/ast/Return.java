//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public class Return extends Node {
    private Expr[] e;
    
    public Return(Expr[] e){
         this.e = e;
    } 

    public void accept(Visitor v){ v.visit(this);}
}