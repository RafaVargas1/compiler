//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Component extends Expr {
    private String id;
    private Node parent;
    
    public Component(Node parent, String id){
        this.id = id;
        this.parent = parent;
    }

    public String getName() { return this.id; };

    public Node getParent(){ return this.parent; };

    public void accept(Visitor v){ v.visit(this);}
}
