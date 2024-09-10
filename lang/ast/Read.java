//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Read extends Node {
    private Node id;
    
    public Read(Node id){
         this.id = id;
    }    

    public Node getName() {
        return this.id;
    }

    public void accept(Visitor v){ v.visit(this);}
}