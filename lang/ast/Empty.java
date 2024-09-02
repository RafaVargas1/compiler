//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public class Empty extends Expr {
    private Node value;

    public Empty() {
        this.value = null;
    }

    public void accept(Visitor v){ v.visit(this);}
}
