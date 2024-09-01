package lang.ast;

public class Data extends Node {
    private String id;
    private Node decl;

    public Data(String id, Node decl){
        this.id = id;
        this.decl = decl;
    }
}
