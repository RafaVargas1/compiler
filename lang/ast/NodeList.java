package lang.ast;

import interpreter.visitor.Visitor;

public class NodeList extends Node {
    private Node n;
    private Node l;

    public NodeList(Node n, Node l){
        this.n = n;
        this.l = l;
    }

    public NodeList(Node n){
        this.n = n;
        this.l = null;
    }

    public Node getCmd1(){ return n;} 
    public Node getCmd2(){ return l; }

    public void accept(Visitor v){ v.visit(this);}
}
