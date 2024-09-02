package lang.ast;

import interpreter.visitor.Visitor;

public class CharacterVar extends Expr {
    private String c;

    public CharacterVar(String c){
        this.c = c;
    }

    public String getValue(){ return c;}

    public void accept(Visitor v){ v.visit(this);}

}