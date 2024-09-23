//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Call extends Node {
    private String id;
    private Expr[] p;
    private NodeList r;

    public Call(String id, Expr[] p, NodeList r){
        this.id = id;
        this.p = p;
        this.r = r;
    }

    public String getName(){ return this.id; }
    public Expr[] getArgs(){ 
        return this.p;
    }
    public NodeList getReturns() { return r; }


    public NodeList getReturn() {return this.r; }

    public void accept(Visitor v){ v.visit(this);}
}