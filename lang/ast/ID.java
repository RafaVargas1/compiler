//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class ID extends Expr {
    private String i;

    public ID(String i){
        this.i = i;
    }

    public String getName() {
        return this.i;
    }

    public void accept(Visitor v){ System.out.println("Variavel"); v.visit(this);}
}
