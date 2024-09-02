//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Data extends Node {
    private String id;
    private Param[] decl;

    public Data(String id, Param[] decl){
        this.id = id;
        this.decl = decl;
    }
}
