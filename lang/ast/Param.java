//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public class Param extends Node {
    private String id;
    private Node t;

    public Param(String id, Node t){
        this.id = id;
        this.t = t;
    }

    public String getParamId() { return this.id; };

    public Node getParamType() { return this.t; };

    public void accept(Visitor v){ v.visit(this);}
}