package lang.ast;

public class Call extends Node {
    private String id;
    private Node p;
    private Node r;

    public Call(String id, Node p, Node r){
        this.id = id;
        this.p = p;
        this.r = r;
    }
}