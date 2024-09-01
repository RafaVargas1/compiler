package lang.ast;

public class Component extends Expr {
    private String id;
    private Node parent;
    
    public Component(Node parent, String id){
        this.id = id;
        this.parent = parent;
    }

    public String getId() { return this.id; };

    public Node getParent(){ return this.parent; };
}
