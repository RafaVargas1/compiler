package lang.ast;

public abstract class Array extends Node {
    private String id;
    private Node c;
    
    public Array(String id, Node c){
        this.id = id;
        this.c = c;
    }
}