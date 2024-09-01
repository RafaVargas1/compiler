package lang.ast;

public class Array extends Expr {
    private Node id;
    private Node c;
    
    public Array(Node id, Node c){
        this.id = id;
        this.c = c;
    }
}