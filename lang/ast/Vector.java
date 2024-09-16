//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Vector extends Literal {
    private Node type;

    public Vector(Node type){
        super("VECTOR");
        this.type = type;
    }

    public Node getVectorType(){
        return this.type;
    }

    public void accept(Visitor v){ v.visit(this);}
}