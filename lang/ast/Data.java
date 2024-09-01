package lang.ast;

public class Data extends Node {
    private String id;
    private Param[] decl;

    public Data(String id, Param[] decl){
        this.id = id;
        this.decl = decl;
    }
}
