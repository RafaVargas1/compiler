package lang.ast;

import interpreter.visitor.Visitor;

public abstract class Literal extends Node {
    private String type;

    public Literal(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void accept(Visitor v){ v.visit(this);}
}
