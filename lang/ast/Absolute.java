package lang.ast;

public abstract class Absolute extends Expr {
    private String value;

    public Absolute(String value){
        this.value = value;
    }
}