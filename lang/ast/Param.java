package lang.ast;

public class Param extends Node {
    private String id;
    private Node t;

    public Param(String id, Node t){
        this.id = id;
        this.t = t;
    }
}