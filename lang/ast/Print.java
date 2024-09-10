//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Print extends Node {
    private Expr e;
    
    public Print(Expr e){
         this.e = e;
    }    
    
    public Expr getExpr(){ return e;}

    public void accept(Visitor v){ v.visit(this);}
}