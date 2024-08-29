package lang.ast;

public abstract class FloatVar extends Node {
    private Float n;

    public FloatVar(Float n){
        this.n = n;
    }
}