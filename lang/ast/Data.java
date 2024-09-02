package lang.ast;

import interpreter.visitor.Visitor;

public class Data extends Node {
    private String id;
    private Param[] decl;

    public Data(String id, Param[] decl){
        this.id = id;
        this.decl = decl;
    }

    public String getId() { return this.id; };

    public Param[] getParams(){ return this.decl; }

    public void accept(Visitor v){ v.visit(this);}
}
