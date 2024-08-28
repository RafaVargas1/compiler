package lang.ast;

public abstract class Call extends Node {
    private Node id;
    private Expr p;
    private Node r;

    public Call(Node id, Expr p, Node r){
        this.id = id;
        this.p = p;
        this.r = r;
    }
    
    public Call(Node id, Expr p){
        this.id = id;
        this.p = p;
        this.r = null;
    }
    
    public Call(Node id, Node r){
        this.id = id;
        this.p = null;
        this.r = r;
    }

    public Call(Node id){
        this.id = id;
        this.p = null;
        this.r = null;
    }
}