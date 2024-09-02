//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Array extends Expr {
    private Node id;
    private Expr index;
    
    public Array(Node id, Expr index){
        this.id = id;
        this.index = index;
    }
}