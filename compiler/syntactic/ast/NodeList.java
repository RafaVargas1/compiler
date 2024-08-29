package lang.ast;

public abstract class NodeList extends Node {
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
}
