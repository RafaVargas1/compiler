package lang.ast;

public class Call extends Node {
    private String id;
    private Expr[] p;
    private NodeList r;

    public Call(String id, Expr[] p, NodeList r){
        this.id = id;
        this.p = p;
        this.r = r;
    }

    public String getName(){ return id; }
    public Node[] getArgs(){ 
        if (r == null){
            return new Node[]{p};
        } else {
            return new Node[]{p, r};
        }
    }
}