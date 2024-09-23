//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Param extends Node {
    private String id;
    private Node t;
    private Object value;

    public Param(String id, Node t){
        this.id = id;
        this.t = t;
        this.value = null;
    }

    public Param(Param copy) {
        this.id = copy.getParamId();
        this.t = copy.getParamType();
        this.value = null;
    }

    public void setValue(Object v) { this.value = v; }

    public Object getValue() { return this.value; }

    public String getParamId() { return this.id; };

    public Node getParamType() { return this.t; };

    public void accept(Visitor v){ v.visit(this);}
}