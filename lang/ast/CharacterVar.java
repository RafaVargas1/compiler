//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class CharacterVar extends Expr {
    private String c;

    public CharacterVar(String c){
        this.c = c;
    }

    public String getValue(){ return c;}

    public void accept(Visitor v){ v.visit(this);}

}