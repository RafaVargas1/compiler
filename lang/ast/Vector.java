package lang.ast;

public class Vector extends Literal {
    private Node type;

    public Vector(Node type){
        super("VECTOR");
        this.type = type;
    }
}