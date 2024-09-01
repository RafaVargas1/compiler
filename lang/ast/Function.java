package lang.ast;

public class Function extends Node {
    private String id;
    private Node params;
    private Node returnType;
    private Node body;

    public Function(String id, Node params, Node returnType, Node body){
        this.id = id;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }
}
