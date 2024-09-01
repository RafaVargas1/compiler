package lang.ast;

public abstract class Literal extends Node {
    private String type;

    public Literal(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
