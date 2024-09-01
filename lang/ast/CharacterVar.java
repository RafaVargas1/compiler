package lang.ast;

public class CharacterVar extends Expr {
    private String c;

    public CharacterVar(String c){
        this.c = c;
    }

    public String getValue(){ return c;}

}