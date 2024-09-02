//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Atribuition extends Node {
    private Node id;
    private Expr e;

    public Atribuition(Node id, Expr e){
        this.id = id;
        this.e = e;
    }
}
