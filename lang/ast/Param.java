//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Param extends Node {
    private String id;
    private Node t;

    public Param(String id, Node t){
        this.id = id;
        this.t = t;
    }
}