//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Function extends Node {
    private String id;
    private Param[] params;
    private NodeList returnType;
    private NodeList body;

    public Function(String id, Param[] params, NodeList returnType, NodeList body){
        this.id = id;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }
}
