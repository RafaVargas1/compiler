//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

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
}
