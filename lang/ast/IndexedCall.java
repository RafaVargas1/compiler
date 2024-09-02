//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class IndexedCall extends Expr {
    private String id;
    private Expr[] params;
    private Expr index;

    public IndexedCall(String id, Expr[] params, Expr index){
        this.id = id;
        this.params = params;
        this.index = index;
    }
}