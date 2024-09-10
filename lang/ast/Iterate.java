//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Iterate extends Node {
    private Expr t;
    private Node c;
    
    public Iterate(Expr t, Node c){
         this.t = t;
         this.c = c;
    }    

    public Expr getTeste(){ return t;}
    public Node getBody(){ return c;}

    public void accept(Visitor v){ v.visit(this);}
}