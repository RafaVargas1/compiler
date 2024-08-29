package lang.ast;

public abstract class Absolute extends Node {
    private String value;

    public Absolute(String value){
        this.value = value;
    }
}
