//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Instance extends Expr {
    private Node type;
    private Expr e;

    public Instance(Node type, Expr e){
        this.type = type;
        this.e = e;
    }

    public Node getType(){ return type; };
    public Expr getExpr(){ return e; }

    public void accept(Visitor v){ v.visit(this);}
}
