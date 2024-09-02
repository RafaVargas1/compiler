//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Call extends Node {
    private String id;
    private Expr[] p;
    private NodeList r;

    public Call(String id, Expr[] p, NodeList r){
        this.id = id;
        this.p = p;
        this.r = r;
    }
}