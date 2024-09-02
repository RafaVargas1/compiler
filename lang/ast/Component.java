//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Component extends Expr {
    private String id;
    private Node parent;
    
    public Component(Node parent, String id){
        this.id = id;
        this.parent = parent;
    }
}
