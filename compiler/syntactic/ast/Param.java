package lang.ast;

public abstract class Param extends Node {
    private Node id;
    private Node t;

    public Param(Node id, Node t){
        this.id = id;
        this.t = t;
    }
}