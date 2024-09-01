package lang.ast;

public class Function extends Node {
    private String id;
    private Param[] params;
    private NodeList returnType;
    private NodeList body;

    public Function(String id, Param[] params, NodeList returnType, NodeList body){
        this.id = id;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }

    public String getID(){ return id;};
    public Node getTipo(){   return returnType; };
    public Param[] getParams(){   return params; };
    public Node getBody(){ return body;}

}
