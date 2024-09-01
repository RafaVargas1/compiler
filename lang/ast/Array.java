package lang.ast;

public class Array extends Expr {
    private Node id;
    private Expr index;
    
    public Array(Node id, Expr index){
        this.id = id;
        this.index = index;
    }
}