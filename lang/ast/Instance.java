//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Instance extends Expr {
    private Node type;
    private Expr e;

    public Instance(Node type, Expr e){
        this.type = type;
        this.e = e;
    }
}
