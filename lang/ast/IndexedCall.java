//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class IndexedCall extends Expr {
    private String id;
    private Expr[] params;
    private Expr index;

    public IndexedCall(String id, Expr[] params, Expr index){
        this.id = id;
        this.params = params;
        this.index = index;
    }

    public String getName() { return this.id; };

    public Expr[] getParams() { return this.params; };

    public Expr getIndex() { return this.index; };

    public void accept(Visitor v){ v.visit(this);}
}